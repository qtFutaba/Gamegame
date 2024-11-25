package GUI.Controllers;

import GUI.Panels.*;
import Game.Entities.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameController implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;
    private Player player;
    private List<Enemy> enemies;
    private int battleCounter;
    private String classChoice;
    private BattlePanel firstBattle;


    // Constructor for the initial controller.
    public GameController(JPanel container)
    {
        this.container = container;
        this.player = new Player();
        this.enemies = new ArrayList<>();
        battleCounter = 0;
        this.classChoice = "";

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
    public void actionPerformed(ActionEvent ae) {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        //-----------------------------------------------------------------------
        //CHARACTER CREATION
        //-----------------------------------------------------------------------
        CharacterCreationPanel panel = (CharacterCreationPanel) container.getComponent(2);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Select Warrior.
        if (command.equals("Warrior")) {
            classChoice = "Warrior";
            panel.warriorButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
            panel.mageButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            panel.paladinButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        }

        // Select Mage.
        else if (command.equals("Mage")) {
            classChoice = "Mage";
            panel.warriorButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            panel.mageButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
            panel.paladinButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        }

        // Select Paladin.
        else if (command.equals("Paladin")) {
            classChoice = "Paladin";
            panel.warriorButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            panel.mageButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            panel.paladinButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
        }

        // Create character and start the game.
        else if (command.equals("Begin Your Journey...")) {
            // Get the name entered by the user
            String playerName = panel.getNameFieldText();

            // Check if the name is empty
            if (playerName.isEmpty()) {
                showError(panel, "You must have a name, right?");
            } else if (classChoice.equals("")) {
                showError(panel, "You must choose a class.");
            } else {
                player.setName(playerName);

                switch (classChoice) {
                    case "Warrior":
                        player.setSprite("src/Sprites/warriorplayer.png");
                        player.setStat(player.HIGH);
                        player.setAttack(player.getStat());

                        player.setStat(player.STANDARD);
                        player.setDefense(player.getStat());

                        player.setStat(player.LOW);
                        player.setMagicPoints(player.getStat());
                        break;
                    case "Mage":
                        player.setSprite("src/Sprites/mageplayer.png");
                        player.setStat(player.LOW);
                        player.setAttack(player.getStat());

                        player.setStat(player.STANDARD);
                        player.setDefense(player.getStat());

                        player.setStat(player.HIGH);
                        player.setMagicPoints(player.getStat());
                        break;
                    case "Paladin":
                        player.setSprite("src/Sprites/paladinplayer.png");
                        player.setStat(player.LOW);
                        player.setAttack(player.getStat());

                        player.setStat(player.HIGH);
                        player.setDefense(player.getStat());

                        player.setStat(player.STANDARD);
                        player.setMagicPoints(player.getStat());
                        break;
                    default:
                        player.setStat(player.LOW);
                        player.setAttack(player.getStat());

                        player.setStat(player.LOW);
                        player.setDefense(player.getStat());

                        player.setStat(player.LOW);
                        player.setMagicPoints(player.getStat());
                        break;
                }

                firstBattle = new BattlePanel(this);
                container.add(firstBattle, "battle");

                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "battle");
            }

        }

        //-----------------------------------------------------------------------
        //MAIN MENU
        //-----------------------------------------------------------------------
        // Start the game.
        else if (command.equals("New Game")) {
            //CharacterCreationPanel newgamePanel = (CharacterCreationPanel) container.getComponent(2);
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "charactercreation");
        }

        // Opens a high score panel.
        else if (command.equals("Leaderboard")) {
            //LeaderboardPanel scoresPanel = (LeaderboardPanel) container.getComponent(1);
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "leaderboard");
        }

        // Quit game.
        else if (command.equals("Exit Game")) {
            System.exit(0);
        }

        //-----------------------------------------------------------------------
        //LEADERBOARD
        //-----------------------------------------------------------------------
        // Return to main menu.
        else if (command.equals("Return to Main Menu.")) {
            //MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(0);
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainmenu");
        }

        //-----------------------------------------------------------------------
        //BATTLE
        //-----------------------------------------------------------------------
        // Opens a panel containing player attacking moves.
        else if (command.equals("Fight")) {
            //******************************
            //Logic to hit enemies and swap to next enemy.
            Enemy currentEnemy = getEnemy(0);
            currentEnemy.setCurrentHealth(currentEnemy.getCurrentHealth() - 50);

            System.out.println(currentEnemy.getCurrentHealth());
            if (currentEnemy.getCurrentHealth() <= 0) {
                if (enemies.size() > 1) {
                    enemies.remove(0);
                    System.out.println("New Enemy appears");
                    update();
                } else if (enemies.size() == 1) {
                    System.out.println("You won!");
                }
            }
            //*****************************


            //****************************
            //Logic to take dmg from enemies
            player.setCurrentHealth(player.getCurrentHealth() - 1);
            System.out.print("PLayer health is now: ");
            System.out.println(player.getCurrentHealth());

            //****************************
        }

        // Open a panel consisting of "Block" and "Heal" (Limited uses)
        else if (command.equals("Utility")) {

        }

        // Surrender the fight to enter game over state without losing gold.
        else if (command.equals("Surrender")) {

        }
        //-----------------------------------------------------------------------
        //SELECT REWARD
        //-----------------------------------------------------------------------

        //-----------------------------------------------------------------------
        //VICTORY OR GAME OVER
        //-----------------------------------------------------------------------
        if (player.getCurrentHealth() <= 0) {

            reset();
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "gameover");
            System.out.println("You are dead");

        } else if (enemies.size()==1) {
            reset();
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "victory");
            System.out.println("Victory");
        }

        ///Buttons for GAMEOVER
        if (command.equals("Try Again")) {

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
        }
        else if (command.equals("Main Menu")) {

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainmenu");
        }


    }

    //Resets the player and enemy list
    public void reset() {
        this.player = new Player();
        this.enemies = new ArrayList<>();
        battleCounter = 0;
        this.classChoice = "";

        enemies.add(new Goblin());
        enemies.add(new Wizard());
        enemies.add(new Orc());
        enemies.add(new Knight());
        enemies.add(new Dragon());
        enemies.add(new SecretBoss());
    }

    // Update panel after victory
    private void update() {
        BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

        // Update enemy to next enemy
        if (!enemies.isEmpty()) {
            Enemy currentEnemy = getEnemy(0);

            battlePanel.getEnemyPanel().updateEntity(currentEnemy);

            battlePanel.revalidate();
            battlePanel.repaint();
        }
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setEnemy(Enemy enemy, int index)
    {
        enemies.set(index, enemy);
    }

    public Enemy getEnemy(int index)
    {
        return enemies.get(index);
    }

    public void setBattleCounter(int index)
    {
        battleCounter = index;
    }

    public int getBattleCounter()
    {
        return battleCounter;
    }

    private void showError(Component parent, String message)
    {
        //Create a dialog
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Message", true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(Color.BLACK);

        //Create the panel.
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.BLACK);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add padding to the panel
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(messageLabel);

        // Create a button to return
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        JButton okButton = new JButton("Right...");
        okButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        okButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        okButton.setBackground(Color.BLACK);
        okButton.setForeground(Color.YELLOW);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> dialog.dispose());

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        // Pack and position the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        // Show the dialog
        dialog.setVisible(true);
    }


}
