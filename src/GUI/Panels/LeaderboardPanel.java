package GUI.Panels;

import GUI.Controllers.*;
import Game.Score;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.List;

public class LeaderboardPanel extends JPanel {
    public LeaderboardPanel(GameController gc) {
        // Create the title
        JLabel label = new JLabel("Leaderboard", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        // Create the leaderboard panel
        JPanel leaderboardBuffer = new JPanel();
        leaderboardBuffer.setLayout(new BoxLayout(leaderboardBuffer, BoxLayout.Y_AXIS));
        leaderboardBuffer.setBackground(Color.BLACK);

        // Load and sort scores
        List<Score> scores = Score.loadScores();

        if (scores.isEmpty()) {
            // Show placeholder if no scores exist
            JLabel placeholder = new JLabel("Nothing to show for now.", JLabel.CENTER);
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
            Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            placeholder.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            placeholder.setBackground(Color.BLACK);
            placeholder.setForeground(Color.YELLOW);
            placeholder.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
            leaderboardBuffer.add(placeholder);
        } else {
            // Display scores in sorted order
            for (int i = 0; i < scores.size(); i++) {
                Score score = scores.get(i);
                String scoreText = String.format("#%d  %-15s %d gold", (i + 1), score.getPlayerName(), score.getMoney());

                JLabel scoreLabel = new JLabel(scoreText, JLabel.CENTER);
                Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
                Border paddingBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
                scoreLabel.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
                scoreLabel.setBackground(Color.BLACK);
                scoreLabel.setForeground(Color.YELLOW);
                scoreLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
                scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                leaderboardBuffer.add(scoreLabel);
                leaderboardBuffer.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        // Add leaderboardBuffer to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(leaderboardBuffer);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for the scrollable area
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create the return to main menu button
        JButton mainmenuButton = new JButton("Return to Main Menu.");
        mainmenuButton.addActionListener(gc);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mainmenuButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        mainmenuButton.setBackground(Color.BLACK);
        mainmenuButton.setForeground(Color.WHITE);
        mainmenuButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel mainmenuButtonBuffer = new JPanel();
        mainmenuButtonBuffer.add(mainmenuButton);
        mainmenuButtonBuffer.setBackground(Color.BLACK);

        // Arrange the components in a grid
        JPanel grid = new JPanel(new BorderLayout());
        grid.add(label, BorderLayout.NORTH);
        grid.add(scrollPane, BorderLayout.CENTER);
        grid.add(mainmenuButtonBuffer, BorderLayout.SOUTH);

        // Set colors
        grid.setBackground(Color.BLACK);

        this.setLayout(new BorderLayout());
        this.add(grid, BorderLayout.CENTER);
        this.setBackground(Color.BLACK);
    }
}
