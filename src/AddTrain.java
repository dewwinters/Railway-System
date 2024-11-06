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

        JPanel panel = new JPanel(new GridLayout(4,2,20,20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        panel.add(Label("ID: "));
        JLabel id = Label(String.valueOf(TrainsDatabase.getNextID(database)));
        panel.add(id);

        panel.add(Label("Capacity: "));
        TextField capacity = new TextField();
        panel.add(capacity);

        panel.add(Label("Description: "));
        TextField description = new TextField();
        panel.add(description);

        Button cancel = new Button("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        Button submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Train t = new Train();
                t.setId(Integer.parseInt(id.getText()));
                t.setCapacity(Integer.parseInt(capacity.getText()));
                t.setDescription(description.getText());
                try {
                    TrainsDatabase.AddTrain(t, database);
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

    private JLabel Label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#012030"));
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JTextField TextField() {
        JTextField textField = new JTextField();
        textField.setForeground(Color.decode("#012030"));
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        return textField;
    }

    private JButton Button(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.white);
        btn.setBackground(Color.decode("#45c4b0"));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        return btn;
    }
}
