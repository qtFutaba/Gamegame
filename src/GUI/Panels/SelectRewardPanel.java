package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SelectRewardPanel extends JPanel
{
    // Constructor for the main menu.
    public SelectRewardPanel(GameController gc)
    {
// Create the title.
        JLabel label = new JLabel("You may choose an item.", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        // Create the new game button.
        JButton newGameButton = new JButton("Next Battle");
        newGameButton.addActionListener(gc);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding
        newGameButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel newGameButtonBuffer = new JPanel();
        newGameButtonBuffer.add(newGameButton);


        // Arrange the components in a grid.
        JPanel grid = new JPanel(new GridLayout(2, 1, 5, 5));
        grid.add(label);
        grid.add(newGameButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        newGameButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
    }
}
