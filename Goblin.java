public class Goblin extends Enemy {
    public Goblin() {
        setName("Goblin");
        setMaxHealth(100);
        setCurrentHealth(100);
        setCoinReward(20);

        // Set random attack, defense, and magic stats
        setStat(LOW);
        setAttack(getStat());

        setStat(LOW);
        setDefense(getStat());

        setStat(LOW);
        setMagicPoints(getStat());
    }
}
