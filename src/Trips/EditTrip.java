package Trips;

import Employees.EmployeesDatabase;
import Main.Database;
import Main.GUI;
import Main.Main;
import Trains.TrainsDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditTrip {

    private JComboBox<String> id, deptHH, deptMM, arriveHH, arriveMM, datedd, datemm, dateyyyy, driver, train;
    private JTextField start, destination, price;

    public EditTrip(JFrame parent, Database database) throws SQLException {
        String[] HH = new String[25];
        HH[0] = "HH";
        for (int i = 0; i < 24; i++) {
            HH[i+1] = String.format("%02d", i);
        }

        String[] mm = new String[61];
        mm[0] = "mm";
        for (int i = 0; i < 60; i++) {
            mm[i+1] = String.format("%02d", i);
        }

        JFrame frame = new JFrame("Edit Trip");
        frame.setSize(750, 1000);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(10, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        id = GUI.JComboBox(TripsDatabase.getIDs(database));
        panel.add(id);

        panel.add(GUI.Label("Start:"));
        start = GUI.TextField();
        panel.add(start);

        panel.add(GUI.Label("Destination:"));
        destination = GUI.TextField();
        panel.add(destination);

        panel.add(GUI.Label("Departure Time:"));
        JPanel dept = new JPanel(new GridLayout(1, 2, 10, 10));
        dept.setBackground(null);
        deptHH = GUI.JComboBox(HH);
        deptMM = GUI.JComboBox(mm);
        dept.add(deptHH);
        dept.add(deptMM);
        panel.add(dept);

        panel.add(GUI.Label("Arrival Time:"));
        JPanel arrive = new JPanel(new GridLayout(1, 2, 10, 10));
        arrive.setBackground(null);
        arriveHH = GUI.JComboBox(HH);
        arriveMM = GUI.JComboBox(mm);
        arrive.add(arriveHH);
        arrive.add(arriveMM);
        panel.add(arrive);

        String[] dd = new String[32];
        dd[0] = "dd";
        for (int i = 1; i < 32; i++) {
            dd[i] = String.format("%02d", i);
        }

        String[] MM = new String[13];
        MM[0] = "MM";
        for (int i = 1; i < 13; i++) {
            MM[i] = String.format("%02d", i);
        }

        String[] yyyy = new String[11];
        yyyy[0] = "yyyy";
        for (int i = 2015; i < 2025; i++) {
            yyyy[i-2014] = String.format("%02d", i);
        }

        panel.add(GUI.Label("Date:"));
        JPanel date = new JPanel(new GridLayout(1, 3, 10, 10));
        date.setBackground(null);
        datedd = GUI.JComboBox(dd);
        datemm = GUI.JComboBox(MM);
        dateyyyy = GUI.JComboBox(yyyy);
        date.add(datedd);
        date.add(datemm);
        date.add(dateyyyy);
        panel.add(date);

        panel.add(GUI.Label("Price:"));
        price = GUI.TextField();
        panel.add(price);

        panel.add(GUI.Label("Driver:"));
        driver = GUI.JComboBox(EmployeesDatabase.getEmployeesNames(database));
        panel.add(driver);

        panel.add(GUI.Label("Train:"));
        train = GUI.JComboBox(TrainsDatabase.getTrainsNames(database));
        panel.add(train);

        JButton delete = GUI.Button("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TripsDatabase.deleteTrip(id.getSelectedItem().toString(), database);
                    JOptionPane.showMessageDialog(frame, "Trip Deleted Successfully");
                    Main.refreshTable(TripsDatabase.getAllTrips(database));
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(delete);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Trip trip = new Trip();
                trip.setId(Integer.parseInt(id.getSelectedItem().toString()));
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
                    TripsDatabase.editTrip(trip, database);
                    JOptionPane.showMessageDialog(frame, "Trip Updated Successfully");
                    Main.refreshTable(TripsDatabase.getAllTrips(database));
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(submit);

        if (id.getSelectedItem() != null) {
            refreshData(database);
        }

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

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void refreshData (Database database) throws SQLException {
        String ID = id.getSelectedItem().toString();
        Trip trip = TripsDatabase.getTripById(ID, database);
        start.setText(trip.getStart());
        destination.setText(trip.getDestination());
        price.setText(String.valueOf(trip.getPrice()));
        driver.setSelectedItem(trip.getDriver().getName());
        train.setSelectedItem(trip.getTrain().getDescription());
        deptHH.setSelectedItem(trip.getDeptHour());
        deptMM.setSelectedItem(trip.getDeptMinute());
        arriveHH.setSelectedItem(trip.getArrivalHour());
        arriveMM.setSelectedItem(trip.getArrivalMinute());
        datedd.setSelectedItem(trip.getDay());
        datemm.setSelectedItem(trip.getMonth());
        dateyyyy.setSelectedItem(trip.getYear());
    }
}
