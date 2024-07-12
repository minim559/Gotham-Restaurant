import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WelcomePage extends JFrame implements ActionListener {
    private JButton startButton;
    private QueueManagement queueManagement;

    public WelcomePage() {
        queueManagement = new QueueManagement();

        setTitle("Gotham Restaurant");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/Logo.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        getContentPane().add(panel);

        JLabel welcomeLabel = new JLabel("Welcome to Gotham Restaurant");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(200, 100, 800, 50);
        panel.add(welcomeLabel);

        startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
        startButton.setBounds(400, 550, 336, 55);
        startButton.setBackground(new Color(255, 165, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(this);
        panel.add(startButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            dispose(); 

            String filename = "customers.txt";
            queueManagement.loadCustomers(filename); 
            UserDashboard userDashboard = new UserDashboard(queueManagement);
            userDashboard.setVisible(true); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage();
            }
        });
    }
}