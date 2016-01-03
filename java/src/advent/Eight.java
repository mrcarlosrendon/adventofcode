package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eight {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
				
		int totalChars = 0;
		int memoryChars = 0;
		
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			totalChars += line.length();
			
			char lastChar = 'a';
			int hexPos = 0;
			for(int i=0; i<line.length(); i++) {
				char c = line.charAt(i);
				if (hexPos > 0 && hexPos < 2) {
					hexPos++;
				} 
				else if (hexPos == 2) {
					hexPos = 0;
					memoryChars++;
					System.out.print(line.charAt(i-3));
					System.out.print(line.charAt(i-2));
					System.out.print(line.charAt(i-1));
					System.out.println(line.charAt(i));
				}
				else if (lastChar == '\\') {
					if (c == 'x') {
						hexPos = 1;
					}	
					else {
						memoryChars++;
						c = 'a'; // reset lastChar
						System.out.print(line.charAt(i-1));
						System.out.println(line.charAt(i));
					}
				}
				else if (c == '"') {
					// nop
				}
				else if (c == '\\') {
					// nop
				}
				else {
					memoryChars++;
					System.out.println(c);
				}
				lastChar = c;
			}
		
		}		
		System.out.println("Total Chars: " + totalChars);
		System.out.println("Total Memory Chars: " + memoryChars);
		System.out.println("Overhead = " + (totalChars - memoryChars));
	}
}
