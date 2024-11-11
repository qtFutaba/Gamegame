public class Entity {
    private int attack;
    private int defense;
    private int magicPoints;
    private int maxHealth;
    private int currentHealth;
    private String name;

    public Entity(){
        attack = 0;
        defense = 0;
        magicPoints = 0;
        maxHealth = 0;
        currentHealth = 0;
        name = "DefaultName";
    }

    public void setAttack(int atk){ this.attack = atk;}
    public void setDefense(int def){ this.defense = def;}
    public void setMagicPoints(int mp){ this.magicPoints = mp;}
    public void setMaxHealth(int hp){ this.maxHealth = hp;}
    public void setCurrentHealth(int c){ this.currentHealth = c;}
    public void setName(String name){ this.name = name;}

    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getMagicPoints(){return magicPoints;}
    public int getMaxHealth(){return maxHealth;}
    public int getCurrentHealth(){return currentHealth;}
    public String getName(){return name;}
}
