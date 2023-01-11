package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;

public class StrengthPotion extends Item {
    private int boostDuration;

	//Définit la durée du gain de force par 3 attaques
    public StrengthPotion() {
        this.boostDuration = 3;
    }

    @Override
    public void isUsedBy(Player player) {
		//Augmentation de la force du joueur de 30 points
        player.setStrength(player.getStrength() + 30);
		//Définit la durée du gain de force par la variable boostDuration
        player.setBoostDuration(boostDuration);
    }
}

