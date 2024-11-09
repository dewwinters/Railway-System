package Trips;

import Main.Database;
import Main.GUI;
import Passengers.Passenger;
import Passengers.PassengersDatabase;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookTrip {

    JComboBox<String> passenger;
    JLabel id, name, tel, email;

    public BookTrip(JFrame parent, Database database, Trip trip) throws SQLException {
        JFrame frame = new JFrame("Book Trip");
        frame.setSize(750, 900);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(9, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("Passenger:"));
        passenger = GUI.JComboBox(PassengersDatabase.getPassengerNames(database));
        panel.add(passenger);

        panel.add(GUI.Label("ID:"));
        id = GUI.Label("");
        panel.add(id);

        panel.add(GUI.Label("Name:"));
        name = GUI.Label("");
        panel.add(name);

        panel.add(GUI.Label("Tel:"));
        tel = GUI.Label("");
        panel.add(tel);

        panel.add(GUI.Label("Email:"));
        email = GUI.Label("");
        panel.add(email);

        panel.add(GUI.Label("Number of tickets:"));
        JTextField numOfTickets = GUI.TextField();
        panel.add(numOfTickets);

        panel.add(GUI.Label("Price:"));
        JLabel price = GUI.Label(trip.getPrice() + " $");
        panel.add(price);

        panel.add(GUI.Label("Total:"));
        JLabel total = GUI.Label("");
        panel.add(total);

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
                try {
                    Integer.parseInt(numOfTickets.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Number of tickets must be int");
                    return;
                }
                try {
                    TripsDatabase.BookTrip(trip, id.getText(), numOfTickets.getText(), database);
                    JOptionPane.showMessageDialog(frame, "Book Successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(submit);

        if (passenger.getSelectedItem() != null) {
            refreshPassengerData(database);
        }

        numOfTickets.getDocument().addDocumentListener(new DocumentListener() {
            public void removeUpdate(DocumentEvent e) {
                try {
                    Integer.parseInt(numOfTickets.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }
                int num = Integer.parseInt(numOfTickets.getText());
                double p = trip.getPrice();
                double t = num * p;
                total.setText(t + " $");
            }

            public void insertUpdate(DocumentEvent e) {
                try {
                    Integer.parseInt(numOfTickets.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }
                int num = Integer.parseInt(numOfTickets.getText());
                double p = trip.getPrice();
                double t = num * p;
                total.setText(t + " $");
            }

            public void changedUpdate(DocumentEvent e) {
                try {
                    Integer.parseInt(numOfTickets.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }
                int num = Integer.parseInt(numOfTickets.getText());
                double p = trip.getPrice();
                double t = num * p;
                total.setText(t + " $");
            }
        });

        passenger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshPassengerData(database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void refreshPassengerData(Database database) throws SQLException {
        String Passenger = passenger.getSelectedItem().toString();
        Passenger p = PassengersDatabase.getPassengerByName(Passenger, database);
        id.setText(String.valueOf(p.getId()));
        name.setText(p.getName());
        tel.setText(p.getTelephone());
        email.setText(p.getEmail());
    }
}
