package Game.Entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity{
    private int coinReward;
    private int turnWasteChance;
    private static final List<String> greetingMsgs = Arrays.asList(
            "Default greeting.");
    private static final List<String> victoryMsgs = Arrays.asList(
            "Default victory."
            );
    private static final List<String> defeatMsgs = Arrays.asList(
            "Default defeat."
            );
    private static final List<String> attackMsgs = Arrays.asList(
            "Default attack."
            );
    private static final List<String> tauntMsgs = Arrays.asList(
            "Default taunt."
    );
    private final Random rand = new Random();

    public Enemy(){
        coinReward = 1;
        turnWasteChance = 1;
        setAttack(1);
        setMaxHealth(5);
        setCurrentHealth(5);
        setName("Acid Trip Head");
    }

    public int getCoinReward(){return coinReward;}
    public void setCoinReward(int coinReward){this.coinReward = coinReward;}

    public int getTurnWasteChance() {return turnWasteChance;}
    public void setTurnWasteChance(int turnWasteChance) {this.turnWasteChance = turnWasteChance;}

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
