package manusquare.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import manusquare.service.util.Annotation;

@ViewScoped
@Component
@Named
public class ClassEditorView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String iri = "http://manusquare.project.eu/industrial-manusquare#AutomationProduct";
	
	private List<Annotation> annotations = Lists.newArrayList();
	
	@PostConstruct
	public void init() {
		annotations.add(new Annotation("rdfs:label","Automation Product","en"));
		annotations.add(new Annotation("rdfs:label","Producto de automatización","pt"));
		annotations.add(new Annotation("rdfs:label","Prodotto di automazione","it"));
		annotations.add(new Annotation("rdfs:label","Automatisierungsprodukt","de"));
		annotations.add(new Annotation("rdfs:comment","Automation products and solutions including drives, motion control systems, "
				+ "industrial control and communication,"
				+ "human machine interfaces, sensors, meters, and robot solutions","en"));
	}
	
	public String getIri() {
		return iri;
	}
	
	public void setIri(String iri) {
		this.iri = iri;
	}
	
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
}
