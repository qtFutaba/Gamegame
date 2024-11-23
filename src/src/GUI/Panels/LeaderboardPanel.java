package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LeaderboardPanel extends JPanel
{
    // Constructor for the main menu.
    public LeaderboardPanel(LeaderboardControl lbc)
    {
        // Create the title.
        JLabel label = new JLabel("Leaderboard", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        // Create the leaderboard.
        JLabel placeholder = new JLabel("Nothing to show for now.");

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        placeholder.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        placeholder.setBackground(Color.BLACK);
        placeholder.setForeground(Color.YELLOW);
        placeholder.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel leaderboardBuffer = new JPanel();
        leaderboardBuffer.add(placeholder);


        // Create the return to main menu button.
        JButton mainmenuButton = new JButton("Return to Main Menu.");
        mainmenuButton.addActionListener(lbc);
        mainmenuButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        mainmenuButton.setBackground(Color.BLACK);
        mainmenuButton.setForeground(Color.WHITE);
        mainmenuButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel mainmenuButtonBuffer = new JPanel();
        mainmenuButtonBuffer.add(mainmenuButton);

        // Arrange the components in a grid.
        JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
        grid.add(label);
        grid.add(leaderboardBuffer);
        grid.add(mainmenuButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        leaderboardBuffer.setBackground(Color.BLACK);
        mainmenuButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }
}
