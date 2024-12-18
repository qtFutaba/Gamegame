package Game;

import javax.swing.*;
import java.awt.*;

public class JPanelFactroy extends JPanel {

    private JPanel panel;
    private JLabel label;

    public JPanelFactroy() {}

    public JPanel Factory(String message) {
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        label = new JLabel(message, JLabel.CENTER);
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(label);
        return panel;
    }
}
