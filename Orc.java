public class Orc extends Enemy {
    public Orc() {
        setName("Orc");
        setMaxHealth(200);
        setCurrentHealth(200);
        setCoinReward(40);

        // Set random attack, defense, and magic stats
        setStat(STANDARD);
        setAttack(getStat());

        setStat(STANDARD);
        setDefense(getStat());

        setStat(STANDARD);
        setMagicPoints(getStat());
    }
}
