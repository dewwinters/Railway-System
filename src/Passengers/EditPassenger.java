package Passengers;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditPassenger {

    private JComboBox<String> id;
    private JTextField name;
    private JTextField email;
    private JTextField telephone;

    public EditPassenger(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Edit Passenger");
        frame.setSize(750, 500);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(5, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        id = GUI.JComboBox(PassengersDatabase.getPassengerIDs(database));
        panel.add(id);

        panel.add(GUI.Label("Name:"));
        name = GUI.TextField();
        panel.add(name);

        panel.add(GUI.Label("Email:"));
        email = GUI.TextField();
        panel.add(email);

        panel.add(GUI.Label("Telephone:"));
        telephone = GUI.TextField();
        panel.add(telephone);

        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshData(database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        try {
            refreshData(database);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }

        JButton delete = GUI.Button("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PassengersDatabase.deletePassenger(id.getSelectedItem().toString(), database);
                    JOptionPane.showMessageDialog(frame, "Passenger Deleted Successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed.");
                    frame.dispose();
                }
            }
        });
        panel.add(delete);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Passenger p = new Passenger();
                p.setId(Integer.parseInt(id.getSelectedItem().toString()));
                p.setName(name.getText());
                p.setEmail(email.getText());
                p.setTelephone(telephone.getText());
                try {
                    PassengersDatabase.editPassenger(p, database);
                    JOptionPane.showMessageDialog(frame, "Passenger Successfully Updated");
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

    private void refreshData(Database database) throws SQLException {
        if (id.getSelectedItem() != null) {
            Passenger p = PassengersDatabase.getPassenger(id.getSelectedItem().toString(), database);
            name.setText(p.getName());
            email.setText(p.getEmail());
            telephone.setText(p.getTelephone());
        }
    }
}