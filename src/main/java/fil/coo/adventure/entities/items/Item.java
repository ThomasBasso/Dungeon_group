package fil.coo.adventure.entities.items;

import fil.coo.adventure.entities.Player;

public abstract class Item {
	private String name;
	private int life, strenght, price;

	public Item(String name, int life, int strenght, int price) {
		this.name = name;
		this.life = life;
		this.strenght = strenght;
		this.price = price;
	}

	public abstract void isUsedBy(Player player);

	public String getName() {
		return this.name;
	}

	public int getLife() {
		return life;
	}

	public int getStrenght() {
		return strenght;
	}
}
