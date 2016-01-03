package advent.twelve;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonElement {

	@Override
	public JsonType getJsonType() {
		return JsonType.ARRAY;
	}
	
	private List<JsonElement> array;
	
	public JsonArray(List<JsonElement> array) {
		this.array = array;
	}
	
	public JsonArray() {
		this.array = new ArrayList<JsonElement>();
	}
	
	public JsonElement getElement(int position) {
		return array.get(position);
	}
	
	public int getLength() {
		return array.size();
	}
	
	public void add(JsonElement elem) {
		this.array.add(elem);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for( JsonElement elem : array) {
			sb.append(elem);
			sb.append(",");
		}
		if (!array.isEmpty()) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");		
		return sb.toString();
	}
}
