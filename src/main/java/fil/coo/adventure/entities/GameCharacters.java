package fil.coo.adventure.entities;

public abstract class GameCharacters {
	protected int LifePoints;
	protected int strength;
	protected int gold;
	protected String name;

	public GameCharacters(String name, int lP, int st, int gold) {
		this.name = name;
		this.LifePoints = lP;
		this.strength = st;
		this.gold = gold;
	}

	public String getName() {
		return name;
	}
	
	public int getLifePoints() {
		return this.LifePoints;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	public void addGold(int g) {
		this.gold += g;
	}
	
	public void LooseLife(int st) {
		this.LifePoints -= st;
	}
	
	public void attack(GameCharacters ennemy) {
		ennemy.LooseLife(this.getStrength());
		System.out.println("Vous avez infligé "+getStrength()+"à "+ennemy.getName());
		if (ennemy.getLifePoints()>= 0)
			this.LooseLife(ennemy.getStrength());
			System.out.println("L'ennemi vous a infligé "+ennemy.getStrength());
	}
}
