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
        // Initialize MusicPlayer
        MusicPlayer musicPlayer = new MusicPlayer();

        // Play background music (looping)
        String musicPath = "src/Game/Music/track1.wav"; // Replace with your file path
        musicPlayer.play(musicPath);
        musicPlayer.loop();
        
        // Set the title and default close operation.
        this.setTitle("Dungeons of Caza");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);

        // Create the card layout container.
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);
        container.setBackground(Color.BLACK);


        //Next, create the Controllers
        MainMenuControl mmc = new MainMenuControl(container);
        LeaderboardControl lbc = new LeaderboardControl(container);
        CharacterCreationControl ccc = new CharacterCreationControl(container);
        BattleControl bc = new BattleControl(container);
        SelectRewardControl src = new SelectRewardControl(container);
        GameOverControl goc = new GameOverControl(container);
        VictoryControl vc = new VictoryControl(container);
        LeaderboardEntryControl lbec = new LeaderboardEntryControl(container);

        JPanel[] views = new JPanel[8];

        //Create the views, and register controllers to each panel.
        views[0] = new MainMenuPanel(mmc);
        views[1] = new LeaderboardPanel(lbc);
        views[2] = new CharacterCreationPanel(ccc);
        views[3] = new BattlePanel(bc);
        views[4] = new SelectRewardPanel(src);
        views[5] = new GameOverPanel(goc);
        views[6] = new VictoryPanel(vc);
        views[7] = new LeaderboardEntryPanel(lbec);

        // Add the views to the card layout container.


        container.add(views[0], "mainmenu");           //COMPONENT 0
        container.add(views[1], "leaderboard");        //COMPONENT 1
        container.add(views[2], "charactercreation");  //COMPONENT 2
        container.add(views[3], "battle");             //COMPONENT 3
        container.add(views[4], "selectreward");       //COMPONENT 4
        container.add(views[5], "gameover");           //COMPONENT 5
        container.add(views[6], "victory");            //COMPONENT 6
        container.add(views[7], "leaderboardentry");   //COMPONENT 7

        // Show the main menu view in the card layout first.
        cardLayout.show(container, "mainmenu");

        // Add the card layout container to the JFrame.
        // GridBagLayout to center the container in the window.
        this.setLayout(new GridBagLayout());
        this.add(container);

        // Color.
        for (JPanel view : views)
        {
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
