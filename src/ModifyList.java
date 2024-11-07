import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

        JButton addTrain = GUI.Button("Add Train");
        addTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddTrain(frame, database);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(addTrain);

        JButton editTrain = GUI.Button("Edit Train");
        editTrain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditTrain(frame, database);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(editTrain);

        JButton addEmployee = GUI.Button("Add Employee");
        panel.add(addEmployee);

        JButton editEmployee = GUI.Button("Edit Employee");
        panel.add(editEmployee);

        JButton addPassenger = GUI.Button("Add Passenger");
        panel.add(addPassenger);

        JButton editPassenger = GUI.Button("Edit Passenger");
        panel.add(editPassenger);

        JButton addTrip = GUI.Button("Add Trip");
        panel.add(addTrip);

        JButton editTrip = GUI.Button("Edit Trip");
        panel.add(editTrip);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
