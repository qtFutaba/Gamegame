package Game.Entities;

public class Enemy extends Entity{
    private int coinReward;
    private int turnWasteChance;

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
}
