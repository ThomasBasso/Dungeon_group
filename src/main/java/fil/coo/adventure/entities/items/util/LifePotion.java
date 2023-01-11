package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;

public class LifePotion extends Item {

	public LifePotion() {
		super("Potion de vie", Constant.LIFE_POTION, 0,  50);
	}

	@Override
    public void isUsedBy(Player player) {
        player.setLifePoints(Math.min(player.getLifePoints()+this.getLife(), player.getMaxLifePoints()));
        player.dropItem(this);
    }
}
