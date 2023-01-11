package fil.coo.adventure.entities;

import java.util.ArrayList;

import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.entities.items.util.GoldChest;
import fil.coo.adventure.entities.items.util.LifePotion;
import fil.coo.adventure.entities.items.util.StrengthPotion;

public class Player extends GameCharacters {
    private int maxLifePoints;
    private int originalStrength;
    private int boostDuration;
    private int defense;
	private ArrayList<Item> inventory = new ArrayList<>();

	public Player(String name, int life, int strenght, int defense, int gold, String items) {
		this.name = name;
		this.LifePoints = life;
		this.strength = strenght;
		this.defense = defense;
		this.gold = gold;

		String[] itemsNames = items.split(",");
		
		for (String item : itemsNames) {
			switch (item) {
				case "Potion de vie":
					this.inventory.add(new LifePotion());
					break;
				case "Potion de force":
					this.inventory.add(new StrengthPotion());
					break;
				case "Gold Chest":
					this.inventory.add(new GoldChest());
					break;
				default:
					break;
			}
		}
	}

	public String getName(){
		return this.name;
	}

	public ArrayList<Item> getInventory() {
		return this.inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public String getInventoryNames() {
		String itemsNames = "";
		for (Item item : inventory) {
			itemsNames += item.getName()+",";
		}
		return itemsNames;
	}

	public int getMaxLifePoints() {
        return this.maxLifePoints;
    }

	public void setLifePoints(int lp) {
        this.LifePoints = lp;
    }

	public void setStrength(int strength) {
        this.originalStrength = this.strength;
        this.strength = strength;
    }

	public void decrementBoostDuration() {
        if (boostDuration > 0) {
            boostDuration--;
        }
        if (boostDuration == 0) {
            strength = originalStrength;
        }
    }

	public void setBoostDuration(int duration) {
        this.boostDuration = duration;
    }

    public void equipArmor(String piece) {
        int armorDefense = 0;

        switch (piece) {
            case "Gauntlets":
                armorDefense = 4;
                break;
            case "Helmet":
                armorDefense = 6;
                break;
            case "Breast-plate":
                armorDefense = 12;
                break;
            case "Trousers":
                armorDefense = 10;
                break;
            case "Boots":
                armorDefense = 8;
                break;
            default:
                System.out.println("Pièce d'armure non valide");
                return;
        }
//TODO : Ajouter la méthode Drop pour modifier l'armure équipée
        defense += armorDefense;
        System.out.println("Vous vous équipez de " + piece + " qui vous donne " + armorDefense + " points de défense supplémentaires.");
    }

    public int getDefense() {
        return defense;
    }
	
	public void setDefense(int defense) {
		this.defense = defense;
	}
}