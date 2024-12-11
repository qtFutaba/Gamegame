package Game;

public class Action
{
    private String action;
    private boolean isLimited;
    private int uses;
    private int maxUses;

    public Action(String action, boolean isLimited, int uses)
    {
        this.action = action;
        this.isLimited = isLimited;
        this.uses = uses;
        this.maxUses = uses;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
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
}
