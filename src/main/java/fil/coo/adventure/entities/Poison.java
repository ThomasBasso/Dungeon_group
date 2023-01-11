package fil.coo.adventure.entities;

public class Poison {
    private int duration;
    private int damage;
    
    public Poison() {
        this.duration = 5;
        this.damage = 1;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void tick(Player player) {
        player.setLifePoints(player.getLifePoints() - damage);
        duration--;
    }
}
