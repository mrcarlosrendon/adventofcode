package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Six {

	public final static int SIZE = 1000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;	
		final boolean[][] lights = new boolean[SIZE][SIZE];
		turnOff(0,SIZE-1,0,SIZE-1, lights);
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			
			if (line.contains("on")) {
				String[] split = line.split(" ");
				String[] starts = split[2].split(",");
				String[] ends = split[4].split(",");				
				turnOn(Integer.parseInt(starts[0]), 
						Integer.parseInt(ends[0]), 
						Integer.parseInt(starts[1]), 
						Integer.parseInt(ends[1]), 
						lights);
			}
			else if (line.contains("off")) {
				String[] split = line.split(" ");
				String[] starts = split[2].split(",");
				String[] ends = split[4].split(",");				
				turnOff(Integer.parseInt(starts[0]), 
						Integer.parseInt(ends[0]), 
						Integer.parseInt(starts[1]), 
						Integer.parseInt(ends[1]), 
						lights);
			}
			else if (line.contains("toggle")) {
				String[] split = line.split(" ");
				String[] starts = split[1].split(",");
				String[] ends = split[3].split(",");				
				toggle(Integer.parseInt(starts[0]), 
						Integer.parseInt(ends[0]), 
						Integer.parseInt(starts[1]), 
						Integer.parseInt(ends[1]), 
						lights);
			}
			else {
				throw new RuntimeException("Didn't parse");
			}
			//print(lights);
			System.out.println("on - " + countOn(lights) + " - total count - " + totalCount(lights));
		}		
		System.out.println("on - " + countOn(lights));		
	}	
	
	public static int countOn(final boolean[][] lights) {
		int count = 0;		
		for (int i=0; i<lights.length; i++) {
			for(int j=0; j<lights[0].length; j++) {
				if (lights[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static int totalCount(final boolean[][] lights) {
		int count = 0;		
		for (int i=0; i<lights.length; i++) {
			for(int j=0; j<lights[0].length; j++) {				
				count++;				
			}
		}
		return count;
	}
	
	public static void print(final boolean[][] lights) {
		for (int i=0; i<lights.length; i++) {
			for(int j=0; j<lights[0].length; j++) {
				if (lights[i][j]) {
					System.out.print(".");
				}
				else {
					System.out.print("x");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void turnOn(int x1, int x2, int y1, int y2, final boolean[][] lights) {
		int lowerX = Math.min(x1, x2);
		int upperX = Math.max(x1, x2);
		int lowerY = Math.min(y1, y2);
		int upperY = Math.max(y1, y2);		
		for(int i=lowerX; i<=upperX; i++) {
			for(int j=lowerY; j<=upperY; j++) {
				lights[i][j] = true;
			}
		}
	}
	
	public static void turnOff(int x1, int x2, int y1, int y2, final boolean[][] lights) {
		int lowerX = Math.min(x1, x2);
		int upperX = Math.max(x1, x2);
		int lowerY = Math.min(y1, y2);
		int upperY = Math.max(y1, y2);		
		for(int i=lowerX; i<=upperX; i++) {
			for(int j=lowerY; j<=upperY; j++) {
				lights[i][j] = false;
			}
		}
	}
	
	public static void toggle(int x1, int x2, int y1, int y2, final boolean[][] lights) {
		int lowerX = Math.min(x1, x2);
		int upperX = Math.max(x1, x2);
		int lowerY = Math.min(y1, y2);
		int upperY = Math.max(y1, y2);		
		for(int i=lowerX; i<=upperX; i++) {
			for(int j=lowerY; j<=upperY; j++) {
				lights[i][j] = !lights[i][j];
			}
		}
	}
}
