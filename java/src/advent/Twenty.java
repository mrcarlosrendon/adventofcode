package advent;

public class Twenty {
	
	public static final long GOAL_PRESENTS = 33100000;
	
	public static void main(String[] args) {
		long house = 1;
		long maxScoreSoFar = 0;
		for(int i=0; i<1000000; i++) {
			house++;
			long score = scoreHouseNumberPart2(house);
			if (score > maxScoreSoFar) {
				maxScoreSoFar=score;
				System.out.println(String.format("House %d Score %d", house, score));
			}
		}
	}
	
	public static void guessAndCheckPart1() {
		int house = 1;
		for(int i=0; i<19; i++) {
			house = house*2;
			long score = scoreHouseNumber(house);
			System.out.println(String.format("House %d Score %d", house, score));
		}
		System.out.println("Guess and check part done");
		long maxScoreSoFar = 0;
		for(int i=0; i<524288; i++) {			
			long score = scoreHouseNumber(house);			
			if (score > maxScoreSoFar) {
				maxScoreSoFar = score;
				System.out.println(String.format("House %d Score %d", house, score));
			}
			if (score > GOAL_PRESENTS) {				
				break;
			}
			house=house+2; // evens always higher
		}		
	}
	
	public static long scoreHouseNumber(long house) {
		long presents = 0;	
		for(int elf=1; elf<=house; elf++) {
			if (house%elf == 0) {
				long amount = (house/elf)*10;
				//System.out.println("House: " + house + " Elf: " + elf + " amount: " + amount);
				presents += amount;
			}
		}
		return presents;
	}
	
	public static long scoreHouseNumberPart2(long house) {
		long presents = 0;				
		for(long elf=Math.max(1, house/50); elf<=house; elf++) {
			if (house%elf == 0 && house/elf <= 50) {
				long amount = (house/elf)*11;
				//System.out.println("House: " + house + " Elf: " + elf + " amount: " + amount);
				presents += amount;
			}
		}
		return presents;
	}
}
