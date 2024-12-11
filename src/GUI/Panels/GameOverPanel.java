package GUI.Panels;

import GUI.Controllers.*;
import Game.Entities.Player;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GameOverPanel extends JPanel
{
    private Player player;
    private JLabel coinCount;

    // Constructor for the main menu.
    public GameOverPanel(GameController gc)
    {
        this.player = gc.getPlayer();

        // Create the title.
        JLabel label = new JLabel("GAME OVER, ADVENTURER.", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.RED);

        // Show player score.
        coinCount = new JLabel(player.getName() + "'s score: " + player.getCoinPurse() + " Coins");

        String coinFilename = "src/Sprites/coin.png";
        Icon coinIcon = new ImageIcon(coinFilename);
        coinCount.setIcon(coinIcon);

        coinCount.setVerticalTextPosition(SwingConstants.TOP);
        coinCount.setHorizontalTextPosition(SwingConstants.CENTER);

        coinCount.setBackground(Color.BLACK);
        coinCount.setForeground(Color.YELLOW);
        coinCount.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        coinCount.setOpaque(true);

        coinCount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel scoreBuffer = new JPanel();
        scoreBuffer.setLayout(new BoxLayout(scoreBuffer, BoxLayout.Y_AXIS));

        coinCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreBuffer.add(coinCount);


        // Create the new game button.
        JButton newGameButton = new JButton("Save Score");
        newGameButton.addActionListener(gc);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        newGameButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel newGameButtonBuffer = new JPanel();
        newGameButtonBuffer.add(newGameButton);

        // Create the exit button.
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(gc);
        exitButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel exitButtonBuffer = new JPanel();
        exitButtonBuffer.add(exitButton);

        // Arrange the components in a grid.
        JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
        grid.add(label);
        grid.add(scoreBuffer);
        grid.add(newGameButtonBuffer);
        grid.add(exitButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        scoreBuffer.setBackground(Color.BLACK);
        newGameButtonBuffer.setBackground(Color.BLACK);
        exitButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }

    public void updateScoreDisplay()
    {
        coinCount.setText(player.getName() + "'s score: " + player.getCoinPurse() + " Coins");
    }
}

