package GUI.Panels;

import GUI.Controllers.GameController;
import Game.Entities.Player;
import src.Game.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemPanel extends JPanel {
    private JLabel messageLabel;
    private JLabel coinCount;
    public boolean itemPurchased = false;
    private JPanel itemPanel;
    private JTextField itemLog;
    private boolean needSoyMilk;
    private Player player;


    public ItemPanel(GameController gc) {
        this.setLayout(new BorderLayout());
        this.player = gc.getPlayer();

        this.itemLog = createItemLog();
        this.add(itemLog, BorderLayout.NORTH);

        this.itemPanel = createItemPanel(gc);
        this.add(itemPanel, BorderLayout.CENTER);

        JPanel messagePanel = createMessagePanel(gc);
        this.add(messagePanel, BorderLayout.SOUTH);
    }

    /// ///////////////////////////////////////////
    ///             HELPER METHODS
    /// ///////////////////////////////////////////

    private JTextField createItemLog() {
        // Create item selection message box (top)
        JTextField log = new JTextField("You may choose an item.");
        log.setEditable(false);
        log.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        log.setBackground(Color.BLACK);
        log.setForeground(Color.WHITE);
        log.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        log.setHorizontalAlignment(JTextField.CENTER);

        Dimension logSize = new Dimension(1000, 100);
        log.setPreferredSize(logSize);
        log.setMinimumSize(logSize);
        log.setMaximumSize(logSize);

        return log;
    }

    private JPanel createItemPanel(GameController gc) {
        // Create itemPanel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(Color.BLACK);

        // Generate items and display in panel
        List<Item> items = generateItems(gc);
        for (Item item : items) {
            JPanel itemIcon = createItemIcon(item, gc);
            panel.add(itemIcon);
        }

        return panel;
    }

    private JPanel createMessagePanel(GameController gc) {
        // Panel for message (bottom)
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(Color.BLACK);

        messageLabel = new JLabel("Select an item.");
        messageLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Create a coin count display.
        JPanel coinPanel = new JPanel(new BorderLayout());
        coinCount = new JLabel(player.getCoinPurse() + " Coins");

        String coinFilename = "src/Sprites/coin.png";
        Icon coinIcon = new ImageIcon(coinFilename);
        coinCount.setIcon(coinIcon);

        coinCount.setBackground(Color.BLACK);
        coinCount.setForeground(Color.YELLOW);
        coinCount.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        coinCount.setOpaque(true);

        coinPanel.add(coinCount);

        messagePanel.add(coinPanel, BorderLayout.WEST);

        // Create "Next Battle" (exit) button
        JPanel exitPanel = new JPanel(new BorderLayout());
        JButton exitButton = new JButton("Next Battle");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        exitButton.setOpaque(true);
        exitButton.addActionListener(gc);
        exitPanel.add(exitButton);

        messagePanel.add(exitPanel, BorderLayout.EAST);
        return messagePanel;
    }

    /// ///////////////////////////////////////////
    ///             RESET PANEL
    /// ///////////////////////////////////////////

    public void resetPanel(GameController gc) {
        
        // Reset ItemPanel for new items
        this.itemPanel.removeAll();
        List<Item> items = generateItems(gc);

        // Add items to ItemPanel
        for (Item item : items) {
            JPanel itemIcon = createItemIcon(item, gc);
            this.itemPanel.add(itemIcon);
        }

        // Reset item purchase state
        itemPurchased = false;

        // Reset message
        this.itemLog.setText("You may choose an item.");
        this.messageLabel.setText("Select an item.");
        this.messageLabel.setForeground(Color.YELLOW);
        updateCoinCount();

        // Refresh panel
        this.revalidate();
        this.repaint();
    }

    /// ///////////////////////////////////////////
    ///             ITEM GENERATION
    /// ///////////////////////////////////////////

    public List<Item> generateItems(GameController gc) {
        // Create list of possible items
        List<Item> items = Item.getItems();

        // Create lists containing items of each rarity
        List<Item> commonItems = new ArrayList<>();
        List<Item> rareItems = new ArrayList<>();
        List<Item> legendaryItems = new ArrayList<>();

        // Add items of each rarity to corresponding list
        for (Item item : items) {
            switch (item.rarity) {
                case 1 -> commonItems.add(item);
                case 2 -> rareItems.add(item);
                case 3 -> legendaryItems.add(item);
            }
        }

        // Create list to contain options for ItemPanel
        List<Item> options = new ArrayList<>();

        // Create list of player's current gear to avoid duplicates
        List<Item> playerItems = (List<Item>) (List<?>) gc.getPlayer().getGear();

        // Randomly select items based on rarity
        Random rand = new Random();

        while (options.size() < 3) {
            // Automatically place SoyMilk in shop before final battle
            if (needSoyMilk) {
                Item soyMilk = new Item.SoyMilk();
                options.add(soyMilk);
                continue;
            }

            /*  -------------------------------------------
             *  ITEM RARITY REFERENCE:
             *  - COMMON:       21/50   =   42.00% chance
             *  - RARE:         1/3     =   33.00% chance
             *  - LEGENDARY:    1/4     =   25.00% chance
             *  -------------------------------------------
             */

            // Generate random number between 1-100
            int roll = rand.nextInt(100) + 1;
            Item item;
            if (roll <= 42) {           // COMMON
                item = commonItems.get(rand.nextInt(commonItems.size()));
            } else if (roll <= 75) {    // RARE
                item = rareItems.get(rand.nextInt(rareItems.size()));
            } else {                    // LEGENDARY
                item = legendaryItems.get(rand.nextInt(legendaryItems.size()));
            }

            // Do not include duplicates in shop
            if (!playerItems.contains(item) && !options.contains(item)) {
                options.add(item);
            }
        }
        return options;
    }

    /// ///////////////////////////////////////////
    ///                ITEM ICONS
    /// ///////////////////////////////////////////

    private JPanel createItemIcon(Item item, GameController gc) {
        // Create ItemPanel
        JPanel itemPanel = new JPanel();
        itemPanel.setPreferredSize(new Dimension(425, 350));
        itemPanel.setBackground(Color.BLACK);
        itemPanel.setLayout(new BorderLayout());

        // Create buttons for each item
        JButton selectButton = new JButton(item.name);
        selectButton.setBackground(Color.BLACK);
        selectButton.setForeground(Color.YELLOW);
        selectButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 18));

        // Process item selection (prompt user for confirmation)
        selectButton.addActionListener(e -> confirmSelection(item, gc));
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
    ///             ITEM CONFIRMATION
    /// ///////////////////////////////////////////

    private void confirmSelection(Item item, GameController gc) {
        // Only allow one purchase per turn
        if (itemPurchased) {
            updateMessage("You can only purchase one item per turn.", Color.RED);
            return;
        }

        // Prompt user to confirm their selection
        JDialog confirmationDialog = new JDialog(SwingUtilities.getWindowAncestor(this));
        confirmationDialog.setUndecorated(true);
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
                updateMessage(message, Color.RED);
            } else {
                updateMessage("Thank you for your purchase... heheh! Now scram!", Color.YELLOW);
                itemPurchased = true;
                updateCoinCount();
            }
            confirmationDialog.dispose();
        });

        // No button
        JButton noButton = new JButton("No");
        noButton.setBackground(Color.BLACK);
        noButton.setForeground(Color.RED);
        noButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        noButton.addActionListener(e -> confirmationDialog.dispose());

        // Add yes/no buttons to buttonPanel
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        confirmationDialog.add(buttonPanel, BorderLayout.SOUTH);
        confirmationDialog.setLocationRelativeTo(this);
        confirmationDialog.setVisible(true);
    }

    public void updateCoinCount()
    {
        coinCount.setText(player.getCoinPurse() + " Coins");
    }

    public void updateMessage(String message, Color color) {
        this.messageLabel.setText(message);
        this.messageLabel.setForeground(color);
    }

    public void needSoyMilk() {
        this.needSoyMilk = true;
    }
}
