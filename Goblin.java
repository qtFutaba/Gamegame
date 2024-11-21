import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class Goblin extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Ooo, fresh meat!",
            "Got a death wish, do ya?",
            "You look like you got somethin' shiny!"
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "I.. I did it! I'm rich! I'm really rich! Hehehe!",
            "You're not as tough as you thought, huh?",
            "Too easy! Now where's the gold?"
    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "AAAAAA! Too strong!",
            "You're tougher than you look...",
            "Owww! Okay, I give up!"
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "Hehehehe!",
            "Grrr...",
            "You're meat to my blade!"
    );

    private final Random rand = new Random();

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

    @Override
    public String getGreeting() {
        return greetingMsgs.get(rand.nextInt(greetingMsgs.size()));
    }
    @Override
    public String getVictoryMsg() {
        return victoryMsgs.get(rand.nextInt(victoryMsgs.size()));
    }
    @Override
    public String getDefeatMsg() {
        return defeatMsgs.get(rand.nextInt(defeatMsgs.size()));
    }
    @Override
    public String getAttackMsg() {
        return attackMsgs.get(rand.nextInt(attackMsgs.size()));
    }
}
