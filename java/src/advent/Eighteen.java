package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eighteen {
		
	public static final int GRID_SIZE = 100;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		boolean[][] lights = new boolean[GRID_SIZE][GRID_SIZE];
		int lineCount = 0;
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}
			
			for(int i=0; i<line.length(); i++) {
				if (line.charAt(i) == '.' ) {
					lights[lineCount][i] = false;
				}
				else if (line.charAt(i) == '#') {
					lights[lineCount][i] = true;
				}
				else {
					throw new RuntimeException("Unrecognized Character: " + line.charAt(i));
				}
			}
			lineCount++;
		}
		// part 2, make sure starting corners set on
		lights[0][0] = true;
		lights[0][lights.length-1] = true;
		lights[lights.length-1][0] = true;
		lights[lights.length-1][lights.length-1] = true;
		
		for(int i=0; i<100; i++) {			
			lights = nextState(lights);
			print(lights);
			System.out.println(i+1);
			System.out.println("on: " + countLightsOn(lights));
		}
	}
	
	public static int countLightsOn(boolean[][] lights) {
		int count=0;
		for (boolean[] row : lights) {
			for(boolean light : row) {
				if (light) {
					count++;
				}				
			}
		}
		return count;
	}
	
	public static void print(boolean[][] lights) {
		for (boolean[] row : lights) {
			for(boolean light : row) {
				if (light) {
					System.out.print("#");
				}
				else {
					System.out.print(".");
				}
			}
			System.out.println("");
		}
	}
	
	public static boolean[][] nextState(boolean[][] lights) {
		boolean[][] next = new boolean[lights.length][lights.length];
		for (int r=0; r<lights.length; r++) {
			for(int c=0; c<lights.length; c++) {
				int count = countNeighborsOn(r, c, lights);
				// part 2 rules
				if (r == 0 && c == 0) {
					next[r][c] = true;
				}
				else if (r == 0 && c == lights.length - 1) {
					next[r][c] = true;
				}
				else if (r == lights.length - 1 && c == 0) {
					next[r][c] = true;
				}
				else if (r == lights.length - 1 && c == lights.length - 1) {
					next[r][c] = true;
				} // end part 2 rules
				else if (lights[r][c]) {
					if (count == 2 || count == 3) {
						next[r][c] = true;
					}
					else {
						next[r][c] = false;
					}
				}
				else {
					if (count == 3) {
						next[r][c] = true;
					}
					else {
						next[r][c] = false;
					}
				}
			}
		}
		return next;
	}
	
	public static int countNeighborsOn(int row, int col, boolean[][] lights) {
		int count = 0;
		if (row > 0 && col > 0) {
			if (lights[row-1][col-1]) {
				count++;
			}
		}
		if (row > 0) {
			if (lights[row-1][col]) {
				count++;
			}
		}
		if (row > 0 && col+1 < lights.length) {
			if (lights[row-1][col+1]) {
				count++;
			}
		}
		if (col > 0) {
			if (lights[row][col-1]) {
				count++;
			}
		}
		if (col+1 < lights.length) {
			if (lights[row][col+1]) {
				count++;
			}
		}
		if (row + 1 < lights.length && col > 0) {
			if (lights[row+1][col-1]) {
				count++;
			}
		}
		if (row + 1 < lights.length) {
			if (lights[row+1][col]) {
				count++;
			}
		}
		if (row + 1 < lights.length && col + 1 < lights.length) {
			if (lights[row+1][col+1]) {
				count++;
			}
		}
		return count;
	}	
}
