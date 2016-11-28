import javax.swing.*;
import java.awt.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public class Login {
    private JFrame jFrame;
    private JPanel jPanel = new JPanel(null);
    private JTextField user_name = new JTextField(20);
    private JTextField password = new JTextField(20);
    private JButton login = new JButton("Login");
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    private Font font2 = new Font("Roboto Light", Font.ITALIC, 15);
    private JLabel user_name_label = new JLabel("Username/Email", SwingConstants.CENTER);
    private JLabel title = new JLabel("Username/Email", SwingConstants.CENTER);
    private JLabel password_label = new JLabel("Username/Email", SwingConstants.CENTER);
    Login(JFrame jFrame) {
        
    }
}