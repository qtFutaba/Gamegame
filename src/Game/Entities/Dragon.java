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
        this.setTurnWasteChance(20);

        this.attacks.add(new Attack("Claw Strike", "Physical", 40, 40, 40));
        this.attacks.add(new Attack("Fearsome Crunch", "Physical", 60, 5, 5));
        this.attacks.add(new Attack("Flame Breath", "Magic", 90, 2, 2));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
        this.actions.add(new Action("Block", false, 1));

    }
}