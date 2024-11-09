package Main;

import Trains.Train;
import Trips.Trip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    private static JFrame frame;
    private static JPanel table;
    private static GridLayout gridLayout;

    public static void main(String[] args) throws SQLException {
        Database database = new Database();

        frame = new JFrame("Railway System");
        frame.setSize(1280, 720);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel title = new JLabel("Railway System");
        title.setForeground(Color.decode("#012030"));
        title.setFont(new Font(null, Font.BOLD, 35));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        gridLayout = new GridLayout(6,1);
        table = new JPanel(gridLayout);
        table.setBackground(Color.decode("#EBFFD8"));

        ArrayList<Trip> trips = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Trip trip = new Trip();
            trip.setArrivalTime("00:00");
            trip.setDepartureTime("00:00");
            trip.setDate("30/09/2023");
            trip.setDestination("Destination");
            trip.setStart("Start");
            Train tr = new Train();
            tr.setDescription("Train 1 VIP");
            trip.setTrain(tr);
            trip.setPrice(50);
            trips.add(trip);
        }
        refreshTable(trips);

        JScrollPane sp = new JScrollPane(table);
        panel.add(sp, BorderLayout.CENTER);

        JButton modify = new JButton("Modify");
        modify.setBackground(Color.decode("#45C4B0"));
        modify.setForeground(Color.white);
        modify.setFont(new Font(null, Font.BOLD, 20));
        modify.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               new ModifyList(frame, database);
           }
        });
        panel.add(modify, BorderLayout.SOUTH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void refreshTable(ArrayList<Trip> trips) {
        int rows = trips.size() + 1;
        if (rows < 6 ) rows = 6;
        gridLayout.setRows(rows);
        table.add(row(0, null));
        for (int i = 0; i < trips.size(); i++) {
            JPanel trip = row(i+1, trips.get(i));
            table.add(trip);
        }
    }

    private static JPanel row(int index, Trip trip) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        if (index % 2 == 0) row.setBackground(Color.decode("#e5e5e5"));
        else row.setBackground(Color.decode("#eeeeee"));

        String trainS, startS, destS, dateS, deptS, arrS, priceS, statusS;

        if (trip != null) {
            trainS = trip.getTrain().getDescription();
            startS = trip.getStart();
            destS = trip.getDestination();
            dateS = trip.getDate();
            deptS = trip.getDepartureTime();
            arrS = trip.getArrivalTime();
            priceS = trip.getPrice() + " $";
            statusS = "Booked";
            if (trip.getTrain().getCapacity() > trip.getBookedSeats()) {
                statusS = "Available";
            }
            row.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            trainS = "Train";
            startS = "From";
            destS = "To";
            dateS = "Date";
            deptS = "Dept";
            arrS = "Arr";
            priceS = "Price";
            statusS = "Status";
        }

        JLabel train = JLabel(trainS, 100);
        row.add(train);

        JLabel start = JLabel(startS, 100);
        row.add(start);

        JLabel dest = JLabel(destS, 100);
        row.add(dest);

        JLabel date = JLabel(dateS, 100);
        row.add(date);

        JLabel deptTime = JLabel(deptS, 75);
        row.add(deptTime);

        JLabel arrTime = JLabel(arrS, 75);
        row.add(arrTime);

        JLabel price = JLabel(priceS, 75);
        row.add(price);

        JLabel status = JLabel(statusS, 100);
        row.add(status);

        return row;
    }

    private static JLabel JLabel(String text, int width) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(width, 20));
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.decode("#13678a"));

        return label;
    }
}