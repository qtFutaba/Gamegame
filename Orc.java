import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Orc extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "An enemy approaches... Prepare to meet your end!",
            "I smell fear... Good. It’ll make your defeat even sweeter.",
            "Another challenger? You’ll be just another corpse at my feet."
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "Another opponent falls. The ground is littered with your failure.",
            "Another challenger falls before me. Victory is my birthright.",
            "Victory tastes as sweet as blood! Yours... to be exact."
    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "I wasn’t ready for you. But I’ll learn from this... and I’ll come back stronger!",
            "I was too careless. But you’ll regret letting me live to fight again.",
            "My strength faltered today. It won’t happen again."
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "Your blood will stain my hands before this is over!",
            "I'll smash you into the dirt like the pathetic creature you are!",
            "You're already dead, you just don't know it yet."
    );

    private final Random rand = new Random();

    public Orc() {
        setName("Orc");
        setMaxHealth(225);
        setCurrentHealth(225);
        setCoinReward(40);

        // Set random attack, defense, and magic stats
        setStat(STANDARD);
        setAttack(getStat());

        setStat(STANDARD);
        setDefense(getStat());

        setStat(STANDARD);
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
