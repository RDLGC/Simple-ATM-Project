import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration {

    private JFrame registrationFrame;
    private JPanel inputPanel;
    private JLabel headerLabel, accountNameLabel;
    private JTextField accountNameField;
    private JButton registerButton, goBackButton;

    private final static int WIDTH = 800;
    private final static int HEIGHT = 500;

    private final String DB_URL = "jdbc:mysql://localhost:3306/MCKPBankingCorp";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "P@st0rp!de_030106";

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Registration() {
        initialize();
    }
    
    public void initialize() {
        registrationFrame = new JFrame();
        registrationFrame.setTitle("MCKP Banking Corporation - Registration");
        registrationFrame.setSize(WIDTH, HEIGHT);
        registrationFrame.setResizable(false);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setLayout(new GridLayout(2, 1));

        headerLabel = new JLabel();
        headerLabel.setText("Register Here ↓");
        headerLabel.setFont(new Font("Monsterrat", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        accountNameLabel = new JLabel();
        accountNameLabel.setText("Please enter your account name (e.g., Juan Dela Cruz): ");
        accountNameLabel.setFont(new Font("Monsterrat", Font.PLAIN, 15));
        accountNameLabel.setHorizontalAlignment(JLabel.LEADING);

        accountNameField = new JTextField();
        accountNameField.setFont(new Font("Monsterrat", Font.PLAIN, 18));

        registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.addActionListener(new RegisterButtonHandler());

        goBackButton = new JButton();
        goBackButton.setText("Go Back");

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 0, 10));
        inputPanel.add(accountNameLabel);
        inputPanel.add(accountNameField);
        inputPanel.add(goBackButton);
        inputPanel.add(registerButton);

        registrationFrame.getContentPane().add(headerLabel);
        registrationFrame.getContentPane().add(inputPanel);
    }

    class RegisterButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountName = accountNameField.getText();

            if (accountName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please enter an account name..");
            } else {
                registerRequest(accountName);
                JOptionPane.showMessageDialog(null, "Registration successful!" +
                " Kindly wait for the administrator to approve your request.");
                disposeRegistrationFrame();
            }
        }
        
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void registerRequest(String accountName) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement insertUser = conn.prepareStatement(
                "INSERT INTO Customers (AccountName)" +
                "VALUES (?)"
            );

            insertUser.setString(1, accountName);
            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        registrationFrame.setVisible(true);
    }

    public void disposeRegistrationFrame() {
        registrationFrame.dispose();
    }
}
