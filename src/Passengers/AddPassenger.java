package Passengers;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddPassenger {

    public AddPassenger(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Add Passenger");
        frame.setSize(750, 700);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(7, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        JLabel id = GUI.Label(String.valueOf(PassengersDatabase.getNextID(database)));
        panel.add(id);

        panel.add(GUI.Label("Name:"));
        JTextField name = GUI.TextField();
        panel.add(name);

        panel.add(GUI.Label("Telephone:"));
        JTextField telephone = GUI.TextField();
        panel.add(telephone);

        panel.add(GUI.Label("Email:"));
        JTextField email = GUI.TextField();
        panel.add(email);

        JButton cancel = GUI.Button("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {frame.dispose();}
        });
        panel.add(cancel);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Passenger p = new Passenger();
                p.setId(Integer.parseInt(id.getText()));
                p.setName(name.getText());
                p.setTelephone(telephone.getText());
                p.setEmail(email.getText());
                try {
                    PassengersDatabase.addPassenger(p, database);
                    JOptionPane.showMessageDialog(parent, "Passenger Added Successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(submit);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
