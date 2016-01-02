package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Five {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		int niceCount = 0;
		
		while ((line = br.readLine()) != null) {
			String word = line;
			
			/*
			if (countUniqueVowels(word) >= 3) {
				if (letterTwiceInARow(word)) {
					if (!containsIllegalSequence(word)) {
						System.out.println(word + " - nice!");
						niceCount++;
					}
				}
			}*/
			
			if (repeatsWithExactlyOneLetterInBetween(word)) {
				if (hasPairOfLettersThatAppearsAtLeastTwiceWithoutOverlapping(word)) {
					System.out.println(word + " - nice!");
					niceCount++;
				}
			}
		}		
		System.out.println("nice count: " + niceCount);
	}
		
	
	public static boolean repeatsWithExactlyOneLetterInBetween(String s) {
		for (int i=2; i<s.length(); i++) {
			char a = s.charAt(i-2);			
			char b = s.charAt(i);
			if (a == b) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasPairOfLettersThatAppearsAtLeastTwiceWithoutOverlapping(String s) {
		for (int i=1; i<s.length(); i++) {
			char a = s.charAt(i-1);			
			char b = s.charAt(i);
			for (int j=i+2; j<s.length(); j++) {
				char ja = s.charAt(j-1);
				char jb = s.charAt(j);				
				if (ja == a && jb == b) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static int countUniqueVowels(String s) {
		String[] vowels = { "a", "e", "i", "o", "u" };
		int count = 0;
		for(int i=0; i<s.length(); i++) {
			char currChar = s.charAt(i);			
			for(String vowel : vowels) {
				if (vowel.charAt(0) == currChar) {
					count++;
					break;
				}
			}			
		}
		return count;
	}
	
	public static boolean letterTwiceInARow(String s) {
		char lastChar = '.'; // assuming this is kosher
		for(int i=0; i<s.length(); i++) {
			char currChar = s.charAt(i);
			if (lastChar == currChar) {
				return true;
			}
			lastChar = currChar;
		}
		return false;
	}
	
	public static boolean containsIllegalSequence(String s) {
		String[] badSequences = { "ab", "cd", "pq", "xy" };		
		for (String badSequence : badSequences) {
			if (s.contains(badSequence)) {
				return true;
			}
		}
		return false;
	}
}
