package Game.Entities;

import Game.Action;
import Game.Attack;
import Game.Music.MusicPlayer;

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
    private static final List<String> tauntMsgs = Arrays.asList(
            "Just FYI, the next due date is approaching - fast. Are you going to make it?",
            "YOUR deadline is approaching. Are you ready?"
            );

    private MusicPlayer voicePlayer;
    private final Random rand = new Random();
    private boolean soyMilkPresent;

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
        this.setTurnWasteChance(10);
        this.voicePlayer = new MusicPlayer();
        this.soyMilkPresent = false;

        this.attacks.add(new Attack("Harsh Criticism", "Magic", 25, 40, 40));
        this.attacks.add(new Attack("New Lab Submission", "Magic", 40, 2, 2));
        this.attacks.add(new Attack("Exam Time", "Physical", 40, 2, 2));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
        this.actions.add(new Action("Block", false, 1));
        this.actions.add(new Action("Heal", true, 2));
    }

    public void soyMilkPresent(boolean present)
    {
        if (present)
        {
            this.soyMilkPresent = true;
            this.setCurrentHealth(this.getCurrentHealth() - 25);
            this.setStat("low");
            this.setAttack(this.getStat());
            this.setStat("low");
            this.setDefense(this.getStat());
            this.setStat("low");
            this.setMagic(this.getStat());
            this.setTurnWasteChance(40);

            this.greetingMsgs.set(0,"Good afternoon cla- WHO BROUGHT SOY MILK? AND WHY?");
        }
    }

    public String getGreeting()
    {
        int index = this.rand.nextInt(greetingMsgs.size());

        String soundEffectFilename = "";

        if(soyMilkPresent)
        {
            soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/greetingSOY.wav";
        }
        else
        {
            soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/greeting" + index + ".wav";
        }

        voicePlayer.play(soundEffectFilename);

        return (String)greetingMsgs.get(index);
    }
    public String getVictoryMsg()
    {
        int index = this.rand.nextInt(victoryMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/victory" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)victoryMsgs.get(index);
    }
    public String getDefeatMsg()
    {
        int index = this.rand.nextInt(defeatMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/defeat" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)defeatMsgs.get(index);
    }
    public String getAttackMsg() {
        int index = this.rand.nextInt(attackMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/attack" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)attackMsgs.get(index);
    }
    public String getTauntMsg() {
        int index = this.rand.nextInt(tauntMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/Baarsch/taunt" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)tauntMsgs.get(index);
    }
}

