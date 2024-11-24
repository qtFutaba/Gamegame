package Game.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wizard extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList(
            "You've wandered far from safety, little one.",
            "Your journey was always meant to end in ruin. I will see it done.",
            "This place shall be your tomb, hero.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "Good thing that's over with. Almost lost my spot in my book!",
            "I'd thank you for the warm-up, but that was hardly worth the effort.",
            "Honestly, did you have to interrupt? My tea is probably cold by now.");
    private static final List<String> defeatMsgs = Arrays.asList(
            "I see... my magic wasn't enough for your might.",
            "So, it seems I miscalculated... how unfortunate for me.",
            "To think... centuries of study undone by you.");
    private static final List<String> attackMsgs = Arrays.asList(
            "One incantation, and you will be no more.",
            "You will regret standing in my way, hero!",
            "The fabric of reality bends to my will!");
    private final Random rand = new Random();

    public Wizard() {
        this.setName("Wizard");
        this.setMaxHealth(150);
        this.setCurrentHealth(150);
        this.setCoinReward(30);
        this.setSprite("src/Sprites/wizard.png");
        this.setStat("low");
        this.setAttack(this.getStat());
        this.setStat("low");
        this.setDefense(this.getStat());
        this.setStat("high");
        this.setMagicPoints(this.getStat());
    }

    public String getGreeting() {
        return (String)greetingMsgs.get(this.rand.nextInt(greetingMsgs.size()));
    }


    public String getVictoryMsg() {
        return (String)victoryMsgs.get(this.rand.nextInt(victoryMsgs.size()));
    }


    public String getDefeatMsg() {
        return (String)defeatMsgs.get(this.rand.nextInt(defeatMsgs.size()));
    }


    public String getAttackMsg() {
        return (String)attackMsgs.get(this.rand.nextInt(attackMsgs.size()));
    }

}