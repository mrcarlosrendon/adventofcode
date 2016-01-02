package advent;

import static org.junit.Assert.*;

import org.junit.Test;

public class SixTest {

	@Test
	public void testOn() {
		boolean[][] lights = new boolean[3][3];
		Six.turnOn(0, 0, 0, 0, lights);
		assertEquals(1, Six.countOn(lights));
				
		Six.turnOn(0, 0, 0, 0, lights);
		assertEquals(1, Six.countOn(lights));
		
		Six.turnOn(0, 1, 0, 1, lights);
		assertEquals(4, Six.countOn(lights));
		
		Six.turnOn(0, 2, 0, 2, lights);
		assertEquals(9, Six.countOn(lights));
	}
	
	@Test
	public void testOff() {
		boolean[][] lights = new boolean[3][3];
		Six.turnOn(0, 2, 0, 2, lights);
		Six.turnOff(0, 0, 0, 0, lights);
		assertEquals(8, Six.countOn(lights));
				
		Six.turnOff(0, 0, 0, 0, lights);
		assertEquals(8, Six.countOn(lights));
		
		Six.turnOn(0, 2, 0, 2, lights);
		Six.turnOff(0, 1, 0, 1, lights);
		assertEquals(5, Six.countOn(lights));
		
		Six.turnOff(0, 2, 0, 2, lights);
		assertEquals(0, Six.countOn(lights));
	}
	
	@Test
	public void testToggle() {
		boolean[][] lights = new boolean[3][3];
		Six.toggle(0, 0, 0, 0, lights);
		assertEquals(1, Six.countOn(lights));
				
		Six.toggle(0, 0, 0, 0, lights);
		assertEquals(0, Six.countOn(lights));
		
		Six.toggle(0, 1, 0, 1, lights);
		assertEquals(4, Six.countOn(lights));
		
		Six.toggle(0, 2, 0, 2, lights);
		assertEquals(5, Six.countOn(lights));
		Six.print(lights);
		
		Six.toggle(0, 2, 0, 2, lights);
		assertEquals(4, Six.countOn(lights));
		Six.print(lights);
		
		Six.toggle(2, 0, 2, 0, lights);
		assertEquals(5, Six.countOn(lights));
		Six.print(lights);
	}

}
