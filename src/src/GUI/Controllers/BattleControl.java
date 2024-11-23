package GUI.Controllers;

import GUI.Panels.CharacterCreationPanel;
import GUI.Panels.LeaderboardPanel;
import Game.Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BattleControl implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;
    private Player player;
    private List<Enemy> enemies;
    private int battleCounter;

    // Constructor for the initial controller.
    public BattleControl(JPanel container)
    {
        this.container = container;
        this.player = new Player();
        this.enemies = new ArrayList<>();
        battleCounter = 0;

        Goblin goblin = new Goblin();
        Wizard wizard = new Wizard();
        Orc orc = new Orc();
        Knight knight = new Knight();
        Dragon dragon = new Dragon();
        SecretBoss baarsch = new SecretBoss();

        enemies.add(goblin);
        enemies.add(wizard);
        enemies.add(orc);
        enemies.add(knight);
        enemies.add(dragon);
        enemies.add(baarsch);
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae)
    {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // The Login button takes the user to the login panel.
        if (command.equals("New Game"))
        {

        }

        // Opens a high score panel.
        else if (command.equals("Leaderboard"))
        {

        }

        // Quit game.
        else if (command.equals("Quit"))
        {

        }
    }



    public int getBattleCounter(){return battleCounter;}
    public void setBattleCounter(int battleCounter){this.battleCounter = battleCounter;}
}
