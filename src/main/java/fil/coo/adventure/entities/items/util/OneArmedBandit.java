package fil.coo.adventure.entities.items.util;

import fil.coo.adventure.entities.Player;
import fil.coo.adventure.entities.items.Item;
import fil.coo.adventure.entities.Poison;

import java.util.Random;

public class OneArmedBandit extends Item {

    public OneArmedBandit() {
		super("Boite mystère", 0, 0, 0, 0, 40);
		//TODO Auto-generated constructor stub
	}

	@Override
    public void isUsedBy(Player player) {
        Random rand = new Random();
        String[] symbols = {"coeur", "éclair", "épée", "or", "poison"};
        
        String symbol1 = symbols[rand.nextInt(symbols.length)];
        String symbol2 = symbols[rand.nextInt(symbols.length)];
        String symbol3 = symbols[rand.nextInt(symbols.length)];
        
        System.out.println("Vous avez tiré le levier, voici les symboles sortis : ");
        System.out.println(symbol1 + " " + symbol2 + " " + symbol3);
        
        if (symbol1.equals(symbol2) && symbol2.equals(symbol3)) {
            System.out.println("Vous avez gagné !");
            if (symbol1.equals("coeur")) {
                player.setLifePoints(player.getMaxLifePoints());
                System.out.println("Toute votre vie est soignée !");
            } else if (symbol1.equals("éclair")) {
                player.removeNegativeStatus();
                System.out.println("Vous avez été purifié de tous vos effets négatifs !");
            } else if (symbol1.equals("épée")) {
                int newStrength = (int)(player.getStrength() * 1.5);
                player.setStrength(newStrength);
                System.out.println("Votre force a augmentée de 50% de façon permantente !");
            } else if (symbol1.equals("or")) {
                player.addGold(500);
                System.out.println("Vous avez reçu 500 gold !");
            } else if (symbol1.equals("poison")) {
                player.addNegativeStatus(new Poison());
                System.out.println("Mince, vous avez été empoisoné !");
            }
        } else {
            System.out.println("Aucun symbole ne correpond.");
        }
    }

}

