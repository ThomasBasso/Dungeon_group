package fil.coo.adventure.entities.actions.util;

import fil.coo.adventure.places.Room;

public class Attack {
	public boolean isPossible(Room r) {
		return !r.getMonsters().isEmpty();
	}
}
