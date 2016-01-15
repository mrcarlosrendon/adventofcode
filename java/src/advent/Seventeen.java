package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Seventeen {
	
	private static int minContainers = Integer.MAX_VALUE;
	private static int minComboCount = 0;
	
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
		int waysToFill = waysToFill(containers, new ArrayList<Container>(), 150, 0);
		System.out.println(waysToFill);		
		System.out.println("Smallest number of containers for sum: " + minContainers);
		System.out.println("Number of combos of smallest container count: " + minComboCount);
	}
	
	private static int waysToFill(Set<Container> containers, List<Container> alreadyUsed, 
			final int goalAmount, int usedSoFar) {
		if (usedSoFar == goalAmount) {
			System.out.println(alreadyUsed);
			
			int containerCount = 0;
			for (Container c : alreadyUsed) {
				if (c.used) {
					containerCount++;
				}
			}
			
			if (containerCount < minContainers) {
				minContainers = containerCount;
				minComboCount = 1;
			}
			else if (containerCount == minContainers) {
				minComboCount++;
			}
			
			return 1;
		}
		else if (usedSoFar > goalAmount || alreadyUsed.size() == containers.size()) {
			return 0;
		}
		else {
			int totalWays = 0;
			for (Container c : containers) {
				if (alreadyUsed.size() > 0 && alreadyUsed.get(alreadyUsed.size()-1).ContainerId > c.ContainerId) {
					continue; // enforce ordering
				}
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
