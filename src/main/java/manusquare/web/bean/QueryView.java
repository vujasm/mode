package manusquare.web.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.stereotype.Component;

@ViewScoped
@Component
@Named
public class QueryView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PostConstruct
	public void init() {

	}
}
