package Employees;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddEmployee {
    public AddEmployee(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Add Employee");
        frame.setSize(750, 700);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(7, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        JLabel id = GUI.Label(String.valueOf(EmployeesDatabase.getNextID(database)));
        panel.add(id);

        panel.add(GUI.Label("Name:"));
        JTextField name = GUI.TextField();
        panel.add(name);

        panel.add(GUI.Label("Email:"));
        JTextField email = GUI.TextField();
        panel.add(email);

        panel.add(GUI.Label("Tel:"));
        JTextField tel = GUI.TextField();
        panel.add(tel);

        panel.add(GUI.Label("Salary:"));
        JTextField salary = GUI.TextField();
        panel.add(salary);

        panel.add(GUI.Label("Position:"));
        JTextField position = GUI.TextField();
        panel.add(position);

        JButton cancel = GUI.Button("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(id.getText()));
                emp.setName(name.getText());
                emp.setEmail(email.getText());
                emp.setTelephone(tel.getText());
                emp.setSalary(Double.parseDouble(salary.getText()));
                emp.setPosition(position.getText());
                try {
                    EmployeesDatabase.addEmployee(emp, database);
                    JOptionPane.showMessageDialog(frame, "Employee Added Successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                }
            }
        });
        panel.add(submit);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
