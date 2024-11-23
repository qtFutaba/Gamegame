package GUI.Controllers;

import GUI.Panels.CharacterCreationPanel;
import GUI.Panels.LeaderboardPanel;
import GUI.Panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderboardControl implements ActionListener
{
    // Private data field for storing the container.
    private JPanel container;

    // Constructor for the initial controller.
    public LeaderboardControl(JPanel container)
    {
        this.container = container;
    }

    // Handle button clicks.
    public void actionPerformed(ActionEvent ae)
    {
        // Get the name of the button clicked.
        String command = ae.getActionCommand();

        // The Login button takes the user to the login panel.
        if (command.equals("Return to Main Menu."))
        {
            //MainMenuPanel mainMenuPanel = (MainMenuPanel) container.getComponent(0);
            CardLayout cardLayout = (CardLayout)container.getLayout();
            cardLayout.show(container, "mainmenu");
        }
    }
}