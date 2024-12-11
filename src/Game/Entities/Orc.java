package Game.Entities;

import Game.Action;
import Game.Attack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Orc extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList(
            "An enemy approaches... Prepare to meet your end!",
            "I smell fear... Good. It’ll make your defeat even sweeter.",
            "Another challenger? You’ll be just another corpse at my feet.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "Another opponent falls. The ground is littered with your failure.",
            "Another challenger falls before me. Victory is my birthright.",
            "Victory tastes as sweet as blood! Yours... to be exact.");
    private static final List<String> defeatMsgs = Arrays.asList(
            "I wasn’t ready for you. But I’ll learn from this... and I’ll come back stronger!",
            "I was too careless. But you’ll regret letting me live to fight again.",
            "My strength faltered today. It won’t happen again.");
    private static final List<String> attackMsgs = Arrays.asList(
            "Your blood will stain my hands before this is over!",
            "I'll smash you into the dirt like the pathetic creature you are!",
            "You're already dead, you just don't know it yet.");
    private static final List<String> tauntMsgs = Arrays.asList(
            "Do you like the sight of your own blood, adventurer?",
            "I will grind your bones into dust!",
            "GRAAARGGHHHHH!!!! YOU WILL NOT WIN.");
    private final Random rand = new Random();

    public Orc() {
        this.setName("Orc");
        this.setLevel(29);
        this.setMaxHealth(105);
        this.setCurrentHealth(105);
        this.setCoinReward(40);
        this.setSprite("src/Sprites/orc.png");
        this.setStat("standard");
        this.setAttack(this.getStat());
        this.setStat("standard");
        this.setDefense(this.getStat());
        this.setStat("standard");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(20);

        this.attacks.add(new Attack("Angry Fist", "Physical", 30, 40, 40));
        this.attacks.add(new Attack("Heavy Smack", "Physical", 40, 5, 5));
        this.attacks.add(new Attack("Mighty Bash", "Physical", 50, 3, 3));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
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
