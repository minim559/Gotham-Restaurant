import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class UserDashboard extends JFrame {
    private QueueManagement queueManagement;
    private JPanel countersPanel;

    public UserDashboard(QueueManagement queueManagement) {
        this.queueManagement = queueManagement;
        queueManagement.setUserDashboard(this); // Set UserDashboard reference in QueueManagement

        setTitle("User Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel);

        JLabel titleLabel = new JLabel("User Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        countersPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        countersPanel.setBorder(BorderFactory.createTitledBorder("Counters"));
        mainPanel.add(countersPanel, BorderLayout.CENTER);

        updateCountersPanel(); // Initial update of counters

        JButton addCustomerButton = new JButton("Add New Customer");
        addCustomerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCustomerDialog();
            }
        });

        JButton displayDetailsButton = new JButton("Display Customer Details");
        displayDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        displayDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCustomerDetails();
            }
        });

        JButton removeButton = new JButton("Remove Customer/Order");
        removeButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCustomerOrOrder();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(displayDetailsButton);
        buttonPanel.add(removeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateCountersPanel() {
        countersPanel.removeAll();

        countersPanel.add(createCounterPanel("Counter 1", queueManagement.getCounter1()));
        countersPanel.add(createCounterPanel("Counter 2", queueManagement.getCounter2()));
        countersPanel.add(createCounterPanel("Counter 3", queueManagement.getCounter3()));

        countersPanel.revalidate();
        countersPanel.repaint();
    }

    private JPanel createCounterPanel(String counterName, Queue<CustomerInformation> counter) {
        JPanel counterPanel = new JPanel(new BorderLayout());
        counterPanel.setBorder(BorderFactory.createTitledBorder(counterName));

        JTextArea counterArea = new JTextArea();
        counterArea.setEditable(false);
        counterArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(counterArea);
        counterPanel.add(scrollPane, BorderLayout.CENTER);

        if (!counter.isEmpty()) {
            for (CustomerInformation customer : counter) {
                counterArea.append(customer.toString() + "\n");
            }
        } else {
            counterArea.append("No customers in this counter.\n");
        }

        return counterPanel;
    }

    private void showAddCustomerDialog() {
        AddCustomerDialog addCustomerDialog = new AddCustomerDialog(this, queueManagement);
        addCustomerDialog.setVisible(true);
    }

    private void displayCustomerDetails() {
        String input = JOptionPane.showInputDialog(this, "Enter Customer ID:");
        if (input != null && !input.isEmpty()) {
            try {
                int custId = Integer.parseInt(input);
                CustomerInformation customer = queueManagement.findCustomerById(custId);
                if (customer != null) {
                    JOptionPane.showMessageDialog(this, customer.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid Customer ID.");
            }
        }
    }

    private void removeCustomerOrOrder() {
        RemoveCustomerDialog removeCustomerDialog = new RemoveCustomerDialog(this, queueManagement);
        removeCustomerDialog.setVisible(true);
    }

    public static void main(String[] args) {
        QueueManagement queueManagement = new QueueManagement();
        SwingUtilities.invokeLater(() -> new UserDashboard(queueManagement));
    }
}
