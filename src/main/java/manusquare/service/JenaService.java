package manusquare.service;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manusquare.service.util.JModelHandler;

@Service
public class JenaService {
	
	@Autowired
	private JModelHandler modelHandler;
	
	public OntModel readManuSquareOntologyFromFile(){
		OntModel model = modelHandler.
				fetchOntologyFromLocalLocation("http://manusquare.project.eu/industrial-manusquare#", FileUtils.langTurtle);
		
		return model;
		
	}

}
