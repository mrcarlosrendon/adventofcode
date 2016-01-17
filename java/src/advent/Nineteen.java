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
		//part1(input, rules);
		
		String goal = input;
		Set<String> inputs = new HashSet<>();
		String start = "e";
		inputs.add(start);
		//System.out.println(part2_bfs(rules, inputs, goal, 1));
		int depth = 1;
		do {
			System.out.println("DEBUG: searching with maxDepth: " + depth);
			int res = part2_dfids(rules, start, goal, 1, depth);
			if (res < 0) {
				depth++;
			}
			else {
				System.out.println(res);
				break;
			}
		} while (true);
	}	
	
	public static int part2_dfids(final List<ReplacementRule> rules, final String input, final String goal, final int currDepth, final int maxDepth) {
		if (currDepth > maxDepth) {
			return -1;
		}
		for(int i=0; i<=input.length(); i++) {
			for(ReplacementRule rule : rules) {
				if (rule.from.length() <= i) {
					int replaceStart = i-rule.from.length();
					int replaceEnd = i;
					String before = input.substring(0, replaceStart);
					String toReplace = input.substring(replaceStart, replaceEnd);
					String after = input.substring(replaceEnd, input.length()); 
					if (toReplace.equals(rule.from)) {
						String newMolecule = before + rule.to + after;
						/*if (currDepth == maxDepth) {
							System.out.println("DEBUG: " + newMolecule);
						}*/
						if(goal.equals(newMolecule)) {
							return currDepth;
						}
						int res = part2_dfids(rules, newMolecule, goal, currDepth+1, maxDepth);
						if (res > 0) {
							return res;
						}
					}
				}
			}
		}
		return -1;
	}
	
	public static int part2_bfs(List<ReplacementRule> rules, Set<String> inputs, final String goal, int depth) {
		System.out.println("DEBUG: " + depth + " inputs size: " + inputs.size());
		Set<String> nextLevelInputs = new HashSet<String>();
		for(String input : inputs) {
			for(int i=0; i<=input.length(); i++) {
				for(ReplacementRule rule : rules) {
					if (rule.from.length() <= i) {
						int replaceStart = i-rule.from.length();
						int replaceEnd = i;
						String before = input.substring(0, replaceStart);
						String toReplace = input.substring(replaceStart, replaceEnd);
						String after = input.substring(replaceEnd, input.length()); 
						if (toReplace.equals(rule.from)) {
							String newMolecule = before + rule.to + after;
							//System.out.println("DEBUG: " + depth + ": " + newMolecule);
							if(goal.equals(newMolecule)) {
								return depth;
							}						
							nextLevelInputs.add(newMolecule);
						}
					}
				}
			}
		}
		
		if (nextLevelInputs.size() == 0) {
			throw new RuntimeException("Not possible to meet goal");
		}
		
		return part2_bfs(rules, nextLevelInputs, goal, depth+1);
	}
	
	public static void part1(String input, List<ReplacementRule> rules) {
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
