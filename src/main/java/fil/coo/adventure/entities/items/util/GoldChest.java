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
        int a = rand.nextInt(armorPieces.length); // Génère un nombre aléatoire en fonction du nombre d'élément dans la liste armorPieces
        int w = rand.nextInt(weaponPieces.length); // Génère un nombre aléatoire en fonction du nombre d'élément dans la liste weaponPieces
        int n = rand.nextInt(2); // tire un nombre entre 0 et 1 pour définir si le joueur tirera une arme ou une armure
        
        /*******
         WEAPON
         ********/
        if (n==1) {
            // piece prend une valeur au hasard de la liste des weapons et on l'affiche
            piece = weaponPieces[w];
            System.out.println("Vous avez tiré au hasard : " + piece);

            // Le joueur n'ajoute pas l'arme s'il l'a déjà
            if (player.getWeaponEquiped().get(piece)) {
                System.out.println("Dommage ! Vous aviez déjà cet équipement !");
            } else {
                // Le joueur ajoute l'arme obtenu et se l'équipe, cela augmente sa strenght du montant en fonction de l'arme obtenu
                int strengthBonus = Fonctions.getStrengthFromObject(piece);
                System.out.println("Bravo ! Vous avez maintenant "+piece+" qui ajoute "+strengthBonus+" de force !");
                // Remplace l'ancienne valeur par la nouvelle à sa strength
                player.getWeaponEquiped().replace(piece, true);
                player.addStrength(strengthBonus);
            }

        /*******
         ARMOR
         ********/
        } else {
            // piece prend une valeur au hasard de la liste des armors et on l'affiche
            piece = armorPieces[a];
            System.out.println("Vous avez tiré au hasard : " + piece);

            // Le joueur n'ajoute pas l'armure s'il l'a déjà
            if (player.getArmorEquiped().get(piece)) {
                System.out.println("Dommage ! Vous aviez déjà cet équipement !");
            } else {
                // Le joueur ajoute l'armure obtenu et se l'équipe, cela augmente sa defense du montant en fonction de l'armure obtenu
                int armorBonus = Fonctions.getArmorFromObject(piece);
                System.out.println("Bravo ! Vous avez maintenant "+piece+" qui ajoute "+armorBonus+" d'armure !");
                // Remplace l'ancienne valeur par la nouvelle à sa defense
                player.getArmorEquiped().replace(piece, true);
                player.addDefense(armorBonus);
            }
        }
    }
}