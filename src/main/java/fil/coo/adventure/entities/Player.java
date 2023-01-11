package fil.coo.adventure.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private HashMap<String, Boolean> armorEquiped = new HashMap<>();
    private HashMap<String, Boolean> weaponEquiped = new HashMap<>();

    /**
     * Permet de créer un joueur uniquement en lui attribuant un nom
     * @param name le nom du joueur
     */
    public Player(String name) {
        super(100, 10);
        this.name = name;
    }

    public Player(String name, int life, int strenght, int defense, int gold, String items, String armor, String weapon) {
		this.name = name;
		this.LifePoints = life;
		this.strength = strenght;
		this.defense = defense;
		this.gold = gold;
		this.inventory = new Inventory(items);

        String[] armorInfos = armor.split(",");
        int i = 0;
        for (String armorItem : Constant.ARMOR) {
            armorEquiped.put(armorItem, Boolean.parseBoolean(armorInfos[i]));
            i++;
        }

        String[] weaponInfos = weapon.split(",");
        i = 0;
        for (String weaponItem : Constant.WEAPON) {
            weaponEquiped.put(weaponItem, Boolean.parseBoolean(weaponInfos[i]));
            i++;
        }
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
        if (inventory.getConsoItems().size()+inventory.getEquipItems().size() < Constant.MAX_ITEMS) {
            if (Arrays.stream(Constant.CONSOMMABLE_ITEM).anyMatch(w -> w.contains(item.getName()))) {
                this.inventory.getConsoItems().add(item);
            } else {
                this.inventory.getEquipItems().add(item);
            }
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

    public void addStrength(int strenght) {
        this.originalStrength+=this.strength;
        this.strength+=strenght;
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

    public int getDefense() {
        return defense;
    }

    public void addDefense(int defense) {
		this.defense+=defense;
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

    public HashMap<String, Boolean> getWeaponEquiped() {
        return weaponEquiped;
    }

    public HashMap<String, Boolean> getArmorEquiped() {
        return armorEquiped;
    }

    public String getArmorsStatus() {
        String armorsStatus = "";
        for (Boolean armor : getArmorEquiped().values()) {
            armorsStatus+=armor.toString()+",";
        }
        System.out.println("les armures status : " + armorsStatus);
        return armorsStatus;
    }

    public String getWeaponsStatus() {
        String weaponsStatus = "";
        for (Boolean weapon : getWeaponEquiped().values()) {
            weaponsStatus+=weapon.toString()+",";
        }
        System.out.println("les weapons status : " + weaponsStatus);
        return weaponsStatus;
    }
}