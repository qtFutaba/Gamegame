package Game.Entities;

import Game.Action;
import Game.Attack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Goblin extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Ooo, fresh meat!",
            "Got a death wish, do ya?",
            "You look like you got somethin' shiny!");
    private static final List<String> victoryMsgs = Arrays.asList(
            "I.. I did it! I'm rich! I'm really rich! Hehehe!",
            "You're not as tough as you thought, huh?",
            "Too easy! Now where's the gold?");
    private static final List<String> defeatMsgs = Arrays.asList(
            "AAAAAA! Too strong!",
            "You're tougher than you look...",
            "Owww! Okay, I give up!");
    private static final List<String> attackMsgs = Arrays.asList(
            "Hehehehe!",
            "Grrr...",
            "You're meat to my blade!");
    private static final List<String> tauntMsgs = Arrays.asList(
            "Hehehehe! Your purse will soon be mine!",
            "You look like you crawled out of a horse's rear! He-hehehaha!",
            "I can't wait to slice you up!");

    private final Random rand = new Random();

    public Goblin() {
        this.setName("Goblin");
        this.setLevel(26);
        this.setMaxHealth(60);
        this.setCurrentHealth(60);
        this.setCoinReward(20);
        this.setSprite("src/Sprites/goblin.png");
        this.setStat("low");
        this.setAttack(this.getStat());
        this.setStat("low");
        this.setDefense(this.getStat());
        this.setStat("low");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(30);

        this.attacks.add(new Attack("Slash", "Physical", 20, 20, 20));
        this.attacks.add(new Attack("Mad Stab", "Physical", 30, 5, 5));

        this.actions.add(new Action("Stall", false, 1));
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
    public String getTauntMsg() {
        return (String)tauntMsgs.get(this.rand.nextInt(tauntMsgs.size()));
    }
}

