import javax.swing.*;
import java.awt.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public class Login {
    private String username, Password;
    private JFrame jFrame;
    private JPanel jPanel = new JPanel(null);
    private JTextField user_name = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JButton login = new JButton("Login");
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    private Font font2 = new Font("Roboto Light", Font.ITALIC, 15);
    private JLabel user_name_label = new JLabel("Username/Email", SwingConstants.CENTER);
    private JLabel title = new JLabel("Username/Email", SwingConstants.CENTER);
    private JLabel password_label = new JLabel("Password", SwingConstants.CENTER);
    private JLabel user_name_warning = new JLabel("(Empty Field)", SwingConstants.CENTER);
    private JLabel password_warning = new JLabel("(Empty field)", SwingConstants.CENTER);
    private JLabel user_existence = new JLabel("(User does not exist)", SwingConstants.CENTER);
    private JLabel password_wrong = new JLabel("(Password does not match)", SwingConstants.CENTER);
    private boolean flag = true;
    Login(JFrame jFrame) {
        this.jFrame = jFrame;

        // Set fonts
        title.setFont(font);
        user_name_label.setFont(font1);
        password_label.setFont(font1);
        user_name_warning.setFont(font2);
        password_warning.setFont(font2);
        user_existence.setFont(font2);
        password_wrong.setFont(font2);

        // Add components to jPanel
        jPanel.add(title);
        jPanel.add(user_name_label);
        jPanel.add(password_label);
        jPanel.add(user_name_warning);
        user_name_warning.setVisible(false);
        jPanel.add(password_warning);
        password_warning.setVisible(false);
        jPanel.add(user_existence);
        user_existence.setVisible(false);
        jPanel.add(password_wrong);
        password_wrong.setVisible(false);
        jPanel.add(user_name);
        jPanel.add(password);
        jPanel.add(login);

        // Set layout
        title.setBounds(300,100,500,70);
        user_name_label.setBounds(100,200,300,30);
        user_name.setBounds(400, 200, 300, 30);
        password_label.setBounds(100, 250, 300, 30);
        password.setBounds(400, 250, 300, 30);
        login.setBounds(400, 300, 150, 40);

        // Set jFrame
        this.jFrame.add(jPanel);
        this.jFrame.setVisible(true);

        login.addActionListener(e -> {
            if (user_name.getText().isEmpty()) {
                user_name_warning.setBounds(700, 200, 300, 30);
                user_name_warning.setVisible(true);
                jFrame.setVisible(true);
                flag = false;
            } else {
                user_name_warning.setVisible(false);
                if (password.getPassword().length==0) {
                    password_warning.setBounds(700, 250, 300, 30);
                    password_warning.setVisible(true);
                    jFrame.setVisible(true);
                    flag = false;
                } else {password_warning.setVisible(false);}
                if (flag) {
                    boolean flag1 = false;
                    this.username = user_name.getText();
                    this.Password = new String(password.getPassword());

                    // Staff login
                    for (Person person:Main.Staff) {
                        if (person.getUser_name().equals(this.username)) {
                            user_existence.setVisible(false);
                            if (person.getPassword().equals(this.Password))
                            {
                                Staff.staff_login(person, jFrame);
                                flag1 = true;
                            }
                            else {
                                password_wrong.setBounds(700, 250, 300, 30);
                                password_wrong.setVisible(true);
                                jFrame.setVisible(true);
                                flag1 = true;
                            }
                        }
                        else if (person.getEmail().equals(this.username)) {
                            if (person.getPassword().equals(this.Password))
                            {
                                Staff.staff_login(person, jFrame);
                                flag1 = true;
                            }
                            else {
                                password_wrong.setBounds(700, 250, 300, 30);
                                password_wrong.setVisible(true);
                                jFrame.setVisible(true);
                                flag1 = true;
                            }
                        }
                    }

                    // Supervisor Login
                    for (Person person:Main.supervisors) {
                        if (person.getUser_name().equals(this.username)) {
                            user_existence.setVisible(false);
                            if (person.getPassword().equals(this.Password))
                            {
                                Supervisor.supervisor_login(person, jFrame);
                                flag1 = true;
                            }
                            else {
                                password_wrong.setBounds(700, 250, 300, 30);
                                password_wrong.setVisible(true);
                                jFrame.setVisible(true);
                                flag1 = true;
                            }
                        }
                        else if (person.getEmail().equals(this.username)) {
                            if (person.getPassword().equals(this.Password))
                            {
                                Supervisor.supervisor_login(person, jFrame);
                                flag1 = true;
                            }
                            else {
                                password_wrong.setBounds(700, 250, 300, 30);
                                password_wrong.setVisible(true);
                                jFrame.setVisible(true);
                                flag1 = true;
                            }
                        }
                    }

                    // Admin Login
                    if (Main.admin.getUser_name().equals(this.username)) {
                        user_existence.setVisible(false);
                        if (Main.admin.getPassword().equals(this.Password))
                        {
                            jFrame.remove(jPanel);
                            Main.admin.admin_login(jFrame);
                            flag1 = true;
                        }
                        else {
                            password_wrong.setBounds(700, 250, 300, 30);
                            password_wrong.setVisible(true);
                            jFrame.setVisible(true);
                            flag1 = true;
                        }
                    }
                    else if (Main.admin.getEmail().equals(this.username)) {
                        if (Main.admin.getPassword().equals(this.Password))
                        {
                            jFrame.remove(jPanel);
                            Main.admin.admin_login(jFrame);
                            flag1 = true;
                        }
                        else {
                            password_wrong.setBounds(700, 250, 300, 30);
                            password_wrong.setVisible(true);
                            jFrame.setVisible(true);
                            flag1 = true;
                        }
                    }

                    if (!flag1) {
                        user_existence.setBounds(700, 200, 300, 30);
                        user_existence.setVisible(true);
                        jFrame.setVisible(true);
                    }
                }
            }
            flag=true;
        });
    }
}