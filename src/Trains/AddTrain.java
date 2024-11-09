package Trains;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddTrain {
    public AddTrain(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Add Train");
        frame.setSize(750, 400);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        JLabel id = GUI.Label(String.valueOf(TrainsDatabase.getNextID(database)));
        panel.add(id);

        panel.add(GUI.Label("Capacity:"));
        JTextField capacity = GUI.TextField();
        panel.add(capacity);

        panel.add(GUI.Label("Description:"));
        JTextField description = GUI.TextField();
        panel.add(description);

        JButton cancel = GUI.Button("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Train t = new Train();
                t.setId(Integer.parseInt(id.getText()));
                t.setCapacity(Integer.parseInt(capacity.getText()));
                t.setDescription(description.getText());
                try {
                    TrainsDatabase.addTrain(t, database);
                    JOptionPane.showMessageDialog(frame, "Train Added Successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                }
            }
        });
        panel.add(submit);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
