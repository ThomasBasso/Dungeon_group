package fil.coo.adventure.entities.actions.util;

import java.util.List;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.places.Room;

public class Loot {
	public void execute(Room r, Player p){
		List<Item> items = r.getItems();

		for(Item i :items){
			System.out.println("Vous avez trouvé " + i.toString());
			i.isUsedBy(p);
			r.removeItem(i);
		}
	}

	public boolean isPossible(Room r) {
		return r.getMonsters().isEmpty();
	}
}
