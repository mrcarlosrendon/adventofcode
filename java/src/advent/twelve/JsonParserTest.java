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
	public void nonNestedArray() {
		fail("todo");
	}
	
	@Test
	public void arrayWithNestedObjects() {
		fail("todo");
	}
	
	@Test
	public void nestedArray() {
		fail("todo");
	}

}