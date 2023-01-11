package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;

public class StrengthPotion extends Item {
    private int boostDuration = Constant.STRENGHT_POTION_DURATION;

	//Définit la durée du gain de force par 3 attaques
    public StrengthPotion() {
        super("Potion de force", 0, Constant.STRENGHT_POTION, 50);
    }

    @Override
    public void isUsedBy(Player player) {
		// Add strenght to the player
        player.setStrength(player.getStrength()+this.getStrenght());
        player.setBoostDuration(boostDuration);
        player.dropItem(this);
    }
}
