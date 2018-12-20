package manusquare.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import manusquare.service.util.ExtractedTerm;

@ViewScoped
@Component
@Named
public class TermExtractionView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<ExtractedTerm> extractedTerms = Lists.newArrayList();
	
	
	@PostConstruct
    public void init() {
		extractedTerms.add(new ExtractedTerm("term here", "description here"));
    }
	
	
	public List<ExtractedTerm> getExtractedTerms() {
		return extractedTerms;
	}
	
	
	public void setExtractedTerms(List<ExtractedTerm> extractedTerms) {
		this.extractedTerms = extractedTerms;
	}

}
