import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

        loginButton = new JButton();
        loginButton.setText("Login >");
        loginButton.setFont(new Font("Monsterrat", Font.PLAIN, 18));

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
    }

    public void show() {
        loginFrame.setVisible(true);
    }

}