package fil.coo.adventure.places.rooms;

import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.places.*;

public class Third extends Room {


    public Third(Dungeon type) {
        super(type);
        this.nbMonster = 5;
        this.monsters = Monster.getRandomListMonsters(this.donjon.getActual_level(), nbMonster);
    }
}

