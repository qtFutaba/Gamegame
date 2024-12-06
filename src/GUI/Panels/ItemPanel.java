package GUI.Panels;

import GUI.Controllers.*;
import src.Game.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class ItemPanel extends JPanel
{
    private JLabel messageLabel;

    public ItemPanel(GameController gc)
    {
        this.setLayout(new BorderLayout());

        /// ///////////////////////////////////////////
        ///                ITEM PANEL
        /// ///////////////////////////////////////////

        // Create item selection message box (top)
        JTextField itemLog = new JTextField();
        itemLog.setEditable(false);
        itemLog.setText("You may choose an item.");
        itemLog.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        itemLog.setBackground(Color.BLACK);
        itemLog.setForeground(Color.WHITE);

        itemLog.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        itemLog.setHorizontalAlignment(JTextField.CENTER);

        Dimension logSize = new Dimension(1000, 100);
        itemLog.setPreferredSize(logSize);
        itemLog.setMinimumSize(logSize);
        itemLog.setMaximumSize(logSize);

        // Add itemLog to panel
        this.add(itemLog, BorderLayout.NORTH);

        // Create itemPanel
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        itemPanel.setBackground(Color.BLACK);

        // Generate items
        List<Item> items = generateItems();

        // Create icons for items
        for (Item item : items) {
            JPanel itemIcon = createItemIcon(item, gc);
            itemPanel.add(itemIcon);
        }

        // Add items to itemPanel
        this.add(itemPanel, BorderLayout.CENTER);

        // Panel for message (bottom)
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(Color.BLACK);

        messageLabel = new JLabel("Select an item.");
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);

        // Add messageLabel to messagePanel (which also contains exitButton)
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Panel for the exit button
        JPanel exitPanel = new JPanel(new BorderLayout());
        JButton exitButton = new JButton("Return to Battle");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        exitButton.setOpaque(true);
        exitButton.addActionListener(gc);

        // Add exitButton to exitPanel
        exitPanel.add(exitButton);

        // Add exitPanel to messagePanel (so both can be at bottom of screen)
        messagePanel.add(exitPanel, BorderLayout.EAST);
        this.add(messagePanel, BorderLayout.SOUTH);
    }

    /// ///////////////////////////////////////////
    ///             ITEM GENERATION
    /// ///////////////////////////////////////////

    public List<Item> generateItems() {
        /*  NOTE: THIS IS A WORK-IN-PROGRESS!!
         *  TO-DO:
         *      Create randomization process for item generation, based on:
         *          - Item rarity
         *          - Whether player currently owns item or not
         *          - Boss fight (before final boss = guaranteed soy milk)
         */

        // Create list of all possible items
        List<Item> items = Item.getItems();

        // Shuffle item list
        Collections.shuffle(items, new Random());

        // Create list containing random items for player to choose from
        List<Item> options = new ArrayList<Item>();

        // Add first three items to option list
        options.add(items.get(0));
        options.add(items.get(1));
        options.add(items.get(2));

        // Return options
        return options;
    }

    /// ///////////////////////////////////////////
    ///                ITEM ICONS
    /// ///////////////////////////////////////////

    private JPanel createItemIcon(Item item, GameController gc) {
        /*
            TO-DO:
                - Include item sprites?
                - Make item descriptions look cleaner and more readable.
         */
        // Create itemPanel
        JPanel itemPanel = new JPanel();
        itemPanel.setPreferredSize(new Dimension(425, 350));
        itemPanel.setBackground(Color.BLACK);
        itemPanel.setLayout(new BorderLayout());

        // Create buttons for each item
        JButton selectButton = new JButton(item.itemName);
        selectButton.setBackground(Color.BLACK);
        selectButton.setForeground(Color.YELLOW);
        selectButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));

        // Process item selection (prompt user for confirmation)
        selectButton.addActionListener(e -> confirmSelection(item, gc));

        // Add selectButton to itemPanel
        itemPanel.add(selectButton, BorderLayout.NORTH);

        // Item Description formatting
        JTextArea description = new JTextArea(item.toString());
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));
        description.setBackground(Color.BLACK);
        description.setForeground(Color.WHITE);
        itemPanel.add(description, BorderLayout.CENTER);

        return itemPanel;
    }

    /// ///////////////////////////////////////////
    ///                ITEM CONFIRMATION
    /// ///////////////////////////////////////////

    private void confirmSelection(Item item, GameController gc) {
        // Prompt user to confirm their selection
        JDialog confirmationDialog = new JDialog(SwingUtilities.getWindowAncestor(this));
        confirmationDialog.setUndecorated(true); // this keeps the tab black and follows the theme
        confirmationDialog.setSize(400, 200);
        confirmationDialog.setLayout(new BorderLayout());
        confirmationDialog.getContentPane().setBackground(Color.BLACK);

        // Confirmation messageLabel
        JLabel messageLabel = new JLabel("Are you sure you want to purchase this item?");
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmationDialog.add(messageLabel, BorderLayout.CENTER);

        // Create buttonPanel for yes/no buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK);

        // Yes button
        JButton yesButton = new JButton("Yes");
        yesButton.setBackground(Color.BLACK);
        yesButton.setForeground(Color.GREEN);
        yesButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        // If the player cannot afford the item, reject the purchase & inform
        yesButton.addActionListener(e -> {
            String message = gc.rewardSelection(item);
            this.messageLabel.setText(message);
            if (message.contains("Not enough coins")) {
                this.messageLabel.setForeground(Color.RED);
            }
            confirmationDialog.dispose();
        });

        // No button
        JButton noButton = new JButton("No");
        noButton.setBackground(Color.BLACK);
        noButton.setForeground(Color.RED);
        noButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        noButton.addActionListener(e -> {
            this.messageLabel.setForeground(Color.YELLOW);
            confirmationDialog.dispose();
        });

        // Add yes/no buttons to buttonPanel
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        // Add buttonPanel to confirmation window
        confirmationDialog.add(buttonPanel, BorderLayout.SOUTH);
        confirmationDialog.setLocationRelativeTo(this);
        confirmationDialog.setVisible(true);
    }

}
