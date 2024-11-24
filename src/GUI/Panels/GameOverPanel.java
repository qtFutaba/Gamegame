package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GameOverPanel extends JPanel
{
    // Constructor for the main menu.
    public GameOverPanel(GameController gc)
    {
        // Create the title.
        JLabel label = new JLabel("GAME OVER, ADVENTURER.", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.RED);

        // Create the new game button.
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(gc);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        newGameButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel newGameButtonBuffer = new JPanel();
        newGameButtonBuffer.add(newGameButton);

        // Create the leaderboard button.
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(gc);
        leaderboardButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        leaderboardButton.setBackground(Color.BLACK);
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));


        JPanel leaderboardButtonBuffer = new JPanel();
        leaderboardButtonBuffer.add(leaderboardButton);

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
        grid.add(newGameButtonBuffer);
        grid.add(leaderboardButtonBuffer);
        grid.add(exitButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        newGameButtonBuffer.setBackground(Color.BLACK);
        leaderboardButtonBuffer.setBackground(Color.BLACK);
        exitButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }
}
