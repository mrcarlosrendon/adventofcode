package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Three {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		HashMap<Coordinate, Integer> houses = new HashMap<Coordinate, Integer>();
		
		Coordinate currSanta = new Coordinate(0,0);
		Coordinate currRoboSanta = new Coordinate(0,0);
		visitCoordinate(currSanta, houses);
		
		while((line = br.readLine()) != null) {
			for(int c=0; c<line.length(); c++) {
				if (c%2==0) {
					currSanta = updateCoordinate(line.charAt(c), currSanta);
					visitCoordinate(currSanta, houses);
				}
				else {
					currRoboSanta = updateCoordinate(line.charAt(c), currRoboSanta);
					visitCoordinate(currRoboSanta, houses);
				}
			}
		}
		
		System.out.println("Houses with 1 present: " + houses.keySet().size());
	}
	
	public static Coordinate updateCoordinate(char c, Coordinate currLoc) {
		if (c == '>') {
			return new Coordinate(currLoc.x+1, currLoc.y);
		}
		else if (c == '<') {
			return new Coordinate(currLoc.x-1, currLoc.y);
		}				
		else if (c == '^') {
			return new Coordinate(currLoc.x, currLoc.y+1);
		}
		else if (c == 'v') {
			return new Coordinate(currLoc.x, currLoc.y-1);
		}
		throw new RuntimeException("unexpected character");
	}
	
	public static void visitCoordinate(Coordinate c, HashMap<Coordinate, Integer> houses) {
		System.out.println(String.format("Visiting Coodinate: (%d, %d)", c.x, c.y));
		if (houses.containsKey(c)) {
			houses.put(c, houses.get(c) + 1);
		}
		else {
			houses.put(c, 1);
		}
	}
	
	public static class Coordinate {
		public int x;
		public int y;
		public Coordinate(int x, int y) {			
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinate other = (Coordinate) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
}
