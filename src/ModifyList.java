import javax.swing.*;
import java.awt.*;

public class ModifyList {
    public ModifyList(JFrame oldFrame) {
        JFrame frame = new JFrame("Railway System");
        frame.setSize(500, 500);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(oldFrame);
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));

        JPanel panel = new JPanel(new GridLayout(8, 18, 10, 10));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton addTrain = new JButton("Add Train");
        panel.add(addTrain);

        JButton editTrain = new JButton("Edit Train");
        panel.add(editTrain);

        JButton addEmployee = new JButton("Add Employee");
        panel.add(addEmployee);

        JButton editEmployee = new JButton("Edit Employee");
        panel.add(editEmployee);

        JButton addPassenger = new JButton("Add Passenger");
        panel.add(addPassenger);

        JButton editPassenger = new JButton("Edit Passenger");
        panel.add(editPassenger);

        JButton addTrip = new JButton("Add Trip");
        panel.add(addTrip);

        JButton editTrip = new JButton("Edit Trip");
        panel.add(editTrip);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton JButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.decode("#45c4b0"));
        btn.setFont(new Font("Serif", Font.BOLD, 20));
        return btn;
    }
}
