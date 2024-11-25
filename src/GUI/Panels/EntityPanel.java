package GUI.Panels;

import Game.Entities.*;

import java.awt.*;
import javax.swing.*;
import GUI.Controllers.*;

public class EntityPanel extends JPanel
{

    private Entity entity;
    private Image sprite;
    private GameController gc;
    private Graphics2D g2d;

    public EntityPanel(Entity entity, GameController gc)
    {
        this.setBackground(Color.BLACK); // Set the background color
        this.setPreferredSize(new Dimension(350, 350)); // Set preferred size
        this.entity = entity;
        this.gc = gc;
        // Load sprites
        sprite = new ImageIcon(entity.getSprite()).getImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2d = (Graphics2D) g;

        // FONTMETRICS TO CALCULATE NAME TEXT WIDTH
        FontMetrics fm = g2d.getFontMetrics(new Font("Viner Hand ITC", Font.BOLD, 20));

        //---------------------------------------------------------------------------
        // DISPLAY PLAYER
        //---------------------------------------------------------------------------
        // DISPLAY NAME
        String name = entity.getName();
        int nameWidth = fm.stringWidth(name);


        // NAME POSITION
        int center = 175;
        int nameX = center - nameWidth / 2;
        int nameY = 20;

        // FONT
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(name, nameX, nameY);

        int healthBarWidth = 250;
        int healthBarHeight = 30;
        int healthBarX = nameX + nameWidth / 2 - healthBarWidth / 2; //CENTER
        int healthBarY = nameY + 20;

        // BORDER
        g2d.drawRect(healthBarX - 1, healthBarY - 1, healthBarWidth + 2, healthBarHeight + 2);

        // FILL
        g2d.setColor(Color.RED);
        g2d.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

        // TEXT
        int currentHP = gc.getEnemy(0).getCurrentHealth();
        int MaxHP = gc.getEnemy(0).getMaxHealth();
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        g2d.drawString("HP: " + currentHP + "/" + MaxHP, healthBarX + healthBarWidth / 2 - g2d.getFontMetrics().stringWidth("HP: 100/100") / 2, healthBarY + 20);

        // SPRITE
        if (sprite != null)
        {
            int spriteWidth = 150;
            int spriteHeight = 150;

            // CENTER SPRITE UNDER HEALTH BAR
            int playerSpriteX = healthBarX + (healthBarWidth / 2) - (spriteWidth / 2); // CENTER
            int playerSpriteY = healthBarY + 40; // GAP FOR SPACING

            g2d.drawImage(sprite, playerSpriteX, playerSpriteY, spriteWidth, spriteHeight, this);
        }
    }
}