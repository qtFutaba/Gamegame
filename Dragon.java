import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dragon extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Who dares disturb my slumber?",
            "Kneel before your better, insect.",
            "Speak quickly... or die."
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "How dreadful. You were no different from the last fool to challenge me.",
            "Was there any doubt of my triumph?",
            "Fate bends to my will."
    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "What a surprise! To be defeated like this, I never thought it possible...",
            "Even gods fall... but I shall return.",
            "The cycle turns... my flame will rekindle."
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "No mortal escapes my wrath!",
            "I shall grind you to ash and bone.",
            "Fire is my gift... and your curse!"
    );

    private final Random rand = new Random();

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