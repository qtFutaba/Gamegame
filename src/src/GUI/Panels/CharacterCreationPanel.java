package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class CharacterCreationPanel extends JPanel
{
    private JLabel nameLabel;
    private JTextField nameField;

    // Constructor for the main menu.
    public CharacterCreationPanel(CharacterCreationControl ccc)
    {
        // Create the title.
        JLabel label = new JLabel("CREATE YOUR CHARACTER, ADVENTURER.", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels padding

        //----------------------------------------------------------------------------------------------------------------
        // Create the character choices.
        //----------------------------------------------------------------------------------------------------------------
        //Warrior. High attack. Medium defense. Low magic.
        JButton warriorButton = new JButton("Warrior");
        warriorButton.addActionListener(ccc);

        warriorButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        warriorButton.setBackground(Color.BLACK);
        warriorButton.setForeground(Color.YELLOW);
        warriorButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        warriorButton.setFocusPainted(false);

        JLabel warriorLabel1 = new JLabel("- High ATK.", JLabel.CENTER);
        warriorLabel1.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        warriorLabel1.setForeground(Color.WHITE);

        JLabel warriorLabel2 = new JLabel("- Medium DEF.", JLabel.CENTER);
        warriorLabel2.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        warriorLabel2.setForeground(Color.WHITE);

        JLabel warriorLabel3 = new JLabel("- Low MAG.", JLabel.CENTER);
        warriorLabel3.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        warriorLabel3.setForeground(Color.WHITE);

        String warriorFilename = "src/Sprites/warriorplayer.png";
        Icon warriorIcon = new ImageIcon(warriorFilename);
        JLabel warriorImage = new JLabel();
        warriorImage.setIcon(warriorIcon);

        JPanel warriorButtonBuffer = new JPanel();
        warriorButtonBuffer.setLayout(new BoxLayout(warriorButtonBuffer, BoxLayout.Y_AXIS));
        warriorButtonBuffer.add(warriorButton);
        warriorButtonBuffer.add(warriorLabel1);
        warriorButtonBuffer.add(warriorLabel2);
        warriorButtonBuffer.add(warriorLabel3);
        warriorButtonBuffer.add(warriorImage);

        warriorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        warriorLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        warriorLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        warriorLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        warriorImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Mage. Low attack. Medium defense. High magic.
        JButton mageButton = new JButton("Mage");
        mageButton.addActionListener(ccc);
        mageButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        mageButton.setBackground(Color.BLACK);
        mageButton.setForeground(Color.YELLOW);
        mageButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        mageButton.setFocusPainted(false);

        JLabel mageLabel1 = new JLabel("- Low ATK.", JLabel.CENTER);
        mageLabel1.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        mageLabel1.setForeground(Color.WHITE);

        JLabel mageLabel2 = new JLabel("- Medium DEF.", JLabel.CENTER);
        mageLabel2.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        mageLabel2.setForeground(Color.WHITE);

        JLabel mageLabel3 = new JLabel("- High MAG.", JLabel.CENTER);
        mageLabel3.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        mageLabel3.setForeground(Color.WHITE);

        String mageFilename = "src/Sprites/mageplayer.png";
        Icon mageIcon = new ImageIcon(mageFilename);
        JLabel mageImage = new JLabel();
        mageImage.setIcon(mageIcon);

        JPanel mageButtonBuffer = new JPanel();
        mageButtonBuffer.setLayout(new BoxLayout(mageButtonBuffer, BoxLayout.Y_AXIS));
        mageButtonBuffer.add(mageButton);
        mageButtonBuffer.add(mageLabel1);
        mageButtonBuffer.add(mageLabel2);
        mageButtonBuffer.add(mageLabel3);
        mageButtonBuffer.add(mageImage);

        mageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mageLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mageLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mageLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        mageImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Paladin. Low attack. High defense. Medium magic.
        JButton paladinButton = new JButton("Paladin");
        paladinButton.addActionListener(ccc);
        paladinButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        paladinButton.setBackground(Color.BLACK);
        paladinButton.setForeground(Color.YELLOW);
        paladinButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        paladinButton.setFocusPainted(false);

        JLabel paladinLabel1 = new JLabel("- Low ATK.", JLabel.CENTER);
        paladinLabel1.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        paladinLabel1.setForeground(Color.WHITE);

        JLabel paladinLabel2 = new JLabel("- High DEF.", JLabel.CENTER);
        paladinLabel2.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        paladinLabel2.setForeground(Color.WHITE);

        JLabel paladinLabel3 = new JLabel("- Medium MAG.", JLabel.CENTER);
        paladinLabel3.setFont(new Font("Viner Hand ITC", Font.BOLD, 10));
        paladinLabel3.setForeground(Color.WHITE);

        String paladinFilename = "src/Sprites/paladinplayer.png";
        Icon paladinIcon = new ImageIcon(paladinFilename);
        JLabel paladinImage = new JLabel();
        paladinImage.setIcon(paladinIcon);

        JPanel paladinButtonBuffer = new JPanel();
        paladinButtonBuffer.setLayout(new BoxLayout(paladinButtonBuffer, BoxLayout.Y_AXIS));
        paladinButtonBuffer.add(paladinButton);
        paladinButtonBuffer.add(paladinLabel1);
        paladinButtonBuffer.add(paladinLabel2);
        paladinButtonBuffer.add(paladinLabel3);
        paladinButtonBuffer.add(paladinImage);

        paladinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paladinLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        paladinLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        paladinLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        paladinImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        //----------------------------------------------------------------------------------------------------------------
        //Add choices to the choices panel.
        //----------------------------------------------------------------------------------------------------------------
        JPanel choices = new JPanel(new GridLayout(1, 3, 0, 0));
        choices.add(warriorButtonBuffer);
        choices.add(mageButtonBuffer);
        choices.add(paladinButtonBuffer);

        // Character name entry.
        nameLabel = new JLabel("ENTER YOUR NAME.", JLabel.CENTER);
        nameLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);

        nameField = new JTextField(10);
        nameField.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setMaximumSize(new Dimension(200, nameField.getPreferredSize().height));
        nameField.setHorizontalAlignment(JTextField.CENTER);

        JPanel nameEntryBuffer = new JPanel();
        nameEntryBuffer.setLayout(new BoxLayout(nameEntryBuffer, BoxLayout.Y_AXIS));
        nameEntryBuffer.add(nameLabel);
        nameEntryBuffer.add(nameField);

        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Begin journey button.
        JButton beginJourneyButton = new JButton("Begin Your Journey...");
        beginJourneyButton.addActionListener(ccc);
        beginJourneyButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        beginJourneyButton.setBackground(Color.BLACK);
        beginJourneyButton.setForeground(Color.YELLOW);
        beginJourneyButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));



        //----------------------------------------------------------------------------------------------------------------
        // Arrange all components in a grid.
        //----------------------------------------------------------------------------------------------------------------
        JPanel mainGrid = new JPanel();
        mainGrid.setLayout(new BoxLayout(mainGrid, BoxLayout.Y_AXIS));
        mainGrid.add(label);
        mainGrid.add(choices);
        mainGrid.add(nameEntryBuffer);
        mainGrid.add(beginJourneyButton);

        //Align everything in main grid.
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        choices.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameEntryBuffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        beginJourneyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Color.
        mainGrid.setBackground(Color.BLACK);
        choices.setBackground(Color.BLACK);
        warriorButtonBuffer.setBackground(Color.BLACK);
        mageButtonBuffer.setBackground(Color.BLACK);
        paladinButtonBuffer.setBackground(Color.BLACK);
        nameEntryBuffer.setBackground(Color.BLACK);
        
        this.add(mainGrid);
    }

    public String getNameFieldText()
    {
        return nameField.getText();
    }
}
