import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCustomerDialog extends JDialog {
    private JTextField custIdField;
    private JComboBox<String> counterComboBox;
    private QueueManagement queueManagement;

    public RemoveCustomerDialog(JFrame parent, QueueManagement queueManagement) {
        super(parent, "Remove Customer/Order", true);
        this.queueManagement = queueManagement;

        setSize(400, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel);

        JLabel counterLabel = new JLabel("Counter:");
        mainPanel.add(counterLabel);

        String[] counterOptions = {"Counter 1", "Counter 2", "Counter 3"};
        counterComboBox = new JComboBox<>(counterOptions);
        mainPanel.add(counterComboBox);

        JLabel custIdLabel = new JLabel("Customer ID:");
        mainPanel.add(custIdLabel);

        custIdField = new JTextField();
        mainPanel.add(custIdField);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCustomer();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        setVisible(true);
    }

    private void removeCustomer() {
        String counterName = (String) counterComboBox.getSelectedItem();
        String custIdStr = custIdField.getText().trim();

        if (counterName.isEmpty() || custIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int custId;
        try {
            custId = Integer.parseInt(custIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Customer ID. Please enter a valid number.");
            return;
        }

        queueManagement.removeCustomer(counterName, custId);
        JOptionPane.showMessageDialog(this, "Customer removed successfully.");
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            QueueManagement queueManagement = new QueueManagement();
            new RemoveCustomerDialog(frame, queueManagement);
        });
    }
}
