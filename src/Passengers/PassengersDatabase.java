package Passengers;

import Main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengersDatabase {
    static void addPassenger(Passenger p, Database database) throws SQLException {
        String insert = "INSERT INTO `passengers`(`ID`, `Name`, `Email`, `Tel`)"
                + "VALUES ('" + p.getId() + "','" + p.getName() + "','" + p.getEmail() + "','" +p.getTelephone() + "');";
        database.getStatement().executeUpdate(insert);
    }

    static int getNextID(Database database) throws SQLException {
        int id = 0;
        int size = getAllPassengers(database).size();
        ArrayList<Passenger> passengers = getAllPassengers(database);
        if (size != 0) {
            id = passengers.get(size - 1).getId() + 1;
        }
        return id;
    }

    static ArrayList<Passenger> getAllPassengers(Database database) throws SQLException {
        String select = "SELECT * FROM `passengers`";
        ArrayList<Passenger> passengers = new ArrayList<>();
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Passenger p = new Passenger();
            p.setId(rs.getInt("ID"));
            p.setName(rs.getString("Name"));
            p.setEmail(rs.getString("Email"));
            p.setTelephone(rs.getString("Tel"));
            passengers.add(p);
        }
        return passengers;
    }
}
