package Passengers;

import Main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengersDatabase {
    public static void addPassenger(Passenger p, Database database) throws SQLException {
        String insert = "INSERT INTO `passengers`(`ID`, `Name`, `Email`, `Tel`)"
                + "VALUES ('" + p.getId() + "','" + p.getName() + "','" + p.getEmail() + "','" +p.getTelephone() + "');";
        database.getStatement().executeUpdate(insert);
    }

    public static int getNextID(Database database) throws SQLException {
        int id = 0;
        int size = getAllPassengers(database).size();
        ArrayList<Passenger> passengers = getAllPassengers(database);
        if (size != 0) {
            id = passengers.get(size - 1).getId() + 1;
        }
        return id;
    }

    public static ArrayList<Passenger> getAllPassengers(Database database) throws SQLException {
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

    public static String[] getPassengerIDs(Database database) throws SQLException {
        ArrayList<Passenger> passengers = getAllPassengers(database);
        String[] array = new String[passengers.size()];
        for (int i = 0; i < passengers.size(); i++) {
            array[i] = String.valueOf(passengers.get(i).getId());
        }
        return array;
    }

    public static Passenger getPassenger (String id, Database database) throws SQLException {
        Passenger p = new Passenger();
        String select = "SELECT `ID`, `Name`, `Email`, `Tel` FROM `passengers` WHERE `ID` = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            p.setId(rs.getInt("ID"));
            p.setName(rs.getString("Name"));
            p.setEmail(rs.getString("Email"));
            p.setTelephone(rs.getString("Tel"));
        }
        return p;
    }

    public static void editPassenger(Passenger p, Database database) throws SQLException {
        String update = "UPDATE `passengers` SET `Name`='" + p.getName() + "', `Email`='" + p.getEmail() + "', `Tel`='" + p.getTelephone() + "' WHERE `ID` = " + p.getId() + ";";
        database.getStatement().execute(update);
    }

    public static void deletePassenger(String id, Database database) throws SQLException {
        String delete = "DELETE FROM `passengers` WHERE `ID` = " + id + ";";
        database.getStatement().execute(delete);
    }
}
