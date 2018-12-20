package manusquare.service.util;


import java.util.Map;

import org.apache.jena.ext.com.google.common.collect.Maps;
import org.apache.jena.riot.system.stream.LocationMapper;

public class LocationMapping {

	public static Map<String, String> getMapping() {
		
		Map<String, String> map = Maps.newHashMap();

		map.put("http://manusquare.project.eu/industrial-manusquare#","ontologies/manusquare-industrial.ttl");
        return map;
	}
	
	
	public static synchronized String resolveLocation(String modelUri){
		return LocationMapping.getMapping().get(modelUri);
	}
	
	public static synchronized LocationMapper obtainLocationMapper(){
		LocationMapper lm = new LocationMapper();
		Map<String, String> locationMappings = LocationMapping.getMapping();
		  for (Map.Entry<String, String> mapping : locationMappings.entrySet()) {
	            lm.addAltEntry(mapping.getKey(), mapping.getValue());
	        }

		return lm;
	}

}
