package advent.twelve;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonObject implements JsonElement {

	@Override
	public JsonType getJsonType() {
		return JsonType.OBJECT;
	}
	
	Map<String, JsonElement> mapping;

	public JsonObject(Map<String, JsonElement> mapping) {
		this.mapping = mapping;
	}
	
	public JsonObject() {
		this.mapping = new HashMap<String, JsonElement>();
	}
	
	public Set<String> keys() {
		return mapping.keySet();
	}
	
	public JsonElement get(String key) {
		return mapping.get(key);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(Entry<String, JsonElement> entry : mapping.entrySet()) {
			sb.append(String.format("\"%s\": %s", entry.getKey(), entry.getValue()));
			sb.append(",");
		}
		if (mapping.size()>0) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}");
		return sb.toString();
	}

	public void put(String key, JsonElement value) {
		mapping.put(key, value);
	}
}
