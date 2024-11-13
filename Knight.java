public class Knight extends Enemy {
    public Knight() {
        setName("Knight");
        setMaxHealth(200);
        setCurrentHealth(200);
        setCoinReward(40);

        // Set random attack, defense, and magic stats
        setStat(STANDARD);
        setAttack(getStat());

        setStat(HIGH);
        setDefense(getStat());

        setStat(LOW);
        setMagicPoints(getStat());
    }
}
