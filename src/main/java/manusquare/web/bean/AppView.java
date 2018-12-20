package manusquare.web.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.jena.ext.com.google.common.collect.Maps;
import org.springframework.stereotype.Component;


@ApplicationScoped
@Component
@Named
public class AppView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Map<String, Boolean> editorsTabState = Maps.newHashMap();	
	
	
	@PostConstruct
	public void init() {
	    	
		editorsTabState.put("classTab", true);
		editorsTabState.put("ruleTab", false);
		editorsTabState.put("queryTab", false);
		editorsTabState.put("termExtractionTab", false);
		editorsTabState.put("reasonerTab", false);
	}
	
	
	public void renderShowTab(String tabName){
		getEditorsTabState().remove(tabName);
		getEditorsTabState().put(tabName, true);
	}
	
	public Boolean getEditorTabState(String tabName){
		if (getEditorsTabState().containsKey(tabName))
			return getEditorsTabState().get(tabName);
		return false;
	}


	public Map<String, Boolean> getEditorsTabState() {
		return editorsTabState;
	}


	public void setEditorsTabState(Map<String, Boolean> editorsTabState) {
		this.editorsTabState = editorsTabState;
	}
	
	

}
