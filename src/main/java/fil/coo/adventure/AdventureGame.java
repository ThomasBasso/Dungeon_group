package fil.coo.adventure;

import fil.coo.adventure.entities.*;
import fil.coo.adventure.places.*;
import fil.coo.adventure.utils.Fonctions;

import java.util.ArrayList;
import java.util.Scanner;

public class AdventureGame {
	private final Player player;
	private Dungeon dungeon;
	private Room currentRoom;

	/**
	 * Permet de créer un nouvel adventureGame avec un nouveau joueur
	 */
	public AdventureGame() {
		String name = this.isNamePlayer();
		this.player = new Player(name);
		this.dungeon = new Dungeon(this.player);
		this.currentRoom = new Room(this.dungeon);

	}

	/**
	 * Permet de créer une adventureGame lorsque l'on connait le joueur
	 * 
	 * @param player le joueur
	 */
	public AdventureGame(Player player) {
		this.player = player;
		this.dungeon = new Dungeon(this.player);
		this.currentRoom = new Room(this.dungeon);
	}

	public Player getPlayer() {
		return this.player;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Permet de créer une aventure ou d'en continuer une ancienne selon l'envi du
	 * joueur
	 * 
	 * @return l'object AdventureGame que l'on utilisera
	 */
	public static AdventureGame start() {
		System.out.println("Bienvenu dans le jeu du Donjon " + new String(Character.toChars(0x1f3f0)));
		if (Fonctions.saveGameAvailable()) {
			ArrayList<Player> playersList = Fonctions.getAllGameSave();
			int sizeList = playersList.size();
			int diff = 2;
			Scanner scanner = new Scanner(System.in);
			String result = "";
			ArrayList<String> possible = new ArrayList<>();
			possible.add("1");
			do {
				System.out.println("Vous avez au moins une partie sauvegardée, souhaitez vous : \n" +
						" 1 - Commencer une nouvelle partie");

				for (int i = 0; i < sizeList; i++) {
					int index = i + diff;
					possible.add(String.valueOf(index));
					System.out.println(" " + index + " - " + playersList.get(i).getName());
				}
				result = scanner.next();
			} while (!possible.contains(result));
			if (result.equals("1")) {
				System.out.println();
				return new AdventureGame();
			} else {
				int index = Integer.parseInt(result) - diff;
				Player player1 = playersList.get(index);
				Fonctions.removeLine(player1);
				return new AdventureGame(player1);
			}
		} else
			return new AdventureGame();
	}

	/**
	 * Permet de vérifier si le nom choisit existe déjà dans la sauvegarde
	 * 
	 * @return le nom du nouveau joueur
	 */
	private String isNamePlayer() {
		ArrayList<String> playersName = this.recoverAllPlayerNameUpperCase();
		System.out.println("Merci de bien vouloir choisir le nom de votre joueur :");
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		if (playersName.contains(result.toUpperCase())) {
			System.out.println("\nCe nom est déjà utilisé!! ");
			return isNamePlayer();
		}
		System.out.println("Bienvenu dans le jeu " + result + " !");
		return result;
	}

	/**
	 * Permet de récupérer la liste de tous les noms de joueur en majuscule
	 * 
	 * @return la liste des noms de joueurs en majuscule
	 */
	private ArrayList<String> recoverAllPlayerNameUpperCase() {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<Player> allPlayers = Fonctions.getAllGameSave();
		for (Player player1 : allPlayers)
			result.add(player1.getName().toUpperCase());
		return result;
	}

	/**
	 * Permet de lancer la partie après le choix du joueur
	 */
	protected void runGame() {
		System.out.println("\nBienvenu dans le donjon " + this.dungeon.getCurrentLevel());
		do {
			this.currentRoom.chooseRoom();
		} while (!this.currentRoom.isLose() && !this.currentRoom.donjonFinish() && !this.currentRoom.isStopGame());

		if (this.currentRoom.isLose() || this.currentRoom.isStopGame())
			optionEndGame();
		if (this.currentRoom.donjonFinish())
			this.optionEndDungeon(this.dungeon.getNextLevel());
	}

	/**
	 * Permet de demander au joueur s'il souhaite continuer de jouer ou arrêter la
	 * partie
	 * 
	 * @param nextLevel le prochain niveau du donjon
	 */
	protected void optionEndDungeon(Dungeon.TYPE nextLevel) {
		System.out.println("Bravo vous venez de terminer le donjon " + this.dungeon.getCurrentLevel() + " "
				+ new String(Character.toChars(0x1F3C6)));
		if (nextLevel != null) {
			System.out.println(
					"Vous avez désormais le choix entre : \n 1 - Passer au donjon suivant \n 2 - Quitter la partie");
			Scanner scanner = new Scanner(System.in);
			String value = scanner.next();
			switch (value) {
				case "1" -> {
					this.dungeon = new Dungeon(this.player, nextLevel);
					this.currentRoom.initializeNeighbour();
					this.runGame();
				}
				case "2" -> this.optionEndGame();
				default -> {
					System.out.println("Entrer soit 1 soit 2 uniquement");
					optionEndDungeon(nextLevel);
				}
			}
		} else {
			System.out.println(
					new String(Character.toChars(0x1F3C6)) + " Bravo vous avez terminé le jeu, vous êtes le meilleur !" +
							new String(Character.toChars(0x1F3C6)));
			optionEndGame();
		}
	}

	/**
	 * Permet de demander au joueur s'il souhaite enregistrer sa partie avant de
	 * partir s'il est encore en vie sinon le jeu se termine
	 */
	protected void optionEndGame() {
		if (this.player.getLifePoints() > 0 && this.dungeon.getNextLevel() != null) {
			System.out.println();
			System.out.println(
					"Tu souhaites donc arrêter de jouer alors que tu viens de terminer le donjon, si tu le souhaites" +
							"tu peux enregistrer ta partie pour la reprendre une prochaine fois. \n" +
							" Veux-tu enregistrer ta partie avant de quitter ?  OUI ou NON");
			Scanner scanner = new Scanner(System.in);
			String result = scanner.nextLine().toUpperCase();
			switch (result) {
				case "OUI" -> {
					Fonctions.saveNewLine(this.player);
					System.out.println("Enregistrement fini \nAu revoir " + new String(Character.toChars(0x1F44B)));
				}
				case "NON" -> System.out.println("Au revoir " + new String(Character.toChars(0x1F44B)));
				default -> optionEndGame();
			}
		} else
			System.out.println("Au revoir " + new String(Character.toChars(0x1F44B)));

	}
}
