package advent.twelve;

public class JsonString implements JsonElement {

	@Override
	public JsonType getJsonType() {
		return JsonType.STRING;
	}
	
	public JsonString() {
		string = "";
	}
	
	public JsonString(String str) {
		string = str;
	}
	
	private final String string;

	public String getString() {
		return string;
	}

	@Override
	public String toString() {
		return String.format("\"%s\"", string);
	}
}
