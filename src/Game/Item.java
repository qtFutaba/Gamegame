package src.Game;

import java.util.ArrayList;
import java.util.List;


public class Item {
    public String itemName;
    public String itemDescription;
    public int itemPrice;
    public int rarity;
    public int attackBoost;
    public int defenseBoost;
    public int magicBoost;
    public int healthBuff;
    public boolean isBuff;
    public boolean isAction;
    public boolean isMisc;

    public Item() {
        this.itemName = "Default item";
        this.itemDescription = "A default item.";
        this.rarity = 1;
        this.itemPrice = 0;
        this.attackBoost = 0;
        this.defenseBoost = 0;
        this.magicBoost = 0;
        this.healthBuff = 0;
        this.isBuff = false;
        this.isAction = false;
        this.isMisc = false;
    }

    @Override
    public String toString() {
        return itemDescription;
    }
    /// ///////////////////////////////////////////
    ///                ATTACK ITEMS
    /// ///////////////////////////////////////////

    public static class RingOfBone extends Item {
        public RingOfBone() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Ring of Bone";

            // Set item stats
            this.attackBoost = 9;

            // Set item price
            this.itemPrice = 35;

            // Set item rarity
            this.rarity = 3;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A ring made from the bone of an ancient warrior... or so they claim. " +
                    "It could be from a really big animal. Either way, it's got a mysterious aura. Wear at " +
                    "your own risk.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class JarOfMushrooms extends Item {
        public JarOfMushrooms() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Jar of Mushrooms";

            // Set item stats
            this.attackBoost = 6;

            // Set item price
            this.itemPrice = 22;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A jar of slightly moldy mushrooms. They might look questionable, but eat them, " +
                    "and you'll feel an attack boost so strong, it'll make you forget they were ever moldy... " +
                    "or that you even ate them. Afterward, your enemies might look a little more terrifying... " +
                    "which, of course, gives you all the more reason to hit harder.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class HellfirePepper extends Item {
        public HellfirePepper() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Hellfire Pepper";

            // Set item stats
            this.attackBoost = 3;

            // Set item price
            this.itemPrice = 8;

            // Set item rarity
            this.rarity = 1;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A pepper so hot that it could melt steel. Eat one, and your anger will " +
                    "flourish, making every hit feel like a thunderclap. Just be prepared to regret your decision " +
                    "later...";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    /// ///////////////////////////////////////////
    ///                DEFENSE ITEMS
    /// ///////////////////////////////////////////

    public static class NaglfarFragment extends Item {
        public NaglfarFragment() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Naglfar Fragment";

            // Set item stats
            this.defenseBoost = 10;

            // Set item price
            this.itemPrice = 40;

            // Set item rarity
            this.rarity = 3;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A shard from the ship of doom, Naglfar, said to be crafted from the " +
                    "nails of dead men. This dark relic emits a faint, unsettling aura, as if it carries the " +
                    "weight of countless souls. This fragment offers powerful protection, drawing strength " +
                    "from its dark and ancient origins.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class Sock extends Item {
        public Sock() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Sock";

            // Set item stats
            this.defenseBoost = 2;

            // Set item price
            this.itemPrice = 5;

            // Set item rarity
            this.rarity = 1;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "... It's just a sock.\nIts pungent odor may not be pleasant, but it has a " +
                    "way of keeping enemies at bay. Some say the smell is worse than the most powerful of curses.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class Nazar extends Item {
        public Nazar() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Nazar";

            // Set item stats
            this.defenseBoost = 7;

            // Set item price
            this.itemPrice = 25;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "An eye-shaped amulet that protects against the evil eye.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    /// ///////////////////////////////////////////
    ///                MAGIC ITEMS
    /// ///////////////////////////////////////////

    public static class CrownOfTheFallenKing extends Item {
        public CrownOfTheFallenKing() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Crown of the Fallen King";

            // Set item stats
            this.magicBoost = 10;

            // Set item price
            this.itemPrice = 40;

            // Set item rarity
            this.rarity = 3;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A crown once worn by an ancient king whose pyromaniac tendencies damned " +
                    "his kingdom. The remnants of his fiery reign still linger in the crown's blackened metal, " +
                    "cursed with the weight of his people's ruin. Wear it, and you'll gain immense magical power, but " +
                    "the echoes of their suffering will never leave your mind.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class CloakofSwiftCasting extends Item {
        public CloakofSwiftCasting() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Cloak of Swift Casting";

            // Set item stats
            this.magicBoost = 7;

            // Set item price
            this.itemPrice = 25;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "An old, velvety green cloak with a hood. You take a closer look and " +
                    "notice the name \"Brian\" embroidered inside. It looks like it was once part of an elaborate cosplay.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class FortuneCookie extends Item {
        public FortuneCookie() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Fortune Cookie";

            // Set item stats
            this.magicBoost = 3;

            // Set item price
            this.itemPrice = 9;

            // Set item rarity
            this.rarity = 1;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "A crunchy snack that promises a fortune.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    /// ///////////////////////////////////////////
    ///                RANDOM ITEMS
    /// ///////////////////////////////////////////

    public static class SoyMilk extends Item {
        public SoyMilk() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Soy Milk";

            // Set item price
            this.itemPrice = 10;

            // Set item rarity
            this.rarity = 1;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "It smells... overpriced.\nYou scratch your head in confusion. Why is this here? " +
                    "Who knows, adventurer? ...it may come in handy.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class MysteriousLiquid extends Item {
        public MysteriousLiquid() {
            // Inherit superclass
            super();

            // Set item name
            this.itemName = "Mysterious Liquid";

            // Set item price
            this.itemPrice = 25;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = true;
            this.isAction = false;
            this.isMisc = false;

            // Item description line
            String customDescription = "It's glowing, and it smells like cigarettes and cheap perfume— like something" +
                    "you'd find at a shady motel.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    /// ///////////////////////////////////////////
    ///                ITEM METHODS
    /// ///////////////////////////////////////////

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

        // Display rarity
        String rarityTag = generateRarityTag(rarity);
        description.append("Rarity: ").append(rarityTag).append("\n");

        // Display price
        description.append("Price: ").append(itemPrice).append("\n");

        // Display stat boost information
        for (String boost : boosts) {
            description.append(boost).append("\n");
        }
        return description.toString();
    }

    public String generateRarityTag(int rarity) {
        return switch (rarity) {
            case 1 -> "COMMON" ;
            case 2 -> "RARE" ;
            case 3 -> "LEGENDARY" ;
            default -> "" ;
        };
    }

    public static List<Item> getItems() {
        // Note: SoyMilk is purposely omitted to ensure it only appears before the final boss fight.
        List<Item> items = new ArrayList<>();
        items.add(new RingOfBone());
        items.add(new JarOfMushrooms());
        items.add(new HellfirePepper());
        items.add(new NaglfarFragment());
        items.add(new Sock());
        items.add(new Nazar());
        items.add(new CrownOfTheFallenKing());
        items.add(new CloakofSwiftCasting());
        items.add(new FortuneCookie());
        items.add(new MysteriousLiquid());
        return items;
    }

}