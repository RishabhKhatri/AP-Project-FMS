import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Supervisor extends Person {
    private String Department;
    private ArrayList<Task_staff> tasks = new ArrayList<>();
    private ArrayList<SupervisorTask> mytasks = new ArrayList<>();
    private ArrayList<Leave> leave_staff = new ArrayList<>();
    private ArrayList<Leave> myLeaves = new ArrayList<>();
    private ArrayList<Logistics> logisticsArrayList = new ArrayList<>();
    private ArrayList<Staff> registration_staff = new ArrayList<>();
    private ArrayList<TaskReport> myReports = new ArrayList<>();
    Supervisor(String Name, long ID, String user_name, String Password, String Contact, String Email, String Department) {
        super(Name, ID, user_name, Password, Contact, Email);
        this.Department = Department;
    }

    public void RegistrationRequest_Staff(Staff person, Main main) {
        registration_staff.add(person);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }

    public String getDepartment() {
        return Department;
    }

    public void supervisor_login(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel welcome_label = new JLabel("Welcome to Facility Management Service", SwingConstants.CENTER);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        // Set Fonts
        name_label.setFont(font2);
        welcome_label.setFont(font);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(welcome_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);
        welcome_label.setBounds(400, 300, 800, 70);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void logistics(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        int j=100;
        JLabel[] request_list = new JLabel[logisticsArrayList.size()];
        for (int i=0;i<logisticsArrayList.size();i++) {
            request_list[i] = new JLabel(logisticsArrayList.get(i).list_string());
//            request_list[i].setBorder(border);
            request_list[i].setFont(font2);
            jPanel.add(request_list[i]);
            request_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }
        j=150;
        JButton[] approve_buttons = new JButton[logisticsArrayList.size()];
        for (int i=0;i<logisticsArrayList.size();i++) {
            approve_buttons[i] = new JButton("Approve");
            approve_buttons[i].setBackground(new Color(0, 153, 204));
            approve_buttons[i].setFont(font2);
            jPanel.add(approve_buttons[i]);
            approve_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=210;
        JButton[] reject_buttons = new JButton[logisticsArrayList.size()];
        for (int i=0;i<logisticsArrayList.size();i++) {
            reject_buttons[i] = new JButton("Reject");
            reject_buttons[i].setBackground(new Color(255, 51, 0));
            reject_buttons[i].setFont(font2);
            jPanel.add(reject_buttons[i]);
            reject_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons

        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        for (int i = 0; i< approve_buttons.length; i++) {
            final int temp = i;
            approve_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                logisticsArrayList.get(temp).setValid(true);
                logisticsArrayList.get(temp).setValid(true);
                jFrame.remove(jPanel);
                request(jFrame, main);
            });
        }
        for (int i = 0; i< reject_buttons.length; i++) {
            final int temp = i;
            reject_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                logisticsArrayList.remove(temp);
                jFrame.remove(jPanel);
                request(jFrame, main);
            });
        }
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void reports(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<main.Staff.size();i++) {
            if (this.getDepartment().equals(main.Staff.get(i).getDepartment()) && main.Staff.get(i).isValid()) {
                department_list.add(main.Staff.get(i));
            }
        }

        JLabel[] report_list = new JLabel[myReports.size()];
        int j=100;
        for (int i=0;i<myReports.size();i++) {
            report_list[i] = new JLabel(myReports.get(i).list_string());
//            report_list[i].setBorder(border);
            report_list[i].setFont(font2);
            jPanel.add(report_list[i]);
            report_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void staff_list(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<main.Staff.size();i++) {
            if (this.getDepartment().equals(main.Staff.get(i).getDepartment()) && main.Staff.get(i).isValid()) {
                department_list.add(main.Staff.get(i));
            }
        }

        JLabel[] staff_list = new JLabel[department_list.size()];
        int j=100;
        for (int i=0;i<department_list.size();i++) {
            staff_list[i] = new JLabel(department_list.get(i).list_string());
//            staff_list[i].setBorder(border);
            staff_list[i].setFont(font2);
            jPanel.add(staff_list[i]);
            staff_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }
        j=150;
        JButton[] delete_buttons = new JButton[department_list.size()];
        for (int i=0;i<department_list.size();i++) {
            delete_buttons[i] = new JButton("Delete");
            delete_buttons[i].setBackground(new Color(255, 51, 0));
            delete_buttons[i].setFont(font2);
            jPanel.add(delete_buttons[i]);
            delete_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        for (int i=0;i<delete_buttons.length;i++) {
            final int temp = i;
            delete_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                removeStaff(department_list.get(temp), main);
                jFrame.remove(jPanel);
                staff_list(jFrame, main);
            });
        }
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void request(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        JLabel signup_label = new JLabel("Signup Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] request_list = new JLabel[registration_staff.size()];
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        int j=100;
        for (int i=0;i<registration_staff.size();i++) {
            request_list[i] = new JLabel(registration_staff.get(i).list_string());
//            request_list[i].setBorder(border);
            request_list[i].setFont(font2);
            jPanel.add(request_list[i]);
            request_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }
        j=150;
        JButton[] approve_signup_buttons = new JButton[registration_staff.size()];
        for (int i=0;i<registration_staff.size();i++) {
            approve_signup_buttons[i] = new JButton("Approve");
            approve_signup_buttons[i].setBackground(new Color(0, 153, 204));
            approve_signup_buttons[i].setFont(font2);
            jPanel.add(approve_signup_buttons[i]);
            approve_signup_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=210;
        JButton[] reject_signup_buttons = new JButton[registration_staff.size()];
        for (int i=0;i<registration_staff.size();i++) {
            reject_signup_buttons[i] = new JButton("Reject");
            reject_signup_buttons[i].setBackground(new Color(255, 51, 0));
            reject_signup_buttons[i].setFont(font2);
            jPanel.add(reject_signup_buttons[i]);
            reject_signup_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons

        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        for (int i = 0; i< approve_signup_buttons.length; i++) {
            final int temp = i;
            approve_signup_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                main.Staff.get(temp).setValid(true);
                registration_staff.remove(temp);

                // Write to database
                main.writeAdmin();
                main.writeStaff();
                main.writeSupervisors();
                jFrame.remove(jPanel);
                request(jFrame, main);
            });
        }
        for (int i = 0; i< reject_signup_buttons.length; i++) {
            final int temp = i;
            reject_signup_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                main.Staff.remove(temp);
                registration_staff.remove(temp);

                // Write to database
                main.writeAdmin();
                main.writeStaff();
                main.writeSupervisors();
                jFrame.remove(jPanel);
                request(jFrame, main);
            });
        }
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void removeStaff(Staff staff, Main main) {
        for (int i=0;i<main.Staff.size();i++) {
            if (main.Staff.get(i).equals(staff)) {
                main.Staff.remove(i);

                // Write to database
                main.writeAdmin();
                main.writeStaff();
                main.writeSupervisors();
            }
        }
    }
    public void Tasks_action(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] task_list = new JLabel[tasks.size()];
        JButton new_task = new JButton("New Task");
        JButton leave_request = new JButton("Leave Request");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<main.Staff.size();i++) {
            if (this.getDepartment().equals(main.Staff.get(i).getDepartment()) && main.Staff.get(i).isValid()
                    && !(main.Staff.get(i).isTask_assigned())) {
                department_list.add(main.Staff.get(i));
            }
        }

        int j=200;
        for (int i=0;i<tasks.size();i++) {
            task_list[i] = new JLabel(tasks.get(i).list_string());
//            task_list[i].setBorder(border);
            task_list[i].setFont(font2);
            jPanel.add(task_list[i]);
            task_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(new_task);
        jPanel.add(leave_request);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);
        new_task.setBounds(160, 50, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        new_task.setBackground(new Color(0, 153, 204));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        new_task.addActionListener(actionEvent -> {
            if (department_list.size()==0) {
                JOptionPane.showMessageDialog(null, "Sorry, no staff members are available!");
                jFrame.remove(jPanel);
                Tasks_action(jFrame, main);
            }
            else {
                int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of staff members required (1-5)"));
                if (number>department_list.size()) {
                    JOptionPane.showMessageDialog(null, "Sorry, only "+number+" are available!");
                    jFrame.remove(jPanel);
                    Tasks_action(jFrame, main);
                }
                else {
                    JFrame jFrame1 = new JFrame("Assign Task");
                    JLabel title = new JLabel("Task Form");
                    JLabel description_label = new JLabel("Description(Not more than 40 letters)");
                    JLabel name_label1 = new JLabel("Name");
                    JLabel equipments_label = new JLabel("Equipments");
                    JLabel deadline_label = new JLabel("Deadline");
                    JLabel supervisor_label = new JLabel("Staffers required");
                    JButton submit = new JButton("Assign Task");

                    title.setFont(font);
                    description_label.setFont(font2);
                    name_label1.setFont(font2);
                    equipments_label.setFont(font2);
                    deadline_label.setFont(font2);
                    supervisor_label.setFont(font2);
                    submit.setBackground(new Color(0, 153, 204));

                    JPanel jPanel1 = new JPanel(null);
                    jPanel1.setBackground(new Color(255, 255, 255));

                    JTextField Description = new JTextField(40);
                    JTextField Name = new JTextField(20);
                    JTextField Equipments = new JTextField(40);
                    JTextField Deadline = new JTextField(8);
                    String[] strings = new String[department_list.size()];
                    for (int i=0;i<department_list.size();i++) {
                        strings[i] = department_list.get(i).getName();
                    }
                    JComboBox<String>[] jComboBoxes = new JComboBox[number];
                    for (int i=0;i<number;i++) {
                        jComboBoxes[i] = new JComboBox<>(strings);
                        jComboBoxes[i].setBackground(new Color(255, 255, 255));
                    }

                    // setup panel
                    jPanel1.add(deadline_label);
                    jPanel1.add(description_label);
                    jPanel1.add(name_label1);
                    jPanel1.add(equipments_label);
                    jPanel1.add(supervisor_label);
                    jPanel1.add(Description);
                    jPanel1.add(Name);
                    jPanel1.add(Equipments);
                    jPanel1.add(Deadline);
                    jPanel1.add(submit);
                    for (int i=0;i<number;i++) {
                        jPanel1.add(jComboBoxes[i]);
                    }

                    // layout
                    title.setBounds(300,100,500,70);
                    name_label1.setBounds(100,200,300,30);
                    Name.setBounds(400 , 200, 300,30);
                    description_label.setBounds(100,250,300,30);
                    Description.setBounds(400,250,300,30);
                    equipments_label.setBounds(100,300,300,30);
                    Equipments.setBounds(400,300,300,30);
                    deadline_label.setBounds(100,350,300,30);
                    Deadline.setBounds(400,350,300,30);
                    supervisor_label.setBounds(100,400,300,30);
                    int k=400;
                    for (int i=0;i<number;i++) {
                        jComboBoxes[i].setBounds(400, k, 200, 30);
                        k+=50;
                    }
                    submit.setBounds(400,k,300,30);

                    // Setup frame
                    jFrame1.add(jPanel1);
                    jFrame1.setSize(1000,1000);
                    jFrame1.setLocationRelativeTo(null);
                    jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jFrame1.setVisible(true);

                    // button listeners
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if ((Description.getText().isEmpty() || Name.getText().isEmpty() || Equipments.getText().isEmpty() ||
                                    Deadline.getText().isEmpty())) {
                                JOptionPane.showMessageDialog(null, "Some of the field(s) left empty!");
                                jFrame1.setVisible(false);
                                jFrame.remove(jPanel);
                                Tasks_action(jFrame, main);
                            }
                            else {
                                String[] strings1 = new String[number];
                                for (int i=0;i<number;i++) {
                                    strings1[i] = (String)jComboBoxes[i].getSelectedItem();
                                }
                                ArrayList<Staff> arrayList = new ArrayList<Staff>();
                                for (int i=0;i<department_list.size();i++) {
                                    for (int j=0;j<number;j++) {
                                        if (department_list.get(i).getName().equals(strings1[j])) {
                                            arrayList.add(department_list.get(i));
                                        }
                                    }
                                }
                                for (int i=0;i<arrayList.size();i++) {
                                    arrayList.get(i).addTask(new Task_staff(Description.getText(), Name.getText(), "Not Started",
                                            Equipments.getText(),
                                            Deadline.getText(), arrayList), main);
                                    arrayList.get(i).setTask_assigned(true);
                                }
                                tasks.add(new Task_staff(Description.getText(), Name.getText(), "Not Started", Equipments.getText(),
                                        Deadline.getText(), arrayList));
                                jFrame1.setVisible(false);
                                jFrame.remove(jPanel);
                                Tasks_action(jFrame, main);
                            }
                        }
                    });
                }
            }
        });
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void leave_request(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Admin)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        JButton leave_request = new JButton("Leave Request");
        JLabel leave_request_label = new JLabel("Leave Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        JLabel[] request_list = new JLabel[leave_staff.size()];
        int j=100;
        for (int i=0;i<leave_staff.size();i++) {
            request_list[i] = new JLabel(leave_staff.get(i).list_string());
//            request_list[i].setBorder(border);
            request_list[i].setFont(font2);
            jPanel.add(request_list[i]);
            request_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }
        j=150;
        JButton[] approve_buttons = new JButton[leave_staff.size()];
        for (int i=0;i<leave_staff.size();i++) {
            approve_buttons[i] = new JButton("Approve");
            approve_buttons[i].setBackground(new Color(0, 153, 204));
            approve_buttons[i].setFont(font2);
            jPanel.add(approve_buttons[i]);
            approve_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=210;
        JButton[] reject_buttons = new JButton[leave_staff.size()];
        for (int i=0;i<leave_staff.size();i++) {
            reject_buttons[i] = new JButton("Reject");
            reject_buttons[i].setBackground(new Color(730, 51, 0));
            reject_buttons[i].setFont(font2);
            jPanel.add(reject_buttons[i]);
            reject_buttons[i].setBounds(1260, j, 100, 50);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request_label.setFont(font2);
        leave_request.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);
        jPanel.add(leave_request_label);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons
        for (int i = 0; i< approve_buttons.length; i++) {
            final int temp = i;
            approve_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                leave_staff.get(temp).setValid(true);
                leave_staff.remove(temp);
                jFrame.remove(jPanel);
                leave_request(jFrame, main);
            });
        }
        for (int i = 0; i< reject_buttons.length; i++) {
            final int temp = i;
            reject_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                leave_staff.remove(temp);
                jFrame.remove(jPanel);
                leave_request(jFrame, main);
            });
        }
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void accept_leave_request(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Supervisor)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton leave_request = new JButton("Leave Request");
        JButton new_leave = new JButton("Apply for leave");
        JButton my_leaves = new JButton("My Leaves");
        my_leaves.setFont(font2);
        my_leaves.setBounds(1, 60, 150, 50);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<main.Staff.size();i++) {
            if (this.getDepartment().equals(main.Staff.get(i).getDepartment()) && main.Staff.get(i).isValid()) {
                department_list.add(main.Staff.get(i));
            }
        }

        JLabel[] leave_list = new JLabel[myLeaves.size()];
        int j=200;
        for (int i=0;i<myLeaves.size();i++) {
            leave_list[i] = new JLabel(myLeaves.get(i).list_string());
//            leave_list[i].setBorder(border);
            leave_list[i].setFont(font2);
            jPanel.add(leave_list[i]);
            leave_list[i].setBounds(300, j, 700, 200);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Staff_list.setFont(font2);
        Logistics.setFont(font2);
        Reports.setFont(font2);
        Requests.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        leave_request.setFont(font2);
        new_leave.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Staff_list);
        jPanel.add(Logistics);
        jPanel.add(Requests);
        jPanel.add(Reports);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(leave_request);
        jPanel.add(new_leave);

        // Set Layout of components
        name_label.setBounds(1,1,150,50);
        date_label.setBounds(160, 1, 150, 50);
        time_label.setBounds(320, 1, 100, 50);
        Home.setBounds(1,50,150,50);
        Staff_list.setBounds(1,100,150,50);
        Logistics.setBounds(1,150,150,50);
        Reports.setBounds(1,200,150,50);
        Requests.setBounds(1,250,150,50);
        leave_request.setBounds(1, 300, 150, 50);
        Tasks.setBounds(1, 350, 150, 50);
        my_leaves.setBounds(1, 400, 150, 50);
        new_leave.setBounds(160, 50, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1, 450, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        my_leaves.setBackground(new Color(51, 204, 255));
        new_leave.setBackground(new Color(0, 153, 204));
        logout.setBackground(new Color(255, 51, 0));
        logout.setFont(font2);
        jPanel.add(logout);

        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame, main);

            // Write to database
            main.writeAdmin();
            main.writeStaff();
            main.writeSupervisors();
        });
        new_leave.addActionListener(e -> {
            JFrame jFrame1 = new JFrame("Application for leave");
            JPanel jPanel1 = new JPanel(null);
            jPanel1.setBackground(Color.WHITE);
            JLabel title = new JLabel("Application for leave");
            JLabel toWhom_label = new JLabel("Apply to");
            JLabel reason_label = new JLabel("Reason");
            JLabel from_label = new JLabel("From (Date)");
            JLabel to_label = new JLabel("To (Date)");
            JTextField reason = new JTextField();
            JTextField from = new JTextField();
            JTextField to = new JTextField();
            JTextField toWhom = new JTextField();
            JButton submit = new JButton("Submit leave");

            title.setFont(font);
            toWhom_label.setFont(font2);
            from_label.setFont(font2);
            to_label.setFont(font2);
            reason.setFont(font2);
            from.setFont(font2);
            to.setFont(font2);
            submit.setFont(font2);
            reason_label.setFont(font2);
            toWhom.setFont(font2);
            submit.setBackground(new Color(0, 253, 204));

            jPanel1.add(title);
            jPanel1.add(toWhom_label);
            jPanel1.add(from_label);
            jPanel1.add(to_label);
            jPanel1.add(reason);
            jPanel1.add(from);
            jPanel1.add(to);
            jPanel1.add(submit);
            jPanel1.add(reason_label);
            jPanel1.add(toWhom);
            toWhom.setEnabled(false);
            toWhom.setText("Admin");

            title.setBounds(300,100,500,70);
            toWhom_label.setBounds(100,200,300,30);
            toWhom.setBounds(400,200,300,30);
            reason_label.setBounds(100,250,300,30);
            reason.setBounds(400,250,300,30);
            from_label.setBounds(100,300,300,30);
            from.setBounds(400,300,300,30);
            to_label.setBounds(100,350,300,30);
            to.setBounds(400,350,300,30);
            submit.setBounds(400, 400, 150, 30);

            jFrame1.add(jPanel1);
            jFrame1.setSize(1000,1000);
            jFrame1.setLocationRelativeTo(null);
            jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame1.setVisible(true);

            submit.addActionListener((ActionEvent actionEvent) -> {
                if (reason.getText().isEmpty() || from.getText().isEmpty() || to.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Some of the field(s) left empty!");
                    jFrame.remove(jPanel);
                    accept_leave_request(jFrame, main);
                }
                Leave temp=null;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    temp = new Leave("admin", reason.getText(), format.parse(from.getText()), format.parse(to.getText()));
                }
                catch (ParseException a) {
                    a.printStackTrace();
                }
                myLeaves.add(temp);
                main.admin.addLeaves(temp, main);
                jFrame1.setVisible(false);
                jFrame.remove(jPanel);
                accept_leave_request(jFrame, main);
            });
        });
        // Button Listeners
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame, main);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame, main);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            reports(jFrame, main);
        });
        Requests.addActionListener(e -> {
            jFrame.remove(jPanel);
            request(jFrame, main);
        });
        leave_request.addActionListener(e -> {
            jFrame.remove(jPanel);
            leave_request(jFrame, main);
        });
    }
    public void addTask(SupervisorTask task, Main main) {
        mytasks.add(task);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }
    public void addLeave(Leave leave, Main main) {
        leave_staff.add(leave);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }
    public void addLogistics(Logistics logistics, Main main) {
        logisticsArrayList.add(logistics);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }
}
