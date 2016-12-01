import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Admin extends Person {
    private ArrayList<SupervisorTask> tasks = new ArrayList<>();
    public ArrayList<Leave> leave_supervisor = new ArrayList<>();
    private ArrayList<Logistics> logistics_list = new ArrayList<>();
    private ArrayList<TaskReport> myReports = new ArrayList<>();
    private ArrayList<Staff> registration_staff = new ArrayList<>();
    Admin(String Name, long ID, String user_name, String Password, String Contact, String Email) {
        super(Name, ID, user_name, Password, Contact, Email);
    }

    public void RegistrationRequest_Staff(Staff person, Main main) {
        registration_staff.add(person);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }

    public void admin_login(JFrame jFrame, Main main) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel welcome_label = new JLabel("Welcome to Facility Management Service", SwingConstants.CENTER);
        JLabel name_label = new JLabel(this.getName()+"(Admin)", SwingConstants.CENTER);
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
        jPanel.setBackground(Color.WHITE);

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

        // Set Layout of components
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);
        welcome_label.setBounds(300, 300, 800, 70);

        JButton logout = new JButton("Logout");
        logout.setBounds(1190, 1, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
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
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
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
        jPanel.setBackground(Color.WHITE);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        JLabel name_label = new JLabel(this.getName()+"(Admin)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] staff_list = new JLabel[main.Staff.size()];
        JButton leave_request = new JButton("Leave Request");

        ArrayList<Staff> valid_staff = new ArrayList<>();
        for (int i=0;i<main.Staff.size();i++) {
            if (main.Staff.get(i).isValid()) {
                valid_staff.add(main.Staff.get(i));
            }
        }

        int j=100;
        for (int i=0;i<valid_staff.size();i++) {
            staff_list[i] = new JLabel(valid_staff.get(i).list_string());
            staff_list[i].setFont(font2);
            jPanel.add(staff_list[i]);
            staff_list[i].setBounds(50, j, 700, 200);
            j+=200;
        }
        j=100;
        JButton[] delete_buttons = new JButton[valid_staff.size()];
        for (int i=0;i<valid_staff.size();i++) {
            delete_buttons[i] = new JButton("Delete");
            delete_buttons[i].setFont(font2);
            delete_buttons[i].setBackground(new Color(255, 51, 0));
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);

        JButton logout = new JButton("Logout");
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
        logout.setBounds(1190, 1, 150, 50);
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
                removeStaff(valid_staff.get(temp), main);
                jFrame.remove(jPanel);
                staff_list(jFrame, main);
            });
        }
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
    public void Tasks_action(JFrame jFrame, Main main) {

        JPanel jPanel = new JPanel(null);
        jPanel.setBackground(Color.WHITE);
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
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] task_list = new JLabel[tasks.size()];
        JButton new_task = new JButton("New Task");
        new_task.setBackground(new Color(0, 153, 204));
        JButton leave_request = new JButton("Leave Request");

        int j=200;
        for (int i=0;i<tasks.size();i++) {
            task_list[i] = new JLabel(tasks.get(i).list_string());
            task_list[i].setBorder(border);
            task_list[i].setFont(font2);
            jPanel.add(task_list[i]);
            task_list[i].setBounds(50, j, 1000, 200);
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);
        new_task.setBounds(50, 100, 150, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1190, 1, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
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
            JFrame jFrame1 = new JFrame("Assign Task");
            JLabel title = new JLabel("Task Form");
            JLabel description_label = new JLabel("Description(Not more than 40 letters)");
            JLabel name_label1 = new JLabel("Name");
            JLabel equipments_label = new JLabel("Equipments");
            JLabel deadline_label = new JLabel("Deadline");
            JLabel supervisor_label = new JLabel("Supervisor");
            JButton submit = new JButton("Assign Task");

            title.setFont(font);
            deadline_label.setFont(font2);
            name_label1.setFont(font2);
            equipments_label.setFont(font2);
            deadline_label.setFont(font2);
            supervisor_label.setFont(font2);
            submit.setBackground(new Color(0, 153, 204));
            description_label.setFont(font2);

            JPanel jPanel1 = new JPanel(null);
            jPanel1.setBackground(new Color(255, 255, 255));

            JTextField Description = new JTextField(40);
            JTextField Name = new JTextField(20);
            JTextField Equipments = new JTextField(40);
            JTextField Deadline = new JTextField(8);
            String[] supervisors = new String[5];
            for (int i=0;i<5;i++) {
                supervisors[i] = main.supervisors.get(i).getName();
            }
            JComboBox<String> supervisorJComboBox = new JComboBox<String>(supervisors);
            supervisorJComboBox.setBackground(new Color(255, 255, 255));

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
            jPanel1.add(supervisorJComboBox);
            jPanel1.add(submit);

            // Layout
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
            supervisorJComboBox.setBounds(400,400,300,30);
            submit.setBounds(400,450,300,30);

            // Setup frame
            jFrame1.add(jPanel1);
            jFrame1.setSize(800,800);
            jFrame1.setLocationRelativeTo(null);
            jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame1.setVisible(true);

            // button listeners
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (!(Description.getText().isEmpty() || Name.getText().isEmpty() || Equipments.getText().isEmpty() ||
                            Deadline.getText().isEmpty())) {
                        for (int i=0;i<5;i++) {
                            if (main.supervisors.get(i).getName().equals((String)supervisorJComboBox.getSelectedItem())) {
                                tasks.add(new SupervisorTask(Description.getText(), Name.getText(), "Pending", Equipments.getText(),
                                        Deadline.getText(), main.supervisors.get(i)));
                                main.supervisors.get(i).addTask(new SupervisorTask(Description.getText(), Name.getText(),
                                        "Pending", Equipments.getText(),
                                        Deadline.getText(), main.supervisors.get(i)), main);
                                jFrame1.setVisible(false);
                            }
                        }
                    }
                }
            });
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
        jPanel.setBackground(Color.WHITE);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        JLabel name_label = new JLabel(this.getName()+"(Admin)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Staff_list = new JButton("Staff");
        JButton Logistics = new JButton("Logistics");
        JButton Reports = new JButton("Reports");
        JButton Requests = new JButton("Requests");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] logistics_app = new JLabel[logistics_list.size()];
        JButton leave_request = new JButton("Leave Request");

        int j=100;
        for (int i=0;i<logistics_list.size();i++) {
            logistics_app[i] = new JLabel(logistics_list.get(i).list_string());
            logistics_app[i].setFont(font2);
            jPanel.add(logistics_app[i]);
            logistics_app[i].setBounds(50, j, 700, 200);
            j+=200;
        }
        j=100;
        JButton[] approve_buttons = new JButton[logistics_list.size()];
        for (int i=0;i<logistics_list.size();i++) {
            approve_buttons[i] = new JButton("Approve");
            approve_buttons[i].setFont(font2);
            approve_buttons[i].setBackground(new Color(51, 204, 255));
            jPanel.add(approve_buttons[i]);
            approve_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=160;
        JButton[] reject_buttons = new JButton[logistics_list.size()];
        for (int i=0;i<logistics_list.size();i++) {
            reject_buttons[i] = new JButton("Reject");
            reject_buttons[i].setFont(font2);
            reject_buttons[i].setBackground(new Color(255, 51, 0));
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);

        JButton logout = new JButton("Logout");
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
        logout.setBounds(1190, 1, 150, 50);
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
                logistics_list.get(temp).setValid(true);
                logistics_list.remove(temp);
                jFrame.remove(jPanel);
                logistics(jFrame, main);
            });
        }
        for (int i = 0; i< reject_buttons.length; i++) {
            final int temp = i;
            reject_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                logistics_list.get(temp).setValid(false);
                logistics_list.remove(temp);
                jFrame.remove(jPanel);
                logistics(jFrame, main);
            });
        }
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
        jPanel.setBackground(Color.WHITE);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
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

        JLabel[] report_list = new JLabel[myReports.size()];
        int j=100;
        for (int i=0;i<myReports.size();i++) {
            report_list[i] = new JLabel(myReports.get(i).list_string());
            report_list[i].setFont(font2);
            jPanel.add(report_list[i]);
            report_list[i].setBounds(50, j, 1000, 200);
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 100, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);

        JButton logout = new JButton("Logout");
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
        logout.setBounds(1190, 1, 150, 50);
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
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
        jPanel.setBackground(Color.WHITE);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        JLabel name_label = new JLabel(this.getName()+"(Admin)", SwingConstants.CENTER);
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

        int j=100;
        for (int i=0;i<registration_staff.size();i++) {
            request_list[i] = new JLabel(registration_staff.get(i).list_string());
            request_list[i].setFont(font2);
            jPanel.add(request_list[i]);
            request_list[i].setBounds(50, j, 700, 200);
            j+=200;
        }
        j=100;
        JButton[] approve_signup_buttons = new JButton[registration_staff.size()];
        for (int i=0;i<registration_staff.size();i++) {
            approve_signup_buttons[i] = new JButton("Approve");
            approve_signup_buttons[i].setBackground(new Color(0, 153, 204));
            approve_signup_buttons[i].setFont(font2);
            jPanel.add(approve_signup_buttons[i]);
            approve_signup_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=160;
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1190, 1, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
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
        for (int i = 0; i< approve_signup_buttons.length; i++) {
            final int temp = i;
            approve_signup_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                main.Staff.get(temp).setValid(true);
                registration_staff.get(temp).setValid(true);
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
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
        jPanel.setBackground(Color.WHITE);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
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

        JLabel[] request_list = new JLabel[leave_supervisor.size()];
        int j=100;
        for (int i=0;i<leave_supervisor.size();i++) {
            request_list[i] = new JLabel(leave_supervisor.get(i).list_string());
            request_list[i].setFont(font2);
            jPanel.add(request_list[i]);
            request_list[i].setBounds(50, j, 700, 200);
            j+=200;
        }
        j=100;
        JButton[] approve_buttons = new JButton[leave_supervisor.size()];
        for (int i=0;i<leave_supervisor.size();i++) {
            approve_buttons[i] = new JButton("Approve");
            approve_buttons[i].setBackground(new Color(0, 153, 204));
            approve_buttons[i].setFont(font2);
            jPanel.add(approve_buttons[i]);
            approve_buttons[i].setBounds(730, j, 100, 50);
            j+=200;
        }
        j=160;
        JButton[] reject_buttons = new JButton[leave_supervisor.size()];
        for (int i=0;i<leave_supervisor.size();i++) {
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
        name_label.setBounds(790,1,150,50);
        date_label.setBounds(940, 1, 150, 50);
        time_label.setBounds(1090, 1, 70, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(1190, 1, 150, 50);
        Home.setBackground(new Color(51, 204, 255));
        Tasks.setBackground(new Color(51, 204, 255));
        Staff_list.setBackground(new Color(51, 204, 255));
        Logistics.setBackground(new Color(51, 204, 255));
        Reports.setBackground(new Color(51, 204, 255));
        Requests.setBackground(new Color(51, 204, 255));
        date_label.setBackground(new Color(51, 204, 255));
        time_label.setBackground(new Color(51, 204, 255));
        leave_request.setBackground(new Color(51, 204, 255));
        logout.setBackground(new Color(255, 153, 51));
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
                leave_supervisor.get(temp).setValid(true);
                leave_supervisor.remove(temp);
                jFrame.remove(jPanel);
                leave_request(jFrame, main);
            });
        }
        for (int i = 0; i< reject_buttons.length; i++) {
            final int temp = i;
            reject_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                leave_supervisor.remove(temp);
                jFrame.remove(jPanel);
                leave_request(jFrame, main);
            });
        }
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            admin_login(jFrame, main);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame, main);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Tasks_action(jFrame, main);
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
    public void addLeaves(Leave leave, Main main) {
        leave_supervisor.add(leave);

        // Write to database
        main.writeAdmin();
        main.writeStaff();
        main.writeSupervisors();
    }
}
