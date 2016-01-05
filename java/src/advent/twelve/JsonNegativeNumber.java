package advent.twelve;

public class JsonNegativeNumber extends JsonNumber {
	public JsonNegativeNumber(long number) {
		super(number);		
	}

	@Override
	public JsonType getJsonType() {
		return JsonType.NEGATIVENUMBER;
	}
}
