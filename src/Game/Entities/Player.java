package Game.Entities;

import Game.Gear;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private List<Gear> gear;
    private int coinPurse;

    public Player()
    {
        setMaxHealth(5);
        setCurrentHealth(5);
        setName("Player");

        gear = new ArrayList<Gear>();

        Gear Dagger = new Gear(
                "Dagger", 2, 0, 0);
        Gear LeatherScraps = new Gear(
                "Leather Scraps", 0, 1, 0);
        gear.add(Dagger);
        gear.add(LeatherScraps);
        coinPurse = 0;
    }

    public List<Gear> getGear(){return gear;}
    public int getCoinPurse(){return coinPurse;}

    public void AddCoinPurse(int p){coinPurse += p;}
    public void AddGear(Gear g){ gear.add(g);}

    public String removeCoin(int p){
        coinPurse -= p;
        if (coinPurse < 0){
            coinPurse += p;
            return "You do not have the coin.";
        }

        return "You have paid the price.";
    }
    public void removeGear(String n){
        gear.removeIf(g -> g.getName().equals(n));
    }

    public int getAttack(){
        int dmg = super.getAttack();
        for(Gear g : gear){
            dmg += g.getAtkBuff();
        }
        return dmg;
    }

}