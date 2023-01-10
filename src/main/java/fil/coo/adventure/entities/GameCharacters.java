package fil.coo.adventure.entities;

public abstract class GameCharacters {
	protected int LifePoints;
	protected int strength;
	protected int gold;
	
	public GameCharacters() {
		this.LifePoints = 0;
		this.strength = 0;
		this.gold = 0;
	}
	
	public GameCharacters(int lP, int st) {
		this.LifePoints = lP;
		this.strength = st;
		this.gold = 0;
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
	
	public void attack(GameCharacters theOtherCharacterToAttack) {
		theOtherCharacterToAttack.LooseLife(this.getStrength());
		if (theOtherCharacterToAttack.getLifePoints() >= 0)
			this.LooseLife(theOtherCharacterToAttack.getStrength());
	}
}
