package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sixteen {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;		
		Set<Sue> sues = new HashSet<Sue>();		
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}
			String[] split = line.split(" ");			
			int number = Integer.parseInt(split[1].split(":")[0]);			
			Map<String, Integer> attribs = new HashMap<String, Integer>();
			attribs.put(split[2].split(":")[0], Integer.parseInt(split[3].split(",")[0]));
			attribs.put(split[4].split(":")[0], Integer.parseInt(split[5].split(",")[0]));
			attribs.put(split[6].split(":")[0], Integer.parseInt(split[7].split(",")[0]));			
			sues.add(new Sue(number, attribs));
		}
		
		sues = filter(sues, "children", 3);
		sues = filter(sues, "cats", 7);
		sues = filter(sues, "samoyeds", 2);
		sues = filter(sues, "pomeranians", 3);		
		sues = filter(sues, "akitas", 0);
		sues = filter(sues, "vizslas", 0);
		sues = filter(sues, "goldfish", 5);
		sues = filter(sues, "trees", 3);
		sues = filter(sues, "cars", 2);
		sues = filter(sues, "perfumes", 1);
		
		for(Sue sue : sues) {
			System.out.println(sue.number);
		}
	}	
	
	public static Set<Sue> filter(Set<Sue> filter, String attrib, int value) {
		Set<Sue> ret = new HashSet<>();		
		for (Sue sue : filter) {
			if (sue.attribs.containsKey(attrib)) {
				if (sue.attribs.get(attrib) == value) {
					ret.add(sue);
				}				
			}
			else {
				ret.add(sue);
			}
		}
		return ret;
	}
	
	public static class Sue {
		public final int number;
		public final Map<String, Integer> attribs;
		public Sue(int number, Map<String, Integer> attribs) {			
			this.number = number;
			this.attribs = attribs;
		}		
	}
}
