package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;

public class LifePotion extends Item {

	@Override
    public void isUsedBy(Player player) {
        int lifeToAdd = 30;
        player.setLifePoints(Math.min(player.getMaxLifePoints()+lifeToAdd, player.getMaxLifePoints()));
    }

}
