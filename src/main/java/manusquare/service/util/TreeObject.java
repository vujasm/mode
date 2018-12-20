package manusquare.service.util;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeObject {
	
	Object object;
	String uri;
	String keyName;
	String name;
	

	public String toString() {
		if (StringUtils.isEmpty(name)){
			return uri.substring(uri.indexOf("#")+1, uri.length());
		}
		return name;
	}
}
