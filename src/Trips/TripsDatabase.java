package Trips;

import Employees.EmployeesDatabase;
import Main.Database;
import Trains.TrainsDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TripsDatabase {

    public static void addTrip(Trip trip, Database database) throws SQLException {
        String insert = "INSERT INTO `trips` (`ID`, `Start`, `Destination`, `DepartureTime`, `ArriveTime`, `Date`, `BookedSeats`, `Price`, `Driver`, `Train`) VALUES ('"
                + trip.getId() + "','"
                + trip.getStart() + "','"
                + trip.getDestination() + "','"
                + trip.getDepartureTime() + "','"
                + trip.getArrivalTime() + "','"
                + trip.getDate() + "','"
                + trip.getBookedSeats() + "','"
                + trip.getPrice() + "','"
                + trip.getDriver().getId() + "','"
                + trip.getTrain().getId() + "');";
        database.getStatement().execute(insert);

        String create = "CREATE TABLE `Trip " + trip.getId() + " Passengers` (Passenger int, Tickets int);";
        database.getStatement().execute(create);
    }

    public static int getNextID(Database database) throws SQLException {
        int id = 0;
        ArrayList<Trip> trips = getAllTrips(database);
        int size = trips.size();
        if (size != 0) id = trips.get(size - 1).getId() + 1;
        return id;
    }

    public static ArrayList<Trip> getAllTrips(Database database) throws SQLException {
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Integer> drivers = new ArrayList<>();
        ArrayList<Integer> trains = new ArrayList<>();
        String select = "SELECT * FROM `trips`;";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Trip trip = new Trip();
            trip.setId(rs.getInt("ID"));
            trip.setStart(rs.getString("Start"));
            trip.setDestination(rs.getString("Destination"));
            trip.setDepartureTime(rs.getString("DepartureTime"));
            trip.setArrivalTime(rs.getString("ArriveTime"));
            trip.setDate(rs.getString("Date"));
            trip.setBookedSeats(rs.getInt("BookedSeats"));
            trip.setPrice(rs.getDouble("Price"));
            drivers.add(rs.getInt("Driver"));
            trains.add(rs.getInt("Train"));
            trips.add(trip);
        }
        for (int i = 0; i < trips.size(); i++) {
            trips.get(i).setDriver(EmployeesDatabase.getEmployeeByID(String.valueOf(drivers.get(i)), database));
            trips.get(i).setTrain(TrainsDatabase.getTrainByID(String.valueOf(trains.get(i)), database));
        }
        return trips;
    }
}
