package fil.coo.adventure.entities.actions;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.places.Room;

public interface Actions {
	public boolean isPossible(Room room);
	public void execute(Room room, Player player);
}
