package fil.coo.adventure.places.rooms;

import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.places.*;

public class First extends Room {


    public First(Dungeon type){
        super(type);
        this.nbMonster = 2;
        this.monsters = Monster.getRandomListMonsters(this.donjon.getActual_level(), nbMonster);
    }

}