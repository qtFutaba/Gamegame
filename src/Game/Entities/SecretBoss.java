package Game.Entities;

public class SecretBoss extends Enemy {
    public SecretBoss() {
        setName("Dr. Baarsch");
        setMaxHealth(700);
        setCurrentHealth(700);
        setCoinReward(400);
        setSprite("src/Sprites/secretboss.png");

        // Set random attack, defense, and magic stats
        setStat(ULTRA);
        setAttack(getStat());

        setStat(HIGH);
        setDefense(getStat());

        setStat(HIGH);
        setMagicPoints(getStat());
    }
}
