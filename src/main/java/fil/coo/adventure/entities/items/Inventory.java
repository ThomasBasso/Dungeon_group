package fil.coo.adventure.entities.items;

import java.util.ArrayList;

import fil.coo.adventure.entities.items.util.GoldChest;
import fil.coo.adventure.entities.items.util.LifePotion;
import fil.coo.adventure.entities.items.util.OneArmedBandit;
import fil.coo.adventure.entities.items.util.StrengthPotion;
import fil.coo.adventure.utils.Constant;

public class Inventory {
    private ArrayList<Item> inventoryConsommable = new ArrayList<>();
    private ArrayList<Item> inventoryEquippable = new ArrayList<>();
    private final int maxCapacity = Constant.MAX_ITEMS;

    public Inventory(String items) {
        String[] itemsNames = items.split(",");

        for (String item : itemsNames) {
			switch (item) {
				case "Potion de vie":
					this.inventoryConsommable.add(new LifePotion());
					break;
				case "Potion de force":
					this.inventoryConsommable.add(new StrengthPotion());
					break;
				case "Coffre":
					this.inventoryConsommable.add(new GoldChest());
					break;
                // case "Lotterie":
				// 	this.inventory.add(new OneArmedBandit());
				// 	break;
                // case "Epée":
				// 	this.inventoryEquippable.add(new Sword());
				// 	break;
				default:
					break;
			}
		}
    }

    public ArrayList<Item> getConsoItems() {
		return inventoryConsommable;
	}

    public ArrayList<Item> getEquipItems() {
		return inventoryEquippable;
	}
}
