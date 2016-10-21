package pe.gob.miraflores.mobile.service.mapaincidencias.impl;

import java.io.File;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.dao.detalleincidencia.GeoDetalleIncidenciaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.AuditoriaIncidenciaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.GeometriaLineaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciaGenericoMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciaScheduleMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciasGeometriaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciasRegistroMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.PreInscripcionCierreCalleMapper;
import pe.gob.miraflores.mobile.domain.catalogo.Catalogo;
import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidencia;
import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidenciaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.AuditoriaIncidencia;
import pe.gob.miraflores.mobile.domain.mapaincidencias.GeometriaLinea;
import pe.gob.miraflores.mobile.domain.mapaincidencias.GeometriaLineaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaGenerico;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaGenericoCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaSchedule;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaScheduleCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasGeometria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasGeometriaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistroCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.PreInscripcionCierreCalle;
import pe.gob.miraflores.mobile.domain.parametrosistema.ParametroSistema;
import pe.gob.miraflores.mobile.service.catalogo.LocalCatalogoService;
import pe.gob.miraflores.mobile.service.email.EmailService;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;
import pe.gob.miraflores.mobile.service.parametrosistema.ParametroSistemaService;
import pe.gob.miraflores.mobile.util.Util;
import pe.gob.miraflores.mobile.vo.RadioTetraVO;
import pe.gob.miraflores.mobile.vo.ResponseRadioTetraVO;
import pe.gob.miraflores.mobile.vo.VoxivaVO;

@Service
public class MapaIncidenciasServiceImpl implements MapaIncidenciasService {

	@Autowired
	private MapaIncidenciasRegistroMapper mapaIncidenciasRegistroMapper;

	@Autowired
	private MapaIncidenciasGeometriaMapper mapaIncidenciasGeometriaMapper;
	
	@Autowired 
	private GeoDetalleIncidenciaMapper geoDetalleIncidenciaMapper;

	@Autowired
	private LocalCatalogoService catalogoService;
	
	@Autowired
	private ParametroSistemaService parametroSistemaService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private GeometriaLineaMapper geometriaLineaMapper;
	
	@Autowired
	private MapaIncidenciaGenericoMapper mapaIncidenciaGenericoMapper;
	
	
	@Autowired
	private PreInscripcionCierreCalleMapper preInscripcionCierreCalleMapper;
	
	@Autowired
	private AuditoriaIncidenciaMapper auditoriaIncidenciaMapper; 
	
	@Autowired
	private MapaIncidenciaScheduleMapper incidenciaScheduleMapper;
	
	public String buildJsonRestWaze(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		String action = MobileConstantes.URL_JSON_WAZE;
		String strJson = Util.getUrlRemote(action);
		return strJson;
	}

	public MapaIncidenciasRegistro registrar(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub

		if(incidencia.getIdIncidencia() == null){
			Date now = new Date();
			incidencia.setFecRegistra(now);
			incidencia.setEstado(MobileConstantes.ESTADO_ACTIVO);
			incidencia.setUsuarioRegistro(incidencia.getIdUsuarioSesion());
			mapaIncidenciasRegistroMapper.insertSelective(incidencia);
		}else{
			incidencia.setUsuarioModifica(incidencia.getIdUsuarioSesion());
			incidencia.setFecModifica(new Date());
			mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		}
		
		MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
		c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
		
		mapaIncidenciasGeometriaMapper.deleteByExample(c);
		
		GeometriaLineaCriteria gc = new GeometriaLineaCriteria();
		gc.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
		geometriaLineaMapper.deleteByExample(gc);
		
		List<String> calles = new ArrayList<String>();
		
		for (GeometriaLinea linea : incidencia.getLineas()) {
			linea.setIdIncidencia(incidencia.getIdIncidencia());
			geometriaLineaMapper.insertSelective(linea);
			calles.add(linea.getDesCalle());
		}
		
		
		MapaIncidenciaScheduleCriteria cmd = new MapaIncidenciaScheduleCriteria();
		cmd.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
		incidenciaScheduleMapper.deleteByExample(cmd);
		
		for (MapaIncidenciaSchedule dia : incidencia.getDias()){
			dia.setIdIncidencia(incidencia.getIdIncidencia());
			incidenciaScheduleMapper.insertSelective(dia);
		}

		MapaIncidenciasRegistro incidenciaUpdate = new MapaIncidenciasRegistro();
		incidenciaUpdate.setIdIncidencia(incidencia.getIdIncidencia());
		incidenciaUpdate.setDesCallles(StringUtils.join(calles, ", "));
		
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidenciaUpdate);
		
		
		if(incidencia.getIdPreRegistro() != null){
			PreInscripcionCierreCalle e = new PreInscripcionCierreCalle();
			e.setId(incidencia.getIdPreRegistro());
			e.setEstado(MobileConstantes.ESTADO_INACTIVO_NUMERO);
			e.setIdReferencia(incidencia.getIdIncidencia());
			preInscripcionCierreCalleMapper.updateByPrimaryKeySelective(e);
		}
		
		return incidencia;
	}

	public List<MapaIncidenciasRegistro> obtenerIncidenciasNoProcesadas() throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciasRegistroCriteria c = new MapaIncidenciasRegistroCriteria();
		c.createCriteria();
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectByExample(c);
		
		MapaIncidenciasGeometriaCriteria cg = null;
		
		if(list!=null){
			cg = new MapaIncidenciasGeometriaCriteria();
			for (MapaIncidenciasRegistro m : list) {
				cg.clear();
				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
			}
		}
		
		return list;
	}

	public List<MapaIncidenciasRegistro> listaInicdenciasWaze(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		
		if(incidencia == null){
			incidencia = new MapaIncidenciasRegistro();
		}
		
		Date now = new Date();
		MapaIncidenciasRegistroCriteria c = new MapaIncidenciasRegistroCriteria();
		c.createCriteria().andFecHoraFinGreaterThanOrEqualTo(now)
		.andTipoIncidenciaEqualTo(MobileConstantes.FORMULARIO_CIERRE_CALLE)
		.andEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO).andVisibleEqualTo(MobileConstantes.LETRA_SI);
		
		List<MapaIncidenciasRegistro> list = null;
		
		if(incidencia.getIsXmlFormat() == null){
			incidencia.setIsXmlFormat("N");
		}
		
		if(incidencia.getIsXmlFormat().equals("S")){
			list = mapaIncidenciasRegistroMapper.listarIncidenciasWazeService();
		}else{
			list = mapaIncidenciasRegistroMapper.selectByExample(c);
		}
		
		MapaIncidenciasGeometriaCriteria cg = null;
		
		if(list!=null){
			cg = new MapaIncidenciasGeometriaCriteria();
			for (MapaIncidenciasRegistro m : list) {
				cg.clear();
				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
				m.setDescripcion(m.getDescripcion().replaceAll("", " "));
			}
		}
		return list;
	}

	
	public List<MapaIncidenciasRegistro> listaInicdenciasWazeJSON(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String nowStr = sdf.format(now);
		
		MapaIncidenciasRegistroCriteria c = new MapaIncidenciasRegistroCriteria();
		c.createCriteria().andFecHoraFinGreaterThanOrEqualToEspecial(nowStr)
		.andTipoIncidenciaEqualTo(MobileConstantes.FORMULARIO_CIERRE_CALLE)
		.andEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO).andVisibleEqualTo(MobileConstantes.LETRA_SI);
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectByExample(c);
		
//		MapaIncidenciasGeometriaCriteria cg = null;
		
//		if(list!=null){
//			cg = new MapaIncidenciasGeometriaCriteria();
//			for (MapaIncidenciasRegistro m : list) {
//				cg.clear();
//				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				//m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
//				m.setDescripcion(m.getDescripcion().replaceAll("", " "));
//			}
//		}
		return list;
	}
	
	public String listaIncidenciasXmlFormatWaze(MapaIncidenciasRegistro incidencia)
			throws Exception {
		// TODO Auto-generated method stub
		
		String xml ="";
		xml+="<events>";
		
		SimpleDateFormat sdfGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat sdfGMT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT2.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		if(incidencia == null){
			incidencia = new MapaIncidenciasRegistro();
		}
		
		incidencia.setIsXmlFormat("N");
		
		List<MapaIncidenciasRegistro> list = this.listaInicdenciasWaze(incidencia);
		
		String[] arrPuntos = null;
		String[] arrPuntosEnd = null;
		
		List<GeometriaLinea> lineas = null;
		GeometriaLineaCriteria lineaCriteria = null;
		
		if(list!=null){
			for (MapaIncidenciasRegistro mapaIncidenciasRegistro : list) {
				
				if(mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_CONSTRUCTION_TYPE){
					List<MapaIncidenciasGeometria> puntos = null;
					xml+="<event id=\""+(mapaIncidenciasRegistro.getIdIncidencia() +"_"+mapaIncidenciasRegistro.getFecRegistra().getTime())+"\"> ";
					xml+="<type>CONSTRUCTION</type> ";
					xml+="<start_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</start_date>";
					if(mapaIncidenciasRegistro.getFecModifica()!=null){
						xml+="<update_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</update_date>";
					}else{
						xml+="<update_date></update_date>";
					}
					
					xml+="<end_date>"+(sdfGMT2.format(mapaIncidenciasRegistro.getFecHoraFin()))+"</end_date>";
					xml+="<subtype></subtype>";
					
					xml+="<description>"+mapaIncidenciasRegistro.getDescripcion().replaceAll("", " ")+"</description>";
					
					xml+="<severity>"+mapaIncidenciasRegistro.getSeveridad()+"</severity>";
					
					xml+="<location>";
					
					puntos = mapaIncidenciasRegistro.getDetalleGeometria();
					if(puntos!=null){
						
						xml+="<street>"+puntos.get(0).getDesdireccion()+"</street> ";
						xml+="<city>Miraflores</city> ";
						
						arrPuntos = puntos.get(0).getGeometria().split(" "); 
						
						xml+="<latitude>"+arrPuntos[1]+"</latitude> ";
						
						xml+="<longitude>"+arrPuntos[0]+"</longitude> ";
						
						xml+="<specify_end></specify_end>";
						
					}
					
					xml+="</location>";
					
					xml+="</event>";
					
					
				}else{
					if(mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_ROAD_CLOSED_TYPE){
						lineaCriteria = new GeometriaLineaCriteria();
						lineaCriteria.createCriteria().andIdIncidenciaEqualTo(mapaIncidenciasRegistro.getIdIncidencia());
						lineas = geometriaLineaMapper.selectByExample(lineaCriteria);
						
						if(lineas != null){
							
							for (GeometriaLinea linea : lineas) {
								
								xml+="<event id=\""+(mapaIncidenciasRegistro.getIdIncidencia() +"_"+linea.getIdLinea()+"_"+mapaIncidenciasRegistro.getFecRegistra().getTime())+"\"> ";
								
								if(mapaIncidenciasRegistro.getTipoCierre().equalsIgnoreCase(MobileConstantes.CIERRE_PARCIAL)){
									xml+="<type>CONSTRUCTION</type> ";
								}else{
									xml+="<type>ROAD_CLOSED</type> ";
								}
								
								xml+="<start_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</start_date>";
								if(mapaIncidenciasRegistro.getFecModifica()!=null){
									xml+="<update_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</update_date>";
								}else{
									xml+="<update_date></update_date>";
								}
								
								xml+="<end_date>"+(sdfGMT2.format(mapaIncidenciasRegistro.getFecHoraFin()))+"</end_date>";
								xml+="<subtype></subtype>";
								
								xml+="<description>"+mapaIncidenciasRegistro.getDescripcion().replaceAll("", " ")+"</description>";
								
								xml+="<severity>"+mapaIncidenciasRegistro.getSeveridad()+"</severity>";
								
								xml+="<location>";
								
								xml+="<street>"+linea.getDesCalle().replaceAll("", " ")+"</street> ";
								xml+="<city>Miraflores</city> ";
								
								arrPuntos = linea.getPointIni().split(" "); 
								
								xml+="<latitude>"+arrPuntos[0]+"</latitude> ";
								
								xml+="<longitude>"+arrPuntos[1]+"</longitude> ";
								
//								if(mapaIncidenciasRegistro.getDireccionFinal() == null){
//									xml+="<direction>BOTH_DIRECTIONS</direction>";
//								}else{
//									xml+="<direction>"+mapaIncidenciasRegistro.getDireccionFinal()+"</direction>";
//								}
								
								
								if(linea.getDirCardinal() != null){
									xml+="<direction>"+linea.getDirCardinal()+"</direction>";
								}else{
									xml+="<direction>BOTH_DIRECTIONS</direction>";
								}
								
								xml+="<specify_end>";
									
								arrPuntosEnd = linea.getPointFin().split(" "); 
								if(!StringUtils.isEmpty(linea.getIntercepcion1())){
									xml+="<from_cross_street>"+linea.getIntercepcion1()+"</from_cross_street>";
								}
								
								if(!StringUtils.isEmpty(linea.getIntercepcion2())){
									xml+="<end_cross_street>"+linea.getIntercepcion2()+"</end_cross_street>";
								}
								
								
								xml+="<end_latitude>"+arrPuntosEnd[0]+"</end_latitude>";
								xml+="<end_longitude>"+arrPuntosEnd[1]+"</end_longitude>";
									
								
								xml+="</specify_end> ";
									
								
								xml+="</location>";
								
								xml+="</event>";
							}
							
						}
						
					}
				}
				
			}
		}
		
		
		xml+="</events>";
		
		
		return xml;
	}

	public MapaIncidenciasRegistro registrarIncidenciaVoxiva(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciasRegistroCriteria c1 = new MapaIncidenciasRegistroCriteria();
		c1.createCriteria().andEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO).andIdCasoVoxivaEqualTo(incidencia.getIdCasoVoxiva());
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectByExample(c1);
		
		if(list!=null && list.size()>0){
			incidencia.setIdIncidencia(list.get(0).getIdIncidencia());
		}
		
		if(incidencia.getIdIncidencia() == null){
			mapaIncidenciasRegistroMapper.insertSelective(incidencia);
		}else{
			mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		}
		
		if(incidencia.getLatitud()!=null && incidencia.getLongitud()!=null){
		
			MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
			c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
			
			mapaIncidenciasGeometriaMapper.deleteByExample(c);
			
			MapaIncidenciasGeometria geometria = new MapaIncidenciasGeometria();
			geometria.setIndestado(MobileConstantes.ESTADO_ACTIVO_NUMERO);
			geometria.setIdetipogeometria(MobileConstantes.TIPO_GEOMETRIA_PUNTO);
			geometria.setIdecodident(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
			geometria.setIdecodvalor(incidencia.getIdIncidencia());
			geometria.setGeometria(incidencia.getLatitud()+" "+incidencia.getLongitud());
			
			mapaIncidenciasGeometriaMapper.insertSelective(geometria);
			
		}
		
		return incidencia;
	}

	
	/* *
	 * DOLPHIN
	 * */
	
	private GeoDetalleIncidencia getDetalleFronList(List<GeoDetalleIncidencia> list ,Integer issi){
		
		GeoDetalleIncidencia  d = null;
		for (GeoDetalleIncidencia i : list) {
			if(i.getIssi().toString().trim().equals(issi.toString())){
				d = i;
				break;
			}
		}
		
		return d;
		
	}
	
	public String buildJsonRestDolphin() throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dActual = sdf.format(new Date());
		query.append(MobileConstantes.ID_USER_DOLPHIN).append(MobileConstantes.CLI_SALT).append(dActual);
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(query.toString().getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
		
		String action = MobileConstantes.URL_JSON_DOLPHIN+sb.toString()+MobileConstantes.URL_JSON_DOLPHIN_END;
//		System.out.println(action);
		String strJson = Util.getUrlRemote(action);
		
		Gson gson = new Gson();
		
		ResponseRadioTetraVO response = gson.fromJson(strJson, ResponseRadioTetraVO.class);
		String strJson2=null;
		if(response!=null && response.getItems()!=null && response.getItems().size()>0){
			GeoDetalleIncidencia d = null;
			
//			System.out.println(response.getItems().size());
			
			List<Integer> issis = new ArrayList<Integer>();
			
			for (RadioTetraVO r : response.getItems()) {
				issis.add(r.getIssi());
			}
			
			GeoDetalleIncidenciaCriteria dic = new GeoDetalleIncidenciaCriteria();
			
		//	System.out.println(StringUtils.join("IN ("+issis,",")+")");
			
			dic.createCriteria().andIssiIn(issis);
			
			List<GeoDetalleIncidencia> listTemp = geoDetalleIncidenciaMapper.selectByExample(dic);
			
			ResponseRadioTetraVO result = new ResponseRadioTetraVO();
			result.setStatus(response.isStatus());
			
			
			result.setItems(response.getItems());
			
			for (RadioTetraVO r2 : result.getItems()) {
				d = this.getDetalleFronList(listTemp, r2.getIssi());
				r2.setDetalle(d);
			}
			
			
			
			strJson2 = gson.toJson(result);
		}
		
		return strJson2;
	}

	@Override
	public List<MapaIncidenciasRegistro> listaInicdenciasVoxiva(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
	
		List<MapaIncidenciasRegistro> incidencias = this.selectVoxivaListMap(incidencia);
		
		MapaIncidenciasGeometria geometria = null;
		
		for (MapaIncidenciasRegistro m : incidencias) {
			try{
			geometria = m.getGeometria();
			m.setLatitud(geometria.getGeometria().split(" ")[1]);
			m.setLongitud(geometria.getGeometria().split(" ")[0]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		return incidencias;
	}
	
	
	@Override
	public GeoDetalleIncidencia obtenerDetalleIncidencia(Integer issi) throws Exception {

		GeoDetalleIncidencia detalle = geoDetalleIncidenciaMapper.selectByPrimaryKey(issi);
		
		return detalle;
	}

	@Override
	public MapaIncidenciasRegistro obtenerIncidenciaPorId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciasRegistro incidencia = mapaIncidenciasRegistroMapper.selectByPrimaryKey(id);
		
		if(incidencia !=null){
			
			if(incidencia.getTipoIncidencia() != null){
					incidencia.setTipo(catalogoService.findCatalogoById(incidencia.getTipoIncidencia()));
			}
			
				
				MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
				c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				List<MapaIncidenciasGeometria> geometrias = mapaIncidenciasGeometriaMapper.selectByExample(c);
				incidencia.setDetalleGeometria(geometrias);
				

				GeometriaLineaCriteria c2 = new GeometriaLineaCriteria();
				c2.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
				List<GeometriaLinea> lineas = geometriaLineaMapper.selectByExample(c2);
				incidencia.setLineas(lineas);
				
			
				MapaIncidenciaScheduleCriteria c3 = new MapaIncidenciaScheduleCriteria();
				c3.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
				List<MapaIncidenciaSchedule> dias = incidenciaScheduleMapper.selectByExample(c3);
				incidencia.setDias(dias);
				
			
			return incidencia;
		}
		
		return null;
	}

	@Override
	public Map<String, Object> listaInicdenciasPortipo(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
//		int c = 0;
//		if (start != 0) {
//			c = c + 1;
//		}
//		params.put("START", start + c);
//		params.put("LIMIT", limit + start);
		
		int c = 0;
		
		if(incidencia.getStart() == null){
			incidencia.setStart(0);
		}
		
		if(incidencia.getLimit() == null){
			incidencia.setLimit(10);
		}
		
		
		if (incidencia.getStart() != 0) {
			c = c + 1;
		}
		
		if(incidencia.getTipoIncidencia() != null){
			params.put("TIPO_INCIDENCIA", incidencia.getTipoIncidencia());
		}
		
		if(incidencia.getDesTipoIncidencia() != null){
			params.put("NOMBRE_TIPO_EVENTO", incidencia.getDesTipoIncidencia());
		}
		
		if(incidencia.getDescripcion() != null){
			params.put("DESCRIPCION", incidencia.getDescripcion());
		}
		
		if(incidencia.getStrFecIni() != null){
			params.put("FECHA_INI", incidencia.getStrFecIni());
		}
		
		if(incidencia.getStrFecFin() != null){
			params.put("FECHA_FIN", incidencia.getStrFecFin());
		}
		
		if(incidencia.getDesCallles() != null){
			params.put("DES_CALLLES", incidencia.getDesCallles());
		}
		
		params.put("START", incidencia.getStart()+c);
		params.put("LIMIT", incidencia.getStart()+incidencia.getLimit());
		
		List<MapaIncidenciasRegistro> list =  mapaIncidenciasRegistroMapper.listarIncidenciasConPaginacion(params);
		
//		MapaIncidenciasGeometriaCriteria cg = null;
		
//		if(list!=null){
//			cg = new MapaIncidenciasGeometriaCriteria();
//			for (MapaIncidenciasRegistro m : list) {
//				cg.clear();
//				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
//				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
//			}
//		}		
				
		result.put("data",list);
		result.put("total", mapaIncidenciasRegistroMapper.countListarIncidenciasConPaginacion(params));
		
		
		return result;
	}

	@Override
	public Map<String, Object> listaInicdenciasGeneric(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		int c = 0;
		
		if(incidencia.getStart() == null){
			incidencia.setStart(0);
		}
		
		if(incidencia.getLimit() == null){
			incidencia.setLimit(10);
		}
		
		
		if (incidencia.getStart() != 0) {
			c = c + 1;
		}
		
		if(incidencia.getIncidenciaPresentada() != null){
			params.put("INCIDENCIA_PRESENTADA", incidencia.getIncidenciaPresentada());
		}
		
		if(incidencia.getHoraLlamadaVoxiva()!= null){
			params.put("HORA_LLAMADA_VOXIVA", incidencia.getHoraLlamadaVoxiva());
		}
		
		if(incidencia.getListmedios()!= null){
			params.put("LIST_MEDIOS", incidencia.getListmedios());
		}
		
		
		
		params.put("START", incidencia.getStart()+c);
		params.put("LIMIT", incidencia.getStart()+incidencia.getLimit());
		
		List<Integer> listTipo = new ArrayList<Integer>();
		listTipo.add(MobileConstantes.VOXIVA_WS);
		
		params.put("LIST_TIPO", listTipo);
		
		params.put("LIST_ESTADO_CALIFICA", incidencia.getListEstadoCalifica());
		
		List<MapaIncidenciasRegistro> list =  mapaIncidenciasRegistroMapper.listarIncidenciasGenericConPaginacion(params);
				
		result.put("data",list);
		result.put("total", mapaIncidenciasRegistroMapper.countListarIncidenciasGenericConPaginacion(params));
		
		return result;
	}
	
	
	@Override
	public void anularIncidencia(Integer id) throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciasRegistro e = new MapaIncidenciasRegistro();
		e.setIdIncidencia(id);
		e.setFecModifica(new Date());
		e.setEstado(MobileConstantes.ESTADO_INACTIVO);
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(e);
		
	}

	public void enviarAlertasCierreCalle() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("inicio");
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> params = new HashMap<String, Object>();
		String strNow = sdf.format(now); 
		params.put("FECHA_FIN", strNow);
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.cierreCallesPorVencer(params);
		
		Map<String, Object> paramsPorIniciar = new HashMap<String, Object>();
		paramsPorIniciar.put("FECHA_INI", strNow);
		List<MapaIncidenciasRegistro> listPorIniciar = mapaIncidenciasRegistroMapper.cierreCallesPorIniciar(paramsPorIniciar);

		
		
	}
	
	private void enviarNotificacion(List<MapaIncidenciasRegistro> list,String tipo,Date now) throws Exception{
		
		List<String> calles = null;
		
		List<MapaIncidenciasGeometria> geometrias = null;
		
		MapaIncidenciasGeometriaCriteria criteriaGeo = null;
		
		Catalogo tipoEvento = null;
		
		String tipoCierre = "";
		
		Integer countRegistros = 0;
		
		List<Catalogo> emailsCatalogo = null;
		
		if(list != null){
			
			int diasFaltantes;
			ParametroSistema parametroVencimiento = parametroSistemaService.obtenerParametroSistemaById(MobileConstantes.PARAMETRO_NUM_DIAS_VENCIMIENTO_CIERRA_CALLE);
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			String mensaje = "<table style=\"border-top:1px #ccc solid;border-left:1px #ccc solid;border-right:1px #ccc solid;width:100%;font-family:Verdana;text-align:left;font-size:12px;\" cellpadding=\"0\" cellspacing=\"0\">";
			mensaje+="<tr><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\">TIPO DE EVENTO</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\">DESCRIPCIÓN</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\" >INICIO</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\" >VENCIMIENTO</th><th style=\"padding:5px;background:#f5f5f5;border-bottom:1px #ccc solid;\" >CALLES</th></tr>";
			for (MapaIncidenciasRegistro m : list) {
				 
				diasFaltantes = Util.daysBetween(now, m.getFecHoraFin());
				
				if(diasFaltantes<=Integer.parseInt(parametroVencimiento.getValor().trim())){
					
					criteriaGeo = new MapaIncidenciasGeometriaCriteria();
					criteriaGeo.clear();
					criteriaGeo.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				
					geometrias = mapaIncidenciasGeometriaMapper.selectByExample(criteriaGeo);
					calles = new ArrayList<String>();
					for (MapaIncidenciasGeometria g : geometrias) {
						calles.add(g.getDesdireccion());
					}
					
					tipoEvento = catalogoService.findCatalogoById(m.getTipoEvento());
					
					
					if(m.getTipoEvento() == 8749){
						if(m.getTipoCierre()!=null){
						tipoCierre = "("+(m.getTipoCierre().equalsIgnoreCase("T")?"<span style=\"color:red;\">Cierre Total</span>":"<span style=\"color:green;\">Cierre Parcial</span>")+")";
						}else{
							tipoCierre="<span style=\"color:green;\">Cierre Parcial</span>";
						}
					}else{
						tipoCierre ="";
					}
					
					mensaje+="<tr>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\">"+tipoEvento.getDesNombre()+" "+tipoCierre+"</td>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\">"+m.getDescripcion()+"</td>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\" >"+sdf2.format(m.getFecHoraInicio())+"</td>"
								+ "<td style=\"border-bottom:1px #ccc solid;padding:5px;border-right:1px #ccc solid;font-size:13px;\" >"+sdf2.format(m.getFecHoraFin())+"</td>"
								+ "<td style=\"padding:5px;border-bottom:1px #ccc solid;font-size:13px;\" >"+StringUtils.join(calles, ", ")+"</td>"
							+ "</tr>";
				
				countRegistros++;
					
				}
				
			}
			
			mensaje+="</table>";
			
			mensaje ="<p>Estimado Usuario los siguientes registros de cierre de calles estan por vencer en pocos dias:</p>"+mensaje+""
					+ "<p>Para acceder a la consulta de cierre de calles hacer click <a href=\"http://digital.miraflores.gob.pe:8080/mobileApps/cierre-calles/consulta-cierre-calles\">aqui</a></p>";
			
			if(countRegistros > 0){
				
				emailsCatalogo = catalogoService.findCatalogoByGrupo(MobileConstantes.GRUPO_EMAILS_NOTI_CIERRE_CALLES);
				
				if(emailsCatalogo != null){
					String[] arrMails = new String[emailsCatalogo.size()];
					Catalogo emailCatalogo = null;
					for(int i = 0 ; i < emailsCatalogo.size() ; i++){
						emailCatalogo = emailsCatalogo.get(i);
						arrMails[i] = emailCatalogo.getDesNombre();
					}
					emailService.enviarCorreo(arrMails, "Notificación de vencimiento de cierre de calles", mensaje);
				}
				
			}
			
			
		}
	}

	@Override
	public File exportarExcelBandejaCierreCalles(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		
		int c = 0;
		
		if(incidencia.getStart() == null){
			incidencia.setStart(0);
		}
		
		if(incidencia.getLimit() == null){
			incidencia.setLimit(100000);
		}
		
		
		if (incidencia.getStart() != 0) {
			c = c + 1;
		}
		
		if(incidencia.getTipoIncidencia() != null){
			params.put("TIPO_INCIDENCIA", incidencia.getTipoIncidencia());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDesTipoIncidencia())){
			params.put("NOMBRE_TIPO_EVENTO", incidencia.getDesTipoIncidencia());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDescripcion())){
			params.put("DESCRIPCION", incidencia.getDescripcion());
		}
		
		if(!StringUtils.isEmpty(incidencia.getStrFecIni())){
			params.put("FECHA_INI", incidencia.getStrFecIni());
		}
		
		if(!StringUtils.isEmpty(incidencia.getStrFecFin())){
			params.put("FECHA_FIN", incidencia.getStrFecFin());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDesCallles())){
			params.put("DES_CALLLES", incidencia.getDesCallles());
		}
		
		params.put("START", incidencia.getStart());
		params.put("LIMIT", incidencia.getLimit());
		
		List<MapaIncidenciasRegistro> list =  mapaIncidenciasRegistroMapper.listarIncidenciasConPaginacion(params);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Cierre de Calles");
				 
		 Row rowInit = sheet.createRow(0);
		 
		 Font headerFont = workbook.createFont();
		 headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 
		 CellStyle headerStyle = workbook.createCellStyle();
		 headerStyle.setFont(headerFont);
		    
		 
		 Cell cell1 = rowInit.createCell(0);
		 cell1.setCellValue("TIPO EVENTO");
		 cell1.setCellStyle(headerStyle);
		 
		 Cell cell2 = rowInit.createCell(1);
		 cell2.setCellValue("TIPO CIERRE");
		 cell2.setCellStyle(headerStyle);
		 
		 Cell cell3 = rowInit.createCell(2);
		 cell3.setCellValue("DESCRIPCIÓN");
		 cell3.setCellStyle(headerStyle);
		 
		 Cell cell4 = rowInit.createCell(3);
		 cell4.setCellValue("CALLES");
		 cell4.setCellStyle(headerStyle);
		 
//		 Cell cell5 = rowInit.createCell(4);
//		 cell5.setCellValue("DIRECCION CARDINAL");
//		 cell5.setCellStyle(headerStyle);
		 
		 Cell cell6 = rowInit.createCell(4);
		 cell6.setCellValue("FECHA INICIO");
		 cell6.setCellStyle(headerStyle);
		 
		 Cell cell7 = rowInit.createCell(5);
		 cell7.setCellValue("FECHA FINALIZAÓN");
		 cell7.setCellStyle(headerStyle);
		 
		 int index = 1;
		 
		 Row row = null;
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

   		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("NORTH","NORTE");
		 map.put("SOUTH","SUR");
		 map.put("EAST","ESTE");
		 map.put("WEST","OESTE");
		 map.put("NORTH_WEST","NOR-OESTE");
		 map.put("SOUTH_WEST","SUR-OESTE");
		 map.put("NORTH_EAST","NOR-ESTE");
		 map.put("SOUTH_EAST","SUR-ESTE");
		 map.put("BOTH_DIRECTIONS","AMBAS DIRECCIONES");
		 
		 for (MapaIncidenciasRegistro m : list) {
			 row = sheet.createRow(index);
			 row.createCell(0).setCellValue(m.getNombreTipoEvento());
			 row.createCell(1).setCellValue((m.getTipoCierre()!=null && m.getTipoCierre().equalsIgnoreCase("T"))?"TOTAL":"PARCIAL");
			 row.createCell(2).setCellValue(m.getDescripcion());
			 row.createCell(3).setCellValue(m.getDesCallles());
			 //row.createCell(4).setCellValue((map.get(m.getDireccionFinal())!=null)?map.get(m.getDireccionFinal()).toString():m.getDireccionFinal());
			 row.createCell(4).setCellValue(sdf.format(m.getFecHoraInicio()));
			 row.createCell(5).setCellValue(sdf.format(m.getFecHoraFin()));
			 index++;
		}
		
		 sheet.autoSizeColumn(0);
		 sheet.autoSizeColumn(1);
		 sheet.autoSizeColumn(2);
		 sheet.autoSizeColumn(3);
		 sheet.autoSizeColumn(4);
		 sheet.autoSizeColumn(5);
//		 sheet.autoSizeColumn(6);
		 
		File temp = null;
		try {
			
			temp = File.createTempFile("Export_Cierre_Calles", ".xls");
			FileOutputStream out =  new FileOutputStream(temp);
			workbook.write(out);
			return temp;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}
	
		
	
	}

	@Override
	public Map<String, Object> createVoxivaInit(VoxivaVO incidencia) throws Exception {
		// TODO Auto-generated method stub
		//ParametroSistema parametroAutenticacion = parametroSistemaService.obtenerParametroSistemaById(incidencia.getUserName());
		Map<String, Object> result = new HashMap<String, Object>();
//		if(parametroAutenticacion == null){
//			result.put("message","El usuario ingresado no existe.");
//			result.put("success",Boolean.FALSE);
//			return result;
//		}
//		
//		if(!parametroAutenticacion.getValor().equalsIgnoreCase(incidencia.getSecurityKey())){
//			result.put("message","El código de seguridad es incorrecto.");
//			result.put("success",Boolean.FALSE);
//			return result;
//		}
		
		MapaIncidenciasRegistro e = new MapaIncidenciasRegistro();
		
//		try {
//			Catalogo referenciaViaCatastro = catalogoService.findCatalogoByCodigo(incidencia.getIdVia().toString(),MobileConstantes.GRUPO_REFERENCIA_VIA_VOXIVA);
//			e.setIdViaCatastro(Integer.parseInt(referenciaViaCatastro.getReferencia1()));
//			e.setIdTipoViaCatastro(Integer.parseInt(referenciaViaCatastro.getReferencia2()));
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//			result.put("message","El código de via enviado no existe.");
//			result.put("success",Boolean.FALSE);
//			return result;
//		}
		
		e.setIdCasoVoxiva(incidencia.getIdCaso());
		
//		String strFecha = incidencia.getFechaHoraCreacion();
//		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		
//		Date date=null;
//		try {
//			date = simpleDateFormat.parse(strFecha);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		e.setFecRegistra(new Date());
		e.setTipoIncidencia(MobileConstantes.VOXIVA_WS);
//		e.setTipoCasoVoxiva(incidencia.getTipoCaso());
//		e.setSubTipoCasoVoxiva(incidencia.getSubTipocaso());
//		e.setDescripcion(incidencia.getDetalle());
//		e.setIdTipoVia(incidencia.getIdTipoVia());
//		e.setIdVia(incidencia.getIdVia());
//		e.setCuadraEvento(incidencia.getNumCuadra());
		e.setLatitud(incidencia.getLatitud());
		e.setLongitud(incidencia.getLongitud());
//		e.setIdDispositivo(incidencia.getIdDispositivo());
		e.setVisible("S");
		e.setEstado("A");
		
		try {
			this.registrarIncidenciaVoxiva(e);
			result.put("success",Boolean.TRUE);
			result.put("message","Incidencia registrada de forma correcta.");
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
			result.put("message",exc.getMessage());
			result.put("success",Boolean.FALSE);
		}
		
		return result;
		
	}

	@Override
	public List<MapaIncidenciaGenerico> getGeoGenerico(MapaIncidenciaGenerico param) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciaGenericoCriteria c = new MapaIncidenciaGenericoCriteria();
		pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaGenericoCriteria.Criteria criteria = c.createCriteria();
		if(param.getTipo()!=null){
			criteria.andTipoEqualTo(param.getTipo());
		}
		
		if(param.getPublico()!=null){
			criteria.andPublicoEqualTo(param.getPublico());
		}
		
		if(param.getCuadra()!=null){
			criteria.andCuadraLikeInsensitive(param.getCuadra()+"%");
		}
		
		if(param.getNombre()!=null){
			criteria.andNombreLikeInsensitive("%"+param.getNombre()+"%");
		}
		
		return mapaIncidenciaGenericoMapper.selectByExample(c);
	}

	@Override
	public List<Map<String, Object>> buildServiceWazeEstacionamientos(MapaIncidenciaGenerico param) throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciaGenericoCriteria c = new MapaIncidenciaGenericoCriteria();
		c.createCriteria().andTipoEqualTo(MobileConstantes.MEDIO_PLAYA_ESTACIONAMIENTO).andPublicoEqualTo(MobileConstantes.LETRA_SI);
		
		List<MapaIncidenciaGenerico> list = mapaIncidenciaGenericoMapper.selectByExample(c);
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = null,cordenadas;
		
		for (MapaIncidenciaGenerico m : list) {
			map = new HashMap<String, Object>();
			
			map.put("id", m.getId());
			map.put("nombre", m.getNombre());
			map.put("via", m.getNombreVia());
			map.put("numeroVia", m.getNumero());
			map.put("interior", m.getInterior());
			map.put("letra", m.getLetra());
			map.put("zona", m.getZona());
			map.put("espacios", m.getEspacios());
			map.put("giroNegocio", m.getGiro());
			map.put("isPublico", m.getPublico());
			
			cordenadas = new HashMap<String, Object>();
			
			cordenadas.put("longitud", m.getGeometria().split(",")[1]);
			cordenadas.put("latitud", m.getGeometria().split(",")[0]);
			
			map.put("cordenadas", cordenadas);
			
			result.add(map);
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> buildServiceWazeLimitesVelocidad(MapaIncidenciaGenerico param) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciaGenericoCriteria c = new MapaIncidenciaGenericoCriteria();
		c.createCriteria().andTipoEqualTo(MobileConstantes.MEDIO_PLAYA_LIMITES_VELOCIDAD);
		
		List<MapaIncidenciaGenerico> list = mapaIncidenciaGenericoMapper.selectByExample(c);
		
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = null,cordenadaInicial = null,cordenadaFinal = null;
		
		for (MapaIncidenciaGenerico m : list) {
			map = new HashMap<String, Object>();
			map.put("id", m.getId());
			map.put("nombreVia", m.getNombreVia());
			map.put("tramo", m.getTramo());
			map.put("viaInicial", m.getViaIni());
			map.put("viaFinal", m.getViaFin());
			map.put("sentido", m.getSentido());
			map.put("percentil", m.getPercentil());
			map.put("limite", m.getLimite());
			
			cordenadaInicial = new HashMap<String, Object>();
			cordenadaInicial.put("longitud", m.getPuntoIni().split(",")[1]);
			cordenadaInicial.put("latitud", m.getPuntoIni().split(",")[0]);
			map.put("cordenadaInicial", cordenadaInicial);
			
			if(StringUtils.isNotBlank(m.getPuntoFin()) && !m.getPuntoFin().equals(",")){
				cordenadaFinal = new HashMap<String, Object>();
				cordenadaFinal.put("longitud", m.getPuntoFin().split(",")[1]);
				cordenadaFinal.put("latitud", m.getPuntoFin().split(",")[0]);
				map.put("cordenadaFinal", cordenadaFinal);
			}
			result.add(map);
		}
		
		return result;
	}

	@Override
	public PreInscripcionCierreCalle insertPreRegistroCierreCalle(PreInscripcionCierreCalle data) throws Exception {
		return null;
	}

	@Override
	public List<PreInscripcionCierreCalle> obtenerPreRegistrosCierreCalle(PreInscripcionCierreCalle data)
			throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("DES_USUARIO", data.getDesUsuario());
		
		if(data.getCuadra()!=null){
			parametros.put("DES_USUARIO", data.getDesUsuario());
		}
		
		if(data.getNumExpediente()!=null){
			parametros.put("NUM_EXPEDIENTE", data.getNumExpediente());
		}
		
		if(data.getDireccion()!=null){
			parametros.put("DIRECCION", data.getDireccion());
		}
		
		if(data.getCuadra()!=null){
			parametros.put("CUADRA", data.getCuadra());
		}
		
		if(data.getEmpresa()!=null){
			parametros.put("EMPRESA", data.getEmpresa());
		}
		
		return preInscripcionCierreCalleMapper.bandejaPreRegistroCierreCalle(parametros);
		
	}

	@Override
	public PreInscripcionCierreCalle getPreRegistroById(Integer idPreRegistro) throws Exception {
		// TODO Auto-generated method stub
		
		PreInscripcionCierreCalle p = preInscripcionCierreCalleMapper.selectByPrimaryKey(idPreRegistro);
		
		if(p != null && p.getEstado() != 1 ){
			return null;
		}
				
		return p;
	}

	@Override
	public MapaIncidenciasRegistro actualizarGeoIncidencia(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		if(incidencia.getIdIncidencia() == null){
			incidencia.setTipoIncidencia(MobileConstantes.VOXIVA_WS);
			return this.registrarIncidenciaGenericWithGeometria(incidencia);
		}
		
		incidencia.setFecModifica(new Date());
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		
		MapaIncidenciasRegistro recordUpdate = mapaIncidenciasRegistroMapper.selectByPrimaryKey(incidencia.getIdIncidencia());
		
		recordUpdate.setSubTipoCasoVoxiva(incidencia.getSubTipoCasoVoxiva());
		recordUpdate.setDireccionFinal(incidencia.getDireccionFinal());
		
		recordUpdate.setEstadoVoxiva(incidencia.getEstadoVoxiva());
		recordUpdate.setDesEstadoVoxiva(incidencia.getDesEstadoVoxiva());
		
		recordUpdate.setSubEstadoVoxiva(incidencia.getSubEstadoVoxiva());
		recordUpdate.setDesSubEstadoVoxiva(incidencia.getDesSubEstadoVoxiva());
		
		recordUpdate.setEstadoCalifVoxiva(incidencia.getEstadoCalifVoxiva());
		recordUpdate.setDesEstadoCalifVoxiva(incidencia.getDesEstadoCalifVoxiva());
		
		recordUpdate.setDireccionIncidencia(incidencia.getDesEstadoCalifVoxiva());
		recordUpdate.setCuadraEvento(incidencia.getCuadraEvento());
		
		recordUpdate.setNombreRecepcionaIncidencia(incidencia.getNombreRecepcionaIncidencia());
		recordUpdate.setDniRecepcionaIncidencia(incidencia.getDniRecepcionaIncidencia());
		
		recordUpdate.setDesUnidIntervVoxiva(incidencia.getDesUnidIntervVoxiva());
		
		//No cambiar importante
		mapaIncidenciasRegistroMapper.updateByPrimaryKey(recordUpdate);
		
		return recordUpdate;
	}

	@Override
	public Map<String, Object> getGeometriaByIdIncidencia(Integer idIncidencia) throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciasGeometriaCriteria cg = new MapaIncidenciasGeometriaCriteria();
		
		MapaIncidenciasRegistro m = mapaIncidenciasRegistroMapper.selectByPrimaryKey(idIncidencia);
		cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
		m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("data", m);
		
		return data;
	}
	
	@Override
	public MapaIncidenciasRegistro registrarIncidenciaGenericWithGeometria(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		if(incidencia.getIdIncidencia() == null){
			incidencia.setEstado(MobileConstantes.ESTADO_ACTIVO);
			incidencia.setVisible(MobileConstantes.LETRA_SI);
			mapaIncidenciasRegistroMapper.insertSelective(incidencia);
		}else{
			mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		}
		
		if(incidencia.getLatitud()!=null && incidencia.getLongitud()!=null){
		
			MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
			c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
			
			mapaIncidenciasGeometriaMapper.deleteByExample(c);
			
			MapaIncidenciasGeometria geometria = new MapaIncidenciasGeometria();
			geometria.setIndestado(MobileConstantes.ESTADO_ACTIVO_NUMERO);
			geometria.setIdetipogeometria(MobileConstantes.TIPO_GEOMETRIA_PUNTO);
			geometria.setIdecodident(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
			geometria.setIdecodvalor(incidencia.getIdIncidencia());
			geometria.setGeometria(incidencia.getLatitud()+" "+incidencia.getLongitud());
			
			mapaIncidenciasGeometriaMapper.insertSelective(geometria);
			
		}
		
		return incidencia;
	}


	@Override
	public void updateIncidenciaByPk(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
	}


	@Override
	public void registrarAuditoria(AuditoriaIncidencia a) throws Exception {
		// TODO Auto-generated method stub
		auditoriaIncidenciaMapper.insertSelective(a);
	}
	
	
	public List<Map<String, Object>> listaIncidenciasJsonFormatWaze(MapaIncidenciasRegistro incidencia)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> listJsonIncidencias = new ArrayList<Map<String,Object>>();
		Map<String, Object> event = null;//new HashMap<String, Object>();
		Map<String, Object> eventFinal = null;//new HashMap<String, Object>();
		
		Map<String, Object> locationPrev = null;
		Map<String, Object> locationFinal = null;
		
		SimpleDateFormat sdfGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat sdfGMT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT2.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectListWazeService(params);

		List<GeometriaLinea> lineas = null;
		
		List<MapaIncidenciaSchedule> schedules = null;
		Integer num=0;
		if(list!=null){
		
			for (MapaIncidenciasRegistro mapaIncidenciasRegistro : list) {
				num++;
				event = new HashMap<String, Object>();
				eventFinal = new HashMap<String, Object>();
				event.put("-id", mapaIncidenciasRegistro.getIdIncidencia() +"_"+mapaIncidenciasRegistro.getFecRegistra().getTime());
				event.put("parent_event", "");
				event.put("creationtime", sdfGMT.format(mapaIncidenciasRegistro.getFecRegistra()));
				if(mapaIncidenciasRegistro.getFecModifica()!=null){
					event.put("updatetime", sdfGMT.format(mapaIncidenciasRegistro.getFecModifica()));
				}else{
					event.put("updatetime", "");
				}
				
				event.put("source", "");
				
				if(mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_CONSTRUCTION_TYPE){
					event.put("type", "CONSTRUCTION");
				}else{
					if(mapaIncidenciasRegistro.getTipoCierre().equalsIgnoreCase(MobileConstantes.CIERRE_PARCIAL)){
						event.put("type", "CONSTRUCTION");
					}else{
						event.put("type", "ROAD_CLOSED");
					}
				
				}

				event.put("subtype", "");
				event.put("description", mapaIncidenciasRegistro.getDescripcion());
				
				lineas = mapaIncidenciasRegistro.getLineas();
				
				String strStreet = null;
				String strPolyline = null;
				String strInter = null;
				String strDirection = null;
				
				if(lineas != null){
					locationPrev = new HashMap<String, Object>();
					locationFinal = new HashMap<String, Object>();
					for (GeometriaLinea linea : lineas) {
						num++;
						if(locationPrev.containsKey("street")){
							strStreet = locationPrev.get("street").toString()+", "+linea.getDesCalle();
							locationPrev.put("street", strStreet);
						}else{
							locationPrev.put("street", linea.getDesCalle());
						}
						
						if(locationPrev.containsKey("polyline")){
							strPolyline = locationPrev.get("polyline").toString()+" "+linea.getPointIni()+" "+linea.getPointFin();
							locationPrev.put("polyline", strPolyline);
						}else{
							locationPrev.put("polyline", linea.getPointIni()+" "+linea.getPointFin());
						}
						
						if(locationPrev.containsKey("location_description")){
							strInter = locationPrev.get("location_description").toString()+", "+linea.getIntercepcion1()+"-"+linea.getIntercepcion2();
							locationPrev.put("location_description", strInter);
						}else{
							locationPrev.put("location_description", linea.getIntercepcion1()+"-"+linea.getIntercepcion2());
						}
						
						if(locationPrev.containsKey("direction")){
							strDirection = locationPrev.get("direction").toString()+", "+linea.getDirCardinal();
							locationPrev.put("direction", strDirection);
						}else{
							locationPrev.put("direction", linea.getDirCardinal());
						}
						
					}
					
					event.put("location", locationPrev);
				}
				
				
				event.put("starttime", sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()));
				
				if(mapaIncidenciasRegistro.getFecHoraFin()!=null){
					event.put("endtime", sdfGMT.format(mapaIncidenciasRegistro.getFecHoraFin()));
				}

				schedules = mapaIncidenciasRegistro.getDias();
				
				Map<String, Object> schedulePrev = null;
				String strSchedule = null;
				
				if(schedules!=null && schedules.size()>0){
					
					schedulePrev = new HashMap<String, Object>();
					
					for(MapaIncidenciaSchedule schedule : schedules){
						num++;
						if(schedule.getDesDia().equalsIgnoreCase("LUNES")){
							
							if(schedulePrev.containsKey("monday")){
								strSchedule = schedulePrev.get("monday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("monday", strSchedule);
							}else{
								schedulePrev.put("monday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
							
						}else if(schedule.getDesDia().equalsIgnoreCase("MARTES")){
							
							if(schedulePrev.containsKey("tuesday")){
								strSchedule = schedulePrev.get("tuesday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("tuesday", strSchedule);
							}else{
								schedulePrev.put("tuesday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}else if(schedule.getDesDia().equalsIgnoreCase("MIERCOLES")){
							
							if(schedulePrev.containsKey("wednesday")){
								strSchedule = schedulePrev.get("wednesday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("wednesday", strSchedule);
							}else{
								schedulePrev.put("wednesday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}else if(schedule.getDesDia().equalsIgnoreCase("JUEVES")){
							
							if(schedulePrev.containsKey("thursday")){
								strSchedule = schedulePrev.get("thursday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("thursday", strSchedule);
							}else{
								schedulePrev.put("thursday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}else if(schedule.getDesDia().equalsIgnoreCase("VIERNES")){
							
							if(schedulePrev.containsKey("friday")){
								strSchedule = schedulePrev.get("friday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("friday", strSchedule);
							}else{
								schedulePrev.put("friday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}else if(schedule.getDesDia().equalsIgnoreCase("SABADO")){
							
							if(schedulePrev.containsKey("saturday")){
								strSchedule = schedulePrev.get("saturday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("saturday", strSchedule);
							}else{
								schedulePrev.put("saturday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}else if(schedule.getDesDia().equalsIgnoreCase("DOMINGO")){
							
							if(schedulePrev.containsKey("sunday")){
								strSchedule = schedulePrev.get("sunday").toString()+","+schedule.getHoraInicio()+"-"+schedule.getHoraFin();
								schedulePrev.put("sunday", strSchedule);
							}else{
								schedulePrev.put("sunday", schedule.getHoraInicio()+"-"+schedule.getHoraFin());
							}
							
						}
						
						
					}
					
					event.put("schedule", schedulePrev);
				}
				
				eventFinal.put("event", event);
				listJsonIncidencias.add(eventFinal);
				
			}
		}
		
		this.writeLogGetWaze();
		
		return listJsonIncidencias;
	}

	private void writeLogGetWaze(){
		
		try
    	{
    		String timeLog = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    	    File logFile = new File(MobileConstantes.PATH_ADJUNTOS+"log_consumo_waze.txt");
    	    FileWriter fw = new FileWriter(logFile,true); //the true will append the new data
    	    System.out.println(logFile.getCanonicalPath());
    	    fw.write("Get: generate-jsonformat-waze => "+ timeLog+"\n");//appends the string to the file
    	    fw.close();
    	}
    	catch(IOException ioe)
    	{
    	    System.err.println("IOException: " + ioe.getMessage());
    	}
		
	}
	

	@Override
	public List<MapaIncidenciasRegistro> selectVoxivaListMap(MapaIncidenciasRegistro incidencia) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String,Object>();
		if(incidencia!=null && incidencia.getIdIncidencia()!=null){
			params.put("ID_INCIDENCIA", incidencia.getIdIncidencia());
		}
		return mapaIncidenciasRegistroMapper.selectVoxivaListMap(params);
	}
	
}
