package Main;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public static Color background = Color.decode("#EBFFD8");

    public static JLabel Label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#012030"));
        label.setFont(new Font(null, Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static JTextField TextField() {
        JTextField textField = new JTextField();
        textField.setForeground(Color.decode("#012030"));
        textField.setFont(new Font(null, Font.BOLD, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    public static JButton Button(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.white);
        btn.setBackground(Color.decode("#45c4b0"));
        btn.setFont(new Font(null, Font.BOLD, 20));
        return btn;
    }

    public static JComboBox<String> JComboBox(String[] data) {
        JComboBox<String> box = new JComboBox<>(data);
        box.setFont(new Font(null, Font.PLAIN, 20));
        box.setBackground(Color.white);
        return box;
    }
}
