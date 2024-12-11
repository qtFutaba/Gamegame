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
    private boolean devMode;
    private int playerAttackActionIndex;

    // Constructor for the main game controller.
    public GameController(JPanel container)
    {
        // Initialize MusicPlayer.
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
        this.battleCounter = 0;
        this.classChoice = "";
        this.devMode = false;
        this.playerAttackActionIndex = 0;

        battles.add(new Battle(this.player, new Goblin()));
        battles.add(new Battle(this.player, new Wizard()));
        battles.add(new Battle(this.player, new Orc()));
        battles.add(new Battle(this.player, new Knight()));
        battles.add(new Battle(this.player, new Dragon()));
        battles.add(new Battle(this.player, new SecretBoss()));
    }

    // HANDLE ALL BUTTON CLICKS.
    /*
    EFFECTIVELY, EACH BUTTON IS CALLED BY NAME.
    EACH PANEL HAS ITS OWN BUTTONS, BUT SOME SHARE THE SAME FUNCTION (IE: MAIN MENU).
    DEPENDING ON THOSE ACTIONS, CALL THE FUNCTION APPROPRIATE.
     */
    public void actionPerformed(ActionEvent ae) {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // Play a sound effect when a button is clicked.
        soundPlayer.play("src/Game/Music/button.wav");

        // For certain buttons, create two borders for unselected or selected choices.
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        //-----------------------------------------------------------------------
        //SETTINGS
        //-----------------------------------------------------------------------
        CharacterCreationPanel charPanel = (CharacterCreationPanel) container.getComponent(2);
        SettingsPanel settingsPanel = (SettingsPanel) container.getComponent(8);

        // Dev mode on.
        if (command.equals("On")) {
            devMode = true;
            settingsPanel.devModeOnButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
            settingsPanel.devModeOffButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        }

        // Dev mode off.
        else if (command.equals("Off")) {
            devMode = false;
            settingsPanel.devModeOnButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            settingsPanel.devModeOffButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
        }

        //-----------------------------------------------------------------------
        //CHARACTER CREATION
        //-----------------------------------------------------------------------
        CharacterCreationPanel panel = (CharacterCreationPanel) container.getComponent(2);

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
        else if (command.equals("Begin Your Journey..."))
        {
            // Get the name entered by the user
            playerName = panel.getNameFieldText();

            // Check if the name is empty
            if (playerName.isEmpty())
            {
                showError(panel, "You must have a name, right?");
            }
            // Check if the player has actually picked a class.
            else if (classChoice.equals(""))
            {
                showError(panel, "You must choose a class.");
            }
            //If the player has put in a name and selected a class, create a character and start the game.
            else
            {
                //Create the character.
                createCharacter();

                //Initialize the first battle panel.
                firstBattle = new BattlePanel(this);
                container.add(firstBattle, "battle");

                //Connect the battle panel to the battle class.
                setBattlePanels();

                //Enemy greeting.
                firstBattle.getBattleLog().setText("An enemy " + battles.get(battleCounter).getEnemy().getName() + " draws near. \"" + battles.get(battleCounter).getEnemy().getGreeting()+ "\"");

                //Open the battle panel.
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
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "charactercreation");
        }

        // Opens a high score charPanel.
        else if (command.equals("Leaderboard")) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "leaderboard");
        }

        // Quit game.
        else if (command.equals("Exit Game")) {
            System.exit(0);
        }

        // Show settings panel.
        else if (command.equals("Settings")) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "settings");
        }

        // Show credits panel.
        else if (command.equals("Credits")) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "credits");
        }

        //-----------------------------------------------------------------------
        //LEADERBOARD
        //-----------------------------------------------------------------------
        // Return to main menu.
        else if (command.equals("Return to Main Menu.")) {
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainmenu");
        }

        //-----------------------------------------------------------------------
        //BATTLE
        //-----------------------------------------------------------------------
        // Opens a dialog panel containing player attacking moves.
        else if (command.equals("Fight"))
        {
            showAttacks(container);
        }

        // Open a dialog panel consisting of "Block" and "Heal" (Limited uses)
        else if (command.equals("Utility"))
        {
            showActions(container);
        }

        // Open a dialog panel to show current player stats.
        else if (command.equals("Stats"))
        {
            showStats(container);
        }

        // Surrender the fight to enter game over state without losing gold.
        else if (command.equals("Surrender"))
        {
            surrender(container);
        }

        //-----------------------------------------------------------------------
        //SELECT REWARD
        //-----------------------------------------------------------------------

        //Move from the item selection panel to the next battle.
        else if (command.equals("Next Battle"))
        {
            // Stop the current music.
            musicPlayer.stop();

            // Play new battle music.
            String musicPath = "src/Game/Music/battle1.wav";
            musicPlayer.play(musicPath);
            musicPlayer.loop();

            //Get battle panel.
            BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

            //Update it.
            battlePanel.revalidate();
            battlePanel.repaint();

            //Enemy greeting and open panel.
            battlePanel.getBattleLog().setText("An enemy " + battles.get(battleCounter).getEnemy().getName() + " draws near. \"" + battles.get(battleCounter).getEnemy().getGreeting()+ "\"");
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
        }


        //-----------------------------------------------------------------------
        //VICTORY OR GAME OVER
        //-----------------------------------------------------------------------

        //Return to the main menu.
        else if (command.equals("Main Menu") || command.equals("Wait, I change my mind..."))
        {
            //If the source of the main menu command is a game over panel, reset the game.
            Component source = (Component) ae.getSource();
            if (SwingUtilities.getAncestorOfClass(GameOverPanel.class, source) != null)
            {
                reset();
            }

            //Open the main menu.
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "mainmenu");
        }

        //If you're clicking the "Continue..." button, it leads you to the final boss.
        else if (command.equals("Continue..."))
        {
            //Set battle panel.
            BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);
            battlePanel.getEnemyPanel().updateEntity(battles.get(battleCounter).getEnemy());

            // Stop the current music.
            musicPlayer.stop();

            // Play final boss music.
            String musicPath = "src/Game/Music/finalboss.wav";
            musicPlayer.play(musicPath);
            musicPlayer.loop();

            //Update the battle panel.
            battlePanel.revalidate();
            battlePanel.repaint();

            ///
            battlePanel.getBattleLog().setText("...What? Is that..." + battles.get(battleCounter).getEnemy().getName() + "? \"" + battles.get(battleCounter).getEnemy().getGreeting()+ "\"");

            //Open to final battle.
            CardLayout cardLayout = (CardLayout) container.getLayout();
            cardLayout.show(container, "battle");
        }
    }

    //-----------------------------------------------------------------------
    // UTILITY FUNCTIONS
    //-----------------------------------------------------------------------

    //This function links the battles in the battle list to the battle panel.
    public void setBattlePanels()
    {
        BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

        for (Battle battle : battles)
        {
            battle.setBattlePanel(battlePanel);
        }
    };

    //Create a character based on player choices.
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

                player.addAttack(new Attack("Frontal Slash", "Physical", 15, 100, 100));
                player.addAttack(new Attack("Vital Strike", "Physical", 30, 15, 15));
                player.addAttack(new Attack("Piercing Blow", "Physical", 50, 3, 3));

                break;
            case "Mage":
                player.setSprite("src/Sprites/mageplayer.png");
                player.setStat(player.LOW);
                player.setAttack(player.getStat());

                player.setStat(player.STANDARD);
                player.setDefense(player.getStat());

                player.setStat(player.HIGH);
                player.setMagic(player.getStat());

                player.addAttack(new Attack("Staff Bash", "Physical", 15, 100, 100));
                player.addAttack(new Attack("Fire Bolt", "Magic", 30, 15, 15));
                player.addAttack(new Attack("Lightning Spear", "Magic", 50, 3, 3));
                break;
            case "Paladin":
                player.setSprite("src/Sprites/paladinplayer.png");
                player.setStat(player.LOW);
                player.setAttack(player.getStat());

                player.setStat(player.HIGH);
                player.setDefense(player.getStat());

                player.setStat(player.STANDARD);
                player.setMagic(player.getStat());

                player.addAttack(new Attack("Heavy Swing", "Physical", 15, 100, 100));
                player.addAttack(new Attack("Holy Smite", "Magic", 30, 15, 15));
                player.addAttack(new Attack("Divine Judgement", "Magic", 50, 3, 3));

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
        //A special attack that insta-kills any enemy. For skipping through battles.
        if (devMode)
        {
            player.addAttack(new Attack("UNHOLY DESTRUCTION", "Magic", 9001, 404, 404));
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

    // Update battle panel.
    private void updateBattle()
    {
        BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

        battlePanel.getEnemyPanel().updateEntity(battles.get(battleCounter).getEnemy());
        battlePanel.getPlayerPanel().updateEntity(battles.get(battleCounter).getPlayer());
    }

    //This function handles the majority of the battle turn logic.
    /*
    The function is called when the player chooses an attack or action.
    That attack or action is then applied through the Battle class.
    From there, the enemy gets a chance to act.
    Through some timers, the game should check to see if the enemy successfully offs you or not.
    It's not all perfectly functional, but it's close.
     */
    public void playerAction()
    {
        BattlePanel battlePanel = (BattlePanel) container.getComponent(container.getComponentCount() - 1);

        Timer actionTimer = new Timer(2000, new ActionListener() { // Set a one-second delay between steps
            private int step = 0; // Track the current stage of the turn

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (step) {
                    case 0: // Player attacking
                        // Check if the player wins
                        if (battles.get(battleCounter).winConditionMet()) {
                            player.setCurrentHealth(player.getMaxHealth());
                            battlePanel.getBattleLog().setText("The enemy " + battles.get(battleCounter).getEnemy().getName() +
                                    " has been defeated. \"" + battles.get(battleCounter).getEnemy().getDefeatMsg() + "\"");
                            player.AddCoinPurse(battles.get(battleCounter).getEnemy().getCoinReward());
                            battleCounter++;

                            // Move to the appropriate victory screen
                            if (battleCounter == 5) {
                                battleVictoryOrLoss(container, "YOU EMERGE VICTORIOUS.", true, "incompletevictory");
                            } else if (battleCounter > 5) {
                                battleVictoryOrLoss(container, "YOU EMERGE VICTORIOUS.", true, "truevictory");
                            } else {
                                battleVictoryOrLoss(container, "YOU EMERGE VICTORIOUS.", true, "itemselection");
                            }
                            ((Timer) e.getSource()).stop(); // Stop the timer as the battle ends
                            return;
                        }
                        step++; // Proceed to enemy attack
                        enemyTurn();
                        break;

                    case 1: // Enemy attacking
                        // Check if the player loses
                        if (battles.get(battleCounter).loseConditionMet()) {
                            battlePanel.getBattleLog().setText("The " + battles.get(battleCounter).getEnemy().getName() +
                                    "'s attack has proven fatal. \"" + battles.get(battleCounter).getEnemy().getVictoryMsg() + "\"");
                            battleVictoryOrLoss(container, "YOU HAVE BEEN DEFEATED.", false, "gameover");
                            ((Timer) e.getSource()).stop(); // Stop the timer as the battle ends
                            return;
                        }
                        step++; // Proceed to resetting the log message
                        break;

                    case 2: // Resetting log message for player's next move
                        battlePanel.getBattleLog().setText("Your move, adventurer.");
                        ((Timer) e.getSource()).stop(); // Stop the timer; turn ends
                        break;

                    default: // Stop the timer for any unexpected case
                        ((Timer) e.getSource()).stop();
                        break;
                }
            }
        });

        actionTimer.setRepeats(true); // Ensure the timer continues for multiple stages
        actionTimer.start();
    }

    //This function just lets the enemy choose an action.
    public void enemyTurn()
    {
        battles.get(battleCounter).determineEnemyAction();
    }

    //This function moves to the item selection screen.
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
    public Player getPlayer()
    {
        return player;
    }

    public Battle getBattle(int index)
    {
        return battles.get(index);
    }

    public int getBattleCounter()
    {
        return battleCounter;
    }

    public MusicPlayer getMusicPlayer() {return musicPlayer;}

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
            int actionIndex = i;
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

                battles.get(battleCounter).playerAction(battles.get(battleCounter).getPlayer().getAction(actionIndex));

                playerAction();
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
            boolean outOfUses = false;

            if (attack.getUses() <= 0)
            {
                outOfUses = true;
            }

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
            attackButton.setForeground(Color.RED);
            attackButton.setFocusPainted(false);
            if (!outOfUses)
            {
            attackButton.setForeground(Color.YELLOW);
            attackButton.addActionListener(e -> {
                // Play the sound effect
                soundPlayer.play("src/Game/Music/button.wav");

                // Close the dialog
                dialog.dispose();

                battles.get(battleCounter).attack(battles.get(battleCounter).getPlayer(),battles.get(battleCounter).getEnemy(),battles.get(battleCounter).getPlayer().getAttackMove(attackIndex));

                playerAction();
            });
            }

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

    private void showStats(Component parent)
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

        JLabel messageLabel = new JLabel(player.getName() + "'s Stats", JLabel.CENTER);
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add padding to the panel
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(messageLabel);

        // Create a panel to show all player stats.
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(Color.BLACK);
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        //Show stats and labels.
        JLabel hpLabel = new JLabel("HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth(), JLabel.CENTER);
        hpLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(hpLabel);

        JLabel attackLabel = new JLabel("ATK: " + player.getAttack(), JLabel.CENTER);
        attackLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        attackLabel.setForeground(Color.WHITE);
        attackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(attackLabel);

        JLabel defenseLabel = new JLabel("DEF: " + player.getDefense(), JLabel.CENTER);
        defenseLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        defenseLabel.setForeground(Color.WHITE);
        defenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(defenseLabel);

        JLabel magicLabel = new JLabel("MAG: " + player.getMagic(), JLabel.CENTER);
        magicLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        magicLabel.setForeground(Color.WHITE);
        magicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(magicLabel);

        JLabel coinsLabel = new JLabel("Coins: " + player.getCoinPurse(), JLabel.CENTER);
        coinsLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        coinsLabel.setForeground(Color.WHITE);
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(coinsLabel);

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
        dialog.add(statsPanel, BorderLayout.CENTER);
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

            // Stop the current music
            musicPlayer.stop();

            // Play the new music (looping)
            String musicPath = "src/Game/Music/mainmenu1.wav";
            musicPlayer.play(musicPath);
            musicPlayer.loop();

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

    private void battleVictoryOrLoss(Component parent, String message, boolean victory, String destination)
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

        //Set up border format for buttons
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding


        // Create a button to move to the next panel.
        JButton closeButton = new JButton("Continue");
        closeButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        closeButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.YELLOW);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            // Play the sound effect
            soundPlayer.play("src/Game/Music/button.wav");

            if(victory)
            {
                if (destination.equals("incompletevictory"))
                {
                    // Stop the current music.
                    musicPlayer.stop();

                    // Play new music.
                    String musicPath = "src/Game/Music/mainmenu1.wav";
                    musicPlayer.play(musicPath);
                    musicPlayer.loop();

                    VictoryPanel victoryPanel = (VictoryPanel) container.getComponent(6);
                    victoryPanel.trueVictory(false);

                    CardLayout cardLayout = (CardLayout) container.getLayout();
                    cardLayout.show(container, "victory");
                }
                else if(destination.equals("truevictory"))
                {
                    // Stop the current music.
                    musicPlayer.stop();

                    // Play new music.
                    String musicPath = "src/Game/Music/mainmenu1.wav";
                    musicPlayer.play(musicPath);
                    musicPlayer.loop();

                    VictoryPanel victoryPanel = (VictoryPanel) container.getComponent(6);
                    victoryPanel.trueVictory(true);

                    CardLayout cardLayout = (CardLayout) container.getLayout();
                    cardLayout.show(container, "victory");
                }
                else if (destination.equals("itemselection"))
                {
                    moveToSelectItemScreen();
                }
            }
            else
            {
                // Stop the current music.
                musicPlayer.stop();

                // Play new music.
                String musicPath = "src/Game/Music/mainmenu1.wav";
                musicPlayer.play(musicPath);
                musicPlayer.loop();

                reset();
                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "gameover");
                System.out.println("You are dead");
            }



            // Close the dialog
            updateBattle();
            dialog.dispose();
        });

        // Add components to the dialog
        dialog.add(messagePanel, BorderLayout.NORTH);
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
            player.AddGear(selectedItem);
        } else {
            // Message to reject purchase
            message = "Not enough coins to purchase: " + selectedItem.name + ".";
        }

        // Add buffs/attacks/misc to player
        if (selectedItem.isBuff) {
            // Buff = % of stat to add to current stat, round up to nearest int
            int attack = player.getAttack();
            int defense = player.getDefense();
            int magic = player.getMagic();

            player.setAttack((int) Math.ceil(attack + (attack * (selectedItem.atkBuff / 100.0))));
            player.setDefense((int) Math.ceil(defense + (defense * (selectedItem.defBuff / 100.0))));
            player.setMagic((int) Math.ceil(magic + (magic * (selectedItem.magBuff / 100.0))));
        } else if (selectedItem.isAction) {
            // LOGIC TO ADD ACTIONS TO PANEL
        } else if (selectedItem.isMisc) {
            Item soyMilk = new Item.SoyMilk();
            Item mysteriousLiquid = new Item.MysteriousLiquid();

            if (selectedItem.getName().equals(soyMilk.getName()))
            {
               SecretBoss boss = (SecretBoss) battles.get(5).getEnemy();
               boss.soyMilkPresent(true);
            }
            else if (selectedItem.equals(mysteriousLiquid)) {
                // LOGIC TO FULLY REGENERATE PLAYER HEALTH UPON DEATH
            }
        }

        // List current gear (for testing)
        System.out.println("Current Gear:");
        List<Game.Gear> gear = player.getGear();
        for (Game.Gear g : gear) {
            System.out.println(g.getName());
        }

        return message;
    }

}
