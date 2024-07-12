import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerDialog extends JDialog {
    private JTextField idField, nameField, orderItemField, orderAmountField;
    private JComboBox<String> counterComboBox;
    private QueueManagement queueManagement;

    public AddCustomerDialog(JFrame parent, QueueManagement queueManagement) {
        super(parent, "Add New Customer", true);
        this.queueManagement = queueManagement;

        setSize(400, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel);

        mainPanel.add(new JLabel("Customer ID:"));
        idField = new JTextField();
        mainPanel.add(idField);

        mainPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        mainPanel.add(new JLabel("Order Item:"));
        orderItemField = new JTextField();
        mainPanel.add(orderItemField);

        mainPanel.add(new JLabel("Order Amount:"));
        orderAmountField = new JTextField();
        mainPanel.add(orderAmountField);

        mainPanel.add(new JLabel("Counter Name:"));
        String[] counterOptions = {"Counter 1", "Counter 2", "Counter 3"};
        counterComboBox = new JComboBox<>(counterOptions);
        mainPanel.add(counterComboBox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        mainPanel.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(cancelButton);

        setVisible(true);
    }

    private void addCustomer() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String orderItem = orderItemField.getText().trim();
            double orderAmount = Double.parseDouble(orderAmountField.getText().trim());
            String counter = (String) counterComboBox.getSelectedItem();

            if (name.isEmpty() || orderItem.isEmpty() || counter.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Assuming orderAmount is the totalAmount for the customer
            CustomerInformation customer = new CustomerInformation(id, name, orderAmount);
            OrderInformation order = new OrderInformation(customer.getOrders().size() + 1, orderItem, orderAmount, 1, "14:10", counter);
            customer.addOrder(order);
            queueManagement.addCustomer(counter, customer);
            JOptionPane.showMessageDialog(this, "Customer added successfully.");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID or Order Amount. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            QueueManagement queueManagement = new QueueManagement();
            new AddCustomerDialog(frame, queueManagement);
        });
    }
}
