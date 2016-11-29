import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public class Register {
    private String Name, username, Password, Contact, Email, Department, Type;
    private long ID;
    private JFrame jFrame;
    private JPanel jPanel = new JPanel(null);
    private Font font = new Font("Roboto Light", Font.PLAIN, 35);
    private Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
    private Font font2 = new Font("Roboto Light", Font.ITALIC, 15);
    private JTextField name = new JTextField(20);
    private JTextField user_name = new JTextField(20);
    private JComboBox<String> department;
    private JComboBox<String> type;
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
        JLabel name_warning = new JLabel("(Empty field)");
        JLabel user_name_warning = new JLabel("(Empty field)");
        JLabel email_warning = new JLabel("(Empty field)");
        JLabel password_warning = new JLabel("(Empty field)");
        JLabel confirm_password_warning = new JLabel("(Empty field)");
        JLabel contact_warning = new JLabel("(Empty field)");
        JLabel warning = new JLabel("(Empty field)");
        JLabel department_label = new JLabel("Department", SwingConstants.CENTER);
        JLabel type_label = new JLabel("Type", SwingConstants.CENTER);
        JButton back = new JButton("Back");
        JButton submit = new JButton("Submit");

        // Setup combo box
        String[] strings = {"Electricity", "HVAC", "Audio/Video", "Security", "Housekeeping"};
        department = new JComboBox<>(strings);
        String[] strings1 = {"Staffer", "Supervisor"};
        type = new JComboBox<>(strings1);

        // Set fonts
        title.setFont(font);
        name_label.setFont(font1);
        user_name_label.setFont(font1);
        id_label.setFont(font1);
        password_label.setFont(font1);
        confirm_password_label.setFont(font1);
        contact_label.setFont(font1);
        email_label.setFont(font1);
        name_warning.setFont(font2);
        user_name_warning.setFont(font2);
        email_warning.setFont(font2);
        password_warning.setFont(font2);
        confirm_password_warning.setFont(font2);
        contact_warning.setFont(font2);
        warning.setFont(font2);
        submit.setFont(font1);
        department_label.setFont(font1);
        type_label.setFont(font1);
        back.setFont(font1);

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
        jPanel.add(name_warning);
        jPanel.add(department_label);
        jPanel.add(department);
        jPanel.add(type);
        jPanel.add(back);
        jPanel.add(type_label);
        name_warning.setVisible(false);
        jPanel.add(user_name_warning);
        user_name_warning.setVisible(false);
        jPanel.add(email_warning);
        email_warning.setVisible(false);
        jPanel.add(password_warning);
        password_warning.setVisible(false);
        jPanel.add(confirm_password_warning);
        confirm_password_warning.setVisible(false);
        jPanel.add(contact_warning);
        contact_warning.setVisible(false);
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
        type_label.setBounds(100,550,300,30);
        type.setBounds(400,550,300,30);
        department_label.setBounds(100,600,300,30);
        department.setBounds(400,600,300,30);
        submit.setBounds(400, 650, 130,50);
        back.setBounds(550, 650, 130, 50);

        // Initialize frame
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button listeners
        back.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame);
        });
        submit.addActionListener(e -> {
            flag=true;
            // Display error messages
            if (name.getText().isEmpty()) {
                name_warning.setBounds(720,200,300,30);
                name_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {name_warning.setVisible(false);}
            if (user_name.getText().isEmpty()) {
                user_name_warning.setBounds(720,300,300,30);
                user_name_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {user_name_warning.setVisible(false);}
            if (email.getText().isEmpty()) {
                email_warning.setBounds(720,350,300,30);
                email_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {email_warning.setVisible(false);}
            if (password.getPassword().length==0) {
                password_warning.setBounds(720,400,300,30);
                password_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {password_warning.setVisible(false);}
            if (confirm_password.getPassword().length==0) {
                confirm_password_warning.setBounds(720,450,300,30);
                confirm_password_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {confirm_password_warning.setVisible(false);}
            if (contact.getText().isEmpty()) {
                contact_warning.setBounds(720,500,300,30);
                contact_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {contact_warning.setVisible(false);}
            if (!Arrays.equals(password.getPassword(), confirm_password.getPassword())) {
                warning.setText("Passwords are not same!");
                warning.setBounds(720,450,300,30);
                warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {warning.setVisible(false);}

            // Form a new person
            this.Name = name.getText();
            this.username = user_name.getText();
            String string = new String(password.getPassword());
            this.Password = string;
            this.Contact = contact.getText();
            this.Email = email.getText();
            this.Type = (String)type.getSelectedItem();
            this.Department = (String)department.getSelectedItem();

            Staff temp=null;
            Supervisor temp1=null;
            if (Type.equals("Staffer")) {
                temp = new Staff(Name, ID, username, Password, Contact, Email, Department);
            } else {
                temp1 = new Supervisor(Name, ID, username, Password, Contact, Email, Department);
            }

            // Check for existing staff member
            if (Type.equals("Staffer")) {
                for (Person person : Main.Staff) {
                    if (person.getUser_name().equals(temp.getUser_name()) ||
                            person.getEmail().equals(temp.getEmail())) {
                        warning.setText("User already exists!");
                        submit.setBounds(400, 600, 200,50);
                        jFrame.setVisible(true);
                        flag=false;
                    }
                }
            }
            else {
                for (Person person : Main.supervisors) {
                    if (person.getUser_name().equals(temp1.getUser_name()) ||
                            person.getEmail().equals(temp1.getEmail())) {
                        warning.setText("User already exists!");
                        submit.setBounds(400, 600, 200,50);
                        jFrame.setVisible(true);
                        flag=false;
                    }
                }
            }

            // Create a new staff/supervisor member if everything is good to go
            if (flag) {
                if (Type.equals("Staffer")) {
                    Main.Staff.add(temp);
                    Admin.RegistrationRequest_Staff(temp);
                    for (int i=0;i<Main.supervisors.size();i++) {
                        if (Main.supervisors.get(i).getDepartment().equals(temp.getDepartment())) {
                            Main.supervisors.get(i).RegistrationRequest_Staff(temp);
                        }
                    }
                    System.out.println(Main.Staff.size());
                }
                else {
                    Main.supervisors.add(temp1);
                }
                jFrame.remove(jPanel);
                Interface.FrontScreen(jFrame);
            }
        });
    }
}