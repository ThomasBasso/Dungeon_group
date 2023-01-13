package fil.coo.adventure;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		AdventureGame adventureGame = AdventureGame.start();
		adventureGame.getCurrentRoom().initializeNeighbour(adventureGame.getPlayer());
		adventureGame.runGame();
	}
}
