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
            battlePanel.getBattleLog().setText("\"" + ((Enemy) attacker).getAttackMsg()+ "\" " + attacker.getName() + " used " + attack.getName() + "!");
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

            battlePanel.getBattleLog().setText(battlePanel.getBattleLog().getText() + " Critical Hit!");
        }
        
        if (attacker.isCharging())
        {
            critMultiplier = 2;
            attacker.setCharging(false);
        }

        if (attacker.isBlocking())
        {
            battlePanel.getBattleLog().setText(attacker.getName() + " drops their guard.");
            attacker.setBlocking(false);
        }

        int damage;
        if (!defender.isBlocking() && !attacker.isBlocking()) {

            String soundEffectFileName;

            // Random factor between 220 and 255
            double randomFactor = (220 + random.nextInt(36)) / 255.0;

            // Determine the stat to be used for damage/defense calculations

            int attackerAttack = 0;
            int defenderDefense = 0;

            if (attack.getType().equals("Physical")) {
                attackerAttack = attacker.getAttack();
                defenderDefense = defender.getDefense();
                soundEffectFileName = "src/Game/Music/physicalattack.wav";
            } else if (attack.getType().equals("Magic")) {
                attackerAttack = attacker.getMagic();
                defenderDefense = defender.getMagic();
                soundEffectFileName = "src/Game/Music/magicattack.wav";
            } else
            {
                soundEffectFileName = "";
            }
            Timer soundEffectTimer = new Timer(100, e ->
            {
                this.soundPlayer.play(soundEffectFileName);
            });
            soundEffectTimer.setRepeats(false);
            soundEffectTimer.start();

            // Damage formula
            damage = (int) ((((2 * attacker.getLevel() * critMultiplier) * attack.getPower() * ((double) attackerAttack / defenderDefense)) / 60) * randomFactor);
            if (attacker instanceof Player)
            {
                battlePanel.getEnemyPanel().dropHealthBar(defender.getCurrentHealth() - damage , defender);
            }
            else
            {
                battlePanel.getPlayerPanel().dropHealthBar(defender.getCurrentHealth() - damage, defender);
            }

            battlePanel.getBattleLog().setText(battlePanel.getBattleLog().getText() + " " + defender.getName() + " takes " + damage + " damage!");
        }
        else
        {
            damage = 0;
            battlePanel.getBattleLog().setText(battlePanel.getBattleLog().getText() + " " + defender.getName() +" blocked the attack.");
            defender.setBlocking(false);
        }

        // Deal the damage
        defender.setCurrentHealth(defender.getCurrentHealth() - damage);

        // Reduce the use counter.
        attack.setUses(attack.getUses() - 1);
        if (attack.getUses() < 0)
        {
            attack.setUses(0);
        }
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

        else
        {
            stall();
        }
    }

    public void stall()
    {
        battlePanel.getBattleLog().setText("The " + enemy.getName() +" taunts you. \"" + enemy.getTauntMsg()+ "\"");
    }

    public void playerAction(Action action)
    {

        if (action.getAction().equals("Guard"))
        {
            player.setBlocking(true);
            battlePanel.getBattleLog().setText(player.getName() + " raises their guard.");

        }
        else if (action.getAction().equals("Healing Potion"))
        {
            if (action.getUses() > 0)
            {
                //Determine the difference between the player's current health and their max health.
                int difference = player.getMaxHealth() - player.getCurrentHealth();

                //If you have taken damage...
                if (difference > 0) {
                    //Determine if recovering 50 health will make you go over your max hp.
                    if (difference > 50) {
                        difference = 50;
                    }

                    //Then heal at most 50 health.

                    battlePanel.getPlayerPanel().dropHealthBar(player.getCurrentHealth() + difference, player);
                    battlePanel.getBattleLog().setText(player.getName() + " chugs a potion... and heals " + difference + " health.");
                } else {
                    battlePanel.getBattleLog().setText(player.getName() + " chugs a potion... but nothing happens.");
                }

                action.setUses(action.getUses() - 1);
                if (action.getUses() < 0)
                {
                    action.setUses(0);
                }
            }
            else
            {
                battlePanel.getBattleLog().setText(player.getName() + " reaches for a potion, but turns up empty.");
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


                action.setUses(action.getUses() - 1);
                if (action.getUses() < 0)
                {
                    action.setUses(0);
                }
            }
        }
        else if (action.getAction().equals("Block"))
        {
            enemy.setBlocking(true);
            battlePanel.getBattleLog().setText("The " + enemy.getName() +" raises their guard.");

        }
        else if (action.getAction().equals("Stall"))
        {
            stall();
        }
        else if (action.getAction().equals("Heal"))
        {
            if (action.getUses() > 0) {
                //Determine the difference between the enemy's current health and their max health.
                int difference = enemy.getMaxHealth() - enemy.getCurrentHealth();

                //If they have taken damage...
                if (difference > 0) {
                    //Determine if recovering 50 health will make them go over their max hp.
                    if (difference > 50) {
                        difference = 50;
                    }

                    //Then heal at most 50 health.

                    battlePanel.getEnemyPanel().dropHealthBar(enemy.getCurrentHealth() + difference, enemy);
                    battlePanel.getBattleLog().setText("The " + enemy.getName() + " casts a healing spell... and recovers " + difference + " health.");

                    action.setUses(action.getUses() - 1);
                    if (action.getUses() < 0)
                    {
                        action.setUses(0);
                    }
                } else {
                    //Otherwise, why heal? Do something else.
                    determineEnemyAction();
                }
            }
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
                if (gear.getName().equals("Mysterious Liquid"))
                {
                    player.removeGear(gear.getName());

                    int difference = player.getMaxHealth() - player.getCurrentHealth();

                    battlePanel.getPlayerPanel().dropHealthBar(player.getCurrentHealth() + difference, player);
                    battlePanel.getBattleLog().setText(player.getName() + " drops... but is brought back by a Mysterious Liquid.");
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

    public Player getPlayer()
    {
        return player;
    }

    public void setBattlePanel(BattlePanel battlePanel)
    {
        this.battlePanel = battlePanel;
    }
}
