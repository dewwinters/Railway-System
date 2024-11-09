package Passengers;

import Main.Database;
import Main.GUI;
import Trips.TripsDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ShowPassengers {

    private JPanel table;
    private GridLayout gridLayout;
    private JComboBox<String> trip;

    public ShowPassengers(JFrame parent, Database database) throws SQLException {

        JFrame frame = new JFrame("Show Passengers");
        frame.setSize(750, 700);
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel top = new JPanel(new GridLayout(1, 2));
        top.setBackground(null);
        top.add(GUI.Label("Trip:"));
        trip = GUI.JComboBox(TripsDatabase.getIDs(database));
        top.add(trip);
        panel.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(7, 1);
        table = new JPanel(gridLayout);
        if (trip.getSelectedItem() != null) {
            refreshTable(database);
        }
        trip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshTable(database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void refreshTable(Database database) throws SQLException {

        table.removeAll();
        table.repaint();
        table.revalidate();

        String[][] data = TripsDatabase.getPassengers(trip.getSelectedItem().toString(), database);

        if (data.length != 0 && data[0].length != 0) {
            int rows = data[0].length;
            if (rows < 7) {
                rows = 7;
            }
            gridLayout.setRows(rows);

            JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            row1.setBackground(Color.decode("#e5e5e5"));
            row1.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
            row1.add(JLabel("ID", 100));
            row1.add(JLabel("Name", 100));
            row1.add(JLabel("Tel", 100));
            row1.add(JLabel("Email", 100));
            row1.add(JLabel("Tickets", 100));
            table.add(row1);

            for (int i = 0; i < data.length; i++) {
                Passenger p = PassengersDatabase.getPassenger(data[i][0], database);
                JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
                row.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
                if (i % 2 == 1) {
                    row.setBackground(Color.decode("#e5e5e5"));
                }
                row.add(JLabel(String.valueOf(p.getId()), 100));
                row.add(JLabel(p.getName(), 100));
                row.add(JLabel(p.getTelephone(), 100));
                row.add(JLabel(p.getEmail(), 100));
                row.add(JLabel(data[i][1], 100));
                table.add(row);
            }
        }
    }

    private JLabel JLabel(String text, int width) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(width, 20));
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.decode("#13678A"));
        return label;
    }
}
