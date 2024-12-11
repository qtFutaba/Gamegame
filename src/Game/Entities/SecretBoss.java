package Game.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SecretBoss extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList("Good afternoon, class.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "Class is dismissed.",
            "Hmm... maybe I'll add five minutes to the next exam.");
    private static final List<String> defeatMsgs = Arrays.asList(
            "This fight turned out to be more challenging than I originally anticipated");
    private static final List<String> attackMsgs = Arrays.asList(
            "* uncontrollable laughter *",
            "Time to throw an exception you can’t catch!",
            "There’s no escaping this one. Lab 5 is due soon— hope you’re ready to sacrifice your weekend.");
    private final Random rand = new Random();

    public SecretBoss() {
        this.setLevel(33);
        this.setName("Dr. Baarsch");
        this.setMaxHealth(200);
        this.setCurrentHealth(200);
        this.setCoinReward(400);
        this.setSprite("src/Sprites/secretboss.png");
        this.setStat("ultra");
        this.setAttack(this.getStat());
        this.setStat("high");
        this.setDefense(this.getStat());
        this.setStat("high");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(25);
    }

    public void soyMilkPresent(boolean present)
    {
        if (present)
        {
            this.setStat("low");
            this.setAttack(this.getStat());
            this.setStat("low");
            this.setDefense(this.getStat());
            this.setStat("low");
            this.setMagic(this.getStat());
            this.setTurnWasteChance(40);
        }
    }

}
