package manusquare.service.util;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.apache.jena.ext.com.google.common.collect.Maps;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.adapters.AdapterFileManager;
import org.apache.jena.riot.system.stream.StreamManager;
import org.springframework.stereotype.Service;


@Service
public class JModelHandler {

	boolean useCache = true;
	Map<String, OntModel> modelCache = Maps.newConcurrentMap();

	public OntModel fetch(URI uri, String syntax) {
		return fetch(uri.toASCIIString(), syntax);
	}

	public OntModel fetch(String filenameOrURI, String syntax) {
		
		if (hasCachedModel(filenameOrURI))
			return getFromCache(filenameOrURI);
		
		StreamManager streamManager = StreamManager.makeDefaultStreamManager();
		streamManager.setLocationMapper(LocationMapping.obtainLocationMapper());
		
		Model m = new AdapterFileManager(streamManager).loadModel(filenameOrURI);
		OntModel model = ModelFactory.createOntologyModel(getModelSpec(), m);
		
		if (isCachingModels())
			addCacheModel(filenameOrURI, model);

		
		return model;
	}
		
	public OntModel fetchOntologyFromLocalLocation(String uri, String syntax) {
		if (hasCachedModel(uri))
			return getFromCache(uri);
		else{
			InputStream is = getClass().getResourceAsStream("/"+LocationMapping.obtainLocationMapper().altMapping(uri));
			return fetch(uri, is);
		}
		
	}

	/**
	 * 
	 * @param url
	 * @param syntax
	 * @param modelSpec
	 * @return
	 */
	public OntModel fetch(String uri, InputStream inputStream) {

		if (hasCachedModel(uri))
			return getFromCache(uri);

		OntModel m = ModelFactory.createOntologyModel(getModelSpec());
		m.read(inputStream, null, "TURTLE");

		if (isCachingModels())
			addCacheModel(uri, m);

		return m;
	}
	
	private OntModelSpec getModelSpec(){
		 Map<String, String> locationMappings =  LocationMapping.getMapping();
	     Set<String> ignoredImports = new  HashSet<String>();
	     OntModelSpec modelSpecShared = createModelSpecification(locationMappings, ignoredImports);
	     return modelSpecShared;
	}
	
	 public static OntModelSpec createModelSpecification(Map<String, String> locationMappings, Set<String> ignoredImports) {

	        OntDocumentManager documentManager = new OntDocumentManager();


	        for (Map.Entry<String, String> mapping : locationMappings.entrySet()) {
	            documentManager.addAltEntry(mapping.getKey(), mapping.getValue());
	        }

	        // Ignore the imports indicated
	        for (String ignoreUri : ignoredImports) {
	            documentManager.addIgnoreImport(ignoreUri);
	        }

	        // follow imports for now..
	        documentManager.setProcessImports(false);

	        OntModelSpec ontModelSpec =  new OntModelSpec(OntModelSpec.OWL_MEM);
	        ontModelSpec.setDocumentManager(documentManager);
	        return ontModelSpec;
	    }

	private void addCacheModel(String filenameOrURI, OntModel m) {
		modelCache.put(filenameOrURI, m);
	}

	public OntModel getFromCache(String filenameOrURI) {
		return modelCache.get(filenameOrURI);
	}

	public boolean hasCachedModel(String filenameOrURI) {
		return modelCache.containsKey(filenameOrURI);
	}

	public boolean isCachingModels() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}
	
	


}
