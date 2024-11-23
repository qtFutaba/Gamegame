package GUI.Controllers;

import GUI.Panels.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainMenuControl implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;

    // Constructor for the initial controller.
    public MainMenuControl(JPanel container)
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
            //CharacterCreationPanel newgamePanel = (CharacterCreationPanel) container.getComponent(2);
            CardLayout cardLayout = (CardLayout)container.getLayout();
            cardLayout.show(container, "charactercreation");
        }

        // Opens a high score panel.
        else if (command.equals("Leaderboard"))
        {
            //LeaderboardPanel scoresPanel = (LeaderboardPanel) container.getComponent(1);
            CardLayout cardLayout = (CardLayout)container.getLayout();
            cardLayout.show(container, "leaderboard");
        }

        // Quit game.
        else if (command.equals("Exit Game"))
        {
            System.exit(0);
        }
    }
}