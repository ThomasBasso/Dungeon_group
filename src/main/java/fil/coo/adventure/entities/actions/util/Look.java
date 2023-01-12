package fil.coo.adventure.entities.actions.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.places.Room;
import fil.coo.adventure.places.directions.Direction;
import fil.coo.adventure.places.rooms.First;
import fil.coo.adventure.places.rooms.Second;
import fil.coo.adventure.places.rooms.Third;

public class Look {
	protected Map<Direction, Room> neighbour;
	private int nbRoomMax = 0;


	public Set<Direction> getPossibleDirections() {
		return this.neighbour.keySet();
	}

	public Room getNeighbour(Direction d) {
		return this.neighbour.get(d);
	}

	public void initializeNeighbour(Room r){
	r.addNeighbour(Direction.W, new First(r.getDonjon()));
	r.addNeighbour(Direction.N, new Second(r.getDonjon()));
	r.addNeighbour(Direction.E, new Third(r.getDonjon()));
	r.setNbRoomMax(this.neighbour.size());
	}

	public void chooseRoom() {
		Set<Direction> directionPossible = this.getPossibleDirections();
		ArrayList<String> directionPossibleName = new ArrayList<>();
		String result;
		do {
			System.out.println("Dans quelle salle souhaitez-vous aller ? ");
			for (Direction d : directionPossible) {
				directionPossibleName.add(d.name());
				System.out.println(d.name() + " - " + d);
			}
			Scanner scanner = new Scanner(System.in);
			result = scanner.nextLine().toUpperCase();
		} while (!directionPossibleName.contains(result));
		Direction directionChoose = Direction.valueOf(result);
		Room nextRoom = this.getNeighbour(directionChoose);
		this.neighbour.remove(directionChoose);
		this.execute(nextRoom);
	}

	public void execute(Room r) {
		List<Monster> monsters = r.getMonsters();
		List<Item> items = r.getItems();
		System.out.println("Il y a " + monsters.size() + " monstres dans cette pièce.");
	}

	public boolean isPossible(Room r) {
		return true;
	}
}
