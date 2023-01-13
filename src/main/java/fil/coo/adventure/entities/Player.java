package fil.coo.adventure.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import fil.coo.adventure.entities.items.Inventory;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.utils.Constant;
import fil.coo.adventure.utils.Fonctions;

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
     * 
     * @param name le nom du joueur
     */
    public Player(String name) {
        super(name, 100, 10, 20);
        // Player starts a game with one LifePotion
        this.inventory = new Inventory("Potion de vie");

        for (String armorItem : Constant.ARMOR) {
            armorEquiped.put(armorItem, false);
        }

        for (String weaponItem : Constant.WEAPON) {
            weaponEquiped.put(weaponItem, false);
        }
    }

    public Player(String name, int life, int strenght, int defense, int gold, String items, String armor,
            String weapon) {
        super(name, life, strenght, gold);
        this.defense = defense;
        this.inventory = new Inventory(items);

        String[] armorInfos = armor.split(",");
        int i = 0;
        for (String armorItem : Constant.ARMOR) {
            armorEquiped.put(armorItem, Boolean.parseBoolean(armorInfos[i]));
            if (armorItem=="true") {
                addDefense(Fonctions.getArmorFromObject(armorItem));
            }
            i++;
        }

        String[] weaponInfos = weapon.split(",");
        i = 0;
        for (String weaponItem : Constant.WEAPON) {
            weaponEquiped.put(weaponItem, Boolean.parseBoolean(weaponInfos[i]));
            if (weaponItem=="true") {
                addStrength(Fonctions.getStrengthFromObject(weaponItem));
            }
            i++;
        }
    }

    public void askToUseItem() {
        ArrayList<Item> consoItems = inventory.getItems();
        int choix = 0;
        String possible = "";
        for (int i = 0; i < consoItems.size(); i++) {
            possible += i;
        }
        if (possible.isEmpty()) {
        System.out.println("Vous n'avez rien dans votre inventaire.");
        return;
        }
        possible += "q";

        System.out.println("Voulez avez actuellement "+getLifePoints()+" points de vie et "+getStrength()+" de force.");
        System.out.println("Une potion de vie rend "+Constant.LIFE_POTION+" PV.");
        System.out.println("Une potion de force donne "+Constant.STRENGHT_POTION+" de force.");
        System.out.println("Voulez avez en votre possession les consommables suivants :");

        String input;
        Scanner sc = new Scanner(System.in);
        do {
            for (int i = 0; i < consoItems.size(); i++) {
                System.out.println("choix " + i + ") " + consoItems.get(i).getName());
                choix++;
            }
            System.out.println("Saisissez votre choix (q pour quitter):");
            input = sc.nextLine().toLowerCase();
        } while (possible.indexOf(input.charAt(0)) == -1);

        if (input.indexOf("q") == 0) {
            System.out.println("Vous n'utilisez aucun item de votre inventaire.");
        } else {
            choix = Integer.parseInt(input);
            System.out.println("Vous utilisez une " + consoItems.get(choix).getName());
            consoItems.get(choix).isUsedBy(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public void addItem(Item item) {
        if (inventory.getItems().size() < Constant.MAX_ITEMS) {
            this.inventory.getItems().add(item);
        }
    }

    public void dropItem(Item item) {
        this.inventory.getItems().remove(item);
    }

    public String getInventoryNames() {
        String itemsNames = "";
        for (Item item : inventory.getItems()) {
            itemsNames += item.getName() + ",";
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
        this.originalStrength += this.strength;
        this.strength += strenght;
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
        this.defense += defense;
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
            armorsStatus += armor.toString() + ",";
        }
        return armorsStatus;
    }

    public String getWeaponsStatus() {
        String weaponsStatus = "";
        for (Boolean weapon : getWeaponEquiped().values()) {
            weaponsStatus += weapon.toString() + ",";
        }
        return weaponsStatus;
    }

	/**
	 * Player attacks a monster and gets hit back if the monster is still alive
	 * If the monster dies, player gets his gold
	 * @param ennemy	The monster attacked
	 */
	public void attack(GameCharacters ennemy) {
		ennemy.LooseLife(this.getStrength());
		System.out.println("\nVous avez infligé "+getStrength()+" à "+ennemy.getName());
		this.decrementBoostDuration();

		if (ennemy.getLifePoints()>0) {
			System.out.println("Il reste "+ennemy.getLifePoints()+" PV au "+ennemy.getName());;
			this.LooseLife(ennemy.getStrength());
			System.out.println("\nL'ennemi vous a infligé "+ennemy.getStrength());
			System.out.println("Il vous reste "+getLifePoints()+" life points.\n");
		} else {
			System.out.println("Vous avez tué l'ennemi ! Vous lui volez ses "+ennemy.getGold()+" pièces d'or !\n");
			addGold(ennemy.getGold());
		}
	}
}