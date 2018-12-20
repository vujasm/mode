package manusquare.web.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.stereotype.Component;

@ViewScoped
@Component
@Named
public class ReasonerView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String log = "Log.."; 

	@PostConstruct
	public void init() {

	}
	
	
	public String getLog() {
		return log;
	}
	
	
	public void setLog(String log) {
		this.log = log;
	}

}
