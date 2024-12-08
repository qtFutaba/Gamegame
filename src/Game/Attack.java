package Game;

public class Attack
{
    private String name;
    private String type;
    private int power;
    private int uses;
    private int maxUses;

    public Attack(String name, String type, int power, int uses, int maxUses)
    {
        this.name = name;
        this.type = type;
        this.power = power;
        this.uses = uses;
        this.maxUses = maxUses;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getUses() {
        return uses;
    }
    public void setUses(int uses) {
        this.uses = uses;
    }
    public int getMaxUses() {
        return maxUses;
    }
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
