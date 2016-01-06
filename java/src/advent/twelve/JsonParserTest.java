package advent.twelve;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsonParserTest {

	@Test
	public void justNumber() {
		JsonNumber jelem = (JsonNumber)JsonParser.parseJson("12");
		assertEquals(12L, jelem.getNumber().longValue());
				
		jelem = (JsonNumber)JsonParser.parseJson("0");
		assertEquals(0L, jelem.getNumber().longValue());
		
		jelem = (JsonNumber)JsonParser.parseJson("-38273");
		assertEquals(-38273L, jelem.getNumber().longValue());
	}
	
	@Test
	public void justString() {
		JsonString jelem = (JsonString)JsonParser.parseJson("\"abcd\"");
		assertEquals("abcd", jelem.getString());
	}
	
	@Test
	public void emptyArray() {
		JsonArray jelem = (JsonArray)JsonParser.parseJson("[]");
		assertEquals(0, jelem.getArray().size());
	}
	
	@Test
	public void emptyObject() {
		JsonObject jelem = (JsonObject)JsonParser.parseJson("{}");
		assertEquals(0, jelem.keys().size());
	}
	
	@Test
	public void nonNestedObject() {
		JsonObject jelem = (JsonObject)JsonParser.parseJson("{\"a\":1,\"b\":\"str\"}");
		assertEquals(2, jelem.keys().size());
		assertEquals(1L, ((JsonNumber)jelem.get("a")).getNumber().longValue());
		assertEquals("str", ((JsonString)jelem.get("b")).getString());
	}
	
	@Test
	public void multipleNestedObject() {
		JsonObject jelem = (JsonObject)JsonParser.parseJson("{\"a\":{\"b\":{\"c\":2, \"d\":5}, \"e\":{}}}");
		JsonObject a = (JsonObject)jelem.get("a");
		JsonObject b = (JsonObject)a.get("b");
		JsonNumber c = (JsonNumber)b.get("c");
		JsonNumber d = (JsonNumber)b.get("d");
		JsonObject e = (JsonObject)a.get("e");
		
		assertEquals(1L, jelem.keys().size());
		assertEquals(2L, c.getNumber().longValue());
		assertEquals(5L, d.getNumber().longValue());
		assertEquals(0L, e.keys().size());
	}
	
	@Test
	public void objectWithNestedArray() {
		JsonObject jelem = (JsonObject)JsonParser.parseJson("{\"a\":1,\"b\":2,\"c\":[1,1]}");
		JsonNumber a = (JsonNumber)jelem.get("a");
		JsonNumber b = (JsonNumber)jelem.get("b");
		JsonArray c = (JsonArray)jelem.get("c");
		
		assertEquals(1L, a.getNumber().longValue());
		assertEquals(2L, b.getNumber().longValue());
		assertEquals(2, c.getLength());
	}
	
	@Test
	public void nonNestedArray() {
		JsonArray jelem = (JsonArray)JsonParser.parseJson("[1 , \"a\"]");
		assertEquals(1L, ((JsonNumber)jelem.getArray().get(0)).getNumber().longValue());
		assertEquals("a", ((JsonString)jelem.getArray().get(1)).getString());
	}
	
	@Test
	public void arrayWithNestedObjects() {
		JsonArray jelem = (JsonArray)JsonParser.parseJson("[1,{},{\"a\":3}]");
		assertEquals(1L, ((JsonNumber)jelem.getArray().get(0)).getNumber().longValue());
		assertEquals(0, ((JsonObject)jelem.getArray().get(1)).keys().size());
		JsonNumber a = (JsonNumber)((JsonObject)jelem.getArray().get(2)).get("a");
		assertEquals(3L, a.getNumber().longValue());
	}
	
	@Test
	public void nestedArray() {
		JsonArray jelem = (JsonArray)JsonParser.parseJson("[1,[2,[3],[4]]]");
		assertEquals(1L, ((JsonNumber)jelem.getArray().get(0)).getNumber().longValue());
		JsonArray firstNest = (JsonArray)jelem.getArray().get(1);
		assertEquals(2L, ((JsonNumber)firstNest.getArray().get(0)).getNumber().longValue());
		JsonArray secondNest = (JsonArray)firstNest.getArray().get(1);
		JsonArray thirdNest = (JsonArray)firstNest.getArray().get(2);
		assertEquals(3L, ((JsonNumber)secondNest.getArray().get(0)).getNumber().longValue());
		assertEquals(4L, ((JsonNumber)thirdNest.getArray().get(0)).getNumber().longValue());
	}
}
