import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Knight extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Halt, so that I may practice my swordplay!",
            "The weight of your destiny has brought you here, only to break you.",
            "Your journey ends here. This is the crossroads of despair."
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "You fought well... but not well enough!",
            "The weight of your failure is heavy, isn't it?",
            "This is where your story ends, hero."
    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "I have failed, go on then. I can't stop you!",
            "I have fought with all my might. That is enough.",
            "So... this is how it ends."
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "En garde!",
            "You're nothing but a shadow of what you claim to be.",
            "I'll cut you down where you stand!"
    );

    private final Random rand = new Random();

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
