public class Wizard extends Enemy {
    public Wizard() {
        setName("Wizard");
        setMaxHealth(150);
        setCurrentHealth(150);
        setCoinReward(30);

        // Set random attack, defense, and magic stats
        setStat(LOW);
        setAttack(getStat());

        setStat(LOW);
        setDefense(getStat());

        setStat(HIGH);
        setMagicPoints(getStat());
    }
}
