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
    Register(JFrame jFrame, Main main) {
        this.jFrame = jFrame;
        this.ID = Main.id;
        Main.id++;
        GUI(main);
    }
    public void GUI(Main main) {
        // Panel Setup
        jPanel.setBackground(Color.WHITE);
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
        back.setBackground(new Color(0, 153, 204));
        JButton submit = new JButton("Submit");
        submit.setBackground(new Color(0, 153, 204));

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
//        type_label.setFont(font1);
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
//        jPanel.add(type);
        jPanel.add(back);
//        jPanel.add(type_label);
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
        title.setBounds(450,100,500,70);
        name_label.setBounds(250,200,300,30);
        name.setBounds(550,200,300,30);
        id_label.setBounds(250,250,300,30);
        id.setBounds(550,250,300,30);
        user_name_label.setBounds(250,300,300,30);
        user_name.setBounds(550,300,300,30);
        email_label.setBounds(250,350,300,30);
        email.setBounds(550,350,300,30);
        password_label.setBounds(250,400,300,30);
        password.setBounds(550,400,300,30);
        confirm_password_label.setBounds(250,450,300,30);
        confirm_password.setBounds(550,450,300,30);
        contact_label.setBounds(250,500,300,30);
        contact.setBounds(550,500,300,30);
        department_label.setBounds(250,550,300,30);
        department.setBounds(550,550,300,30);
        department.setBackground(new Color(255, 255, 255));
        submit.setBounds(550, 600, 130,50);
        back.setBounds(700, 600, 130, 50);

        // Initialize frame
        jFrame.add(jPanel);
        jFrame.setVisible(true);

        // Button listeners
        back.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);
        });
        submit.addActionListener(e -> {
            flag=true;
            // Display error messages
            if (name.getText().isEmpty()) {
                name_warning.setBounds(870,200,300,30);
                name_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {name_warning.setVisible(false);}
            if (user_name.getText().isEmpty()) {
                user_name_warning.setBounds(870,300,300,30);
                user_name_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {user_name_warning.setVisible(false);}
            if (email.getText().isEmpty()) {
                email_warning.setBounds(870,350,300,30);
                email_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {email_warning.setVisible(false);}
            if (password.getPassword().length==0) {
                password_warning.setBounds(870,400,300,30);
                password_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {password_warning.setVisible(false);}
            if (confirm_password.getPassword().length==0) {
                confirm_password_warning.setBounds(870,450,300,30);
                confirm_password_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {confirm_password_warning.setVisible(false);}
            if (contact.getText().isEmpty()) {
                contact_warning.setBounds(870,500,300,30);
                contact_warning.setVisible(true);
                jFrame.setVisible(true);
                flag=false;
            } else {contact_warning.setVisible(false);}
            if (!Arrays.equals(password.getPassword(), confirm_password.getPassword())) {
                warning.setText("Passwords are not same!");
                warning.setBounds(870,450,300,30);
                password.setText("");
                confirm_password.setText("");
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
            temp = new Staff(Name, ID, username, Password, Contact, Email, Department);

            // Check for existing staff member
            for (int i=0;i<main.Staff.size();i++) {
                if (temp.getUser_name().equals(main.Staff.get(i).getUser_name()) ||
                        temp.getEmail().equals(main.Staff.get(i).getEmail())) {
                    JOptionPane.showMessageDialog(null, "User already exists. Please choose another username/email.");
                    user_name.setText("");
                    email.setText("");
                    jFrame.setVisible(true);
                    flag=false;
                }
            }
            if (flag)
                for (int i=0;i<main.supervisors.size();i++) {
                    if (temp.getUser_name().equals(main.supervisors.get(i).getUser_name()) ||
                            temp.getEmail().equals(main.supervisors.get(i).getEmail())) {
                        JOptionPane.showMessageDialog(null, "User already exists. Please choose another username/email.");
                        user_name.setText("");
                        email.setText("");
                        jFrame.setVisible(true);
                        flag=false;
                    }
                }
            if (flag)
                if (temp.getUser_name().equals(main.admin.getUser_name()) ||
                        temp.getEmail().equals(main.admin.getEmail())) {
                    JOptionPane.showMessageDialog(null, "User already exists. Please choose another username/email.");
                    user_name.setText("");
                    email.setText("");
                    jFrame.setVisible(true);
                    flag=false;
                }

            // Create a new staff member if everything is good to go
            if (flag) {
                if (Type.equals("Staffer")) {
                    main.Staff.add(temp);
                    main.admin.RegistrationRequest_Staff(temp, main);
                    for (int i=0;i<main.supervisors.size();i++) {
                        if (main.supervisors.get(i).getDepartment().equals(temp.getDepartment())) {
                            main.supervisors.get(i).RegistrationRequest_Staff(temp, main);
                        }
                    }
                    // Write to database
                    main.writeAdmin();
                    main.writeStaff();
                    main.writeSupervisors();
                }
                jFrame.remove(jPanel);
                Interface.FrontScreen(jFrame, main);
            }
        });
    }
}