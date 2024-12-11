package GUI.Panels;

import GUI.Controllers.*;
import Game.Entities.Player;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class VictoryPanel extends JPanel
{
    private JLabel label = new JLabel();
    private JButton continueButton = new JButton();
    private JButton mainmenuButton = new JButton();
    private JButton exitButton = new JButton();
    private Player player;
    private JLabel coinCount;

    // Constructor for the main menu.
    public VictoryPanel(GameController gc)
    {
        this.player = gc.getPlayer();
        // Create the title.
        label = new JLabel("YOU HAVE SURVIVED THE DUNGEONS OF CAZA. OR HAVE YOU?", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

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

        // Create the continue button.
        continueButton = new JButton("Continue...");
        continueButton.addActionListener(gc);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        continueButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));


        JPanel continueButtonBuffer = new JPanel();
        continueButtonBuffer.add(continueButton);

        // Create the main menu button.
        mainmenuButton = new JButton("Save Score");
        mainmenuButton.addActionListener(gc);
        mainmenuButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        mainmenuButton.setBackground(Color.BLACK);
        mainmenuButton.setForeground(Color.WHITE);
        mainmenuButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));


        JPanel mainmenuButtonBuffer = new JPanel();
        mainmenuButtonBuffer.add(mainmenuButton);

        // Create the exit button.
        exitButton = new JButton("Exit Game");
        exitButton.addActionListener(gc);
        exitButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel exitButtonBuffer = new JPanel();
        exitButtonBuffer.add(exitButton);

        // Arrange the components in a grid.
        JPanel grid = new JPanel(new GridLayout(5, 1, 5, 5));
        grid.add(label);
        grid.add(scoreBuffer);
        grid.add(continueButtonBuffer);
        grid.add(mainmenuButtonBuffer);
        grid.add(exitButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        scoreBuffer.setBackground(Color.BLACK);
        continueButtonBuffer.setBackground(Color.BLACK);
        mainmenuButtonBuffer.setBackground(Color.BLACK);
        exitButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }

    public void updateScoreDisplay()
    {
        coinCount.setText(player.getName() + "'s score: " + player.getCoinPurse() + " Coins");
    }

    public void trueVictory(boolean achieved)
    {
        if(achieved)
        {
            label.setText("YOU HAVE TRULY CONQUERED THE DUNGEONS OF CAZA.");
            continueButton.setVisible(false);
        }
        else
        {
            label.setText("YOU HAVE SURVIVED THE DUNGEONS OF CAZA. OR HAVE YOU?");
            continueButton.setVisible(true);
        }
    }
}
