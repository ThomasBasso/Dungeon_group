package fil.coo.adventure.entities.actions;

import fil.coo.adventure.places.Room;

public interface Actions {
	public boolean isPossible(Room r);
	public void execute();
}
