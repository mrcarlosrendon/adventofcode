package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eleven {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			String nextPassword = nextPassword(line.toCharArray());
			System.out.println("Next Password: " + nextPassword);
			String nextnextPassword = nextPassword(nextPassword.toCharArray());
			System.out.println("Next Next Password: " + nextnextPassword);
			break;
		}
	}
	
	public static String nextPassword(char[] password) {
		int currCharIndex = 8-1;
		char[] currPassword = password;
		while(true) {
			if (currCharIndex == -1) {
				break;
			}
			char next = (char)(currPassword[currCharIndex] + 1);			
			if (Character.getType(next) != Character.LOWERCASE_LETTER) {
				currPassword[currCharIndex] = 'a';
				currCharIndex--;				
			}			
			else {
				currPassword[currCharIndex] = next;
				if (currCharIndex != 8-1) {
					currCharIndex = 8-1;
				}
			}
			//System.out.println(currPassword);
			if (noForbiddenChars(currPassword)
					&& oneIncreasingStraightOfThree(password)
					&& twoDifferentNonOverlappingPairs(currPassword)
					) {
				return new String(currPassword);
			}			
		}
		return "error";
	}	
	
	public static boolean noForbiddenChars(char[] password) {
		for(int i=0; i<password.length; i++) {
			if (password[i] == 'i' || password[i] == 'o' || password[i] == 'l')
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean oneIncreasingStraightOfThree(char[] password) {		
		for(int i=2; i<password.length; i++) {
			char a = password[i-2];
			char b = password[i-1];
			char c = password[i];
			int aVal = Character.getNumericValue(a);
			int bVal = Character.getNumericValue(b);
			int cVal = Character.getNumericValue(c);
			if (aVal == bVal-1 && bVal == cVal-1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean twoDifferentNonOverlappingPairs(char[] password) {
		int lastCharVal = 0;
		int count = 0;
		for(int i=1; i<password.length; i++) {
			int c = Character.getNumericValue(password[i]);						
			
			if (c == lastCharVal) {
				count++;
				lastCharVal = 0;
			}
			else {			
				lastCharVal = c;
			}
		}
		if (count >= 2) {
			return true;
		}
		return false;
	}
}
