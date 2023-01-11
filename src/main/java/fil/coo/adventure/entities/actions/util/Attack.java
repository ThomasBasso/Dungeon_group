package fil.coo.adventure.entities.actions.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.places.Room;

public class Attack {
	public boolean isPossible(Room r, Player player) {
        if (!r.getMonsters().isEmpty()) {
			//Permer de réduire le temps de boost de la potion de force à chaque fois que le joueur attaque
            player.decrementBoostDuration();
            return true;
        }
        return false;
    }
}
