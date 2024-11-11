public class Gear {
    private String name;
    private int atkBuff;
    private int defBuff;
    private int healthBuff;

    public Gear(String name, int atkBuff, int defBuff, int healthBuff) {
        this.name = name;
        this.atkBuff = atkBuff;
        this.defBuff = defBuff;
        this.healthBuff = healthBuff;
    }
    public String getName() {return name;}
    public int getAtkBuff() {return atkBuff;}
    public int getDefBuff() {return defBuff;}
    public int getHealthBuff() {return healthBuff;}

    public void setName(String name) {this.name = name;}
    public void setAtkBuff(int atkBuff) {this.atkBuff = atkBuff;}
    public void setDefBuff(int defBuff) {this.defBuff = defBuff;}
    public void setHealthBuff(int healthBuff) {this.healthBuff = healthBuff;}
}
