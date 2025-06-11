import javax.swing.JFrame;

public class Dashboard {

    JFrame dashboardFrame;

    private final static int WIDTH =  800;
    private final static int HEIGHT = 500;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Dashboard() {
        initialize();
    }

    public void initialize() {
        dashboardFrame = new JFrame();
        dashboardFrame.setSize(WIDTH, HEIGHT);
        dashboardFrame.setResizable(false);
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setLayout(null);
    }

    public void show() {
        dashboardFrame.setVisible(true);
    }
}
