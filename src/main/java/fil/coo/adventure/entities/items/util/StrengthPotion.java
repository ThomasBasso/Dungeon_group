package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;

public class StrengthPotion extends Item {
    private int boostDuration = Constant.STRENGHT_POTION_DURATION;

	// Constructeur
    public StrengthPotion() {
        super("Potion de force", 0, Constant.STRENGHT_POTION, 50);
    }

    @Override
    public void isUsedBy(Player player) {
		// Ajoute de la force au joueur à sa force actuelle temporairement
        player.setStrength(player.getStrength()+this.getStrenght());
        // Baisse la durée de la force en fonction de la durée du boost
        player.setBoostDuration(boostDuration);
        // Jette l'item de l'inventaire
        player.dropItem(this);
    }
}
