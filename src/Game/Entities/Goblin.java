package Game.Entities;

import Game.Action;
import Game.Attack;
import Game.Music.MusicPlayer;

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

    private MusicPlayer voicePlayer;
    private final Random rand = new Random();

    public Goblin() {
        this.setName("Goblin");
        this.setLevel(26);
        this.setMaxHealth(65);
        this.setCurrentHealth(65);
        this.setCoinReward(20);
        this.setSprite("src/Sprites/goblin.png");
        this.setStat("low");
        this.setAttack(this.getStat());
        this.setStat("low");
        this.setDefense(this.getStat());
        this.setStat("low");
        this.setMagic(this.getStat());
        this.setTurnWasteChance(30);
        this.voicePlayer = new MusicPlayer();

        this.attacks.add(new Attack("Slash", "Physical", 20, 20, 20));
        this.attacks.add(new Attack("Mad Stab", "Physical", 30, 5, 5));

        this.actions.add(new Action("Stall", false, 1));
    }

    public String getGreeting()
    {
        int index = this.rand.nextInt(greetingMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/" + this.getName() + "/greeting" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)greetingMsgs.get(index);
    }
    public String getVictoryMsg()
    {
        int index = this.rand.nextInt(victoryMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/" + this.getName() + "/victory" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)victoryMsgs.get(index);
    }
    public String getDefeatMsg()
    {
        int index = this.rand.nextInt(defeatMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/" + this.getName() + "/defeat" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)defeatMsgs.get(index);
    }
    public String getAttackMsg() {
        int index = this.rand.nextInt(attackMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/" + this.getName() + "/attack" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)attackMsgs.get(index);
    }
    public String getTauntMsg() {
        int index = this.rand.nextInt(tauntMsgs.size());
        String soundEffectFilename = "src/Game/Music/VoiceLines/" + this.getName() + "/taunt" + index + ".wav";
        voicePlayer.play(soundEffectFilename);
        return (String)tauntMsgs.get(index);
    }
}

