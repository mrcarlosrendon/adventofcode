package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import advent.twelve.*;

public class Twelve {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		JsonElement json = JsonParser.parseJson(line);
		System.out.println("Root Element: " + json.getJsonType() + " " + json);
		System.out.println("Sum of Numbers: " + sumOfNumbers(json));
	}
	
	public static long sumOfNumbers(JsonElement json) {
		long sum = 0;
		switch(json.getJsonType()){
		case ARRAY:
			for (JsonElement jelem : ((JsonArray)json).getArray()) {
				sum += sumOfNumbers(jelem);
			}
			break;
		case NUMBER:
			sum = ((JsonNumber)json).getNumber();
			break;
		case OBJECT:
			JsonObject jobj = (JsonObject)json;
			for(String key : jobj.keys()) {
				sum += sumOfNumbers(jobj.get(key));
			}
			break;
		case STRING:
			sum = 0;
			break;
		default:
			throw new RuntimeException("Unexpected json type encountered: " + json.getJsonType());
		}
		return sum;
	}
}
