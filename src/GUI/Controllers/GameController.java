package GUI.Controllers;

import GUI.Panels.*;
import Game.Action;
import Game.Attack;
import Game.Battle;
import Game.Entities.*;
import Game.Music.MusicPlayer;
import src.Game.Item;
import Game.Gear;

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
    private List<Battle> battles;
    private int battleCounter;
    private String classChoice;
    private BattlePanel firstBattle;
    private MusicPlayer musicPlayer;
    private MusicPlayer soundPlayer;
    private String playerName;

    // Constructor for the initial controller.
    public GameController(JPanel container)
    {
        // Initialize MusicPlayer
        musicPlayer = new MusicPlayer();

        // Initialize SoundPlayer
        soundPlayer = new MusicPlayer();

        // Play background music (looping)
        String musicPath = "src/Game/Music/mainmenu1.wav"; // Replace with your file path
        musicPlayer.play(musicPath);
        musicPlayer.loop();

        this.container = container;
        this.player = new Player();
        this.battles = new ArrayList<>();
        battleCounter = 0;
        this.classChoice = "";

        battles.add(new Battle(this.player, new Goblin()));
        battles.add(new Battle(this.player, new Wizard()));
        battles.add(new Battle(this.player, new Orc()));
        battles.add(new Battle(this.player, new Knight()));
        battles.add(new Battle(this.player, new Dragon()));
        battles.add(new Battle(this.player, new SecretBoss()));
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae) {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // Play a sound effect.
        soundPlayer.play("src/Game/Music/button.wav");

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
            playerName = panel.getNameFieldText();

            // Check if the name is empty
            if (playerName.isEmpty())
            {
                showError(panel, "You must have a name, right?");
            }
            else if (classChoice.equals(""))
            {
                showError(panel, "You must choose a class.");
            }
            else
            {
                //Create the character.
                createCharacter();

                firstBattle = new BattlePanel(this);
                container.add(firstBattle, "battle");

                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "battle");

                // Stop the main menu music.
                musicPlayer.stop();

                // Play battle music (looping)
                String musicPath = "src/Game/Music/battle1.wav"; // Replace with your file path
                musicPlayer.play(musicPath);
                musicPlayer.loop();
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
        else if (command.equals("Fight"))
        {
            showAttacks(container);
        }

        // Open a panel consisting of "Block" and "Heal" (Limited uses)
        else if (command.equals("Utility"))
        {
            showActions(container);
        }

        // Surrender the fight to enter game over state without losing gold.
        else if (command.equals("Surrender"))
        {
            surrender(container);
        }

        //-----------------------------------------------------------------------
        //SELECT REWARD
        //-----------------------------------------------------------------------
        else if (command.equals("Next Battle"))
        {
            BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

            battlePanel.getEnemyPanel().updateEntity(battles.get(battleCounter).getEnemy());

            battlePanel.revalidate();
            battlePanel.repaint();

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
            System.out.println("We are exiting the shop!");
        }

        //-----------------------------------------------------------------------
        //VICTORY OR GAME OVER
        //-----------------------------------------------------------------------
        if (player.getCurrentHealth() <= 0) {

            reset();
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "gameover");
            System.out.println("You are dead");
        }

        ///Buttons for GAMEOVER
        if (command.equals("Try Again")) {

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
        }

        else if (command.equals("Main Menu"))
        {
            Component source = (Component) ae.getSource();
            if (SwingUtilities.getAncestorOfClass(GameOverPanel.class, source) != null)
            {
                reset();
            }

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainmenu");
        }

        else if (command.equals("Continue..."))
        {
            BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

            battlePanel.getEnemyPanel().updateEntity(battles.get(battleCounter).getEnemy());

            battlePanel.revalidate();
            battlePanel.repaint();

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
        }
    }

    //-----------------------------------------------------------------------
    // UTILITY FUNCTIONS
    //-----------------------------------------------------------------------

    public void createCharacter()
    {
        player.setName(playerName);
        player.addAction(new Action("Guard", false, 1));
        player.addAction(new Action("Healing Potion", true, 3));

        switch (classChoice) {
            case "Warrior":
                player.setSprite("src/Sprites/warriorplayer.png");
                player.setStat(player.HIGH);
                player.setAttack(player.getStat());

                player.setStat(player.STANDARD);
                player.setDefense(player.getStat());

                player.setStat(player.LOW);
                player.setMagic(player.getStat());

                player.addAttack(new Attack("Frontal Slash", "Physical", 80, 100, 100));
                player.addAttack(new Attack("Vital Strike", "Physical", 120, 15, 15));
                player.addAttack(new Attack("Piercing Blow", "Physical", 180, 3, 3));

                break;
            case "Mage":
                player.setSprite("src/Sprites/mageplayer.png");
                player.setStat(player.LOW);
                player.setAttack(player.getStat());

                player.setStat(player.STANDARD);
                player.setDefense(player.getStat());

                player.setStat(player.HIGH);
                player.setMagic(player.getStat());

                player.addAttack(new Attack("Staff Bash", "Physical", 80, 100, 100));
                player.addAttack(new Attack("Fire Bolt", "Magic", 120, 15, 15));
                player.addAttack(new Attack("Lightning Spear", "Magic", 180, 3, 3));
                break;
            case "Paladin":
                player.setSprite("src/Sprites/paladinplayer.png");
                player.setStat(player.LOW);
                player.setAttack(player.getStat());

                player.setStat(player.HIGH);
                player.setDefense(player.getStat());

                player.setStat(player.STANDARD);
                player.setMagic(player.getStat());

                player.addAttack(new Attack("Heavy Swing", "Physical", 80, 100, 100));
                player.addAttack(new Attack("Holy Smite", "Magic", 120, 15, 15));
                player.addAttack(new Attack("Divine Judgement", "Magic", 180, 3, 3));
                player.addAttack(new Attack("UNHOLY DESTRUCTION", "Magic", 10080, 404, 404));

                break;
            default:
                player.setStat(player.LOW);
                player.setAttack(player.getStat());

                player.setStat(player.LOW);
                player.setDefense(player.getStat());

                player.setStat(player.LOW);
                player.setMagic(player.getStat());
                break;
        }
    }

    //Resets the player and enemy list
    public void reset() {
        this.player = new Player();
        this.battles = new ArrayList<>();
        battleCounter = 0;
        this.classChoice = "";

        battles.add(new Battle(this.player, new Goblin()));
        battles.add(new Battle(this.player, new Wizard()));
        battles.add(new Battle(this.player, new Orc()));
        battles.add(new Battle(this.player, new Knight()));
        battles.add(new Battle(this.player, new Dragon()));
        battles.add(new Battle(this.player, new SecretBoss()));

        VictoryPanel victoryPanel = (VictoryPanel) container.getComponent(6);
        victoryPanel.trueVictory(false);
    }

    private void debugBattle()
    {
        /******************************
        //Logic to hit enemies and swap to next enemy.
        Enemy currentEnemy = battles.get(battleCounter).getEnemy();
        currentEnemy.setCurrentHealth(currentEnemy.getCurrentHealth() - 50);

        System.out.println(currentEnemy.getCurrentHealth());
        if (currentEnemy.getCurrentHealth() <= 0) {
            if (enemies.size() > 1) {
                enemies.remove(0);
                System.out.println("New Enemy appears");
                updateBattle();
            } else if (enemies.size() == 1) {
                System.out.println("You won!");
            }
        }
        //*****************************
        //Logic to take dmg from enemies
        player.setCurrentHealth(player.getCurrentHealth() - 1);
        System.out.print("Player health is now: ");
        System.out.println(player.getCurrentHealth());

        ****************************/
    }

    // Update battle panel after victory
    private void updateBattle()
    {
        BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

        battlePanel.getEnemyPanel().updateEntity(battles.get(battleCounter).getEnemy());

        battlePanel.revalidate();
        battlePanel.repaint();
    }

    public void playerAction()
    {
        if (battles.get(battleCounter).winConditionMet())
        {
            battleCounter++;
            if (battleCounter == 5)
            {
                VictoryPanel victoryPanel = (VictoryPanel) container.getComponent(6);
                victoryPanel.trueVictory(false);

                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "victory");
            }
            else if (battleCounter > 5)
            {
                VictoryPanel victoryPanel = (VictoryPanel) container.getComponent(6);
                victoryPanel.trueVictory(true);

                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "victory");
            }
            else
            {
                moveToSelectItemScreen();
            }
        }
        else
        {
            updateBattle();
        }

    }

    public void enemyTurn()
    {
        battles.get(battleCounter).determineEnemyAction();
    }

    public void moveToSelectItemScreen() {
        ItemPanel itemSelect = (ItemPanel) container.getComponent(4);

        Enemy currentEnemy = battles.get(battleCounter).getEnemy();

        if (currentEnemy.getName().equals("Dragon")) {
            itemSelect.needSoyMilk();
        }

        // Reset item panel to regenerate items
        itemSelect.resetPanel(this);

        // Switch to item panel
        CardLayout cardLayout = (CardLayout) container.getLayout();
        cardLayout.show(container, "selectreward");

        // Stop the current music
        musicPlayer.stop();

        // Play the new music (looping)
        String musicPath = "src/Game/Music/mainmenu1.wav";
        musicPlayer.play(musicPath);
        musicPlayer.loop();
    }


    //-----------------------------------------------------------------------
    // SETTERS AND GETTERS
    //-----------------------------------------------------------------------

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setBattle(Battle battle, int index)
    {
        battles.set(index, battle);
    }

    public Battle getBattle(int index)
    {
        return battles.get(index);
    }

    public List<Battle> getBattles() {return battles;}

    public void setBattles(List<Battle> battles) {this.battles = battles;}

    public void setBattleCounter(int index)
    {
        battleCounter = index;
    }

    public int getBattleCounter()
    {
        return battleCounter;
    }

    //-----------------------------------------------------------------------
    //SPECIAL DIALOG BOXES
    //-----------------------------------------------------------------------
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
        okButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            // Close the dialog
            dialog.dispose();
        });

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        // Pack and position the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        // Show the dialog
        dialog.setVisible(true);
    }

    private void showActions(Component parent)
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

        JLabel messageLabel = new JLabel("Actions", JLabel.CENTER);
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add padding to the panel
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(messageLabel);

        // Create a panel to show all actions

        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.BLACK);
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        for (int i = 0; i < player.getActionCount(); i++)
        {
            // Create a button for the action.
            Action action = player.getAction(i);
            String actionlabel = action.getAction();

            if (action.isLimited())
            {
                actionlabel = action.getAction() + "     [" + action.getUses() + "/" + action.getMaxUses() + "]";
            }

            JButton actionButton = new JButton(actionlabel);
            actionButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
            actionButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            actionButton.setBackground(Color.BLACK);
            actionButton.setForeground(Color.YELLOW);
            actionButton.setFocusPainted(false);
            actionButton.addActionListener(e -> {
                // Play the sound effect
                soundPlayer.play("src/Game/Music/button.wav");

                // Close the dialog
                dialog.dispose();
            });

            actionsPanel.add(actionButton);
        }

        // Create a button to return
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        closeButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.YELLOW);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            // Close the dialog
            dialog.dispose();
        });

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.NORTH);
        dialog.add(actionsPanel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        // Pack and position the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        // Show the dialog
        dialog.setVisible(true);
    }

    private void showAttacks(Component parent)
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

        JLabel messageLabel = new JLabel("Attacks", JLabel.CENTER);
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add padding to the panel
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(messageLabel);

        // Create a panel to show all attacks
        JPanel attackPanel = new JPanel();
        attackPanel.setBackground(Color.BLACK);
        attackPanel.setLayout(new BoxLayout(attackPanel, BoxLayout.Y_AXIS));

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        for (int i = 0; i < player.getAttackCount(); i++)
        {
            // Create a button for the attack.
            int attackIndex = i;
            Attack attack = player.getAttackMove(i);
            String attacklabel = "";

            if (attack.getType().equals("Physical"))
            {
                attacklabel = attack.getName() + "     [" + attack.getUses() + "/" + attack.getMaxUses() + "]  [PHYS]";
            }
            else if (attack.getType().equals("Magic"))
            {
                attacklabel = attack.getName() + "     [" + attack.getUses() + "/" + attack.getMaxUses() + "]   [MAG]";
            }

            JButton attackButton = new JButton(attacklabel);
            attackButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
            attackButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            attackButton.setBackground(Color.BLACK);
            attackButton.setForeground(Color.YELLOW);
            attackButton.setFocusPainted(false);
            attackButton.addActionListener(e -> {
                // Play the sound effect
                soundPlayer.play("src/Game/Music/button.wav");

                battles.get(battleCounter).attack(battles.get(battleCounter).getPlayer(),battles.get(battleCounter).getEnemy(),battles.get(battleCounter).getPlayer().getAttackMove(attackIndex));

                playerAction();

                // Close the dialog
                dialog.dispose();
            });

            attackPanel.add(attackButton);
        }

        // Create a button to return
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        closeButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.YELLOW);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            // Close the dialog
            dialog.dispose();
        });

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.NORTH);
        dialog.add(attackPanel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        // Pack and position the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        // Show the dialog
        dialog.setVisible(true);
    }

    private void surrender(Component parent)
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

        JLabel messageLabel = new JLabel("Surrender?", JLabel.CENTER);
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add padding to the panel
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(messageLabel);

        //Set up border format for buttons
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        //Create a surrender button
        JButton surrenderButton = new JButton("Yes, I give in...");
        surrenderButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        surrenderButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        surrenderButton.setBackground(Color.BLACK);
        surrenderButton.setForeground(Color.YELLOW);
        surrenderButton.setFocusPainted(false);
        surrenderButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "gameover");

            // Close the dialog
            dialog.dispose();
        });


        // Create a button to return to the fight
        JButton closeButton = new JButton("No, I'll keep fighting!");
        closeButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        closeButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.YELLOW);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            // Close the dialog
            dialog.dispose();
        });

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.NORTH);
        dialog.add(surrenderButton, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        // Pack and position the dialog
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        // Show the dialog
        dialog.setVisible(true);
    }

    /// ///////////////////////////////////////////
    ///         UPDATES UPON ITEM PURCHASE
    /// ///////////////////////////////////////////

    public String rewardSelection(Item selectedItem) {
        String message = "Select an item."; // Message to be displayed upon item purchase (if purchase fails)
        // Add item to player Gear if they can afford item
        if (player.getCoinPurse() >= selectedItem.itemPrice) {
            player.AddCoinPurse(-selectedItem.itemPrice); // Deduct coins
            player.AddGear(new Game.Gear( // Add gear
                    selectedItem.itemName,+
                    selectedItem.attackBoost,
                    selectedItem.defenseBoost,
                    selectedItem.magicBoost,
                    selectedItem.healthBuff,
                    selectedItem.isBuff,
                    selectedItem.isAction,
                    selectedItem.isMisc
            ));
            System.out.println("Player has enough coins.");
        } else {
            // Message to reject purchase
            message = "Not enough coins to purchase: " + selectedItem.itemName + ".";
            System.out.println("Player does not have enough coins.");
        }

        // List current gear (for testing)
        System.out.println("Current Gear:");
        List<Game.Gear> gear = player.getGear();
        for (Game.Gear g : gear) {
            System.out.println(g.getName());
        }

        return message;
    }

    public void selectReward() {
        ItemPanel itemPanel = new ItemPanel(this);
        container.add(itemPanel, "selectreward");
    }

    public boolean inInventory(Item item) {
        for (Gear g : player.getGear()) {
            if (g.getName().equals(item.itemName)) {
                return true;
            }
        }
        return false;
    }

}
