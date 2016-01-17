package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nineteen {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		List<ReplacementRule> rules = new ArrayList<ReplacementRule>();
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}			
			String[] split = line.split(" => ");
			String from = split[0];
			String to = split[1];
			rules.add(new ReplacementRule(from, to));
		}
		
		String input = br.readLine();
		Set<String> newMolecules = new HashSet<String>();
		for(int i=0; i<=input.length(); i++) {
			for(ReplacementRule rule : rules) {
				if (rule.from.length() <= i) {
					int replaceStart = i-rule.from.length();
					int replaceEnd = i;
					String before = input.substring(0, replaceStart);
					String toReplace = input.substring(replaceStart, replaceEnd);
					String after = input.substring(replaceEnd, input.length()); 
					if (toReplace.equals(rule.from)) {
						newMolecules.add(before + rule.to + after);
						System.out.println("DEBUG: " + before + rule.to + after);
					}
				}
			}
		}
		System.out.println(newMolecules.size());
	}
	
	public static class ReplacementRule {
		final public String from;
		final public String to;
		public ReplacementRule(String from, String to) {			
			this.from = from;
			this.to = to;
		}
	}
}
