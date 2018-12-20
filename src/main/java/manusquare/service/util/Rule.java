package manusquare.service.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
	
	private String id;
	
	private String name;
	
	private String description;

	private String ruleBody;
}
