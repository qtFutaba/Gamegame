import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wizard extends Enemy implements Dialogue {
    private static final List<String> greetingMsgs = Arrays.asList(
            "You've wandered far from safety, little one.",
            "Your journey was always meant to end in ruin. I will see it done.",
            "This place shall be your tomb, hero."
    );
    private static final List<String> victoryMsgs = Arrays.asList(
            "Good thing that's over with. Almost lost my spot in my book!",
            "I'd thank you for the warm-up, but that was hardly worth the effort.",
            "Honestly, did you have to interrupt? My tea is probably cold by now."

    );
    private static final List<String> defeatMsgs = Arrays.asList(
            "I see... my magic wasn't enough for your might.",
            "So, it seems I miscalculated... how unfortunate for me.",
            "To think... centuries of study undone by you."
    );
    private static final List<String> attackMsgs = Arrays.asList(
            "One incantation, and you will be no more.",
            "You will regret standing in my way, hero!",
            "The fabric of reality bends to my will!"
    );

    private final Random rand = new Random();

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
