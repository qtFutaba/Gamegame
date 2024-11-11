public class Enemy extends Entity{
    private int coinReward;

    public Enemy(){
        coinReward = 1;
        setAttack(1);
        setMaxHealth(5);
        setCurrentHealth(5);
        setName("Acid Trip Head");
    }

    public int getCoinReward(){return coinReward;}
    public void setCoinReward(int coinReward){this.coinReward = coinReward;}
}
