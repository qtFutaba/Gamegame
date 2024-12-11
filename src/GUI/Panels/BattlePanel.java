package GUI.Panels;

import GUI.Controllers.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BattlePanel extends JPanel
{
    private JTextField battleLog;
    private EntityPanel playerPanel;
    private EntityPanel enemyPanel;

    // Constructor for the main menu.
    public BattlePanel(GameController gc)
    {
        this.setLayout(new BorderLayout());
        //----------------------------------------------------------------------------------------------------------------
        // Create the battle log.
        //----------------------------------------------------------------------------------------------------------------
        battleLog = new JTextField();
        battleLog.setEditable(false);
        battleLog.setText("An enemy approaches.");
        battleLog.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        battleLog.setBackground(Color.BLACK);
        battleLog.setForeground(Color.WHITE);

        battleLog.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        battleLog.setHorizontalAlignment(JTextField.CENTER);

        Dimension logSize = new Dimension(1000, 100);
        battleLog.setPreferredSize(logSize);
        battleLog.setMinimumSize(logSize);
        battleLog.setMaximumSize(logSize);


        //----------------------------------------------------------------------------------------------------------------
        // Create the actual battle scene.
        //----------------------------------------------------------------------------------------------------------------
        Dimension sceneSize = new Dimension(350, 350);
        playerPanel = new EntityPanel(gc.getPlayer(), gc);
        playerPanel.setPreferredSize(sceneSize);
        playerPanel.setMinimumSize(sceneSize);
        playerPanel.setMaximumSize(sceneSize);

        enemyPanel = new EntityPanel(gc.getBattle(gc.getBattleCounter()).getEnemy(),gc);
        enemyPanel.setPreferredSize(sceneSize);
        enemyPanel.setMinimumSize(sceneSize);
        enemyPanel.setMaximumSize(sceneSize);

        JPanel battleScene = new JPanel();

        playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        enemyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        battleScene.add(playerPanel);
        battleScene.add(enemyPanel);

        //----------------------------------------------------------------------------------------------------------------
        // Create the options.
        //----------------------------------------------------------------------------------------------------------------
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        //Fight.
        JButton fightButton = new JButton("Fight");
        fightButton.addActionListener(gc);

        fightButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        fightButton.setBackground(Color.BLACK);
        fightButton.setForeground(Color.YELLOW);
        fightButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        fightButton.setFocusPainted(false);

        String fightIconFilename = "src/Sprites/attack.png";
        Icon fightIcon = new ImageIcon(fightIconFilename);
        fightButton.setIcon(fightIcon);

        fightButton.setVerticalTextPosition(SwingConstants.TOP);
        fightButton.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel fightButtonBuffer = new JPanel();
        fightButtonBuffer.setLayout(new BoxLayout(fightButtonBuffer, BoxLayout.Y_AXIS));
        fightButtonBuffer.add(fightButton);

        fightButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Utilities.
        JButton utilityButton = new JButton("Utility");
        utilityButton.addActionListener(gc);

        utilityButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        utilityButton.setBackground(Color.BLACK);
        utilityButton.setForeground(Color.YELLOW);
        utilityButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        utilityButton.setFocusPainted(false);

        String utilityIconFilename = "src/Sprites/utility.png";
        Icon utilityIcon = new ImageIcon(utilityIconFilename);
        utilityButton.setIcon(utilityIcon);

        utilityButton.setVerticalTextPosition(SwingConstants.TOP);
        utilityButton.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel utilityButtonBuffer = new JPanel();
        utilityButtonBuffer.setLayout(new BoxLayout(utilityButtonBuffer, BoxLayout.Y_AXIS));
        utilityButtonBuffer.add(utilityButton);

        utilityButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Stats.
        JButton statsButton = new JButton("Stats");
        statsButton.addActionListener(gc);

        statsButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        statsButton.setBackground(Color.BLACK);
        statsButton.setForeground(Color.YELLOW);
        statsButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        statsButton.setFocusPainted(false);

        String statsIconFilename = "src/Sprites/stats.png";
        Icon statsIcon = new ImageIcon(statsIconFilename);
        statsButton.setIcon(statsIcon);

        statsButton.setVerticalTextPosition(SwingConstants.TOP);
        statsButton.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel statsButtonBuffer = new JPanel();
        statsButtonBuffer.setLayout(new BoxLayout(statsButtonBuffer, BoxLayout.Y_AXIS));
        statsButtonBuffer.add(statsButton);

        utilityButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Surrender.
        JButton surrenderButton = new JButton("Surrender");
        surrenderButton.addActionListener(gc);

        surrenderButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        surrenderButton.setBackground(Color.BLACK);
        surrenderButton.setForeground(Color.YELLOW);
        surrenderButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        surrenderButton.setFocusPainted(false);

        String surrenderIconFilename = "src/Sprites/surrender.png";
        Icon surrenderIcon = new ImageIcon(surrenderIconFilename);
        surrenderButton.setIcon(surrenderIcon);

        surrenderButton.setVerticalTextPosition(SwingConstants.TOP);
        surrenderButton.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel surrenderButtonBuffer = new JPanel();
        surrenderButtonBuffer.setLayout(new BoxLayout(surrenderButtonBuffer, BoxLayout.Y_AXIS));
        surrenderButtonBuffer.add(surrenderButton);

        surrenderButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //----------------------------------------------------------------------------------------------------------------
        //Add choices to the choices panel.
        //----------------------------------------------------------------------------------------------------------------
        JPanel choices = new JPanel();
        choices.add(fightButtonBuffer);
        choices.add(utilityButtonBuffer);
        choices.add(statsButtonBuffer);
        choices.add(surrenderButtonBuffer);


        //----------------------------------------------------------------------------------------------------------------
        // Arrange all components in a grid.
        //----------------------------------------------------------------------------------------------------------------
        JPanel mainGrid = new JPanel();
        mainGrid.setLayout(new BorderLayout());
        mainGrid.add(battleLog, BorderLayout.NORTH);
        mainGrid.add(battleScene, BorderLayout.CENTER);
        mainGrid.add(choices, BorderLayout.SOUTH);

        //Align everything in main grid.
        battleLog.setAlignmentX(Component.CENTER_ALIGNMENT);
        choices.setAlignmentX(Component.CENTER_ALIGNMENT);
        battleScene.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Color.
        mainGrid.setBackground(Color.BLACK);
        choices.setBackground(Color.BLACK);
        playerPanel.setBackground(Color.BLACK);
        enemyPanel.setBackground(Color.BLACK);
        battleScene.setBackground(Color.BLACK);
        fightButtonBuffer.setBackground(Color.BLACK);
        utilityButtonBuffer.setBackground(Color.BLACK);
        statsButtonBuffer.setBackground(Color.BLACK);
        surrenderButtonBuffer.setBackground(Color.BLACK);

        this.add(mainGrid);
    }

    public JTextField getBattleLog() { return battleLog; }

    public EntityPanel getEnemyPanel() {
        return enemyPanel;
    }
    public EntityPanel getPlayerPanel() {return playerPanel; }
}