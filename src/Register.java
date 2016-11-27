import javax.swing.*;
import java.awt.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public class Register {
    private String Name, username, Password, Contact, Email;
    private long ID;
    private JFrame jFrame;
    private JPanel jPanel = new JPanel(null);
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    private JTextField name = new JTextField(20);
    private JTextField user_name = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JPasswordField confirm_password = new JPasswordField(20);
    private JTextField contact = new JTextField(20);
    private JTextField email = new JTextField(20);
    private JTextField id = new JTextField(20);
    private boolean flag=true;
    Register(JFrame jFrame) {
        this.jFrame = jFrame;
        this.ID = Main.id;
        Main.id++;
        GUI();
    }
    public void GUI() {
        // Panel Setup
        JLabel title = new JLabel("Registration Details", SwingConstants.CENTER);
        JLabel name_label = new JLabel("Name ", SwingConstants.CENTER);
        JLabel id_label = new JLabel("ID "+ID, SwingConstants.CENTER);
        JLabel user_name_label = new JLabel("Username ", SwingConstants.CENTER);
        JLabel password_label = new JLabel("Password ", SwingConstants.CENTER);
        JLabel confirm_password_label = new JLabel("Confirm password ", SwingConstants.CENTER);
        JLabel contact_label = new JLabel("Contact ", SwingConstants.CENTER);
        JLabel email_label = new JLabel("E-mail ", SwingConstants.CENTER);
        JLabel warning = new JLabel("Please fill the empty field(s) to continue registration");
        JButton submit = new JButton("Submit");

        // Set width of text fields
        name.setColumns(20);
        user_name.setColumns(20);
        password.setColumns(20);
        confirm_password.setColumns(20);
        contact.setColumns(20);
        email.setColumns(20);

        // Set label sizes
        title.setPreferredSize(new Dimension(300,70));
        name_label.setPreferredSize(new Dimension(300, 70));
        id_label.setPreferredSize(new Dimension(300, 70));
        user_name_label.setPreferredSize(new Dimension(300, 70));
        password_label.setPreferredSize(new Dimension(300, 70));
        confirm_password_label.setPreferredSize(new Dimension(300, 70));
        contact_label.setPreferredSize(new Dimension(300, 70));
        email_label.setPreferredSize(new Dimension(300, 70));
        warning.setPreferredSize(new Dimension(300, 70));
        submit.setPreferredSize(new Dimension(200,50));
        submit.setPreferredSize(submit.getPreferredSize());

        // Set fonts
        title.setFont(font);
        name_label.setFont(font1);
        user_name_label.setFont(font1);
        id_label.setFont(font1);
        password_label.setFont(font1);
        confirm_password_label.setFont(font1);
        contact_label.setFont(font1);
        email_label.setFont(font1);
        warning.setFont(font1);
        submit.setFont(font1);

        // Set Layout
        jPanel.add(title);
        jPanel.add(name_label);
        jPanel.add(user_name_label);
        jPanel.add(password_label);
        jPanel.add(confirm_password_label);
        jPanel.add(contact_label);
        jPanel.add(id_label);
        jPanel.add(email_label);
        jPanel.add(name);
        jPanel.add(user_name);
        jPanel.add(contact);
        jPanel.add(password);
        jPanel.add(confirm_password);
        jPanel.add(email);
        jPanel.add(id);
        jPanel.add(submit);
        jPanel.add(warning);
        warning.setVisible(false);
        id.setText(""+ID);
        id.setEnabled(false);
        title.setBounds(300,100,500,70);
        name_label.setBounds(100,200,300,30);
        name.setBounds(400,200,300,30);
        id_label.setBounds(100,250,300,30);
        id.setBounds(400,250,300,30);
        user_name_label.setBounds(100,300,300,30);
        user_name.setBounds(400,300,300,30);
        email_label.setBounds(100,350,300,30);
        email.setBounds(400,350,300,30);
        password_label.setBounds(100,400,300,30);
        password.setBounds(400,400,300,30);
        confirm_password_label.setBounds(100,450,300,30);
        confirm_password.setBounds(400,450,300,30);
        contact_label.setBounds(100,500,300,30);
        contact.setBounds(400,500,300,30);
        warning.setBounds(200, 550, 500,50);
        submit.setBounds(400, 550, 200,50);

        // Initialize frame
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button listeners
        submit.addActionListener(e -> {
            if (name.getText().isEmpty() || user_name.getText().isEmpty() || password.getPassword().length==0
                    || confirm_password.getPassword().length==0 || contact.getText().isEmpty()) {
                warning.setVisible(true);
                submit.setBounds(400, 600, 200,50);
                jFrame.setVisible(true);
                flag=false;
            }
            if (!password.getPassword().equals(confirm_password.getPassword())) {
                warning.setText("Passwords are not same!");
                warning.setBounds(700,400,300,30);
                warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            }
            this.Name = name.getText();
            this.username = user_name.getText();
            String string = new String(password.getPassword());
            this.Password = string;
            this.Contact = contact.getText();
            this.Email = email.getText();
            System.out.println(Password);
            Person temp_staff = new Staff(Name, ID, username, Password, Contact, Email);
            for (Person person : Main.Staff) {
               if (person.getEmail().equals(temp_staff.getEmail()) || person.getContact().equals(temp_staff.getContact())) {
                   warning.setText("User already exists!");
                   submit.setBounds(400, 600, 200,50);
                   jFrame.setVisible(true);
                   flag=false;
               }
            }
            if (flag) {
                Main.Staff.add(temp_staff);
                jFrame.remove(jPanel);
                Interface frame = new Interface();
                frame.FrontScreen();
            }
        });
    }
}
