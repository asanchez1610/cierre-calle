package pe.gob.miraflores.mobile.service.mapaincidenciasws.impl;

import java.lang.reflect.Field;

import java.lang.reflect.Modifier;

import com.google.gson.Gson;

import pe.gob.miraflores.mobile.domain.mapaincidencias.GeoInfoCambista;
import pe.gob.miraflores.mobile.service.mapaincidenciasws.IncidenciaCambista;

public class Test {

	public static void main(String[] args) {
		
		IncidenciaCambista c = new IncidenciaCambista();
		c.setDesAlerta("des alerta");
		
		GeoInfoCambista g = new GeoInfoCambista();
		
		String json1 = Test.serializeObject(c);
		
		Object obj2 = Test.unserializeObject(json1, g);
		
		System.out.println(obj2);
		
	}
	
	public static String serializeObject(Object o) {
	    Gson gson = new Gson();
	    String serializedObject = gson.toJson(o);
	    return serializedObject;
	}
	//___________________________________________________________________________________

	public static Object unserializeObject(String s, Object o){
	    Gson gson = new Gson();
	    Object object = gson.fromJson(s, o.getClass());
	    return object;
	}
	       //___________________________________________________________________________________
	public static Object cloneObject(Object o){
	    String s = serializeObject(o);
	    Object object = unserializeObject(s,o);
	    return object;
	}
	
	
}
