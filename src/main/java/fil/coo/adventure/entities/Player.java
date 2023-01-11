package fil.coo.adventure.entities;

public class Player extends GameCharacters {
    private int maxLifePoints;
    private int originalStrength;
    private int boostDuration;
    private int defense;

	public Player() {
		super(100, 10);
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
}
