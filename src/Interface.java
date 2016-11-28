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
    private JFrame jFrame = new JFrame("Facility Management Service");
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    Interface() {
        try {
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Light.ttf")));
        }
        catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        jFrame.setSize(1000,1000);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void FrontScreen() {
        JPanel jPanel = new JPanel(null);
        JLabel jLabel = new JLabel("Facility Management Service", SwingConstants.CENTER);
        JLabel label = new JLabel("(For new staff member)", SwingConstants.CENTER);
        label.setFont(font1);
        label.setPreferredSize(new Dimension(200,500));
        jLabel.setPreferredSize(new Dimension(300,70));
        jLabel.setFont(font);
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        JButton exit = new JButton("Exit");
        login.setPreferredSize(new Dimension(200,50));
        register.setPreferredSize(new Dimension(200,50));
        exit.setPreferredSize(new Dimension(200,50));
        login.setFont(font1);
        register.setFont(font1);
        exit.setFont(font1);

        // Label and Button sizing
        jLabel.setPreferredSize(jLabel.getPreferredSize());
        login.setPreferredSize(login.getPreferredSize());
        register.setPreferredSize(register.getPreferredSize());
        exit.setPreferredSize(exit.getPreferredSize());

        // Panel Layout using grid bag constraints
        jPanel.add(jLabel);
        jPanel.add(login);
        jPanel.add(register);
        jPanel.add(exit);
        jLabel.setBounds(250, 100, 500, 70);
        login.setBounds(300, 200, 150, 50);
        register.setBounds(500, 200, 150, 50);
        exit.setBounds(400, 270, 150, 50);

        // JFrame setup
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button Actions
        login.addActionListener(e -> {
            jFrame.remove(jPanel);
            Login login1 = new Login(jFrame);
        });
        register.addActionListener(e -> {
            jFrame.remove(jPanel);
            Register register1 = new Register(jFrame);
        });
        exit.addActionListener(e -> System.exit(0));
    }
}
