package fil.coo.adventure.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import fil.coo.adventure.entities.Player;


public class Fonctions {
    static String path = Constant.PLAYER_FILE;

    public static void savePlayerInfo(Player player) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(player.getName()+"-"+player.getLifePoints()+"-"+player.getStrength()+"-"+player.getDefense()+"-"+player.getGold()+"-"+player.getInventoryNames()+"-"+player.getArmorsStatus()+"-"+player.getWeaponsStatus());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    public static Player getPlayerInfos() throws IOException {
        String[] values;
        Player player;
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        values = line.split("-");
        player = new Player(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), values[5], values[6], values[7]);
        return player;
    }

    /**
     * Permet d'enregistrer un joueur sur une nouvelle ligne
     * @param player le joueur à enregistrer
     */
    public static void saveNewLine(Player player){
        String s = player.getName()+"-"+player.getLifePoints()+"-"+player.getStrength()+"-"
                +player.getDefense()+"-"+player.getGold()+"-"+player.getInventoryNames()+"-"+player.getArmorsStatus()+"-"+player.getWeaponsStatus();
        try (PrintWriter writer = new PrintWriter(new FileWriter(path,true))){
            writer.println(s);
            }
        catch (IOException e) {
            System.out.println("erreur");
        }
    }

    /**
     * Permet de savoir s'il y a des parties enregistrées
     * @return true si oui sinon false
     */
    public static boolean saveGameAvailable(){
        File file = new File(path);
        long size = file.length();
        return size != 0;
    }

    /**
     * Permet de récupérer la liste de toutes les parties enregistrées
     * @return la liste de toutes les parties enregistrée
     */
    public static ArrayList<Player> getAllGameSave() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        Player player;
        try {
            FileInputStream file = new FileInputStream(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String[] elementPlayer = scanner.nextLine().split("-");
                player = new Player(elementPlayer[0], Integer.parseInt(elementPlayer[1]), Integer.parseInt(elementPlayer[2]), Integer.parseInt(elementPlayer[3]),
                                    Integer.parseInt(elementPlayer[4]), elementPlayer[5], elementPlayer[6], elementPlayer[7]);
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
     * @param player la partie à supprimer
     */
    public static void removeLine(Player player){
        String s =  player.getName()+"-"+player.getLifePoints()+"-"+player.getStrength()+"-"
                +player.getDefense()+"-"+player.getGold();
        try {
            File actual = new File(path);
            File tmp = new File("_temp_");
            PrintWriter writer = new PrintWriter(new FileWriter(tmp,true));
            Files.lines(actual.toPath()).filter(line -> !line.contains(s)).forEach(writer::println);
            //writer.flush();
            writer.close();
            tmp.renameTo(actual);
        }
        catch (IOException e) {
            System.out.println("erreur");
        }
    }

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
