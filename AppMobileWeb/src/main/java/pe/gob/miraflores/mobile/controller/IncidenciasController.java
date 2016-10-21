package pe.gob.miraflores.mobile.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;

@Controller
@RequestMapping(IncidenciasController.BASE_URL_MODULO_INCIDENCIA)
public class IncidenciasController {

	protected final Log log = LogFactory.getLog(getClass());
	protected static final String BASE_URL_MODULO_INCIDENCIA = "/mapa-incidencia";

	@Autowired
	private MapaIncidenciasService mapaIncidenciasService;

	@RequestMapping
	public ModelAndView startPage() {
		ModelAndView modelAndView = new ModelAndView("blank");
		return modelAndView;
	}
	
	@RequestMapping(value = "/get-incidencia-by-id")
	public @ResponseBody
	Map<String, ? extends Object> getIncidenciaById(Integer id) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			data.put("incidencia", mapaIncidenciasService.obtenerIncidenciaPorId(id));
			data.put("success", Boolean.TRUE);
		}  catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			data.put("message", "Error al obtener los datos.");
			e.printStackTrace();
		}
		return data;
	}
	
	
	@RequestMapping(value = "/incidencias-list" , method=RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> incidenciasList(
			MapaIncidenciasRegistro incidencia) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.putAll(mapaIncidenciasService.listaInicdenciasPortipo(incidencia));
		} catch (Exception e) {
			// TODO: handle exception
			data.put("data", null);
			data.put("total", 0);
			e.printStackTrace();
		}
		return data;
	}
	

	@RequestMapping(value = "/generate-jsonformat-waze")
	public @ResponseBody
	Map<String, ? extends Object> generateFormatJsonWaze(MapaIncidenciasRegistro incidencia) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.put("incidents", mapaIncidenciasService.listaIncidenciasJsonFormatWaze(incidencia));
		
		} catch (Exception e) {
			data.put("incidents", "The server doesn't send data.");
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/anular-incidencia")
	public @ResponseBody
	Map<String, ? extends Object> anularincidencia(Integer id) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			mapaIncidenciasService.anularIncidencia(id);
			data.put("success", Boolean.TRUE);
		}catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			data.put("message", "Error al anular el registro.");
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
}
