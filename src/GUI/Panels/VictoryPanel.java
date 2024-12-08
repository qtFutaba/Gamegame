package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class VictoryPanel extends JPanel
{
    JLabel label = new JLabel();
    JButton continueButton = new JButton();
    JButton mainmenuButton = new JButton();
    JButton exitButton = new JButton();

    // Constructor for the main menu.
    public VictoryPanel(GameController gc)
    {
    // Create the title.
        label = new JLabel("YOU HAVE SURVIVED THE DUNGEONS OF CAZA. OR HAVE YOU?", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

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
        mainmenuButton = new JButton("Main Menu");
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
        JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
        grid.add(label);
        grid.add(continueButtonBuffer);
        grid.add(mainmenuButtonBuffer);
        grid.add(exitButtonBuffer);

        // Color
        grid.setBackground(Color.BLACK);
        continueButtonBuffer.setBackground(Color.BLACK);
        mainmenuButtonBuffer.setBackground(Color.BLACK);
        exitButtonBuffer.setBackground(Color.BLACK);

        this.add(grid);
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
