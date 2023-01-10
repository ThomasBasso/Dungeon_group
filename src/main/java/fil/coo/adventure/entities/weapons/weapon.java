package fil.coo.adventure.entities.weapons;

import fil.coo.adventure.entities.Player;

public abstract class weapon {
    public abstract void isUsedBy(Player player);
    public abstract void isDropBy(Player player);
}
