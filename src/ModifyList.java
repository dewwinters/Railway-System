import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyList {
    public ModifyList(JFrame oldFrame, Database database) {
        JFrame frame = new JFrame("Railway System");
        frame.setSize(500, 500);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(oldFrame);
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));

        JPanel panel = new JPanel(new GridLayout(8, 18, 10, 10));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        Button addTrain = new Button("Add Train");
        addTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddTrain(oldFrame, database);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(addTrain);

        Button editTrain = new Button("Edit Train");
        panel.add(editTrain);

        Button addEmployee = new Button("Add Employee");
        panel.add(addEmployee);

        Button editEmployee = new Button("Edit Employee");
        panel.add(editEmployee);

        Button addPassenger = new Button("Add Passenger");
        panel.add(addPassenger);

        Button editPassenger = new Button("Edit Passenger");
        panel.add(editPassenger);

        Button addTrip = new Button("Add Trip");
        panel.add(addTrip);

        Button editTrip = new Button("Edit Trip");
        panel.add(editTrip);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton Button(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.decode("#EBFFD8"));
        btn.setForeground(Color.white);
        btn.setFont(new Font("Serif", Font.BOLD, 20));
        return btn;
    }
}
