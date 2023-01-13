package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;

public class LifePotion extends Item {

    // Constructeur (nom, lp, st, price)
	public LifePotion() {
		super("Potion de vie", Constant.LIFE_POTION, 0,  50);
	}

	@Override
    public void isUsedBy(Player player) {
        // Si potion utilisée, ajoute aux lp du joueur 30 lp ou moins en fonction de sa vie max pour ne pas dépasser le montant de sa vie maximale
        player.setLifePoints(Math.min(player.getLifePoints()+this.getLife(), player.getMaxLifePoints()));
        // Jette l'item de l'inventaire
        player.dropItem(this);
    }
}
