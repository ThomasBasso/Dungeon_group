package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.utils.Constant;
import fil.coo.adventure.entities.Poison;

import java.util.Random;

public class OneArmedBandit{

    public void isUsedBy(Player player) {
        Random rand = new Random();
        String[] symbols = Constant.SYMBOLES;
        
        String symbol1 = symbols[rand.nextInt(symbols.length)];
        String symbol2 = symbols[rand.nextInt(symbols.length)];
        String symbol3 = symbols[rand.nextInt(symbols.length)];
        
        System.out.println("Vous avez tiré le levier, voici les symboles sortis : ");
        System.out.print("  ");
        printSymbol(symbol1);
        System.out.print("  ");
        printSymbol(symbol2);
        System.out.print("  ");
        printSymbol(symbol3);
        System.out.println();
        
        if (symbol1.equals(symbol2) && symbol2.equals(symbol3)) {
            System.out.println("Vous avez gagné !");
            //Soin vie
            if (symbol1.equals("coeur")) {
                player.setLifePoints(player.getMaxLifePoints());
                System.out.println("Toute votre vie est soignée !");
            } // Augmentation de Force 
            else if (symbol1.equals("épée")) {
                int newStrength = (int)(player.getStrength() * 1.5);
                player.addStrength(newStrength);
                System.out.println("Votre force a augmentée de 50% de façon permanente !");
            } //Gagne de l'or
            else if (symbol1.equals("or")) {
                player.addGold(500);
                System.out.println("Vous avez reçu 500 gold !");
            } //Empoisonnement 
            else if (symbol1.equals("poison")) {
                player.addNegativeStatus(new Poison());
                System.out.println("Mince, vous avez été empoisoné !");
            }
        } else {
            System.out.println("Aucun symbole ne correspond.");
        }
    }

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

