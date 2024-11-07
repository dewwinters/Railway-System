package Employees;

import Main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDatabase {
    static void addEmployee(Employee emp, Database database) throws SQLException {
        String insert = "INSERT INTO `employees`(`ID`, `Name`, `Email`, `Tel`, `Salary`, `Position`) VALUES" +
                "('" + emp.getId() + "','" + emp.getName() + "','" + emp.getEmail() + "','" + emp.getTelephone() + "','" + emp.getSalary() + "','" + emp.getPosition() + "');";
        database.getStatement().execute(insert);
    }

    static int getNextID(Database database) throws SQLException {
        int id = 0;
        int size = getAllEmployees(database).size();
        ArrayList<Employee> employees = getAllEmployees(database);
        if (size != 0) {
            id = employees.get(size - 1).getId() + 1;
        }
        return id;
    }

    static ArrayList<Employee> getAllEmployees(Database database) throws SQLException {
        String select = "SELECT * FROM `employees`;";
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getInt("ID"));
            emp.setName(rs.getString("Name"));
            emp.setEmail(rs.getString("Email"));
            emp.setTelephone(rs.getString("Tel"));
            emp.setSalary(rs.getDouble("Salary"));
            emp.setPosition(rs.getString("Position"));
            employees.add(emp);
        }
        return employees;
    }
}