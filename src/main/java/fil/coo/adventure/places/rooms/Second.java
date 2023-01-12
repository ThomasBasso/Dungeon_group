package fil.coo.adventure.places.rooms;

import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.places.*;

public class Second extends Room {

    public Second(Dungeon type){
        super(type);
        this.nbMonster = 4;
        this.monsters = Monster.getRandomListMonsters(this.donjon.getActual_level(), nbMonster);
    }

}