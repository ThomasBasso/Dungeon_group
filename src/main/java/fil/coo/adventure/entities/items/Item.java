package fil.coo.adventure.entities.items;

import fil.coo.adventure.entities.Player;

public abstract class Item {
	private String name;
	private int life, strenght, price;

	// Constructeur de tous les items
	public Item(String name, int life, int strenght, int price) {
		this.name = name;
		this.life = life;
		this.strenght = strenght;
		this.price = price;
	}

	public abstract void isUsedBy(Player player);

	// Donne le nom de l'item
	public String getName() {
		return this.name;
	}

	// Donne les lp que l'item peut soigner
	public int getLife() {
		return life;
	}

	// Donne de la st que l'item peut augmenter
	public int getStrenght() {
		return strenght;
	}
}
