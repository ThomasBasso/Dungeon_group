package fil.coo.adventure.places;

import fil.coo.adventure.entities.Player;

public class Dungeon {

    private final Player player;
    private TYPE current_level;

    /**
     * Permet de définir les différents types de donjon
     */
    public enum TYPE{
        EASY,
        MEDIUM,
        HARD
    }

    /**
     * Permet d'initialiser un donjon au niveau par défaut (easy)
     * 
     * @param player le joueur actuel
     */
    public Dungeon(Player player) {
        this.player = player;
        this.current_level = TYPE.EASY;
    }

    /**
     * Permet de creer un donjon d'un niveau précis
     * 
     * @param player le joueur actuel
     * @param level  le niveau du donjon
     */
    public Dungeon(Player player, TYPE level) {
        this.player = player;
        this.current_level = level;
    }

    /**
     * Permet de changer de niveau lorsque l'on termine un donjon
     * 
     * @return le prochain niveau du donjon
     */
    public TYPE getNextLevel() {
        switch (this.current_level) {
            case EASY -> {
                return TYPE.MEDIUM;
            }
            case MEDIUM -> {
                return TYPE.HARD;
            }
            default -> {
                return null;
            }
        }

    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return TYPE the current level
     */
    public TYPE getCurrentLevel() {
        return current_level;
    }

}
