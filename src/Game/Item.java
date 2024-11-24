package src.Game;

import java.util.ArrayList;
import java.util.List;


public class Item {
    public String itemName;
    public String itemDescription;
    public int itemPrice;
    public int attackBoost;
    public int defenseBoost;
    public int magicBoost;


    public Item() {
        this.itemName = "Default item";
        this.itemDescription = "A default item.";
        this.itemPrice = 0;
        this.attackBoost = 0;
        this.defenseBoost = 0;
        this.magicBoost = 0;
    }


    public String generateDescription() {
        StringBuilder description = new StringBuilder();
        List<String> boosts = new ArrayList<>();


        // Store stat boost information into ArrayList
        if (defenseBoost > 0) {
            boosts.add("DEF (+ " + defenseBoost + ")");
        }
        if (attackBoost > 0) {
            boosts.add("ATK (+ " + attackBoost + ")");
        }
        if (magicBoost > 0) {
            boosts.add("MAG (+ " + magicBoost + ")");
        }


        // Display stat boost information
        for (String boost : boosts) {
            description.append(boost).append("\n");
        }
        return description.toString();
    }

    @Override
    public String toString() {
        return String.format("""
           Item Name: %s
           Item Price: %d coins
           Item Description: \n%s
           """, itemName, itemPrice, itemDescription);
    }

    public class FortuneCookie extends Item {
        public FortuneCookie() {
            // Inherit superclass
            super();


            // Set item name
            this.itemName = "Fortune Cookie";


            // Set item stats
            this.magicBoost = 3;


            // Set item price
            this.itemPrice = 5;


            // Item description line
            String customDescription = """
               A crunchy snack that promises a fortune.
               """;


            // Item stat boost information
            String boostDescription = generateDescription();


            // Concatenate item description and stat boost information
            this.itemDescription = customDescription + boostDescription;
        }
    }

    public class NaglfarFragment extends Item {
        public NaglfarFragment() {
            // Inherit superclass
            super();


            // Set item name
            this.itemName = "Naglfar Fragment";


            // Set item stats
            this.defenseBoost = 10;


            // Set item price
            this.itemPrice = 25;


            // Item description line
            String customDescription = """
       A shard from the ship of doom, Naglfar, said to be crafted from the nails of dead men. This dark relic
       emits a faint, unsettling aura, as if it carries the weight of countless souls. This fragment offers
       powerful protection, drawing strength from its dark and ancient origins.
       """;


            // Item stat boost information
            String boostDescription = generateDescription();


            // Concatenate item description and stat boost information
            this.itemDescription = customDescription + boostDescription;
        }
    }

    public class Sock extends Item {
        public Sock() {
            // Inherit superclass
            super();


            // Set item name
            this.itemName = "Sock";


            // Set item stats
            this.defenseBoost = 3;


            // Set item price
            this.itemPrice = 5;


            // Item description line
            String customDescription = """
               ... It's just a sock.
               Its pungent odor may not be pleasant, but it has a way of keeping enemies at bay.
               Some say the smell is worse than the most powerful of curses.
               """;


            // Item stat boost information
            String boostDescription = generateDescription();


            // Concatenate item description and stat boost information
            this.itemDescription = customDescription + boostDescription;
        }
    }


}