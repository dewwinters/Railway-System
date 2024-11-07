package Employees;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditEmployee {
    public EditEmployee(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Edit Employee");
        frame.setSize(750, 700);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);
    }
}
