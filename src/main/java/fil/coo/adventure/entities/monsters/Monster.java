package fil.coo.adventure.entities.monsters;

import java.util.ArrayList;
import java.util.Random;

import fil.coo.adventure.entities.GameCharacters;
import fil.coo.adventure.entities.monsters.util.*;
import fil.coo.adventure.places.Dungeon;

public abstract class Monster extends GameCharacters {
	public Monster(int lP, int st) {
		super(lP, st);
		Random r = new Random();
		this.gold = r.nextInt();
	}

	/**
	 * Permet de récupérer une liste de monstre random en fonction du level et du nombre souhaité
	 * @param level le niveau du donjon
	 * @param nbMonster le nombre de monstres souhaité
	 * @return une liste de nbMonster pour le niveau souhaité
	 */
	public static ArrayList<Monster> getRandomListMonsters(Dungeon.TYPE level, int nbMonster){
		ArrayList<Monster> myListMonsters = new ArrayList<>();
		ArrayList<Monster> default_list = new ArrayList<>();
		default_list.add(new Slime());
		default_list.add(new ZombiesHord());
		default_list.add(new Goblin());
		ArrayList<Monster> medium_list = new ArrayList<>(default_list);
		medium_list.add(new Looter());
		medium_list.add(new Kraken());
		medium_list.add(new Orc());
		ArrayList<Monster> hard_list = new ArrayList<>(medium_list);
		hard_list.add(new Dragon());
		hard_list.add(new Balrog());
		switch (level){
			case EASY -> myListMonsters = getRandomMonsterFrom(default_list,nbMonster);
			case MEDIUM -> myListMonsters = getRandomMonsterFrom(medium_list,nbMonster);
			case HARD -> myListMonsters = getRandomMonsterFrom(hard_list,nbMonster);
		}
		return myListMonsters;
	}

	/**
	 * Permet de récupérer un monstre au hasard appartenant à une certaine liste
	 * @param monsterArrayList la liste contenant les monstres souhaitaient
	 * @return un monstre au hasard
	 */
	private static Monster getRandomMonster(ArrayList<Monster> monsterArrayList){
		int nbElement = monsterArrayList.size();
		int index = (int) Math.floor(Math.random() * nbElement);
		return monsterArrayList.get(index);
	}

	/**
	 * Permet de récupérer une liste de monstre
	 * @param defaultList la liste des monstres disponible
	 * @param nbMonster le nombre de monstres souhaité
	 * @return une liste composée de nbMonster appartenant à la liste defaultList
	 */
	private static ArrayList<Monster> getRandomMonsterFrom(ArrayList<Monster> defaultList, int nbMonster) {
		ArrayList<Monster> resultList = new ArrayList<>();
		for (int i = 0; i < nbMonster; i++) {
			resultList.add(getRandomMonster(defaultList));
		}
		return resultList;
	}
}
