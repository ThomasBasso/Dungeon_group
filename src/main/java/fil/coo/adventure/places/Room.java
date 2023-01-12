package fil.coo.adventure.places;

import java.util.*;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.actions.Actions;
import fil.coo.adventure.entities.monsters.Monster;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.entities.items.util.GoldChest;
import fil.coo.adventure.entities.items.util.LifePotion;
import fil.coo.adventure.entities.items.util.OneArmedBandit;
import fil.coo.adventure.entities.items.util.StrengthPotion;
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

	public void addNeighbour(Direction d, Room r) {
		this.neighbour.put(d, r);
	}

	public Set<Direction> getPossibleDirections() {
		return this.neighbour.keySet();
	}

	public Room getNeighbour(Direction d) {
		return this.neighbour.get(d);
	}

	public Dungeon getDonjon() {
		return this.donjon;
	}

	public void setNbRoomMax(int nbRoomMax) {
		this.nbRoomMax = nbRoomMax;
	}

	/**
	 * Permet n'initialiser les voisins de notre room principale
	 */
	public void initializeNeighbour() {
		this.addNeighbour(Direction.W, new First(this.donjon));
		this.addNeighbour(Direction.E, new Second(this.donjon));
		this.addNeighbour(Direction.S, new Third(this.donjon));
		this.nbRoomMax = this.neighbour.size();
	}

	/**
	 * Permet à l'utilisateur de choisir dans quelle room il souhaite aller
	 */
	public void chooseRoom() {
		Set<Direction> directionsPossible = this.getPossibleDirections();
		ArrayList<String> directionPossibleName = new ArrayList<>();
		String result;
		do {
			System.out.println("Dans quelle direction souhaites tu aller ? ");
			for (Direction d : directionsPossible) {
				directionPossibleName.add(d.name());
				System.out.println(d.name() + " - " + d);
			}
			Scanner scanner = new Scanner(System.in);
			result = scanner.nextLine().toUpperCase();
		} while (!directionPossibleName.contains(result));
		Direction directionChoose = Direction.valueOf(result);
		Room nextRoom = this.getNeighbour(directionChoose);
		this.neighbour.remove(directionChoose);
		this.runRoom(nextRoom);
	}

	/**
	 * Permet de réaliser les combats
	 * 
	 * @param currentRoom la room actuelle
	 */
	private void runRoom(Room currentRoom) {
		int indexRoom = this.nbRoomMax - this.neighbour.size();
		System.out.println(
				"\nVous être actuellement dans la room " + indexRoom + " sur " + this.nbRoomMax + ", Bon courage !");
		while (!currentRoom.endFight()) {
			Monster currentMonster = currentRoom.monsters.get(0);
			currentRoom.playerChooseActionInFight(currentMonster);
			System.out.println("Nombre d'adversaires restants dans la salle :" + currentRoom.monsters.size()+"\n");
		}
		if (this.player.getLifePoints() <= 0) {
			this.lose = true;
		} else {
			System.out.println("Fin de la room " + indexRoom+"\n");
			this.getEndRoomReward(player);
			String result;
			if (indexRoom < this.nbRoomMax) {
				do {
					System.out.println("\nQue voulez-vous faire ?\n 1 - Aller dans la room suivante \n " +
							"2 - Regarder les monstres de la room suivante \n " +
							"3 - Arrêter la partie");
					Scanner scanner = new Scanner(System.in);
					result = scanner.nextLine();
				} while (!result.equals("1") && !result.equals("2") && !result.equals("3") && !result.equals("4"));
				// if (result.equals("2")) {
				// 	Actions.Look()
				// }
				if (result.equals("3")) {
					this.stopGame = true;
				}
			}
		}
	}

	/**
	 * Permet au joueur de faire des actions durant le combat
	 * 
	 * @param monster le monstre actuel contre lequel se trouve le joueur
	 */
	public void playerChooseActionInFight(Monster monster) {
		System.out.println("\nQuelle action souhaites tu réaliser ? \n 1 - Attaquer le monstre " + monster.getName() +
				"\n 2 - Accéder à ton inventaire ");
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		switch (result) {
			case "1" -> {
				player.attack(monster);
				if (monster.getLifePoints() < 1) {
					this.monsters.remove(monster);
				}
			}
			case "2" -> this.player.askToUseItem();
			default -> this.playerChooseActionInFight(monster);
		}
	}

	/**
	 * Permet de savoir si c'est la fin d'un combat
	 * 
	 * @return true si oui, false sinon
	 */
	protected boolean endFight() {
		return this.player.getLifePoints() <= 0 || this.monsters.isEmpty();
	}

	/**
	 * Permet de savoir si le joueur souhaite stopper la partie
	 * 
	 * @return true si le souhaite, false sinon
	 */
	public boolean isStopGame() {
		return stopGame;
	}

	/**
	 * Permet de savoir si tous les voisins ont été parcourus
	 * 
	 * @return si le donjon est fini
	 */
	public boolean donjonFinish() {
		return this.neighbour.isEmpty();
	}

	/**
	 * Permet de savoir si le joueur a perdu le combat
	 * 
	 * @return true si a perdu
	 */
	public boolean isLose() {
		return lose;
	}

	public void getEndRoomReward(Player player) {
		Random rand = new Random();
		int n = rand.nextInt(5); // Génère un nombre entre 0 et 5
		switch (n) {
			case 0:
				System.out.println("La salle abritait un coffre !");
				GoldChest gb = new GoldChest();
				gb.isUsedBy(player);
				break;
			case 1:
				System.out.println("La salle abritait une boite mystère !");
				OneArmedBandit oam = new OneArmedBandit();
				oam.isUsedBy(player);
				break;
			case 2:
				System.out.println("La salle contenait une potion !");
				System.out.println("Vous avez gagné une potion de vie.");
				player.getInventory().addItems(new LifePotion());
				break;
			case 3:
				System.out.println("La salle contenait une potion !");
				System.out.println("Vous avez gagné une potion de force.");
				player.getInventory().addItems(new StrengthPotion());
				break;
			case 4:
				System.out.println("Aucun objet n'apparaît.");
				break;
		}
	}
}
