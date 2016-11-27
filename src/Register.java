import javax.swing.*;
import java.awt.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public class Register {
    private String Name, username, Password, Contact, Email;
    private long ID;
    private JFrame jFrame;
    private JPanel jPanel = new JPanel(new GridBagLayout());
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    private JTextField name = new JTextField(40);
    private JTextField user_name = new JTextField(40);
    private JPasswordField password = new JPasswordField(40);
    private JPasswordField confirm_password = new JPasswordField(40);
    private JTextField contact = new JTextField(40);
    private JTextField email = new JTextField(40);
    private boolean flag=true;
    Register(JFrame jFrame) {
        this.jFrame = jFrame;
        this.ID = Main.id;
        Main.id++;
        GUI();
    }
    public void GUI() {
        // Panel Setup
        JLabel title = new JLabel("User Details", SwingConstants.CENTER);
        JLabel name_label = new JLabel("Name: ", SwingConstants.CENTER);
        JLabel id_label = new JLabel("ID: "+ID, SwingConstants.CENTER);
        JLabel user_name_label = new JLabel("Username: ", SwingConstants.CENTER);
        JLabel password_label = new JLabel("Password: ", SwingConstants.CENTER);
        JLabel confirm_password_label = new JLabel("Confirm password: ", SwingConstants.CENTER);
        JLabel contact_label = new JLabel("Contact: ", SwingConstants.CENTER);
        JLabel email_label = new JLabel("Contact: ", SwingConstants.CENTER);
        JLabel warning = new JLabel("Please the empty field(s) to continue registration");
        JLabel exists = new JLabel("User already exists!");
        JButton submit = new JButton("Submit");

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
        exists.setPreferredSize(new Dimension(300, 70));

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
        exists.setFont(font1);

        // Set Layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = constraints.gridy = 0;
        jPanel.add(title, constraints);
        constraints.gridy++;
        jPanel.add(name_label, constraints);
        constraints.gridx++;
        jPanel.add(name, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(id_label, constraints);
        constraints.gridy++;
        jPanel.add(user_name_label, constraints);
        constraints.gridx++;
        jPanel.add(user_name, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(password_label, constraints);
        constraints.gridx++;
        jPanel.add(password, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(confirm_password_label, constraints);
        constraints.gridx++;
        jPanel.add(confirm_password, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(email_label, constraints);
        constraints.gridx++;
        jPanel.add(email, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(contact_label, constraints);
        constraints.gridx++;
        jPanel.add(contact, constraints);
        constraints.gridy++;constraints.gridx=0;
        jPanel.add(warning, constraints);
        constraints.gridy++;
        warning.setVisible(false);
        jPanel.add(exists, constraints);
        constraints.gridy++;
        warning.setVisible(false);
        jPanel.add(submit, constraints);

        // Initialize frame
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button listeners
        submit.addActionListener(e -> {
            if (name.getText().isEmpty() || user_name.getText().isEmpty() || password.getPassword().length==0
                    || confirm_password.getPassword().length==0 || contact.getText().isEmpty()) {
                warning.setVisible(true);
                jFrame.setVisible(true);
            }
            this.Name = name.getText();
            this.username = user_name.getText();
            this.Password = password.getPassword().toString();
            this.Contact = contact.getText();
            this.Email = email.getText();
            Person temp_staff = new Staff(Name, ID, username, Password, Contact, Email);
            for (Person person : Main.Staff) {
               if (person.getEmail().equals(temp_staff.getEmail()) || person.getContact().equals(temp_staff.getContact())) {
                   exists.setVisible(true);
                   jFrame.setVisible(true);
                   flag=false;
               }
            }
            if (flag) {
                Main.Staff.add(temp_staff);
            }
            else {
                name.setText("");
                user_name.setText("");
                contact.setText("");
                password.setText("");
                confirm_password.setText("");
                email.setText("");
            }
        });
    }
}
