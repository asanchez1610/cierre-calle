package pe.gob.miraflores.mobile.service.mapaincidencias;

import java.io.File;


import java.util.List;
import java.util.Map;

import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidencia;
import pe.gob.miraflores.mobile.domain.mapaincidencias.AuditoriaIncidencia;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciaGenerico;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.domain.mapaincidencias.PreInscripcionCierreCalle;
import pe.gob.miraflores.mobile.vo.VoxivaVO;

public interface MapaIncidenciasService {

	public String buildJsonRestWaze(Map<String, Object> params) throws Exception;
	
	public MapaIncidenciasRegistro registrar(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public List<MapaIncidenciasRegistro> obtenerIncidenciasNoProcesadas() throws Exception;
	
	public List<MapaIncidenciasRegistro> listaInicdenciasWaze(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public String listaIncidenciasXmlFormatWaze(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public MapaIncidenciasRegistro registrarIncidenciaVoxiva(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public String buildJsonRestDolphin() throws Exception;

	public GeoDetalleIncidencia obtenerDetalleIncidencia(Integer issi) throws Exception;
	
	public List<MapaIncidenciasRegistro> listaInicdenciasVoxiva(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public MapaIncidenciasRegistro obtenerIncidenciaPorId(Integer id) throws Exception;
	
	public Map<String, Object> listaInicdenciasPortipo(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public void anularIncidencia(Integer id) throws Exception;
	
	public void enviarAlertasCierreCalle() throws Exception;
	
	public File exportarExcelBandejaCierreCalles(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public Map<String, Object> createVoxivaInit(VoxivaVO incidencia) throws Exception;
	
	
	public List<MapaIncidenciaGenerico> getGeoGenerico(MapaIncidenciaGenerico param)  throws Exception;
	
	public List<Map<String, Object>> buildServiceWazeEstacionamientos(MapaIncidenciaGenerico param)  throws Exception;
	
	public List<Map<String, Object>> buildServiceWazeLimitesVelocidad(MapaIncidenciaGenerico param)  throws Exception;
	
	public PreInscripcionCierreCalle insertPreRegistroCierreCalle(PreInscripcionCierreCalle data) throws Exception;
	
	public PreInscripcionCierreCalle getPreRegistroById(Integer idPreRegistro) throws Exception;
	
	public List<PreInscripcionCierreCalle> obtenerPreRegistrosCierreCalle(PreInscripcionCierreCalle data)  throws Exception;
	
	public Map<String, Object> listaInicdenciasGeneric(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public MapaIncidenciasRegistro actualizarGeoIncidencia(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public Map<String, Object> getGeometriaByIdIncidencia(Integer idIncidencia) throws Exception;
	
	public MapaIncidenciasRegistro registrarIncidenciaGenericWithGeometria(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public void updateIncidenciaByPk(MapaIncidenciasRegistro incidencia) throws Exception;
	
	
	public void registrarAuditoria(AuditoriaIncidencia a) throws Exception;
	
	public List<Map<String, Object>> listaIncidenciasJsonFormatWaze(MapaIncidenciasRegistro incidencia) throws Exception;
	
	
	public List<MapaIncidenciasRegistro> selectVoxivaListMap(MapaIncidenciasRegistro incidencia);
	
}
