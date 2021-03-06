import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by rishabh on 27/11/16.
 */
public class Interface {
    // Declarations
    private static Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private static Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    Interface(Main main) {
        JFrame jFrame = new JFrame("Facility Management Service");
        try {
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));
        }
        catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        jFrame.setSize(1400,1000);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrontScreen(jFrame, main);
    }
    public static void FrontScreen(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        jPanel.setBackground(Color.WHITE);
        JLabel jLabel = new JLabel("Facility Management Service", SwingConstants.CENTER);
        JLabel label = new JLabel("(For new staff member)", SwingConstants.CENTER);
        label.setFont(font1);
        jLabel.setFont(font);
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        JButton exit = new JButton("Exit");
        login.setFont(font1);
        login.setBackground(new Color(0, 153, 204));
        login.setOpaque(true);
        register.setFont(font1);
        register.setBackground(new Color(0, 153, 204));
        register.setOpaque(true);
        exit.setFont(font1);
        exit.setBackground(new Color(255, 51, 0));
        exit.setOpaque(true);

        // Panel Layout using grid bag constraints
        jPanel.add(jLabel);
        jPanel.add(login);
        jPanel.add(register);
        jPanel.add(exit);
        jLabel.setBounds(450, 200, 500, 70);
        login.setBounds(520, 300, 150, 50);
        register.setBounds(690, 300, 150, 50);
        exit.setBounds(600, 370, 150, 50);

        // JFrame setup
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button Actions
        login.addActionListener(e -> {
            jFrame.remove(jPanel);
            Login login1 = new Login(jFrame, main);
        });
        register.addActionListener(e -> {
            jFrame.remove(jPanel);
            Register register1 = new Register(jFrame, main);
        });
        exit.addActionListener(e -> System.exit(0));
    }
}
