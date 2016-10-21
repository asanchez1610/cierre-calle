package pe.gob.miraflores.mobile.service.mapaincidenciasws.impl;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.domain.catalogo.Catalogo;
import pe.gob.miraflores.mobile.domain.mapaincidencias.GeoInfoCambista;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.domain.parametrosistema.ParametroSistema;
import pe.gob.miraflores.mobile.service.catalogo.LocalCatalogoService;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;
import pe.gob.miraflores.mobile.service.mapaincidenciasws.IncidenciaCambista;
import pe.gob.miraflores.mobile.service.mapaincidenciasws.IncidenciaVoxiva;
import pe.gob.miraflores.mobile.service.mapaincidenciasws.MapaIncidenciaWebService;
import pe.gob.miraflores.mobile.service.mapaincidenciasws.MessageResult;
import pe.gob.miraflores.mobile.service.parametrosistema.ParametroSistemaService;
import pe.gob.miraflores.mobile.util.SerializeUtil;

@WebService(endpointInterface = "pe.gob.miraflores.mobile.service.mapaincidenciasws.MapaIncidenciaWebService")
public class MapaIncidenciaWebServiceImpl implements MapaIncidenciaWebService {
	@Autowired
	private MapaIncidenciasService mapaIncidenciasService;
	@Autowired
	private ParametroSistemaService parametroSistemaService;
	@Autowired
	private LocalCatalogoService localCatalogoService;
	@Override
	public MessageResult createIncidenciaInfoCambista(IncidenciaCambista record) {
		// TODO Auto-generated method stub
	
		MessageResult result = new MessageResult();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Date date = null;
		try {
			date = simpleDateFormat.parse(record.getFecHoraRegistro());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		String jsonStringObjRequest = SerializeUtil.serializeObject(record);
		
		GeoInfoCambista cambista = new GeoInfoCambista();
		
		Object objResponse = SerializeUtil.unserializeObject(jsonStringObjRequest, cambista);
		
		cambista = (GeoInfoCambista) objResponse;
		
		cambista.setFechaHoraRegistro(date);
		
		if(record.getIdAlerta() != null){
			cambista.setTipo(MobileConstantes.ESTADO_ACTIVO_STRING);
		}else{
			cambista.setTipo(MobileConstantes.CERO_STRING);
		}
		
		try{
			cambista.setEstado(MobileConstantes.ESTADO_ACTIVO);
			mapaIncidenciasService.insertInfoCambista(cambista);
			result.setSuccess(Boolean.TRUE);
			result.setMessage("Incidencia registrada de forma correcta.");
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			result.setSuccess(Boolean.FALSE);
			result.setMessage("Error al registrar la incidencia");
		}
		
		return result;
	}
	@Override
	public MessageResult createIncidenciaVoxiva(IncidenciaVoxiva incidencia) {
		MessageResult result = new MessageResult();

		ParametroSistema parametroAutenticacion = this.parametroSistemaService
				.obtenerParametroSistemaById(incidencia.getUserName());
		if (parametroAutenticacion == null) {
			result.setMessage("El usuario ingresado no existe.");
			result.setSuccess(Boolean.FALSE);
			return result;
		}
		if (!parametroAutenticacion.getValor().equalsIgnoreCase(incidencia.getSecurityKey())) {
			result.setMessage("El código de seguridad es incorrecto.");
			result.setSuccess(Boolean.FALSE);
			return result;
		}
		MapaIncidenciasRegistro e = new MapaIncidenciasRegistro();
		try {
			Catalogo referenciaViaCatastro = this.localCatalogoService
					.findCatalogoByCodigo(incidencia.getIdVia().toString(), Integer.valueOf(326));
			e.setIdViaCatastro(Integer.valueOf(Integer.parseInt(referenciaViaCatastro.getReferencia1())));
			e.setIdTipoViaCatastro(Integer.valueOf(Integer.parseInt(referenciaViaCatastro.getReferencia2())));
		} catch (Exception e2) {
			//e2.printStackTrace();
			System.err.println("El código de via enviado no existe.");
//			result.setMessage("El código de via enviado no existe.");
//			result.setSuccess(Boolean.FALSE);
//			return result;
		}
		e.setIdCasoVoxiva(incidencia.getIdCaso());

		String strFecha = incidencia.getFechaHoraCreacion();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date date = null;
		try {
			date = simpleDateFormat.parse(strFecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		e.setFecRegistra(date);
		e.setTipoIncidencia(Integer.valueOf(8765));
		e.setTipoCasoVoxiva(incidencia.getTipoCaso());
		e.setSubTipoCasoVoxiva(incidencia.getSubTipocaso());
		e.setDescripcion(incidencia.getDetalle());
		e.setIdTipoVia(incidencia.getIdTipoVia());
		e.setIdVia(incidencia.getIdVia());
		e.setCuadraEvento(incidencia.getNumCuadra());
		e.setLatitud(incidencia.getLatitud());
		e.setLongitud(incidencia.getLongitud());
		e.setIdDispositivo(incidencia.getIdDispositivo());
		e.setHoraLlamadaVoxiva(incidencia.getFechaHoraAtencion());
		e.setNombreReportaIncidencia(incidencia.getNombreReportaIncidencia());
		e.setDireccionIncidencia(incidencia.getDireccionIncidencia());
		e.setAccionesIncidencia(incidencia.getAccionesIncidencia());
		e.setNombreRecepcionaIncidencia(incidencia.getNombreRecepcionaIncidencia());
		e.setDniRecepcionaIncidencia(incidencia.getDniRecepcionaIncidencia());
		e.setNroCasoVoxiva(incidencia.getNroCasoVoxiva());
		e.setTelefReportaIncidencia(incidencia.getTelefReportaIncidencia());
		e.setIncidenciaPresentada(incidencia.getIncidenciaPresentada());

		if (incidencia.getIdSituacion() != null) {
			e.setDesEstadoCalifVoxiva(Integer.parseInt(incidencia.getIdSituacion())+"");
		}
		
		e.setVisible(MobileConstantes.LETRA_SI);
		e.setEstado(MobileConstantes.ESTADO_ACTIVO);
		try {
			this.mapaIncidenciasService.registrarIncidenciaVoxiva(e);
			result.setSuccess(Boolean.TRUE);
			result.setMessage("Incidencia registrada de forma correcta.");
		} catch (Exception exc) {
			exc.printStackTrace();
			result.setMessage(exc.getMessage());
			result.setSuccess(Boolean.FALSE);
		}
		return result;
	}



}
