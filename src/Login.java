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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

    private JFrame loginFrame;
    JPanel inputPanel;
    private JLabel loginLabel, accountNumberLabel, pinLabel;
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton loginButton, exitButton;

    private final static int WIDTH = 800;
    private final static int HEIGHT = 500;

    private String DB_URL = "jdbc:mysql://localhost:3306/MCKPBankingCorp";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "P@st0rp!de_030106";

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Login() {
        initialize();
    }

    public void initialize() {
        loginFrame = new JFrame();
        loginFrame.setTitle("MCKP Banking Corporation - Login");
        loginFrame.setSize(WIDTH, HEIGHT);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(2, 1));

        loginLabel = new JLabel();
        loginLabel.setText("Welcome to MCKP Banking Corporation!");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Monsterrat", Font.BOLD, 24));

        accountNumberLabel = new JLabel();
        accountNumberLabel.setText("Enter your account number: ");
        accountNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        accountNumberLabel.setFont(new Font("Monsterrat", Font.PLAIN, 18));

        accountNumberField = new JTextField();
        accountNumberField.setFont(new Font("Monsterrat", Font.PLAIN, 18));

        pinLabel = new JLabel();
        pinLabel.setText("Enter your PIN: ");
        pinLabel.setHorizontalAlignment(JLabel.CENTER);
        pinLabel.setFont(new Font("Monsterrat", Font.PLAIN, 18));

        pinField = new JPasswordField();
        pinField.setFont(new Font("Monsterrat", Font.PLAIN, 18));

        exitButton = new JButton();
        exitButton.setText("← Exit");
        exitButton.setFont(new Font("Monsterrat", Font.PLAIN, 18));
        exitButton.addActionListener(new ExitButtonHandler());

        loginButton = new JButton();
        loginButton.setText("Login >");
        loginButton.setFont(new Font("Monsterrat", Font.PLAIN, 18));
        loginButton.addActionListener(new LoginButtonHandler());

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 0, 10));
        inputPanel.add(accountNumberLabel);
        inputPanel.add(accountNumberField);
        inputPanel.add(pinLabel);
        inputPanel.add(pinField);
        inputPanel.add(exitButton);
        inputPanel.add(loginButton);

        loginFrame.getContentPane().add(loginLabel);
        loginFrame.getContentPane().add(inputPanel);

        //System.out.println(connectionEstablished(DB_URL, DB_USERNAME, DB_PASSWORD));
        //System.out.println(authenticateUser("0987-654-321", "1234"));
    }

    class LoginButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            String PIN = new String(pinField.getPassword());

            if (!authenticateUser(accountNumber, PIN)) {
                JOptionPane.showMessageDialog(null, "Account number and PIN mismatch! Please try again.");

                accountNumberField.setText("");
                pinField.setText("");
            } else {
                Dashboard d = new Dashboard();
                d.show();
            }
        }
        
    }

    class ExitButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            disposeLoginFrame();
        }

    }

    public boolean connectionEstablished(String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean authenticateUser(String accountNumber, String PIN) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement validateUser = conn.prepareStatement(
                "SELECT * FROM Customers " +
                "WHERE AccountNumber = ? AND PIN = ?"
            );

            validateUser.setString(1, accountNumber);
            validateUser.setString(2, PIN);

            ResultSet result = validateUser.executeQuery();

            if (!result.isBeforeFirst()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void show() {
        loginFrame.setVisible(true);
    }

    public void disposeLoginFrame() {
        loginFrame.dispose();
    }

}