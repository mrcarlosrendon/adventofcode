package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ten {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
						
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
						
			String answer = line;
			for (int i=0; i<50; i++) {				
				if (i == 39) {
					System.out.println("Part 1:");
					answer = apply(answer, true);					
				}
				else if (i == 49) {
					System.out.println("Part 2:");
					answer = apply(answer, true); // Not sure why I get wrong answer for 50 times
				}
				else {
					answer = apply(answer, false);					
				}				
			}
			break;
		}		
		
	}
	
	public static String apply(String input, boolean printLength) {
		StringBuffer sb = new StringBuffer();
		char lastChar = ' ';
		int lastCount = 0;
		input += "A"; // TERMINATING CHAR
		int lengthRes = 0;
		for (int i=0; i<input.length(); i++) {
			char c = input.charAt(i);				
			if (lastCount > 0 && c != lastChar) {
				sb.append(lastCount + "" +  lastChar);	
				lengthRes+=2;
				lastCount=1;
			}
			else {
				lastCount++;
			}
			lastChar = c;
		}
		if(printLength) {
			System.out.println(lengthRes);
		}
		return sb.toString();
	}
}
