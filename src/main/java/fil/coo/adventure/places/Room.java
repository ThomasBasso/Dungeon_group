package fil.coo.adventure.places;

import java.util.*;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.actions.Actions;
import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.places.directions.Direction;
import fil.coo.adventure.places.rooms.*;

public class Room {
	protected List<Monster> monsters;
	protected Dungeon donjon;
	protected Player player;
	protected List<Item> items;
	protected Map<Direction, Room> neighbour;
	private int nbRoomMax = 0;
	protected int nbMonster;
	private boolean lose = false;
	private boolean stopGame = false;

	public Room() {
		this.monsters = new ArrayList<Monster>();
		this.items = new ArrayList<Item>();
		this.neighbour = new HashMap<Direction, Room>();
	}

	public Room(Dungeon donjon) {
		this.donjon = donjon;
		this.monsters = new ArrayList<Monster>();
		this.items = new ArrayList<Item>();
		this.neighbour = new HashMap<Direction, Room>();
		this.player = this.donjon.getPlayer();
	}
	
	public void removeMonster(Monster monster) {
		this.monsters.remove(monster);
	}
	
	public List<Monster> getMonsters() {
		return this.monsters;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
	public List<Item> getItems() {
		return this.items;
	}
	
	public boolean isExit() {
		return false;
	}
	
	private void addNeighbour(Direction d, Room r) {
		this.neighbour.put(d, r);
	}

	public Set<Direction> getPossibleDirections() {
		return this.neighbour.keySet();
	}
	
	public Room getNeighbour(Direction d) {
		return this.neighbour.get(d);
	}


	/**
	 * Permet n'initialiser les voisins de notre room principale
	 */
	public void initializeNeighbour(){
		this.addNeighbour(Direction.W,new First(this.donjon));
		this.addNeighbour(Direction.E, new Second(this.donjon));
		this.addNeighbour(Direction.S, new Third(this.donjon));
		this.nbRoomMax = this.neighbour.size();
	}

	/**
	 * Permet à l'utilisateur de choisir dans quelle room il souhaite aller
	 */
	public void chooseRoom(){
		Set<Direction> directionsPossible = this.getPossibleDirections();
		ArrayList<String> directionPossibleName = new ArrayList<>();
		String result;
		do {
			System.out.println("Dans quelle direction souhaites tu aller ? ");
			for (Direction d: directionsPossible) {
				directionPossibleName.add(d.name());
				System.out.println(d.name()+ " - " +d);
			}
			Scanner scanner = new Scanner(System.in);
			result = scanner.nextLine().toUpperCase();
		} while (!directionPossibleName.contains(result));
		Direction directionChoose = Direction.valueOf(result);
		Room nextRoom = this.getNeighbour(directionChoose);
		this.neighbour.remove(directionChoose);
		this.runGame(nextRoom);
	}

	/**
	 * Permet de réaliser les combats
	 * @param actualRoom la room actuelle
	 */
	private void runGame(Room actualRoom) {
		int indexRoom = this.nbRoomMax - this.neighbour.size();
		System.out.println("\nVous être actuellement dans la room " + indexRoom + " sur " + this.nbRoomMax + " !! Bon courage");
		while (!actualRoom.endFight()) {
			Monster actualMonster = actualRoom.monsters.get(0);
			actualRoom.playerChooseActionInFight(actualMonster);
			System.out.println("adversaire :"  + actualRoom.monsters.size());
			System.out.println("vie : " + this.player.getLifePoints());
		}
		if (this.player.getLifePoints() <= 0){
			this.lose = true;
		}
		else{
			System.out.println("Fin de la room " + indexRoom);
			String result;
			if (indexRoom < this.nbRoomMax) {
				do {
					System.out.println("\nQue voulez-vous faire ?\n 1 - Aller dans la room suivante \n " +
							"2 - Regarder les monstres de la room suivante \n 3 - S'équiper \n " +
							"4 - Arrêter la partie");
					Scanner scanner = new Scanner(System.in);
					result = scanner.nextLine();
				} while (!result.equals("1") && !result.equals("2") && !result.equals("3") && !result.equals("4"));
				if(result.equals("2")) {
					//TODO: Action look
				}
				if(result.equals("3")) {
					//TODO: Action s'equiper
				}
				if (result.equals("4")) {
					this.stopGame = true;
				}
			}
		}
	}


	/**
	 * Permet au joueur de faire des actions durant le combat
	 * @param monster le monstre actuel contre lequel se trouve le joueur
	 */
	public void playerChooseActionInFight(Monster monster){
		System.out.println("\nQuelle action souhaites tu réaliser ? \n 1 - Attaquer le monstre " + monster.getName() +
				"\n 2 - Accéder à ton inventaire ");
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		switch (result){
			case "1" -> {
				this.attackListMonster(); //TODO: Appel la fonction attack pour le joueur et le monstre
				if (!this.monsters.isEmpty()) {
					Monster monsterAttack = this.monsters.get(0);
					monsterAttack.attack(this.player);
				}
			}
			case "2" -> this.player.askToUseItem();
			default -> this.playerChooseActionInFight(monster);
		}
	}

	/**
	 * Permet de savoir si c'est la fin d'un combat
	 * @return true si oui, false sinon
	 */
	protected boolean endFight(){
		return this.player.getLifePoints() <= 0 || this.monsters.isEmpty();
	}

	/**
	 * Permet de savoir si le joueur souhaite stopper la partie
	 * @return true si le souhaite, false sinon
	 */
	public boolean isStopGame(){
		return stopGame;
	}

	/**
	 * Permet de savoir si tous les voisins ont été parcourus
	 * @return si le donjon est fini
	 */
	public boolean donjonFinish() {
		return this.neighbour.isEmpty();
	}

	/**
	 * Permet de savoir si le joueur a perdu le combat
	 * @return  true si a perdu
	 */
	public boolean isLose() {
		return lose;
	}

	/**
	 * Permet de gérer l'attaque du player sur le premier monstre de la liste et le supprime s'il est mort
	 */
	protected void attackListMonster(){
		Monster monster = this.monsters.get(0);
		this.player.attack(monster);
		if (monster.getLifePoints()<=0)
			this.monsters.remove(monster);
	}
}
