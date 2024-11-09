package Trips;

import Employees.EmployeesDatabase;
import Main.Database;
import Main.GUI;
import Trains.TrainsDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddTrip {

    public AddTrip(JFrame parent, Database database) throws SQLException {

        String[] HH = new String[25];
        HH[0] = "HH";
        for (int i = 0; i < 24; i++) {
            HH[i+1] = String.format("%02d", i);
        }

        String[] MM = new String[61];
        MM[0] = "MM";
        for (int i = 0; i < 60; i++) {
            MM[i+1] = String.format("%02d", i);
        }

        JFrame frame = new JFrame("Add Employee");
        frame.setSize(750, 1000);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(10, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        JLabel id = GUI.Label(String.valueOf(TripsDatabase.getNextID(database)));
        panel.add(id);

        panel.add(GUI.Label("Start:"));
        JTextField start = GUI.TextField();
        panel.add(start);

        panel.add(GUI.Label("Destination:"));
        JTextField destination = GUI.TextField();
        panel.add(destination);

        panel.add(GUI.Label("Departure Time:"));
        JPanel dept = new JPanel(new GridLayout(1, 2, 10, 10));
        dept.setBackground(null);
        JComboBox<String> deptHH = GUI.JComboBox(HH);
        JComboBox<String> deptMM = GUI.JComboBox(MM);
        dept.add(deptHH);
        dept.add(deptMM);
        panel.add(dept);

        panel.add(GUI.Label("Arrival Time:"));
        JPanel arrive = new JPanel(new GridLayout(1, 2, 10, 10));
        arrive.setBackground(null);
        JComboBox<String> arriveHH = GUI.JComboBox(HH);
        JComboBox<String> arriveMM = GUI.JComboBox(MM);
        arrive.add(arriveHH);
        arrive.add(arriveMM);
        panel.add(arrive);

        String[] dd = new String[32];
        dd[0] = "dd";
        for (int i = 1; i < 32; i++) {
            dd[i] = String.format("%02d", i);
        }

        String[] mm = new String[13];
        mm[0] = "mm";
        for (int i = 1; i < 13; i++) {
            mm[i] = String.format("%02d", i);
        }

        String[] yyyy = new String[11];
        yyyy[0] = "yyyy";
        for (int i = 2015; i < 2025; i++) {
            yyyy[i-2014] = String.format("%02d", i);
        }

        panel.add(GUI.Label("Date:"));
        JPanel date = new JPanel(new GridLayout(1, 3, 10, 10));
        date.setBackground(null);
        JComboBox<String> datedd = GUI.JComboBox(dd);
        JComboBox<String> datemm = GUI.JComboBox(mm);
        JComboBox<String> dateyyyy = GUI.JComboBox(yyyy);
        date.add(datedd);
        date.add(datemm);
        date.add(dateyyyy);
        panel.add(date);

        panel.add(GUI.Label("Price:"));
        JTextField price = GUI.TextField();
        panel.add(price);

        panel.add(GUI.Label("Driver:"));
        JComboBox<String> driver = GUI.JComboBox(EmployeesDatabase.getEmployeesNames(database));
        panel.add(driver);

        panel.add(GUI.Label("Train:"));
        JComboBox<String> train = GUI.JComboBox(TrainsDatabase.getTrainsNames(database));
        panel.add(train);

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
                if (checkChoice(deptHH, frame)) return;
                if (checkChoice(deptMM, frame)) return;
                if (checkChoice(arriveHH, frame)) return;
                if (checkChoice(arriveMM, frame)) return;
                if (checkChoice(datedd, frame)) return;
                if (checkChoice(datemm, frame)) return;
                if (checkChoice(dateyyyy, frame)) return;
                try {
                    Double.parseDouble(price.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Price must be a number");
                    return;
                }
                Trip trip = new Trip();
                trip.setId(Integer.parseInt(id.getText()));
                trip.setStart(start.getText());
                trip.setDestination(destination.getText());
                String deptTime = deptHH.getSelectedItem().toString() + ":" + deptMM.getSelectedItem().toString();
                String arriveTime = arriveHH.getSelectedItem().toString() + ":" + arriveMM.getSelectedItem().toString();
                trip.setDepartureTime(deptTime);
                trip.setArrivalTime(arriveTime);
                String d = datedd.getSelectedItem().toString() + "/" + datemm.getSelectedItem().toString() + "/" + dateyyyy.getSelectedItem().toString();
                trip.setDate(d);
                trip.setBookedSeats(0);
                trip.setPrice(Double.parseDouble(price.getText()));
                try {
                    trip.setDriver(EmployeesDatabase.getEmployeeByName(driver.getSelectedItem().toString(), database));
                    trip.setTrain(TrainsDatabase.getTrainByName(train.getSelectedItem().toString(), database));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
                trip.setPassengers(new ArrayList<>());
                try {
                    TripsDatabase.addTrip(trip, database);
                    JOptionPane.showMessageDialog(frame, "Trip Added Successfully.");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed.");
                    frame.dispose();
                }
            }
        });
        panel.add(submit);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private boolean checkChoice(JComboBox<String> comboBox, JFrame frame) {
        boolean c = false;
        if (comboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(frame, "Invalid: " + comboBox.getSelectedItem().toString());
            c = true;
        }
        return c;
    }
}
