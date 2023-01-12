package fil.coo.adventure.entities.monsters;

import java.util.ArrayList;
import java.util.Random;

import fil.coo.adventure.entities.GameCharacters;
import fil.coo.adventure.entities.monsters.util.*;
import fil.coo.adventure.places.Dungeon;

public abstract class Monster extends GameCharacters {
	static Random rand = new Random();
	static int randomNumber = rand.nextInt(15);

	public Monster(String name, int lP, int st) {
		super(name, lP, st, randomNumber);
	}

	/**
	 * Permet de récupérer une liste de monstre random en fonction du level et du nombre souhaité
	 * @param level le niveau du donjon
	 * @param nbMonster le nombre de monstres souhaité
	 * @return une liste de nbMonster pour le niveau souhaité
	 */
	public static ArrayList<Monster> getRandomListMonsters(Dungeon.TYPE level, int nbMonster){
		ArrayList<Monster> myListMonsters = new ArrayList<>();
		ArrayList<Class> default_list = new ArrayList<>();
		default_list.add(Slime.class);
		default_list.add(ZombiesHord.class);
		default_list.add(Goblin.class);
		ArrayList<Class> medium_list = new ArrayList<>(default_list);
		medium_list.add(Looter.class);
		medium_list.add(Kraken.class);
		medium_list.add(Orc.class);
		ArrayList<Class> hard_list = new ArrayList<>(medium_list);
		hard_list.add(Dragon.class);
		hard_list.add(Balrog.class);
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
	private static Monster getRandomMonster(ArrayList<Class> monsterArrayList){
		int nbElement = monsterArrayList.size();
		int index = (int) Math.floor(Math.random() * nbElement);
		try {
			return (Monster) monsterArrayList.get(index).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Permet de récupérer une liste de monstre
	 * @param defaultList la liste des monstres disponible
	 * @param nbMonster le nombre de monstres souhaité
	 * @return une liste composée de nbMonster appartenant à la liste defaultList
	 */
	private static ArrayList<Monster> getRandomMonsterFrom(ArrayList<Class> defaultList, int nbMonster) {
		ArrayList<Monster> resultList = new ArrayList<>();
		for (int i = 0; i < nbMonster; i++) {
			resultList.add(getRandomMonster(defaultList));
		}
		return resultList;
	}
}
