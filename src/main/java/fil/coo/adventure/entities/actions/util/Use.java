package fil.coo.adventure.entities.actions.util;

import fil.coo.adventure.places.Room;

public class Use {
	public boolean isPossible(Room r) {
		return !r.getItems().isEmpty();
	}
}
