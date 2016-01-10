package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Thirteen {
		
	public static List<Rule> rules;
	public static int bestScore = 0;
	public static List<String> bestOrder;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		Set<String> names = new HashSet<String>();
		rules = new ArrayList<Rule>();
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}
			String[] split = line.split(" ");
			String startName = split[0];
			String endName = split[10].substring(0, split[10].length()-1);
			String loseGain = split[2];
			String amount = split[3];
			int ruleAmount = Integer.parseInt(amount);
			if (loseGain.equals("lose")) {
				ruleAmount = -ruleAmount;
			}			
			names.add(startName);
			names.add(endName);
			rules.add(new Rule(startName, endName, ruleAmount));
		}		
		List<String> namesLst = new ArrayList<String>();
		namesLst.addAll(names);
		createOrders(namesLst, new ArrayList<String>());
		
		// part 2
		namesLst.add("self");
		bestScore = 0;
		bestOrder = null;
		createOrders(namesLst, new ArrayList<String>());		
	}
	
	public static void createOrders(List<String> names, List<String> currOrder) {
		if (currOrder.size() == names.size()) {
			//System.out.println(currOrder);
			int score = 0;
			for(int i=1; i<currOrder.size(); i++) {
				score += adjust(currOrder.get(i), currOrder.get(i-1));
			}
			score += adjust(currOrder.get(0), currOrder.get(currOrder.size()-1));
			//System.out.println("DEBUG: " + score + " " + currOrder);
			if (score > bestScore) {
				bestScore = score;
				bestOrder = new ArrayList<String>();
				bestOrder.addAll(currOrder);
				System.out.println("New Best: " + bestScore + " " + bestOrder);
			}
		}
		else {
			for(String name : names) {
				if (!currOrder.contains(name)) {
					currOrder.add(name);
					createOrders(names, currOrder);
					currOrder.remove(name);
				}
			}
		}
	}
	
	public static int adjust(String namea, String nameb) {
		int total = 0;
		for(Rule rule : rules) {
			if (	(rule.name1.equals(namea) && rule.name2.equals(nameb)) ||
					(rule.name1.equals(nameb) && rule.name2.equals(namea))) {
				//System.out.println("DEBUG: applied " + rule);
				total += rule.amount;
			}
		}
		return total;
	}
	
	public static class Rule {
		final String name1;
		final String name2;
		final int amount;
		
		public Rule(String name1, String name2, int amount) {		
			this.name1 = name1;
			this.name2 = name2;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Rule [name1=" + name1 + ", name2=" + name2 + ", amount=" + amount + "]";
		}
	}
}
