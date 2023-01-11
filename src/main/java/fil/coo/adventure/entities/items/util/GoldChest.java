package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.entities.Player;

import java.util.Random;
import java.util.Scanner;

public class GoldChest extends Item {

    public void isUsedBy(Player player) {
        Random rand = new Random();
        int n = rand.nextInt(5); // Génère un nombre aléatoire entre 0 et 4
        String[] armorPieces = {"Gauntlets", "Helmet", "Breast-plate", "Trousers", "Boots"};
        String piece = armorPieces[n];

        System.out.println("Vous avez tiré au hasard : " + piece);

        // équipe l'armure au joueur si celui-ci le veut
        Scanner sc = new Scanner(System.in);
        String input ;
        do{
            System.out.println("Souhaitez-vous vous équiper de la pièce d'armure "+ piece + " ? (o/n)");
            input = sc.nextLine().toLowerCase();
        } while(!input.equals("o") && !input.equals("n"));
        if (input.equals("o")) {
            player.equipArmor(piece);
        } else {
            System.out.println("Vous avez décidé de ne pas vous équiper de " + piece);
        }
    }
}