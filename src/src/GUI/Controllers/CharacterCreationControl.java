package GUI.Controllers;

import GUI.Panels.BattlePanel;
import GUI.Panels.CharacterCreationPanel;
import GUI.Panels.LeaderboardPanel;
import Game.Entities.Player;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class CharacterCreationControl implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;
    private Player player;
    private String classChoice = null;


    // Constructor for the initial controller.
    public CharacterCreationControl(JPanel container)
    {
        this.container = container;
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae)
    {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // Select Warrior.
        if (command.equals("Warrior"))
        {
            classChoice = "Warrior";
        }

        // Select Mage.
        else if (command.equals("Mage"))
        {
            classChoice = "Mage";
        }

        // Select Paladin.
        else if (command.equals("Paladin"))
        {
            classChoice = "Paladin";
        }

        // Create character and start the game.
        else if (command.equals("Begin Your Journey...")) {
            // Access the CharacterCreationPanel (cast container to retrieve panel)
            CharacterCreationPanel panel = (CharacterCreationPanel) container.getComponent(2);

            // Get the name entered by the user
            String playerName = panel.getNameFieldText();

            // Check if the name is empty
            if (playerName.isEmpty())
            {
                showError(panel, "You must have a name, right?");
            }

            if (classChoice.equals(null))
            {
                showError(panel, "You must choose a class.");
            }
            else
            {
                player = new Player();
                player.setName(playerName);

                switch(classChoice)
                {
                    case "Warrior":
                        player.setStat(player.HIGH);
                        player.setAttack(player.getStat());

                        player.setStat(player.STANDARD);
                        player.setDefense(player.getStat());

                        player.setStat(player.LOW);
                        player.setMagicPoints(player.getStat());
                        break;
                    case "Mage":
                        player.setStat(player.LOW);
                        player.setAttack(player.getStat());

                        player.setStat(player.STANDARD);
                        player.setDefense(player.getStat());

                        player.setStat(player.HIGH);
                        player.setMagicPoints(player.getStat());
                        break;
                    case "Paladin":
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
                // Access the BattlePanel (cast container to retrieve panel)
                BattlePanel firstBattle = (BattlePanel) container.getComponent(3);
                firstBattle.setPlayer(player);

                CardLayout cardLayout = (CardLayout) container.getLayout();
                cardLayout.show(container, "battle");
            }
        }
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
