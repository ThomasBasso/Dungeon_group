package fil.coo.adventure.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fil.coo.adventure.entities.Player;


public class fonctions {
    static String path = Constant.PLAYER_FILE;

    public static void savePlayerInfo(Player player) throws IOException {   ;
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(player.getName()+"-"+player.getLifePoints()+"-"+player.getStrength()+"-"
                         +player.getDefense()+"-"+player.getGold()+"-"+player.getInventoryNames());
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
        if (values.length>5) {
            player = new Player(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), values[5]);
        } else {
            player = new Player(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), "");
        }

        return player;
    }
}
