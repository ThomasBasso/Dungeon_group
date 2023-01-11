package fil.coo.adventure.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import fil.coo.adventure.entities.items.Inventory;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;

public class Player extends GameCharacters {
    private int maxLifePoints = Constant.MAX_LIFE;
    private int originalStrength;
    private int boostDuration;
    private int defense;
    private ArrayList<Poison> negativeStatuses;
    private Inventory inventory;
	

    public Player(String name, int life, int strenght, int defense, int gold, String items) {
		this.name = name;
		this.LifePoints = life;
		this.strength = strenght;
		this.defense = defense;
		this.gold = gold;

		this.inventory = new Inventory(items);
	}

    public void askToUseItem() {  
        ArrayList<Item> consoItems = inventory.getConsoItems();
        ArrayList<Item> EquipItems = inventory.getEquipItems();
        int choix = 0;
        String possible = "";
        for (int i = 0; i < consoItems.size()+EquipItems.size(); i++) {
            possible+=i;
        }
        possible+="q";

        System.out.println("Voulez avez en votre possession les consommables suivants :");
        for (int i = 0; i < consoItems.size(); i++) {
            System.out.println("choix "+i+") "+consoItems.get(i).getName());
            choix++;
        }

        System.out.println("Voulez avez en votre possession les équipements suivants :");
        for (int i = 0; i < EquipItems.size(); i++) {
            System.out.println("choix"+i+") "+EquipItems.get(i).getName());
            choix++;
        }

        Scanner sc = new Scanner(System.in);
        String input;

        do{
            System.out.println("Saisissez votre choix (q pour quitter) :");
            input = sc.nextLine().toLowerCase();
        } while (possible.indexOf(input.charAt(0))==-1);

        if (input.indexOf("q") == 0) {
            System.out.println("Vous n'utilisez aucun item de votre inventaire.");
        } else {
            choix = Integer.parseInt(input);
            if (choix > consoItems.size()-1) {
                EquipItems.get(choix-consoItems.size()-1).isUsedBy(this);
            } else {
                consoItems.get(choix).isUsedBy(this);
            }
        }
    }

	public String getName(){
		return this.name;
	}

	public void addItem(Item item) {
        if (inventory.getConsoItems().size()+inventory.getEquipItems().size() < Constant.MAX_ITEMS)
        if (Arrays.stream(Constant.CONSOMMABLE_ITEM).anyMatch(w -> w.contains(item.getName()))) {
            this.inventory.getConsoItems().add(item);
        } else {
            this.inventory.getEquipItems().add(item);
        }
	}

    public void dropItem(Item item) {
		if (Arrays.stream(Constant.CONSOMMABLE_ITEM).anyMatch(w -> w.contains(item.getName()))) {
            this.inventory.getConsoItems().remove(item);
        } else {
            this.inventory.getEquipItems().remove(item);
        }
	}

	public String getInventoryNames() {
		String itemsNames = "";
		for (Item item : inventory.getConsoItems()) {
			itemsNames += item.getName()+",";
		}
        for (Item item : inventory.getEquipItems()) {
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

    public void addNegativeStatus(Poison status) {
        negativeStatuses.add(status);
    }
    
    public void removeNegativeStatus() {
        negativeStatuses.clear();
    }
    
    public void applyNegativeStatuses() {
        for (Poison status : negativeStatuses) {
            status.tick(this);
            if (status.getDuration() == 0) {
                negativeStatuses.remove(status);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}