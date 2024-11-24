package Game.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Knight extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Halt, so that I may practice my swordplay!",
            "The weight of your destiny has brought you here, only to break you.",
            "Your journey ends here. This is the crossroads of despair.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "You fought well... but not well enough!",
            "The weight of your failure is heavy, isn't it?",
            "This is where your story ends, hero.");
    private static final List<String> defeatMsgs = Arrays.asList(
            "I have failed, go on then. I can't stop you!",
            "I have fought with all my might. That is enough.",
            "So... this is how it ends.");
    private static final List<String> attackMsgs = Arrays.asList(
            "En garde!",
            "You're nothing but a shadow of what you claim to be.",
            "I'll cut you down where you stand!");

    private final Random rand = new Random();

    public Knight() {
        this.setName("Knight");
        this.setMaxHealth(200);
        this.setCurrentHealth(200);
        this.setCoinReward(40);
        this.setSprite("src/Sprites/knight.png");
        this.setStat("standard");
        this.setAttack(this.getStat());
        this.setStat("high");
        this.setDefense(this.getStat());
        this.setStat("low");
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
