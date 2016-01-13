package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fifteen {

	public static int bestScore = 0;
	public static int best500calScore = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		List<IngredientModifier> ingredients = new ArrayList<IngredientModifier>();
		while((line = br.readLine()) != null) {	
			if (line.isEmpty()) {
				break;
			}
			String[] split = line.split(" ");
			String ingredient = split[0];
			int capacity = Integer.parseInt(split[2].split(",")[0]);
			int durability = Integer.parseInt(split[4].split(",")[0]);
			int flavor = Integer.parseInt(split[6].split(",")[0]);
			int texture = Integer.parseInt(split[8].split(",")[0]);
			int calories = Integer.parseInt(split[10]);			
			ingredients.add(
					new IngredientModifier(
							capacity, durability, flavor, 
							texture, calories, ingredient));
		}		
		findBest(ingredients, new ArrayList<String>(), 0);		
	}
	
	public static void findBest(List<IngredientModifier> ingredients, List<String> alreadySet, final int currTot) {
		if (alreadySet.size() == ingredients.size()) {
			int score = score(ingredients);
			if (score > bestScore) {
				bestScore = score;
				System.out.println("New Best: " + score + " " + ingredients);
			}
			int totCalories = 0;
			for (IngredientModifier ingredient : ingredients) {
				totCalories += ingredient.calories * ingredient.amount;
			}
			if (score > best500calScore && totCalories == 500) {
				best500calScore = score;
				System.out.println("New Best 500 Calorie Score (part2): " + score + " " + ingredients);
			}
		}
		for (IngredientModifier ingredient : ingredients) {
			if (alreadySet.contains(ingredient.name)) {
				continue;
			}
			else {
				alreadySet.add(ingredient.name);
				for (int i=0; i<=100-currTot; i++) {
					ingredient.amount = i;
					findBest(ingredients, alreadySet, currTot+i);
				}
				alreadySet.remove(ingredient.name);
			}
		}
	}
	
	public static int score(List<IngredientModifier> ingredients) {
		int capacityTot = 0;
		int durabilityTot = 0;
		int flavorTot = 0;
		int textureTot = 0;		
		int checkSum = 0;
		for (IngredientModifier ingredient : ingredients) {
			capacityTot += ingredient.amount*ingredient.capacity;
			durabilityTot += ingredient.amount*ingredient.durability;
			flavorTot += ingredient.amount*ingredient.flavor;
			textureTot += ingredient.amount*ingredient.texture;
			checkSum += ingredient.amount;
		}		
		if (checkSum != 100) {
			return 0;			
		}
		if (capacityTot < 0 || durabilityTot < 0 || flavorTot < 0 || textureTot < 0) {
			return 0;
		}		
		return capacityTot*durabilityTot*flavorTot*textureTot;
	}
	
	public static class IngredientModifier {
		public final int capacity;
		public final int durability;
		public final int flavor;
		public final int texture;
		public final int calories;
		public final String name;		
		public IngredientModifier(int capacity, int durability, int flavor, int texture, int calories, String name) {			
			this.capacity = capacity;
			this.durability = durability;
			this.flavor = flavor;
			this.texture = texture;
			this.calories = calories;
			this.name = name;
		}				
		
		public int amount;
		@Override
		public String toString() {
			return "IngredientModifier [name=" + name + ", amount=" + amount + "]";
		}
	}
}
