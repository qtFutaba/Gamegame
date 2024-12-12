package src.Game;

import java.util.ArrayList;
import java.util.List;
import Game.Gear;

public class Item extends Gear {
    public String itemDescription;
    public int itemPrice;
    public int rarity;

    public Item() {
        super();
        this.name = "Default item";
        this.itemDescription = "A default item.";
        this.rarity = 1;
        this.itemPrice = 0;
        this.atkBuff = 0;
        this.defBuff = 0;
        this.magBuff = 0;
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
            this.name = "Ring of Bone";

            // Set item stats
            this.atkBuff = 22;

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
            this.name = "Jar of Mushrooms";

            // Set item stats
            this.atkBuff = 15;

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
            this.name = "Hellfire Pepper";

            // Set item stats
            this.atkBuff = 6;

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
            this.name = "Naglfar Fragment";

            // Set item stats
            this.defBuff = 30;

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
            this.name = "Sock";

            // Set item stats
            this.defBuff = 6;

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
            this.name = "Nazar";

            // Set item stats
            this.defBuff = 19;

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
            this.name = "Crown of the Fallen King";

            // Set item stats
            this.magBuff = 29;

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
            this.name = "Cloak of Swift Casting";

            // Set item stats
            this.magBuff = 18;

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
            this.name = "Fortune Cookie";

            // Set item stats
            this.magBuff = 7;

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
            this.name = "Soy Milk";

            // Set item price
            this.itemPrice = 10;

            // Set item rarity
            this.rarity = 1;

            // Set item type
            this.isBuff = false;
            this.isAction = false;
            this.isMisc = true;

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
            this.name = "Mysterious Liquid";

            // Set item price
            this.itemPrice = 25;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = false;
            this.isAction = false;
            this.isMisc = true;

            // Item description line
            String customDescription = "It's glowing, and it smells like cigarettes and cheap perfume— like " +
                    "something you'd find at a shady motel. They say it'll bring you back to life upon death, but " +
                    "honestly, it probably comes with a few side effects— like a permanent sense of regret.";

            // Item stat boost information
            String boostDescription = generateDescription();

            // Concatenate item description and stat boost information
            this.itemDescription = boostDescription + customDescription;
        }
    }

    public static class RegenerativeTea extends Item {
        public RegenerativeTea() {
            // Inherit superclass
            super();

            // Set item name
            this.name = "Regenerative Tea";

            // Set item price
            this.itemPrice = 35;

            // Set item rarity
            this.rarity = 2;

            // Set item type
            this.isBuff = false;
            this.isAction = false;
            this.isMisc = true;

            // Item description line
            String customDescription = "It appears to be a relatively normal mug of tea - until you look inside. " +
                    "A few strange things float around in the honey colored liquid, and it's unclear what it" +
                    "all actually is. The shopkeep assures you it's safe though- and very energizing.";

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
        if (defBuff > 0) {
            boosts.add("DEF (+ " + defBuff + ")");
        }
        if (atkBuff > 0) {
            boosts.add("ATK (+ " + atkBuff + ")");
        }
        if (magBuff > 0) {
            boosts.add("MAG (+ " + magBuff + ")");
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
	items.add(new RegenerativeTea());
        return items;
    }

}