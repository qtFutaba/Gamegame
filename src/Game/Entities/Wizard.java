package Game.Entities;

import Game.Action;
import Game.Attack;

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
        this.setLevel(28);
        this.setMaxHealth(70);
        this.setCurrentHealth(70);
        this.setCoinReward(30);
        this.setSprite("src/Sprites/wizard.png");
        this.setStat("low");
        this.setAttack(this.getStat());
        this.setStat("low");
        this.setDefense(this.getStat());
        this.setStat("high");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(40);

        this.attacks.add(new Attack("Staff Poke", "Physical", 30, 20, 20));
        this.attacks.add(new Attack("Mote of Fire", "Magic", 50, 5, 5));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
        this.actions.add(new Action("Heal", true, 1));
    }
}