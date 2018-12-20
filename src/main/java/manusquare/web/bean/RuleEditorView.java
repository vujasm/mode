package manusquare.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.github.jsonldjava.shaded.com.google.common.collect.Lists;

import manusquare.service.util.Rule;

@ViewScoped
@Component
@Named
public class RuleEditorView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Rule rule;
	
	private List<Rule> rules = Lists.newArrayList();
	
	
	@PostConstruct
	public void init() {
		rule = new Rule();
	}


	public Rule getRule() {
		return rule;
	}


	public void setRule(Rule rule) {
		this.rule = rule;
	}


	public List<Rule> getRules() {
		return rules;
	}


	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	

}
