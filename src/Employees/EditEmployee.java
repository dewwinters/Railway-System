package Employees;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditEmployee {

    private JComboBox<String> id;
    private JTextField name;
    private JTextField email;
    private JTextField tel;
    private JTextField salary;
    private JTextField position;

    public EditEmployee(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Edit Employee");
        frame.setSize(750, 700);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(GUI.background);

        JPanel panel = new JPanel(new GridLayout(7, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID:"));
        id = GUI.JComboBox(EmployeesDatabase.getEmployeesIDs(database));
        panel.add(id);

        panel.add(GUI.Label("Name:"));
        name = GUI.TextField();
        panel.add(name);

        panel.add(GUI.Label("Email:"));
        email = GUI.TextField();
        panel.add(email);

        panel.add(GUI.Label("Tel:"));
        tel = GUI.TextField();
        panel.add(tel);

        panel.add(GUI.Label("Salary:"));
        salary = GUI.TextField();
        panel.add(salary);

        panel.add(GUI.Label("Position:"));
        position = GUI.TextField();
        panel.add(position);

        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshData(database);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    frame.dispose();
                }
            }
        });

        try {
            refreshData(database);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }

        JButton delete = GUI.Button("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    EmployeesDatabase.deleteEmployee(id.getSelectedItem().toString(), database);
                    JOptionPane.showMessageDialog(frame, "Employee Deleted Successfully.");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed.");
                    frame.dispose();
                }
            }
        });
        panel.add(delete);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(id.getSelectedItem().toString()));
                emp.setName(name.getText());
                emp.setEmail(email.getText());
                emp.setTelephone(tel.getText());
                emp.setSalary(Double.parseDouble(salary.getText()));
                emp.setPosition(position.getText());
                try {
                    EmployeesDatabase.editEmployee(emp, database);
                    JOptionPane.showMessageDialog(frame, "Employee Successfully Updated");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(submit);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void refreshData(Database database) throws SQLException {
        if (id.getSelectedItem() != null) {
            Employee emp = EmployeesDatabase.getEmployeeByID(id.getSelectedItem().toString(), database);
            name.setText(emp.getName());
            email.setText(emp.getEmail());
            tel.setText(emp.getTelephone());
            salary.setText(String.valueOf(emp.getSalary()));
            position.setText(String.valueOf(emp.getPosition()));
        }
    }
}
