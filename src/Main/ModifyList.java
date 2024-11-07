package Main;

import Employees.AddEmployee;
import Employees.EditEmployee;
import Passengers.AddPassenger;
import Passengers.EditPassenger;
import Trains.AddTrain;
import Trains.EditTrain;
import Trips.AddTrip;
import Trips.EditTrip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifyList {
    public ModifyList(JFrame oldFrame, Database database) {
        JFrame frame = new JFrame("Modify");
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
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
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
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(editTrain);

        JButton addEmployee = GUI.Button("Add Employee");
        addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddEmployee(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(addEmployee);

        JButton editEmployee = GUI.Button("Edit Employee");
        editEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditEmployee(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(editEmployee);

        JButton addPassenger = GUI.Button("Add Passenger");
        addPassenger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddPassenger(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(addPassenger);

        JButton editPassenger = GUI.Button("Edit Passenger");
        editPassenger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditEmployee(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(editPassenger);

        JButton addTrip = GUI.Button("Add Trip");
        addTrip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddTrip(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(addTrip);

        JButton editTrip = GUI.Button("Edit Trip");
        editTrip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditTrip(frame, database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(editTrip);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
