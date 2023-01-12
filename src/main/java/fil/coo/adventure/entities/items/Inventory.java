package fil.coo.adventure.entities.items;

import java.util.ArrayList;

import fil.coo.adventure.entities.items.util.LifePotion;
import fil.coo.adventure.entities.items.util.StrengthPotion;
import fil.coo.adventure.utils.Constant;

public class Inventory {
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private final int maxCapacity = Constant.MAX_ITEMS;

    public Inventory(String items) {
        String[] itemsNames = items.split(",");

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

    public ArrayList<Item> getItems() {
		return inventoryItems;
	}

	public void addItems(Item item) {
		inventoryItems.add(item);
	}
}
