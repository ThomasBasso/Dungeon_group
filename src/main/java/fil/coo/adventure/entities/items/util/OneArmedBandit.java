package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.utils.Constant;
import fil.coo.adventure.entities.Poison;

import java.util.Random;

public class OneArmedBandit{

    public void isUsedBy(Player player) {
        // Instancie une variable random rand
        Random rand = new Random();
        // Instancie une liste symbols constantes
        String[] symbols = Constant.SYMBOLES;
        
        // Tire 3 symboles au hasard parmi la liste
        String symbol1 = symbols[rand.nextInt(symbols.length)];
        String symbol2 = symbols[rand.nextInt(symbols.length)];
        String symbol3 = symbols[rand.nextInt(symbols.length)];
        
        // Affiche le nom des symboles tirés et une icône en fonction de l'unicode utilisé par l'IDE
        System.out.println("Vous avez tiré le levier, voici les symboles sortis : ");
        System.out.print("  ");
        printSymbol(symbol1);
        System.out.print("  ");
        printSymbol(symbol2);
        System.out.print("  ");
        printSymbol(symbol3);
        System.out.println();
        
        // Vérifie que les 3 symboles sont égaux
        if (symbol1.equals(symbol2) && symbol2.equals(symbol3)) {
            System.out.println("Vous avez gagné !");
            // Soigne toute la vie
            if (symbol1.equals("coeur")) {
                player.setLifePoints(player.getMaxLifePoints());
                System.out.println("Toute votre vie est soignée !");
            } // Augmentation de Force de 50% de façon permanente
            else if (symbol1.equals("épée")) {
                int newStrength = (int)(player.getStrength() * 1.5);
                player.addStrength(newStrength);
                System.out.println("Votre force a augmentée de 50% de façon permanente !");
            } //Gagne de 500 or
            else if (symbol1.equals("or")) {
                player.addGold(500);
                System.out.println("Vous avez reçu 500 gold !");
            } //Empoisonnement 
            else if (symbol1.equals("poison")) {
                // Applique le poison au joueur pour une durée limitée
                player.addNegativeStatus(new Poison());
                System.out.println("Mince, vous avez été empoisoné !");
            }
        } else {
            System.out.println("Les 3 symboles ne sont pas identiques.");
        }
    }

    // Méthode affichant le nom du symbole + son icône
    private void printSymbol(String symbol) {
        switch(symbol) {
            case "coeur":
                System.out.print("coeur \u2764 ");
                break;
            case "éclair":
                System.out.print("éclair \u26A1 ");
                break;
            case "épée":
                System.out.print("épée \u2694 ");
                break;
            case "or":
                System.out.print("or \uD83D\uDCB0 ");
                break;
            case "poison":
                System.out.print("poison \u2620 ");
                break;
        }
    }
}

