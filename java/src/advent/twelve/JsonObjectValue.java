package advent.twelve;

public class JsonObjectValue implements JsonElement {

	@Override
	public JsonType getJsonType() {
		return JsonType.OBJECTVALUE;
	}

	public JsonObjectValue(JsonElement elem) {
		this.elem = elem;
	}
	
	public JsonObjectValue() {
		this.elem = null;
	}
	
	private final JsonElement elem;

	public JsonElement getElem() {
		return elem;
	}
	
	@Override
	public String toString() {
		return String.format("%s", elem);
	}
}
