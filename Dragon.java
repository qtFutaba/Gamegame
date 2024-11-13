public class Dragon extends Enemy {
    public Dragon() {
        setName("Dragon");
        setMaxHealth(400);
        setCurrentHealth(400);
        setCoinReward(80);

        // Set random attack, defense, and magic stats
        setStat(HIGH);
        setAttack(getStat());

        setStat(HIGH);
        setDefense(getStat());

        setStat(HIGH);
        setMagicPoints(getStat());
    }
}
