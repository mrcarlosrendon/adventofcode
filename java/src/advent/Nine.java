package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nine {
	
	static int minDistance = Integer.MAX_VALUE;
	static String bestRouteSoFar = "";
	
	static int maxDistance = 0;
	static String longestRouteSoFar = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		List<Path> paths = new ArrayList<Path>(); 
		
		while((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			
			String[] split = line.split(" ");
			String source = split[0];
			String dest = split[2];
			int distance = Integer.parseInt(split[4]);
			paths.add(new Path(source, dest, distance));
			
		}		
		pathDistance(paths, 0, "");
	}
	
	public static void pathDistance(List<Path> paths, int currentDistance, String currPath) {
		String[] split = currPath.split(" -> ");
		//System.out.println("DEBUG: " + paths.size() + " " + currPath);
		if (split.length == 8) { // hard coding a bit hackish
			//System.out.println(currPath + " distance: " + currentDistance);
			if (currentDistance < minDistance) {
				System.out.println("New Best " + currentDistance + " " + currPath);
				minDistance = currentDistance;
				bestRouteSoFar = currPath;
			}
			
			if (currentDistance > maxDistance) {
				System.out.println("New Longest " + currentDistance + " " + currPath);
				maxDistance = currentDistance;
				longestRouteSoFar = currPath;
			}
		}
		String lastPlace = null;
		
		Set<String> visited = new HashSet<>(Arrays.asList(split));
		if (!currPath.isEmpty()) {
			lastPlace = split[split.length-1];
		}
		for (int i=0; i<paths.size(); i++) {
			List<Path> pathsCopy = new ArrayList<Path>(paths);			
			pathsCopy.remove(i);
			Path curr = paths.get(i);
			if (visited.contains(curr.start) && visited.contains(curr.end)) {
				continue;
			}
			int newDist = currentDistance + curr.distance;
			if (lastPlace == null) {
				pathDistance(pathsCopy, newDist, curr.start + " -> " + curr.end);	
				pathDistance(pathsCopy, newDist, curr.end + " -> " + curr.start);
			}
			if (curr.start.equals(lastPlace)) {
				pathDistance(pathsCopy, newDist, currPath + " -> " + curr.end);	
			}
			if (curr.end.equals(lastPlace)) {
				pathDistance(pathsCopy, newDist, currPath + " -> " + curr.start);
			}
		}
	}
	
	public static class Path {
		String start;
		String end;
		int distance;
		public Path(String start, String end, int distance) {			
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Path [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}
	}
}
