package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class One {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		int floor = 0;
		int charPositionEntersBasement = 0;
		while((line = br.readLine()) != null) {			
			for(int c=0; c<line.length(); c++) {
				if (line.charAt(c) == '(') {
					floor++;
				}
				else if (line.charAt(c) == ')') {
					floor--;
				}
				
				if (floor<0 && charPositionEntersBasement == 0) {
					charPositionEntersBasement = c + 1; // first character is position 1
				}
			}
		}
		
		System.out.println("Floor: " + floor);
		System.out.println("First enters basement at char: " + charPositionEntersBasement);
	}
}
