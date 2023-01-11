package fil.coo.adventure.entities.items;

import fil.coo.adventure.entities.Player;

public abstract class Item {
	private String name;
	private int life, strenght, attack, armor, gold, prix;

	public Item(String name, int life, int strenght, int armor, int gold, int prix) {
		this.name = name;
		this.life = life;
		this.strenght = strenght;
		this.armor = armor;
		this.gold = gold;
		this.prix = prix;
	}

	public String getName() {
		return this.name;
	}

	public abstract void isUsedBy(Player player);
}
