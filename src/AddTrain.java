import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddTrain {
    public AddTrain(JFrame oldFrame, Database database) throws SQLException {
        JFrame frame = new JFrame("Add Train");
        frame.setSize(750, 350);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(oldFrame);
        frame.getContentPane().setBackground(Color.decode("#EBFFD8"));

        JPanel panel = new JPanel(new GridLayout(4, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(GUI.Label("ID: "));
        JLabel id = GUI.Label(String.valueOf(TrainsDatabase.getNextID(database)));
        panel.add(id);

        panel.add(GUI.Label("Capacity: "));
        TextField capacity = new TextField();
        panel.add(capacity);

        panel.add(GUI.Label("Description: "));
        TextField description = new TextField();
        panel.add(description);

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
                Train t = new Train();
                t.setId(Integer.parseInt(id.getText()));
                t.setCapacity(Integer.parseInt(capacity.getText()));
                t.setDescription(description.getText());
                try {
                    TrainsDatabase.addTrain(t, database);
                    JOptionPane.showMessageDialog(frame, "Train added successfully");
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
