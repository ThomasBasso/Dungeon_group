package fil.coo.adventure.entities.actions.util;

import fil.coo.adventure.places.Room;

public class Loot {
	public boolean isPossible(Room r) {
		return !r.getDeads().isEmpty();
	}
}
