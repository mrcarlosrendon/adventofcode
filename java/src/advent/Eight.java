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
		int encodedChars = 0;
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			totalChars += line.length();
			memoryChars += memoryCharacters(line);
			encodedChars += encodedCharacters(line);
		}		
		System.out.println("Total Chars: " + totalChars);
		System.out.println("Total Memory Chars: " + memoryChars);
		System.out.println("Total Encoded Chars: " + encodedChars);
		System.out.println("Memory Overhead (part1) = " + (totalChars - memoryChars));
		System.out.println("Encoding Overhead (part2) = " + (encodedChars - totalChars));
	}
	
	private static int encodedCharacters(String line) {
		boolean DEBUGENCODEDCHARS = false;
		int total = 2; // starting and ending quotes
		if (DEBUGENCODEDCHARS) {
			System.out.println('"');
		}
		for(int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			
			if (c == '"') {
				total += 2;
				if (DEBUGENCODEDCHARS) {
					System.out.println("\\\"");
				}
			}
			else if (c == '\\') {
				total += 2;
				if (DEBUGENCODEDCHARS) {
					System.out.println("\\\\");
				}
			}
			else {
				total += 1;
				if (DEBUGENCODEDCHARS) {
					System.out.println(c);
				}
			}
		}
		if (DEBUGENCODEDCHARS) {
			System.out.println('"');
		}
		return total;
	}

	public static int memoryCharacters(String line) {
		boolean DEBUGMEMORYCHARS = false;
		int memoryChars = 0;
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
				if (DEBUGMEMORYCHARS) {
					System.out.print(line.charAt(i-3));
					System.out.print(line.charAt(i-2));
					System.out.print(line.charAt(i-1));
					System.out.println(line.charAt(i));
				}
			}
			else if (lastChar == '\\') {
				if (c == 'x') {
					hexPos = 1;
				}	
				else {
					memoryChars++;
					c = 'a'; // reset lastChar
					if (DEBUGMEMORYCHARS) {
						System.out.print(line.charAt(i-1));
						System.out.println(line.charAt(i));
					}
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
				if (DEBUGMEMORYCHARS) {
					System.out.println(c);
				}
			}
			lastChar = c;
		}
		return memoryChars;
	}
}
