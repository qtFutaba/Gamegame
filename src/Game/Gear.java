package Game;

import Game.Entities.Player;

public class Gear
{
    private String name;
    private int atkBuff;
    private int defBuff;
    private int magBuff;
    private int healthBuff;
    private boolean isBuff;
    private boolean isAction;
    private boolean isAttack;
    private boolean isMisc;

    public Gear(String name, int atkBuff, int defBuff, int magBuff, int healthBuff, boolean isBuff, boolean isAction, boolean isMisc) {
        this.name = name;
        this.atkBuff = atkBuff;
        this.defBuff = defBuff;
        this.magBuff = magBuff;
        this.healthBuff = healthBuff;
        this.isBuff = isBuff;
        this.isAction = isAction;
        this.isMisc = isMisc;
    }
    public String getName() {return name;}
    public int getAtkBuff() {return atkBuff;}
    public int getDefBuff() {return defBuff;}
    public int getMagBuff() {return magBuff;}
    public int getHealthBuff() {return healthBuff;}

    public void setName(String name) {this.name = name;}
    public void setAtkBuff(int atkBuff) {this.atkBuff = atkBuff;}
    public void setDefBuff(int defBuff) {this.defBuff = defBuff;}
    public void setMagBuff(int magBuff) {this.magBuff = magBuff;}
    public void setHealthBuff(int healthBuff) {this.healthBuff = healthBuff;}

    public void applyGear(Player player)
    {
        if (isBuff)
        {
            player.setAttack(player.getAttack() + this.atkBuff);
            player.setDefense(player.getDefense() + this.defBuff);
            player.setMagic(player.getMagic() + this.magBuff);
            player.setMaxHealth(player.getMaxHealth() + this.healthBuff);
            player.setCurrentHealth(player.getMaxHealth());
        }
    }




}
