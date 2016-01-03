package advent.twelve;

public class JsonNumber implements JsonElement {

	@Override
	public JsonType getJsonType() {
		return JsonType.NUMBER;
	}
	
	public JsonNumber(long number) {
		this.number = number;
	}

	private final Long number;

	public Long getNumber() {
		return number;
	}
	
	@Override
	public String toString() {
		return number.toString();
	}
}
