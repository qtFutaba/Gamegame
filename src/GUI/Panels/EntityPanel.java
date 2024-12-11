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
    protected void paintComponent(Graphics g) {
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
        int center = 175; // Example center position
        int nameX = center - nameWidth / 2;
        int nameY = 20;

        // FONT
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(name, nameX, nameY);

        //---------------------------------------------------------------------------
        // HEALTH BAR
        //---------------------------------------------------------------------------
        int healthBarWidth = 250; // Total width of the health bar
        int healthBarHeight = 30; // Height of the health bar
        int healthBarX = center - healthBarWidth / 2; // Center the health bar
        int healthBarY = nameY + 20;

        // BORDER
        g2d.setColor(Color.WHITE);
        g2d.drawRect(healthBarX - 1, healthBarY - 1, healthBarWidth + 2, healthBarHeight + 2);

        // CALCULATE HEALTH BAR FILL
        int currentHP = entity.getCurrentHealth(); // Get current health
        if (currentHP < 0)
        {
            currentHP = 0;
        }
        int maxHP = entity.getMaxHealth();  // Get max health
        int filledWidth = (int) ((double) currentHP / maxHP * healthBarWidth); // Scale width

        // FILL BASED ON HP
        g2d.setColor(Color.RED); // Health bar color
        g2d.fillRect(healthBarX, healthBarY, filledWidth, healthBarHeight);

        // TEXT
        String healthText = "HP: " + currentHP + "/" + maxHP;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        g2d.drawString(healthText, healthBarX + healthBarWidth / 2 - g2d.getFontMetrics().stringWidth(healthText) / 2, healthBarY + 20);

        //---------------------------------------------------------------------------
        // SPRITE
        //---------------------------------------------------------------------------
        if (sprite != null) {
            int spriteWidth = 150;
            int spriteHeight = 150;

            // CENTER SPRITE UNDER HEALTH BAR
            int playerSpriteX = healthBarX + (healthBarWidth / 2) - (spriteWidth / 2); // Center
            int playerSpriteY = healthBarY + 40; // Gap for spacing

            g2d.drawImage(sprite, playerSpriteX, playerSpriteY, spriteWidth, spriteHeight, this);
        }
    }

    // Updates entity upon battle victory
    public void updateEntity(Entity entity) {
        this.entity = entity;
        if (entity.getSprite() != null) {
            this.sprite = new ImageIcon(entity.getSprite()).getImage();
        }

        this.revalidate();
        this.repaint();
    }

    public void dropHealthBar(int newHealth)
    {
        int currentHP = entity.getCurrentHealth();
        int targetHP = newHealth;

        int animationDuration = 250;                    // Total animation duration in milliseconds
        int interval = 1;                              // Timer interval in milliseconds
        int totalSteps = animationDuration / interval;  // Total number of animation steps
        int healthDifference = targetHP - currentHP;
        double step = (double) healthDifference / totalSteps; // Calculate dynamic step size

        // Create a mutable variable for the animation progress
        final int[] animatedHealth = {currentHP};

        Timer timer = new Timer(interval, null);

        timer.addActionListener(e -> {
            // Incrementally update health
            animatedHealth[0] += step;

            // Stop when the target is reached
            if ((step < 0 && animatedHealth[0] <= targetHP) || (step > 0 && animatedHealth[0] >= targetHP)) {
                animatedHealth[0] = targetHP; // Snap to exact target value
                entity.setCurrentHealth(targetHP);
                this.repaint();
                ((Timer) e.getSource()).stop();
                return;
            }

            // Update entity's health and repaint
            entity.setCurrentHealth(animatedHealth[0]);
            this.repaint();
        });

        timer.start();
    }
}