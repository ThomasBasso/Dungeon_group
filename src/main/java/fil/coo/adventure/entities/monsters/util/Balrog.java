package fil.coo.adventure.entities.monsters.util;

import java.util.Random;

import fil.coo.adventure.entities.monsters.Monster;



public class Balrog extends Monster {

	static Random rand = new Random();
	static int randomNumber = rand.nextInt(10);

	public Balrog() {
		super("Balrog", 80, 25);
	}
}
