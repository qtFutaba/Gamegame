package Game.Entities;

import Game.Action;
import Game.Attack;
import Game.Music.MusicPlayer;

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

    private MusicPlayer voicePlayer;
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
        this.voicePlayer = new MusicPlayer();

        this.attacks.add(new Attack("Angry Fist", "Physical", 25, 40, 40));
        this.attacks.add(new Attack("Heavy Smack", "Physical", 35, 5, 5));
        this.attacks.add(new Attack("Mighty Bash", "Physical", 45, 3, 3));

        this.actions.add(new Action("Stall", false, 1));
        this.actions.add(new Action("Charge", false, 1));
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
