package advent.twelve;

public class JsonObjectKey extends JsonString {

	public JsonObjectKey(String string) {
		super(string);
	}

	public JsonObjectKey() {
		super();
	}

	@Override
	public JsonType getJsonType() {
		return JsonType.OBJECTKEY;
	}	
}
