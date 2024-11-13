import java.util.Random;

public class Entity {
    private int attack;
    private int defense;
    private int magicPoints;
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int stat;
    public final String LOW = "low";
    public final String STANDARD = "standard";
    public final String HIGH = "high";
    public final String ULTRA = "ultra";

    public Entity(){
        attack = 5;
        defense = 5;
        magicPoints = 5;
        maxHealth = 0;
        currentHealth = 0;
        name = "Default";
    }

    public void setAttack(int atk){ this.attack = atk;}
    public void setDefense(int def){ this.defense = def;}
    public void setMagicPoints(int mp){ this.magicPoints = mp;}
    public void setMaxHealth(int hp){ this.maxHealth = hp;}
    public void setCurrentHealth(int c){ this.currentHealth = c;}
    public void setName(String name){ this.name = name;}
    public void setStat(String level) {
        stat = 0;
        int max, min;
        Random rand = new Random();
        switch (level.toLowerCase()){
            case LOW:
                min = 5;
                max = 9;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case STANDARD:
                min = 10;
                max = 14;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case HIGH:
                min = 15;
                max = 20;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case ULTRA:
                stat = 30;
                break;
            default:
                stat = 5;
                break;
        }
    }

    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getMagicPoints(){return magicPoints;}
    public int getMaxHealth(){return maxHealth;}
    public int getCurrentHealth(){return currentHealth;}
    public String getName(){return name;}
    public int getStat(){return stat;}

    @Override
    public String toString(){
        return String.format("""
                Entity: %s
                Attack: %d
                Defense: %d
                Magic: %d""",
                name,
                attack,
                defense,
                magicPoints);
    }
}
