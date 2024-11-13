public class Baarsch extends Enemy {
    public Baarsch() {
        setName("Dr. Baarsch");
        setMaxHealth(700);
        setCurrentHealth(700);
        setCoinReward(400);

        // Set random attack, defense, and magic stats
        setStat(ULTRA);
        setAttack(getStat());

        setStat(HIGH);
        setDefense(getStat());

        setStat(HIGH);
        setMagicPoints(getStat());
    }
}
