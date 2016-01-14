package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Seventeen {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;		
		Set<Container> containers = new HashSet<Container>();
		long containerId = 0;
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}
			containers.add(new Container(containerId, Integer.parseInt(line)));
			containerId++;
		}		
		int waysToFill = waysToFill(containers, new ArrayList<Container>(), 25, 0);
		System.out.println(waysToFill);
	}
	
	private static int waysToFill(Set<Container> containers, List<Container> alreadyUsed, 
			final int goalAmount, int usedSoFar) {
		if (usedSoFar == goalAmount) {
			System.out.println(alreadyUsed);
			return 1;
		}
		else if (usedSoFar > goalAmount || alreadyUsed.size() == containers.size()) {
			return 0;
		}
		else {
			int totalWays = 0;
			for (Container c : containers) {
				if (alreadyUsed.contains(c)) {
					continue;
				}
				else {					
					alreadyUsed.add(c);
					c.used = true;
					totalWays += waysToFill(containers, alreadyUsed, goalAmount, usedSoFar+c.capacity);					
					c.used = false;
					alreadyUsed.remove(c);
				}
			}
			return totalWays;
		}
	}
	
	public static class Container {
		final long ContainerId;
		final int capacity;
		public boolean used=false;
		public Container(long containerId, int capacity) {			
			ContainerId = containerId;
			this.capacity = capacity;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (ContainerId ^ (ContainerId >>> 32));
			result = prime * result + capacity;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Container other = (Container) obj;
			if (ContainerId != other.ContainerId)
				return false;
			if (capacity != other.capacity)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Id=" + ContainerId + ", " + capacity + ", " + used + "]";
		}
	}
}
