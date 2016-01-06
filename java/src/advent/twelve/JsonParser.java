package advent.twelve;

import java.util.ArrayDeque;
import java.util.Deque;

public class JsonParser {

	public static JsonElement parseJson(String jsonStr) {
		Deque<JsonElement> parsingHierarchy = new ArrayDeque<JsonElement>();
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			JsonType currentType = null;
			if (parsingHierarchy.size() > 0) {
				currentType = parsingHierarchy.peekFirst().getJsonType();
			}

			if (currentType == null || (currentType == JsonType.ARRAY && c != ']' && c != ',')
					|| (currentType == JsonType.OBJECTVALUE && c != '}' && c != ',' && c != ':')) {
				if (c == '[') {
					parsingHierarchy.push(new JsonArray());
				} else if (c == '{') {
					parsingHierarchy.push(new JsonObject());
				} else if (("" + c).matches("[0-9]")) {
					parsingHierarchy.push(new JsonNumber(Long.parseLong("" + c)));
				} else if (c == '-') {
					parsingHierarchy.push(new JsonNegativeNumber(0));
				} else if (c == '"') {
					parsingHierarchy.push(new JsonString());
				} else if (c == ' ') {
					// ignore
				}
				else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.OBJECT) {
				if (c == '}') {
					addToParent(parsingHierarchy, parsingHierarchy.pop(), c);
				} else if (c == '"') {
					parsingHierarchy.push(new JsonObjectKey());
				} else if (c == ',' || c == ' ') {
					// skip
				} else if (c == '{') {
					parsingHierarchy.push(new JsonObject());
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.OBJECTKEY) {
				if (c == '"') {
					parsingHierarchy.push(new JsonObjectValue());
				} else if (("" + c).matches("[a-zA-Z]")) {
					JsonObjectKey key = (JsonObjectKey) parsingHierarchy.pop();
					parsingHierarchy.push(new JsonObjectKey(key.getString() + c));
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.OBJECTVALUE) {
				if (c == '}') {
					JsonObjectValue value = (JsonObjectValue) parsingHierarchy.pop();
					JsonObjectKey key = (JsonObjectKey) parsingHierarchy.pop();
					JsonObject obj = (JsonObject) parsingHierarchy.pop();
					obj.put(key.getString(), value.getElem());
					addToParent(parsingHierarchy, obj, c);
				} else if (c == ',') {
					JsonObjectValue value = (JsonObjectValue) parsingHierarchy.pop();
					JsonObjectKey key = (JsonObjectKey) parsingHierarchy.pop();
					JsonObject obj = (JsonObject) parsingHierarchy.peekFirst();
					obj.put(key.getString(), value.getElem());
				} else if (c == ':') {
					// ignore
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.ARRAY) {
				if (c == ']') {
					addToParent(parsingHierarchy, parsingHierarchy.pop(), c);
				} else if (c == ',') {
					// ignore
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.NUMBER) {
				if (c == ',' || c == ']' || c == '}') {
					addToParent(parsingHierarchy, parsingHierarchy.pop(), c);
				} else if (("" + c).matches("[0-9]")) {
					parsingHierarchy.push(new JsonNumber(Long.parseLong(((JsonNumber) parsingHierarchy.pop()).getNumber().toString()+c)));
				} else if (c == ' ') {
					// skip
				}
				else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.NEGATIVENUMBER) {
				if (c == ',' || c == ']' || c == '}') {
					JsonNegativeNumber num = (JsonNegativeNumber) parsingHierarchy.pop();
					addToParent(parsingHierarchy, new JsonNumber(-num.getNumber()), c);
				} else if (("" + c).matches("[0-9]")) {
					parsingHierarchy.pop();
					parsingHierarchy.push(new JsonNumber(-Long.parseLong("" + c)));
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			} else if (currentType == JsonType.STRING) {
				if (c == '"') {
					addToParent(parsingHierarchy, parsingHierarchy.pop(), c);
				} else if (("" + c).matches("[a-zA-Z]")) {
					JsonString str = (JsonString) parsingHierarchy.pop();
					parsingHierarchy.push(new JsonString(str.getString() + c));
				} else {
					throw new RuntimeException("Invalid JSON at character: " + c + " position " + i);
				}
			}
		}
		return parsingHierarchy.pop();
	}

	private static void addToParent(Deque<JsonElement> parsingHierarchy, JsonElement toAdd, char currentChar) {
		JsonElement elem = parsingHierarchy.peekFirst();
		if (elem == null) {
			parsingHierarchy.push(toAdd);
		} else if (elem.getJsonType() == JsonType.ARRAY) {
			((JsonArray) elem).add(toAdd);
			if (currentChar == ']') {
				addToParent(parsingHierarchy, parsingHierarchy.pop(), ' ');
			}
			
		} else if (elem.getJsonType() == JsonType.OBJECTVALUE) {
			if (currentChar == '}') {
				parsingHierarchy.pop();
				JsonObjectValue value = new JsonObjectValue(toAdd);
				JsonObjectKey key = (JsonObjectKey) parsingHierarchy.pop();
				JsonObject obj = (JsonObject) parsingHierarchy.peekFirst();
				obj.put(key.getString(), value.getElem());
				addToParent(parsingHierarchy, parsingHierarchy.pop(), ' ');
			} else if (currentChar == ',') {
				parsingHierarchy.pop();
				JsonObjectValue value = new JsonObjectValue(toAdd);
				JsonObjectKey key = (JsonObjectKey) parsingHierarchy.pop();
				JsonObject obj = (JsonObject) parsingHierarchy.peekFirst();
				obj.put(key.getString(), value.getElem());
			} else {
				parsingHierarchy.pop();
				parsingHierarchy.push(new JsonObjectValue(toAdd));
			}
		} else {
			throw new RuntimeException("Unexpected parent type: " + elem.getJsonType());
		}
	}

}
