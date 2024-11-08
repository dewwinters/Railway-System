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

    static String[] getEmployeesIDs(Database database) throws SQLException {
        ArrayList<Employee> employees = getAllEmployees(database);
        String[] array = new String[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            array[i] = String.valueOf(employees.get(i).getId());
        }
        return array;
    }

    static Employee getEmployeeByID(String id, Database database) throws SQLException {
        Employee emp = new Employee();
        String select = "SELECT `ID`, `Name`, `Email`, `Tel`, `Salary`, `Position` FROM `employees` WHERE ID = " + id + ";";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            emp.setId(rs.getInt("ID"));
            emp.setName(rs.getString("Name"));
            emp.setEmail(rs.getString("Email"));
            emp.setTelephone(rs.getString("Tel"));
            emp.setSalary(rs.getDouble("Salary"));
            emp.setPosition(rs.getString("Position"));
        }
        return emp;
    }

    static void editEmployee(Employee emp, Database database) throws SQLException {
        String update = "UPDATE `employees` SET `Name`='" + emp.getName() + "', `Email`='" + emp.getEmail() + "', `Tel`='" + emp.getTelephone() + "', `Salary`='" + emp.getSalary() + "', `Position`='" + emp.getPosition() + "' WHERE `ID` = " + emp.getId() + ";";
        database.getStatement().execute(update);
    }

    static void deleteEmployee(String id, Database database) throws SQLException {
        String delete = "DELETE FROM `employees` WHERE ID = " + id + ";";
        database.getStatement().execute(delete);
    }

    public static String[] getEmployeesNames(Database database) throws SQLException {
        ArrayList<Employee> employees = getAllEmployees(database);
        String[] array = new String[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            array[i] = employees.get(i).getName();
        }
        return array;
    }

    public static Employee getEmployeeByName(String name, Database database) throws SQLException {
        Employee emp = new Employee();
        String select = "SELECT `ID`, `Name`, `Email`, `Tel`, `Salary`, `Position` FROM `employees` WHERE Name = '" + name + "';";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            emp.setId(rs.getInt("ID"));
            emp.setName(rs.getString("Name"));
            emp.setEmail(rs.getString("Email"));
            emp.setTelephone(rs.getString("Tel"));
            emp.setSalary(rs.getDouble("Salary"));
            emp.setPosition(rs.getString("Position"));
        }
        return emp;
    }
}