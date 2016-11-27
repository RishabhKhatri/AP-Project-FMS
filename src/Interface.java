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
        jFrame.setSize(700,600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void FrontScreen() {
        JPanel jPanel = new JPanel(new GridBagLayout());
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
        GridBagConstraints grid = new GridBagConstraints();

        // Label and Button sizing
        jLabel.setPreferredSize(jLabel.getPreferredSize());
        login.setPreferredSize(login.getPreferredSize());
        register.setPreferredSize(register.getPreferredSize());
        exit.setPreferredSize(exit.getPreferredSize());

        // Panel Layout using grid bag constraints
        grid.gridx=grid.gridy=0;
        jPanel.add(jLabel, grid);
        grid.gridy++;
        jPanel.add(login, grid);
        grid.gridy++;
        jPanel.add(register, grid);
        grid.gridy++;
        jPanel.add(exit, grid);

        // JFrame setup
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button Actions
        login.addActionListener(e -> {
            jFrame.remove(jPanel);
            new Login(jFrame);
        });
        register.addActionListener(e -> {
            jPanel.remove(jPanel);
            Register register1 = new Register(jFrame);
        });
        exit.addActionListener(e -> System.exit(0));
    }
}