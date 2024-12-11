package Game.Entities;

import Game.Action;
import Game.Attack;

import java.util.ArrayList;
import java.util.Random;

public class Entity {
    private int level;
    private int attack;
    private int defense;
    private int magic;
    private int maxHealth;
    private int currentHealth;
    private String name;
    private String sprite;
    private int stat;
    private boolean isBlocking;
    private boolean isCharging;
    ArrayList<Attack> attacks;
    ArrayList<Action> actions;
    public final String LOW = "low";
    public final String STANDARD = "standard";
    public final String HIGH = "high";
    public final String ULTRA = "ultra";


    public Entity(){
        level = 30;
        attack = 5;
        defense = 5;
        magic = 5;
        maxHealth = 0;
        currentHealth = 0;
        name = "Default";
        attacks = new ArrayList<>();
        actions = new ArrayList<>();
    }

    public void setAttack(int atk){ this.attack = atk;}
    public void setDefense(int def){ this.defense = def;}
    public void setMagic(int mag){ this.magic = mag;}
    public void setMaxHealth(int hp){ this.maxHealth = hp;}
    public void setCurrentHealth(int c){ this.currentHealth = c;}
    public void setName(String name){ this.name = name;}
    public void setSprite(String sprite){ this.sprite = sprite;}
    public void setStat(String level) {
        stat = 0;
        int max, min;
        Random rand = new Random();
        switch (level.toLowerCase()) {
            case LOW:
                min = 50;
                max = 55;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case STANDARD:
                min = 56;
                max = 65;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case HIGH:
                min = 66;
                max = 75;
                stat = rand.nextInt(max - min + 1) + min;
                break;
            case ULTRA:
                stat = 80;
                break;
            default:
                stat = 50;
                break;
        }
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getAttack(){return attack;}
    public int getDefense(){return defense;}
    public int getMagic(){return magic;}
    public int getMaxHealth(){return maxHealth;}
    public int getCurrentHealth(){return currentHealth;}
    public String getName(){return name;}
    public String getSprite(){return sprite;}
    public int getStat(){return stat;}
    public boolean isBlocking() {return isBlocking;}
    public void setBlocking(boolean blocking) {isBlocking = blocking;}
    public boolean isCharging() {return isCharging;}
    public void setCharging(boolean charging) {isCharging = charging;}


    public int getAttackCount(){return attacks.size();}
    public int getActionCount(){return actions.size();}

    public void addAttack(Attack attack){attacks.add(attack);}
    public void addAction(Action action){actions.add(action);}


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
                magic);
    }


    public Action getAction(int i)
    {
        return actions.get(i);
    }

    public Attack getAttackMove(int i)
    {
        return attacks.get(i);
    }


}
