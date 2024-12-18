package GUI;

import GUI.Controllers.*;
import GUI.Panels.*;
import Game.Music.MusicPlayer;


import javax.swing.*;
import java.awt.*;

public class MainGameGUI extends JFrame
{
    // Constructor that creates the client GUI.
    public MainGameGUI()
    {
        // Set the title and default close operation.
        this.setTitle("Dungeons of Caza");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);

        // Start fullscreen. (THIS IS REALLY BUGGY AND DOESN'T REALLY WORK RIGHT.)
        //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice gd = ge.getDefaultScreenDevice();
        //this.setUndecorated(true);
        //gd.setFullScreenWindow(this);

        // Create the card layout container.
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);
        container.setBackground(Color.BLACK);

        //Next, create the Controllers
        GameController gc = new GameController(container);

        container.add(new MainMenuPanel(gc), "mainmenu");
        container.add(new LeaderboardPanel(gc), "leaderboard");
        container.add(new CharacterCreationPanel(gc), "charactercreation");
        container.add(new BattlePanel(gc), "battle");
        container.add(new ItemPanel(gc),"selectreward");
        container.add(new GameOverPanel(gc), "gameover");
        container.add(new VictoryPanel(gc), "victory");
        container.add(new CreditsPanel(gc), "credits");
        container.add(new SettingsPanel(gc), "settings");

        // Show the main menu view in the card layout first.
        cardLayout.show(container, "mainmenu");

        // Add the card layout container to the JFrame.
        // GridBagLayout to center the container in the window.
        this.setLayout(new GridBagLayout());
        this.add(container);

        // Color.
        for (Component view:container.getComponents()){
            view.setBackground(Color.BLACK);
        }

        // Show the JFrame.
        this.setSize(1440, 900);
        this.setVisible(true);
    }

    // Main function that creates the client GUI when the program is started.
    public static void main(String[] args)
    {
        new MainGameGUI();
    }
}

