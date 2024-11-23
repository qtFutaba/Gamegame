package GUI.Panels;

import Game.Entities.Enemy;
import Game.Entities.Player;

import java.awt.*;
import javax.swing.*;

public class ActionPanel extends JPanel
{
    private Player player;
    private Enemy enemy;

    private Image playerSprite;
    private Image enemySprite;
    
    public ActionPanel(Player player, Enemy enemy)
    {
        this.setBackground(Color.BLACK); // Set the background color
        this.setPreferredSize(new Dimension(1000, 500)); // Set preferred size
        this.player = player;
        this.enemy = enemy;

        // Load sprites
        playerSprite = new ImageIcon(player.getSprite()).getImage();
        enemySprite = new ImageIcon(enemy.getSprite()).getImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;





        // DRAW PLAYER
        if (playerSprite != null)
        {
            g2d.drawImage(playerSprite, 250, 250, 150, 150, this);
        }

        // DRAW ENEMY
        if (enemySprite != null)
        {
            g2d.drawImage(enemySprite, 600, 250, 150, 150, this);
        }

        // DISPLAY NAMES
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(player.getName(), 275, 200);
        g2d.drawString(enemy.getName(), 625, 200);

        // DISPLAY ENEMY HEALTH BAR (IN TESTING)
        int width = (int) (250);
        g2d.drawRect(624,219, width+1, 31);

        g2d.setColor(Color.RED);
        g2d.fillRect(625,220, width, 30);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        g2d.drawString("HP: 100/100", 700, 240);
    }
}
