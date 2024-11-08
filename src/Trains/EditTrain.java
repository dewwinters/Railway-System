package Trains;

import Main.Database;
import Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditTrain {
    public EditTrain(JFrame parent, Database database) throws SQLException {
        JFrame frame = new JFrame("Edit Train");
        frame.setSize(750, 400);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        panel.add(GUI.Label("ID:"));

        JComboBox<String> id = GUI.JComboBox(TrainsDatabase.getTrainsIDs(database));
        panel.add(id);

        panel.add(GUI.Label("Capacity:"));
        JTextField capacity = GUI.TextField();
        panel.add(capacity);

        panel.add(GUI.Label("Description:"));
        JTextField description = GUI.TextField();
        panel.add(description);

        JButton delete = GUI.Button("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TrainsDatabase.deleteTrain(id.getSelectedItem().toString(), database);
                    JOptionPane.showMessageDialog(frame, "Train has been deleted");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }
            }
        });
        panel.add(delete);

        JButton submit = GUI.Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Train t = TrainsDatabase.getTrainByID(id.getSelectedItem().toString(), database);
                    t.setCapacity(Integer.parseInt(capacity.getText()));
                    t.setDescription(description.getText());
                    TrainsDatabase.editTrain(t, database);
                    JOptionPane.showMessageDialog(frame, "Train has been updated");
                    frame.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Operation Failed");
                    frame.dispose();
                }

            }
        });
        panel.add(submit);

        id.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Train t = TrainsDatabase.getTrainByID(id.getSelectedItem().toString(), database);
                    capacity.setText(String.valueOf(t.getCapacity()));
                    description.setText(t.getDescription());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, e.toString());
                    frame.dispose();
                }
            }
        });

        if (id.getSelectedItem() != null) {
                try {
                    Train t = TrainsDatabase.getTrainByID(id.getSelectedItem().toString(), database);
                    capacity.setText(String.valueOf(t.getCapacity()));
                    description.setText(t.getDescription());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.toString());
                    frame.dispose();
                }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
