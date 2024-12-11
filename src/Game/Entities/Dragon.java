package Game.Entities;

import Game.Action;
import Game.Attack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dragon extends Enemy {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Who dares disturb my slumber?",
            "Kneel before your better, insect.",
            "Speak quickly... or die.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "How dreadful. You were no different from the last fool to challenge me.",
            "Was there any doubt of my triumph?",
            "Fate bends to my will.");
    private static final List<String> defeatMsgs = Arrays.asList(
            "What a surprise! To be defeated like this, I never thought it possible...",
            "Even gods fall... but I shall return.",
            "The cycle turns... my flame will rekindle.");
    private static final List<String> attackMsgs = Arrays.asList(
            "No mortal escapes my wrath!",
            "I shall grind you to ash and bone.",
            "Fire is my gift... and your curse!");
    private static final List<String> tauntMsgs = Arrays.asList(
            "Soon, your riches will pad my horde like the riches of those before you.",
            "I'm growing bored of you. Die already, will you?",
            "Your flesh will make a fine snack.");
    private final Random rand = new Random();

    public Dragon() {
        this.setName("Dragon");
        this.setLevel(31);
        this.setMaxHealth(165);
        this.setCurrentHealth(165);
        this.setCoinReward(80);
        this.setSprite("src/Sprites/dragon.png");
        this.setStat("high");
        this.setAttack(this.getStat());
        this.setStat("high");
        this.setDefense(this.getStat());
        this.setStat("high");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(10);

        this.attacks.add(new Attack("Claw Strike", "Physical", 30, 40, 40));
        this.attacks.add(new Attack("Fearsome Crunch", "Physical", 40, 5, 5));
        this.attacks.add(new Attack("Flame Breath", "Magic", 70, 2, 2));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
        this.actions.add(new Action("Block", false, 1));

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