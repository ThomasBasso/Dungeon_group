package fil.coo.adventure.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.places.Room;

public class Fonctions {
	static String path = Constant.PLAYER_FILE;

	/**
	 * Save player statistics in a file so he can load his profile at launch of the game
	 * @param player
	 * @throws IOException
	 */
	public static void savePlayerInfo(Player player) throws IOException {
		try (FileWriter writer = new FileWriter(path)) {
			writer.write(player.getName() + "-" + player.getLifePoints() + "-" + player.getStrength() + "-"
					+ player.getDefense() + "-" + player.getGold() + "-" + player.getInventoryNames() + "-"
					+ player.getArmorsStatus() + "-" +player.getWeaponsStatus()+"-");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}

	/**
	 * @return A new player with loaded statistics
	 * @throws IOException
	 */
	public static Player getPlayerInfos() throws IOException {
		String[] values;
		Player player;
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();

		values = line.split("-");
		player = new Player(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),
				Integer.parseInt(values[3]), Integer.parseInt(values[4]), values[5], values[6], values[7], values[8], values[9]);
		return player;
	}

	/**
	 * Permet d'enregistrer un joueur sur une nouvelle ligne
	 * 
	 * @param player le joueur à enregistrer
	 */
	public static void saveNewLine(Player player, Room room) {
		String s = player.getName() + "-" + player.getLifePoints() + "-" + player.getStrength() + "-"
				+ player.getDefense() + "-" + player.getGold() + "-" + player.getInventoryNames() + "-"
				+ player.getArmorsStatus() + "-" + player.getWeaponsStatus()+"-"+room.getStringPossibleDirections()+"-"+room.getDonjon().getCurrentLevel();
		try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
			writer.println(s);
			writer.close();
		} catch (IOException e) {
			System.out.println("erreur");
		}
	}

	/**
	 * Permet de savoir s'il y a des parties enregistrées
	 * 
	 * @return true si oui sinon false
	 */
	public static boolean saveGameAvailable() {
		File file = new File(path);
		long size = file.length();
		return size != 0;
	}

	/**
	 * Permet de récupérer la liste de toutes les parties enregistrées
	 * 
	 * @return la liste de toutes les parties enregistrée
	 */
	public static ArrayList<Player> getAllGameSave() {
		ArrayList<Player> allPlayers = new ArrayList<>();
		Player player;
		try {
			FileInputStream file = new FileInputStream(path);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] infos = scanner.nextLine().split("-");
				player = new Player(infos[0], Integer.parseInt(infos[1]),
						Integer.parseInt(infos[2]), Integer.parseInt(infos[3]),
						Integer.parseInt(infos[4]), infos[5], infos[6], infos[7], infos[8], infos[9]);
				allPlayers.add(player);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return allPlayers;
	}

	/**
	 * Permet de supprimer une partie de notre choix
	 * 
	 * @param player la partie à supprimer
	 */
	public static void removeLine(Player player) {
		String s = player.getName();
		try {
			File current = new File(path);
			File tmp = new File("_temp_");
			PrintWriter writer = new PrintWriter(new FileWriter(tmp, true));
			Files.lines(current.toPath()).filter(line -> !line.contains(s)).forEach(writer::println);
			// writer.flush();
			writer.close();
			current.delete();
			tmp.renameTo(current);
		} catch (IOException e) {
			System.out.println("erreur");
		}
	}

	/**
	 * @param piece		Name of a armor object
	 * @return Int		Number of armor it gives to the player
	 */
	public static int getArmorFromObject(String piece) {
		int armor;
		switch (piece) {
			case "Gauntlets":
				armor = 4;
				break;
			case "Helmet":
				armor = 6;
				break;
			case "Breast_plate":
				armor = 8;
				break;
			case "Trousers":
				armor = 10;
				break;
			case "Boots":
				armor = 2;
				break;
			default:
				armor = 0;
				break;
		}
		return armor;
	}

	/**
	 * @param piece			Name if a weapon object
	 * @return	Int			Number of strenght it gives to the player
	 */
	public static int getStrengthFromObject(String piece) {
		int strength;
		switch (piece) {
			case "dagger":
				strength = 5;
				break;
			case "sword":
				strength = 10;
				break;
			case "gun":
				strength = 20;
				break;
			default:
				strength = 0;
				break;
		}
		return strength;
	}

}
