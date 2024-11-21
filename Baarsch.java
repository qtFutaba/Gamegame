import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Baarsch extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "Good afternoon, class."
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "Class is dismissed.",
            "Just a reminder that Module 5 Lab submission is due tomorrow at Midnight."
    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "Hmm... maybe I'll add five minutes to the next exam."
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "* uncontrollable laughter *",
            "Time to throw an exception you can’t catch!",
            "There’s no escaping this one. Lab 5 is due soon— hope you’re ready to sacrifice your weekend."
    );

    private final Random rand = new Random();

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
