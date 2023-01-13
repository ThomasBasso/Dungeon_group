package fil.coo.adventure.entities.items;

import java.util.ArrayList;

import fil.coo.adventure.entities.items.util.LifePotion;
import fil.coo.adventure.entities.items.util.StrengthPotion;
import fil.coo.adventure.utils.Constant;

public class Inventory {
    private ArrayList<Item> inventoryItems = new ArrayList<>();
	// Fixe une capacité maximale d'objets pouvant être stockés dans l'inventaire
    private final int maxCapacity = Constant.MAX_ITEMS;

	// Constructeur
    public Inventory(String items) {
		// Sépare les éléments de la liste de l'inventaire par une ","
        String[] itemsNames = items.split(",");

		// Pour chaque nom d'item correspondant en possession du joueur, ajoute 1 exemplaire dans l'inventaire
        for (String item : itemsNames) {
			switch (item) {
				case "Potion de vie":
					this.inventoryItems.add(new LifePotion());
					break;
				case "Potion de force":
					this.inventoryItems.add(new StrengthPotion());
					break;
				default:
					break;
			}
		}
    }

	// Retourne la liste des items de l'inventaire
    public ArrayList<Item> getItems() {
		return inventoryItems;
	}

	// Ajoute l'item à l'inventaire
	public void addItems(Item item) {
		inventoryItems.add(item);
	}
}
