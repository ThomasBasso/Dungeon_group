package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.utils.Constant;
import fil.coo.adventure.utils.Fonctions;
import fil.coo.adventure.entities.Player;

import java.util.Random;

public class GoldChest{

    public void isUsedBy(Player player) {
        String piece;
        Random rand = new Random();
        String[] armorPieces = Constant.ARMOR;
        String[] weaponPieces = Constant.WEAPON;
        int a = rand.nextInt(armorPieces.length); // Génère un nombre aléatoire en fonction du nombre d'élément dans la liste
        int w = rand.nextInt(weaponPieces.length);
        int n = rand.nextInt(2);
        if (n==1) {
            piece = weaponPieces[w];
            System.out.println("Vous avez tiré au hasard : " + piece);

            if (player.getWeaponEquiped().get(piece)) {
                System.out.println("Dommage ! Vous aviez déjà cet équipement !");
            } else {
                int strengthBonus = Fonctions.getStrengthFromObject(piece);
                System.out.println("Bravo ! Vous avez maintenant "+piece+" qui ajoute "+strengthBonus+" de force !");
                player.getWeaponEquiped().replace(piece, true);
                player.addStrength(strengthBonus);
            }

        } else {
            piece = armorPieces[a];
            System.out.println("Vous avez tiré au hasard : " + piece);

            if (player.getArmorEquiped().get(piece)) {
                System.out.println("Dommage ! Vous aviez déjà cet équipement !");
            } else {
                int armorBonus = Fonctions.getArmorFromObject(piece);
                System.out.println("Bravo ! Vous avez maintenant "+piece+" qui ajoute "+armorBonus+" d'armure !");
                player.getArmorEquiped().replace(piece, true);
                player.addDefense(armorBonus);
            }
        }
    }
}