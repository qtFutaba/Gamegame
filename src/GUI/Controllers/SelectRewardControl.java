package GUI.Controllers;

import GUI.Panels.CharacterCreationPanel;
import GUI.Panels.LeaderboardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRewardControl implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;

    // Constructor for the initial controller.
    public SelectRewardControl(JPanel container)
    {
        this.container = container;
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae)
    {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // The Login button takes the user to the login panel.
        if (command.equals("New Game"))
        {

        }

        // Opens a high score panel.
        else if (command.equals("Leaderboard"))
        {

        }

        // Quit game.
        else if (command.equals("Quit"))
        {

        }
    }
}
