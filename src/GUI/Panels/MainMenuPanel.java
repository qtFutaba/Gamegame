package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainMenuPanel extends JPanel
{
    // Constructor for the main menu.
    public MainMenuPanel(GameController gc)
    {
        // Create the title.
        //JLabel label = new JLabel("Dungeons of Caza", JLabel.CENTER);
        //label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        //label.setForeground(Color.WHITE);

        String titleLogoFilename = "src/Sprites/dungeonsofcazatitle.png";
        Icon titleLogo = new ImageIcon(titleLogoFilename);
        JLabel label = new JLabel();
        label.setIcon(titleLogo);


        // Create the new game button.
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(gc);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        newGameButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        newGameButton.setFocusPainted(false);

        JPanel newGameButtonBuffer = new JPanel();
        newGameButtonBuffer.add(newGameButton);

        // Create the leaderboard button.
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(gc);
        leaderboardButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        leaderboardButton.setBackground(Color.BLACK);
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        leaderboardButton.setFocusPainted(false);

        JPanel leaderboardButtonBuffer = new JPanel();
        leaderboardButtonBuffer.add(leaderboardButton);

        // Create the Settings button
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(gc);
        settingsButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        settingsButton.setBackground(Color.BLACK);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        settingsButton.setFocusPainted(false);

        JPanel settingsButtonBuffer = new JPanel();
        settingsButtonBuffer.add(settingsButton);


        // Create the Credits button
        JButton creditsButton = new JButton("Credits");
        creditsButton.addActionListener(gc);
        creditsButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        creditsButton.setBackground(Color.BLACK);
        creditsButton.setForeground(Color.WHITE);
        creditsButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        creditsButton.setFocusPainted(false);

        JPanel creditsButtonBuffer = new JPanel();
        creditsButtonBuffer.add(creditsButton);

        // Create the exit button.
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(gc);
        exitButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        exitButton.setFocusPainted(false);

        JPanel exitButtonBuffer = new JPanel();
        exitButtonBuffer.add(exitButton);

        // Arrange the components in a grid.
        JPanel grid = new JPanel(new GridLayout(6, 1, 4, 0));
        grid.add(label);
        grid.add(newGameButtonBuffer);
        grid.add(leaderboardButtonBuffer);
        grid.add(settingsButtonBuffer);
        grid.add(creditsButtonBuffer);
        grid.add(exitButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        newGameButtonBuffer.setBackground(Color.BLACK);
        leaderboardButtonBuffer.setBackground(Color.BLACK);
        exitButtonBuffer.setBackground(Color.BLACK);
        settingsButtonBuffer.setBackground(Color.BLACK);
        creditsButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }
}
