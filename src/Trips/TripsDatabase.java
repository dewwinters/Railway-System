package Trips;

import Main.Database;

import java.sql.SQLException;

public class TripsDatabase {

    static void addTrip(Trip trip, Database database) throws SQLException {
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
    }
}
