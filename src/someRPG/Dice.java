package someRPG;

import java.util.Random;

public class Dice {
	
	private int sides;
	private Random random = new Random();
	
	public Dice(int sides) {
		this.sides = sides;
	}
	
	public int roll() {
		return random.nextInt(sides);
		
	}

}
