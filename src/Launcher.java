import javax.swing.SwingUtilities;

public class Launcher {

    @SuppressWarnings("Convert2Lambda")
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Login login = new Login();
                login.show();
            }
            
        });
    }

}