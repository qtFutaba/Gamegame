package Game;

import GUI.Panels.BattlePanel;
import GUI.Panels.EntityPanel;
import Game.Entities.*;
import Game.Music.MusicPlayer;

import javax.swing.*;
import java.util.Random;

public class Battle
{
    private Player player;
    private Enemy enemy;
    private MusicPlayer soundPlayer;
    private BattlePanel battlePanel;
    private EntityPanel enemyPanel;


    public Battle(Player player, Enemy enemy)
    {
        this.player = player;
        this.enemy = enemy;
        this.soundPlayer = new MusicPlayer();
    }

    public void setSoundPlayer(MusicPlayer soundPlayer)
    {
        this.soundPlayer = soundPlayer;
    }

    public static boolean isCriticalHit()
    {
        Random random = new Random();

        // Generate a number between 0 and 99
        int chance = random.nextInt(100);

        return chance < 15; // 15% chance for a critical hit
    }

    public static boolean isWastedTurn(int turnwastechance)
    {
        Random random = new Random();

        // Generate a number between 0 and 99
        int chance = random.nextInt(100);

        return chance < turnwastechance;
    }

    public void attack(Entity attacker, Entity defender, Attack attack)
    {
        if (attacker instanceof Enemy)
        {
            battlePanel.getBattleLog().setText("\"" + ((Enemy) attacker).getAttackMsg()+ "\"");
            Timer messagetimer = new Timer(1250, e -> {
                battlePanel.getBattleLog().setText(attacker.getName() + " used " + attack.getName() + "!");
            });
            messagetimer.setRepeats(false);
            messagetimer.start();
        }
        else
        {
            battlePanel.getBattleLog().setText(attacker.getName() + " used " + attack.getName() + "!");
        }
        Random random = new Random();

        // Critical multiplier
        int critMultiplier = 1;

        if (isCriticalHit())
        {
            critMultiplier = 2;
        }
        
        if (attacker.isCharging())
        {
            critMultiplier = 2;
            attacker.setCharging(false);
        }

        int damage;
        if (!defender.isBlocking()) {

            // Random factor between 220 and 255
            double randomFactor = (220 + random.nextInt(36)) / 255.0;

            // Determine the stat to be used for damage/defense calculations

            int attackerAttack = 0;
            int defenderDefense = 0;

            if (attack.getType().equals("Physical")) {
                attackerAttack = attacker.getAttack();
                defenderDefense = defender.getDefense();
            } else if (attack.getType().equals("Magic")) {
                attackerAttack = attacker.getMagic();
                defenderDefense = defender.getMagic();
            }

            // Damage formula
            damage = (int) ((((((2 * attacker.getLevel() * critMultiplier + 2) + 2) * attack.getPower() * (attackerAttack / defenderDefense)) / 50) + 2) * randomFactor);
            if (attacker instanceof Player)
            {
                battlePanel.getEnemyPanel().dropHealthBar(defender.getCurrentHealth() - damage);
            }
            else
            {
                battlePanel.getPlayerPanel().dropHealthBar(defender.getCurrentHealth() - damage);
            }

            Timer damagetimer = new Timer(1250, e -> {
                battlePanel.getBattleLog().setText(battlePanel.getBattleLog().getText() + "\n" + defender.getName() +" took " + damage + " damage.");
            });
            damagetimer.setRepeats(false);
            damagetimer.start();


        }
        else
        {
            damage = 0;
            battlePanel.getBattleLog().setText(defender.getName() +" blocked the attack.");
            defender.setBlocking(false);
        }

        // Deal the damage
        defender.setCurrentHealth(defender.getCurrentHealth() - damage);

        // Reduce the use counter.
        attack.setUses(attack.getUses() - 1);
    }

    public void determineEnemyAction()
    {
        Random random = new Random();
        
        if(!isWastedTurn(enemy.getTurnWasteChance()))
        {
            // Generate a number between 0 and 99
            int chance = random.nextInt(100);

            //30% chance to use an action. I'm not making this AI very smart. It just does shit randomly.
            if (chance < 30)
            {
                int actionindex = random.nextInt(enemy.getActionCount());
                enemyAction(enemy.getAction(actionindex));
            }
            
            //Attack otherwise.
            else
            {
                int attackindex = random.nextInt(enemy.getAttackCount());
                attack(enemy,player,enemy.getAttackMove(attackindex));
            }
        }
    }
    
    public void enemyAction(Action action)
    {
        if (action.getAction().equals("Charge"))
        {
            if (enemy.isCharging())
            {
                Random random = new Random();
                int attackindex = random.nextInt(enemy.getAttackCount());
                
                attack(enemy, player, enemy.getAttackMove(attackindex));
            }
            else
            {
                enemy.setCharging(true);
                battlePanel.getBattleLog().setText(enemy.getName() +" draws back, and appears to begin preparing a powerful attack.");
            }
        }
        else if (action.getAction().equals("Block"))
        {
            enemy.setBlocking(true);
            battlePanel.getBattleLog().setText(enemy.getName() +" raises their guard.");

        }
        else if (action.getAction().equals("Stall"))
        {
            battlePanel.getBattleLog().setText(enemy.getName() +" taunts you.");
        }
        else if (action.getAction().equals("Heal"))
        {
            //Determine the difference between the enemy's current health and their max health.
            int difference = enemy.getMaxHealth() - enemy.getCurrentHealth();

            //If they have taken damage...
            if (difference > 0)
            {
                //Determine if recovering 50 health will make them go over their max hp.
                if (difference > 50)
                {
                    difference = 50;
                }

                //Then heal at most 50 health.

                battlePanel.getEnemyPanel().dropHealthBar(enemy.getCurrentHealth() + difference);
                battlePanel.getBattleLog().setText(enemy.getName() +" casts a healing spell... and recovers " + difference + " health.");
            }
            //Otherwise, why heal? Do something else.
            determineEnemyAction();
        }
    }

    public boolean winConditionMet()
    {
        if (enemy.getCurrentHealth() <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean loseConditionMet()
    {
        if (player.getCurrentHealth() <= 0)
        {
            for (Gear gear : player.getGear())
            {
                if (gear.getName().equals("StrangeLiquid"))
                {
                    player.removeGear(gear.getName());
                    player.setCurrentHealth(player.getMaxHealth());
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }


    public Enemy getEnemy()
    {
        return this.enemy;
    }

    public void setEnemy(Enemy enemy)
    {
        this.enemy = enemy;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setBattlePanel(BattlePanel battlePanel)
    {
        this.battlePanel = battlePanel;
    }
}
