package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CreditsPanel extends JPanel {
    JLabel nameLabel1;
    JLabel nameLabel2;
    JLabel nameLabel3;
    JLabel nameLabel4;

    public CreditsPanel(GUI.Controllers.GameController gc) {
        JLabel label = new JLabel("Credits", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        // Create the Name Labels
        JLabel nameLabel1 = new JLabel("Caiden Clement - Developer");
        JLabel nameLabel2 = new JLabel("Ash Frazer - Developer");
        JLabel nameLabel3 = new JLabel("Axel Pinard - Developer");
        JLabel nameLabel4 = new JLabel("Zach Brown - Developer");

        // Create common border style
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Style all labels
        JLabel[] labels = {nameLabel1, nameLabel2, nameLabel3, nameLabel4};
        for (JLabel nameLabel : labels) {
            nameLabel.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
            nameLabel.setBackground(Color.BLACK);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        }

        // Create a panel for names with vertical BoxLayout
        JPanel creditsBuffer = new JPanel();
        creditsBuffer.setLayout(new BoxLayout(creditsBuffer, BoxLayout.Y_AXIS));

        // Add labels
        for (JLabel nameLabel : labels) {
            creditsBuffer.add(nameLabel);
            creditsBuffer.add(Box.createRigidArea(new Dimension(0, 5))); // 5 pixels spacing
        }

        // Create the return to main menu button.
        JButton mainmenuButton = new JButton("Return to Main Menu.");
        mainmenuButton.addActionListener(gc);
        mainmenuButton.setBackground(Color.BLACK);
        mainmenuButton.setForeground(Color.WHITE);
        mainmenuButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel mainmenuButtonBuffer = new JPanel();
        mainmenuButtonBuffer.add(mainmenuButton);

        // Arrange the components vertically
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));

        // Center align the components
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsBuffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainmenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Top spacing
        grid.add(label);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing after title
        grid.add(creditsBuffer);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing before button
        grid.add(mainmenuButton);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Bottom spacing

        // Set colors
        grid.setBackground(Color.BLACK);
        creditsBuffer.setBackground(Color.BLACK);
        mainmenuButtonBuffer.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        this.add(grid);

    }
}
