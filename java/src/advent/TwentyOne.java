package advent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwentyOne {
			
	public enum ITEM_TYPE { WEAPON, ARMOR, RING };
	
	public final static Item[] items = new Item[] {
			new Item(8, 4, 0, ITEM_TYPE.WEAPON),
			new Item(10, 5, 0, ITEM_TYPE.WEAPON),
			new Item(25, 6, 0, ITEM_TYPE.WEAPON),
			new Item(40, 7, 0, ITEM_TYPE.WEAPON),
			new Item(74, 8, 0, ITEM_TYPE.WEAPON),
			
			new Item(13, 0, 1, ITEM_TYPE.ARMOR),
			new Item(31, 0, 2, ITEM_TYPE.ARMOR),
			new Item(53, 0, 3, ITEM_TYPE.ARMOR),
			new Item(75, 0, 4, ITEM_TYPE.ARMOR),
			new Item(102, 0, 5, ITEM_TYPE.ARMOR),
			
			new Item(25, 1, 0, ITEM_TYPE.RING),
			new Item(50, 2, 0, ITEM_TYPE.RING),
			new Item(100, 3, 0, ITEM_TYPE.RING),
			new Item(20, 0, 1, ITEM_TYPE.RING),
			new Item(40, 0, 2, ITEM_TYPE.RING),
			new Item(80, 0, 3, ITEM_TYPE.RING),			
	};
	
	public static void main(String[] args) {		
		System.out.println("least gold: " + leastCostWin(new ArrayList<Item>(), Integer.MAX_VALUE));
	}
	
	
	public static int leastCostWin(List<Item> inventory, int minCost) {		
		if (legalFinalInventory(inventory) 
				&& firstWins(createFromInventory(inventory), createBoss())) {
			int cost = inventoryCost(inventory);
			if (cost < minCost) {
				System.out.println(inventory);
				System.out.println("debug: " + cost);
			}
			return cost;
		}
		
		for(Item item : items) {
			if(!inventory.contains(item)) {
				boolean added = false;
				if (inventoryTypeCount(inventory, ITEM_TYPE.WEAPON) == 0 
						&& item.type == ITEM_TYPE.WEAPON) {
					inventory.add(item);
					added = true;
				}
				else if (inventoryTypeCount(inventory, ITEM_TYPE.ARMOR) == 0 
						&& item.type == ITEM_TYPE.ARMOR) {
					inventory.add(item);
					added = true;
				}
				else if (inventoryTypeCount(inventory, ITEM_TYPE.RING) < 2 
					&& item.type == ITEM_TYPE.RING) {
					inventory.add(item);
					added = true;
				}
				if (added) {
					int cost = leastCostWin(inventory, minCost);
					if (cost < minCost) {
						minCost = cost;
					}
					inventory.remove(item);
				}
			}
		}
		return minCost;
	}
	
	public static Opponent createBoss() {
		return new Opponent(8, 2, 100);
	}
	
	public static Opponent createFromInventory(List<Item> inventory) {
		int damage = 0;
		int defence = 0;
		for (Item item : inventory) {
			damage += item.damage;
			defence += item.defence;
		}
		return new Opponent(damage, defence, 100);
	}
	
	public static boolean legalFinalInventory(List<Item> inventory) {
		if (inventoryTypeCount(inventory, ITEM_TYPE.WEAPON) == 1 
				&& inventoryTypeCount(inventory, ITEM_TYPE.ARMOR) < 2
				&& inventoryTypeCount(inventory, ITEM_TYPE.RING) < 3) {
			return true;
		}
		return false;
	}
	
	public static int inventoryCost(List<Item> inventory) {
		int cost = 0;
		for(Item item : inventory) {
			cost+=item.cost;
		}
		return cost;
	}
	
	public static int inventoryTypeCount(List<Item> inventory, ITEM_TYPE type) {
		int count = 0;
		for(Item item : inventory) {
			if (item.type == type) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean firstWins(Opponent player, Opponent boss) {
		boolean playerTurn = true;
		while(player.hitpoints > 0 && boss.hitpoints > 0) {
			if (playerTurn) {
				boss.hitpoints -= damageDealt(player, boss);
			}
			else {
				player.hitpoints -= damageDealt(boss, player); 
			}
			playerTurn=!playerTurn;
		}
		if (player.hitpoints > 0) {
			return true;
		}
		return false;
	}
	
	public static int damageDealt(Opponent attacker, Opponent defender) {
		return Math.max(1, attacker.damage - defender.defence);
	}
	
	public static class Opponent {
		final int damage;
		final int defence;
		int hitpoints;
		public Opponent(int damage, int defence, int hitpoints) {
			this.damage = damage;
			this.defence = defence;
			this.hitpoints = hitpoints;
		}
	}
	
	public static class Item {
		final int cost;
		final int damage;
		final int defence;
		final ITEM_TYPE type;
		public Item(int cost, int damage, int defence, ITEM_TYPE type) {
			this.cost = cost;
			this.damage = damage;
			this.defence = defence;
			this.type = type;
		}
		@Override
		public String toString() {
			return "Item [cost=" + cost + ", damage=" + damage + ", defence=" + defence + ", type=" + type + "]";
		}
	}
}
