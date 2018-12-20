package manusquare.web.bean;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manusquare.service.JenaService;
import manusquare.service.util.TreeObject;

//@ManagedBean
@ViewScoped
@Component
@Named
public class TreeView implements Serializable {
     
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private TreeNode root;
	
	private String searchField;
	
	@Autowired
	private JenaService jenaService;
     
    @PostConstruct
    public void init() {
    	
    	root = new DefaultTreeNode("Root", null);
    	
    	OntModel model = jenaService.readManuSquareOntologyFromFile();
    	modelToTree(model);
    
    }
 
    private void modelToTree(OntModel model) {
    	Set<OntClass> classes = model.listHierarchyRootClasses().toSet();
    	for (OntClass ontClass : classes) {
    		TreeNode node = new DefaultTreeNode(new TreeObject(ontClass,ontClass.getURI(),"",""));
    		node.setParent(root);
    		root.getChildren().add(node);
    		processOntClassToTree(ontClass, node);
		}
	}

	private void processOntClassToTree(OntClass ontClass,TreeNode node) {
		List<OntClass> list = ontClass.listSubClasses(true).toList();
		for (OntClass subClass : list) {
//			System.out.println(subClass.getLocalName());
			if (subClass.getURI().equals(ontClass.getURI())==false){
				TreeNode subNode = new DefaultTreeNode(new TreeObject(subClass,subClass.getURI(),"",""));
				subNode.setParent(node);
				node.getChildren().add(subNode);
				processOntClassToTree(subClass, subNode);
			}
		}
	}

	public TreeNode getRoot() {
        return root;
    }

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
}