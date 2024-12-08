package Game;

import Game.Entities.Player;

public class Gear
{
    public String name;
    public int atkBuff;
    public int defBuff;
    public int magBuff;
    public int healthBuff;
    public boolean isBuff;
    public boolean isAction;
    public boolean isAttack;
    public boolean isMisc;

    public Gear() {
        this.name = "Default Gear";
        this.atkBuff = 0;
        this.defBuff = 0;
        this.magBuff = 0;
        this.healthBuff = 0;
        this.isBuff = false;
        this.isAction = false;
        this.isAttack = false;
        this.isMisc = false;
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
