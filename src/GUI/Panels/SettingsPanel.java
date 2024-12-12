package GUI.Panels;

import GUI.Controllers.*;
import java.awt.*;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.*;

public class SettingsPanel extends JPanel {
    public JButton devModeOnButton;
    public JButton devModeOffButton;
    public JSlider musicVolumeSlider;

    public SettingsPanel(GameController gc) {
        JLabel label = new JLabel("Settings", JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
        label.setForeground(Color.WHITE);

        // Create common border style
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Create the music volume setting panel
        JPanel musicVolumeSettingBuffer = new JPanel();
        JLabel musicVolumeSettingLabel = new JLabel("Music Volume:");
        musicVolumeSettingLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        musicVolumeSettingLabel.setForeground(Color.YELLOW);

        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) gc.getMusicPlayer().getCurrentVolume());
        musicVolumeSlider.setMajorTickSpacing(1);
        musicVolumeSlider.setMinorTickSpacing(1);
        musicVolumeSlider.setPaintTicks(false);
        musicVolumeSlider.setPaintLabels(false);

        // Add a ChangeListener to the slider to update the volume
        musicVolumeSlider.addChangeListener(e -> {
            int volumeValue = musicVolumeSlider.getValue();
            gc.getMusicPlayer().setVolume(volumeValue);
        });

        musicVolumeSlider.setBackground(Color.BLACK);

        musicVolumeSettingBuffer.add(musicVolumeSettingLabel);
        musicVolumeSettingBuffer.add(musicVolumeSlider);

        // Create the developer setting panel
        JPanel devSettingBuffer = new JPanel();
        JLabel devSettingLabel = new JLabel("Developer Mode:");
        devSettingLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        devSettingLabel.setForeground(Color.YELLOW);

        //Dev mode on button..
        devModeOnButton = new JButton("On");
        devModeOnButton.addActionListener(gc);

        devModeOnButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        devModeOnButton.setBackground(Color.BLACK);
        devModeOnButton.setForeground(Color.YELLOW);
        devModeOnButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        devModeOnButton.setFocusPainted(false);

        //Dev mode off button..
        devModeOffButton = new JButton("Off");
        devModeOffButton.addActionListener(gc);

        devModeOffButton.setBorder(BorderFactory.createCompoundBorder(selectedBorder, paddingBorder));
        devModeOffButton.setBackground(Color.BLACK);
        devModeOffButton.setForeground(Color.YELLOW);
        devModeOffButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        devModeOffButton.setFocusPainted(false);

        //Add to dev mode buffer
        devSettingBuffer.add(devSettingLabel);
        devSettingBuffer.add(devModeOnButton);
        devSettingBuffer.add(devModeOffButton);



        // Create the return to main menu button.
        JButton mainmenuButton = new JButton("Return to Main Menu.");
        mainmenuButton.addActionListener(gc);
        mainmenuButton.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        mainmenuButton.setBackground(Color.BLACK);
        mainmenuButton.setForeground(Color.WHITE);
        mainmenuButton.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));

        JPanel mainmenuButtonBuffer = new JPanel();
        mainmenuButtonBuffer.add(mainmenuButton);

        // Arrange the components vertically
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));

        // Center align the components
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        musicVolumeSettingBuffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        devSettingBuffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainmenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Top spacing
        grid.add(label);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing after title
        grid.add(musicVolumeSettingBuffer);
        grid.add(devSettingBuffer);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing before button
        grid.add(mainmenuButton);
        grid.add(Box.createRigidArea(new Dimension(0, 20))); // Bottom spacing

        // Set colors
        grid.setBackground(Color.BLACK);
        musicVolumeSettingBuffer.setBackground(Color.BLACK);
        devSettingBuffer.setBackground(Color.BLACK);
        mainmenuButtonBuffer.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        this.add(grid);

    }
}
