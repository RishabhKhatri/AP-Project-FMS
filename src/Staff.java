import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Staff extends Person {
    private String Department;
    private boolean valid = false, leave_request = false;
    private ArrayList<TaskReport> reports = new ArrayList<>();
    private ArrayList<Task_staff> myTasks = new ArrayList<>();
    private ArrayList<Leave> myLeaves = new ArrayList<>();
    Staff(String Name, long ID, String user_name, String Password, String Contact, String Email, String Department) {
        super(Name, ID, user_name, Password, Contact, Email);
        this.Department = Department;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public String getDepartment() {
        return Department;
    }

    public void staff_login(JFrame jFrame) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel welcome_label = new JLabel("Welcome to Facility Management Service", SwingConstants.CENTER);
        JLabel name_label = new JLabel(this.getName()+"(Staff)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Logistics = new JButton("Logistics");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        JButton Reports = new JButton("Reports");
        my_leaves.setFont(font2);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment()) && Main.Staff.get(i).isValid()) {
                department_list.add(Main.Staff.get(i));
            }
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Logistics.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        welcome_label.setFont(font);
        Reports.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(welcome_label);
        jPanel.add(Reports);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        Reports.setBounds(470, 1, 150, 50);
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(950, 1, 100, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(900, 1, 150, 50);
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
            Interface.FrontScreen(jFrame);
        });
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Task_action(jFrame);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            report_action(jFrame);
        });
    }
    public void Task_action(JFrame jFrame) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Staff)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Logistics = new JButton("Logistics");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        JButton Reports = new JButton("Reports");
        my_leaves.setFont(font2);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment()) && Main.Staff.get(i).isValid()) {
                department_list.add(Main.Staff.get(i));
            }
        }

        JLabel[] staff_list = new JLabel[myTasks.size()];
        int j=100;
        for (int i=0;i<myTasks.size();i++) {
            staff_list[i] = new JLabel(myTasks.get(i).list_string());
            staff_list[i].setBorder(border);
            staff_list[i].setFont(font2);
            jPanel.add(staff_list[i]);
            staff_list[i].setBounds(20, j, 800, 200);
            j+=200;
        }
        j=100;
        JButton[] ongoing_buttons = new JButton[myTasks.size()];
        for (int i=0;i<myTasks.size();i++) {
            ongoing_buttons[i] = new JButton("PENDING");
            ongoing_buttons[i].setFont(font2);
            jPanel.add(ongoing_buttons[i]);
            if (this.myTasks.get(i).getStatus().equals("Pending")) {
                ongoing_buttons[i].setEnabled(false);
            }
            else {
                ongoing_buttons[i].setEnabled(true);
            }
            ongoing_buttons[i].setBounds(840, j, 100, 50);
            j+=200;
        }
        j=100;
        JButton[] complete_buttons = new JButton[myTasks.size()];
        for (int i=0;i<myTasks.size();i++) {
            complete_buttons[i] = new JButton("COMPLETE");
            complete_buttons[i].setFont(font2);
            jPanel.add(complete_buttons[i]);
            if (this.myTasks.get(i).getStatus().equals("Complete")) {
                ongoing_buttons[i].setEnabled(false);
                complete_buttons[i].setEnabled(false);
            }
            else {
                ongoing_buttons[i].setEnabled(true);
                complete_buttons[i].setEnabled(false);
            }
            complete_buttons[i].setBounds(940, j, 100, 50);
            j+=200;
        }
        j=100;
        JButton[] generate_task = new JButton[myTasks.size()];
        for (int i=0;i<myTasks.size();i++) {
            generate_task[i] = new JButton("Generate Task Report");
            generate_task[i].setFont(font2);
            jPanel.add(generate_task[i]);
            generate_task[i].setBounds(1040, j, 250, 50);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Logistics.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        Reports.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(Reports);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        Reports.setBounds(470, 1, 150, 50);
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(950, 1, 100, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(900, 1, 150, 50);
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
            Interface.FrontScreen(jFrame);
        });
        for (int i=0;i<ongoing_buttons.length;i++) {
            final int temp = i;
            ongoing_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                myTasks.get(temp).setStatus("Pending");
                jFrame.remove(jPanel);
                Task_action(jFrame);
            });
        }
        for (int i=0;i<complete_buttons.length;i++) {
            final int temp = i;
            complete_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                myTasks.get(temp).setStatus("Complete");
                jFrame.remove(jPanel);
                Task_action(jFrame);
            });
        }
        for (int i=0;i<generate_task.length;i++) {
            final int temp = i;
            generate_task[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                generate_task_report(myTasks.get(temp), jFrame);
                jFrame.remove(jPanel);
                Task_action(jFrame);
            });
        }
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Task_action(jFrame);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            report_action(jFrame);
        });
    }
    public void report_action(JFrame jFrame) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Staff)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Logistics = new JButton("Logistics");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        JButton Reports = new JButton("Reports");
        my_leaves.setFont(font2);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment()) && Main.Staff.get(i).isValid()) {
                department_list.add(Main.Staff.get(i));
            }
        }

        JLabel[] staff_list = new JLabel[reports.size()];
        int j=100;
        for (int i=0;i<reports.size();i++) {
            staff_list[i] = new JLabel(reports.get(i).list_string());
            staff_list[i].setBorder(border);
            staff_list[i].setFont(font2);
            jPanel.add(staff_list[i]);
            staff_list[i].setBounds(20, j, 800, 200);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Logistics.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        Reports.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(Reports);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        Reports.setBounds(470, 1, 150, 50);
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(950, 1, 100, 50);

        JButton logout = new JButton("Logout");
        logout.setBounds(900, 1, 150, 50);
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
            Interface.FrontScreen(jFrame);
        });
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Task_action(jFrame);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            report_action(jFrame);
        });
    }
    public void logistics(JFrame jFrame) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Staff)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton Logistics = new JButton("Logistics");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        JButton Reports = new JButton("Reports");
        my_leaves.setFont(font2);
        jPanel.add(my_leaves);

        JLabel title = new JLabel("Logistics Requirements for task");
        JLabel id_label = new JLabel("ID");
        JLabel items_label = new JLabel("Items required");
        JLabel task_label = new JLabel("Task reference ID");
        JTextField id = new JTextField();
        JTextField items = new JTextField();
        JComboBox<String> jComboBox;
        JButton submit = new JButton("Submit requirements");

        ArrayList<Task_staff> arrayList = new ArrayList<>();
        for (Task_staff task_staff:myTasks) {
            if (task_staff.getStatus().equals("Pending")) {
                arrayList.add(task_staff);
            }
        }
        String[] strings = new String[arrayList.size()];
        for (int i=0;i<arrayList.size();i++) {
            strings[i] = ""+arrayList.get(i).getID();
        }
        jComboBox = new JComboBox<>(strings);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment()) && Main.Staff.get(i).isValid()) {
                department_list.add(Main.Staff.get(i));
            }
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Logistics.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        Reports.setFont(font2);
        title.setFont(font);
        id_label.setFont(font2);
        items_label.setFont(font2);
        task_label.setFont(font2);
        submit.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(Reports);
        jPanel.add(title);
        jPanel.add(id_label);
        jPanel.add(items_label);
        jPanel.add(task_label);
        jPanel.add(jComboBox);
        jPanel.add(id);
        jPanel.add(items);
        jPanel.add(submit);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        Reports.setBounds(470, 1, 150, 50);
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(950, 1, 100, 50);
        title.setBounds(400 , 200 , 400, 50);
        id_label.setBounds(200 , 300 , 150, 30);
        id.setBounds(400 , 300 , 300, 30);
        items_label.setBounds(200 , 350 , 150, 30);
        items.setBounds(400 , 350 , 300, 30);
        task_label.setBounds(200 , 400 , 150, 30);
        jComboBox.setBounds(400, 400, 150, 30);
        submit.setBounds(400 , 450 , 300, 30);


        JButton logout = new JButton("Logout");
        logout.setBounds(900, 1, 150, 50);
        logout.setFont(font2);
        jPanel.add(logout);

        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        submit.addActionListener(e -> {
            if (id.getText().isEmpty() || items.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Someof the field(s) left empty!");
                jFrame.remove(jPanel);
                logistics(jFrame);
            }
            String string = (String)jComboBox.getSelectedItem();
            Logistics temp = new Logistics(items.getText(), Long.parseLong(id.getText()), Long.parseLong(string));
            for (Supervisor supervisor:Main.supervisors) {
                if (supervisor.getDepartment().equals(this.getDepartment())) {
                    supervisor.addLogistics(temp);
                }
            }
        });
        logout.addActionListener(e -> {
            jFrame.remove(jPanel);
            Interface.FrontScreen(jFrame);
        });
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame);
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Task_action(jFrame);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            report_action(jFrame);
        });
    }
    public String list_string() {
        String string;
        string = "<html>Name: "+this.getName()+"<br>ID: "+this.getID()+"<br>Username: "+
                this.getUser_name()+"<br>Contact: "+this.getContact()+"<br>Email: "+
                this.getEmail()+"<br>Department: "+this.getDepartment()+"</html>";
        return string;
    }
    public void addTask(Task_staff task) {
        myTasks.add(task);
    }
    public void accept_leave_request(JFrame jFrame) {
        JPanel jPanel = new JPanel(null);
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        Border border = BorderFactory.createLineBorder(Color.blue, 1);
        JLabel name_label = new JLabel(this.getName()+"(Staff)", SwingConstants.CENTER);
        JButton Home = new JButton("Home");
        JButton Tasks = new JButton("Tasks");
        JButton new_leave = new JButton("Apply for leave");
        JButton Logistics = new JButton("Logistics");
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JButton my_leaves = new JButton("My Leaves");
        JButton Reports = new JButton("Reports");
        my_leaves.setFont(font2);
        jPanel.add(my_leaves);

        ArrayList<Staff> department_list = new ArrayList<>();
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment()) && Main.Staff.get(i).isValid()) {
                department_list.add(Main.Staff.get(i));
            }
        }

        JLabel[] leave_list = new JLabel[myLeaves.size()];
        int j=200;
        for (int i=0;i<myLeaves.size();i++) {
            leave_list[i] = new JLabel(myLeaves.get(i).list_string());
            leave_list[i].setBorder(border);
            leave_list[i].setFont(font2);
            jPanel.add(leave_list[i]);
            leave_list[i].setBounds(20, j, 800, 200);
            j+=200;
        }

        // Set Fonts
        name_label.setFont(font2);
        Home.setFont(font2);
        Tasks.setFont(font2);
        Logistics.setFont(font2);
        date_label.setFont(font2);
        time_label.setFont(font2);
        Reports.setFont(font2);
        new_leave.setFont(font2);

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);
        jPanel.add(Reports);
        jPanel.add(new_leave);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        Reports.setBounds(470, 1, 150, 50);
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(950, 1, 100, 50);
        new_leave.setBounds(20 , 70, 200, 30);

        JButton logout = new JButton("Logout");
        logout.setBounds(900, 1, 150, 50);
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
            Interface.FrontScreen(jFrame);
        });
        my_leaves.addActionListener(e -> {
            jFrame.remove(jPanel);
            accept_leave_request(jFrame);
        });
        new_leave.addActionListener(e -> {
            JFrame jFrame1 = new JFrame("Application for leave");
            JPanel jPanel1 = new JPanel(null);
            JLabel title = new JLabel("Application for leave");
            JLabel toWhom_label = new JLabel("Apply to");
            JLabel reason_label = new JLabel("Reason");
            JLabel from_label = new JLabel("From (Date)");
            JLabel to_label = new JLabel("To (Date)");
            JTextField reason = new JTextField();
            JTextField from = new JTextField();
            JTextField to = new JTextField();
            JComboBox<String> toWhom;
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
            String[] strings = new String[2];
            strings[0] = "Admin";
            for (int i=0;i<5;i++) {
                if (Main.supervisors.get(i).getDepartment().equals(this.getDepartment())) {
                    strings[1] = Main.supervisors.get(i).getName();
                }
            }
            toWhom = new JComboBox<String>(strings);

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
                    accept_leave_request(jFrame);
                }
                Leave temp=null;
                String string = (String)toWhom.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    temp = new Leave(string, reason.getText(), format.parse(from.getText()), format.parse(to.getText()));
                }
                catch (ParseException a) {
                    a.printStackTrace();
                }
                myLeaves.add(temp);
                if (string.equals("Admin")) {
                    Admin.leave_supervisor.add(temp);
                }
                else {
                    for (Supervisor supervisor:Main.supervisors) {
                        if (supervisor.getDepartment().equals(this.getDepartment())) {
                            supervisor.addLeave(temp);
                        }
                    }
                }
                jFrame1.setVisible(false);
                jFrame.remove(jPanel);
                accept_leave_request(jFrame);
            });
        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
        Tasks.addActionListener(e -> {
            jFrame.remove(jPanel);
            Task_action(jFrame);
        });
        Logistics.addActionListener(e -> {
            jFrame.remove(jPanel);
            logistics(jFrame);
        });
        Reports.addActionListener(e -> {
            jFrame.remove(jPanel);
            report_action(jFrame);
        });
    }
    public void generate_task_report(Task_staff task_staff, JFrame frame) {
        Font font = new Font("Roboto Light", Font.PLAIN, 35);
        Font font1 = new Font("Roboto Light", Font.PLAIN, 20);
        Font font2 = new Font("Roboto Light", Font.PLAIN, 17);
        JPanel jPanel = new JPanel(null);
        JFrame jFrame = new JFrame("Task Report");
        JLabel title = new JLabel("Task Report");
        JLabel id_label = new JLabel("ID");
        JLabel name_label = new JLabel("Task Name");
        JLabel description_label = new JLabel("Task Description");
        JLabel items_label = new JLabel("Items used");
        JLabel time_label = new JLabel("Time taken(hrs)");
        JLabel comments_label = new JLabel("Comments");

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField description = new JTextField();
        JTextField items = new JTextField();
        JTextField time = new JTextField();
        JTextField comments = new JTextField();

        JButton submit = new JButton("Submit");

        title.setFont(font);
        id_label.setFont(font2);
        name_label.setFont(font2);
        description_label.setFont(font2);
        items_label.setFont(font2);
        time_label.setFont(font2);
        comments_label.setFont(font2);
        submit.setFont(font2);

        jPanel.add(title);
        jPanel.add(id_label);
        jPanel.add(name_label);
        jPanel.add(description_label);
        jPanel.add(items_label);
        jPanel.add(time_label);
        jPanel.add(comments_label);
        jPanel.add(id);
        jPanel.add(description);
        jPanel.add(items);
        jPanel.add(time);
        jPanel.add(comments);
        jPanel.add(name);
        jPanel.add(submit);
        items.setText(task_staff.getEquipments());
        items.setEnabled(false);

        title.setBounds(300,100,500,70);
        id_label.setBounds(100,200,300,30);
        id.setBounds(400,200,300,30);
        name_label.setBounds(100,250,300,30);
        name.setBounds(400,250,300,30);
        description_label.setBounds(100,300,300,30);
        description.setBounds(400,300,300,30);
        items_label.setBounds(100,350,300,30);
        items.setBounds(400,350,300,30);
        time_label.setBounds(100,400,300,30);
        time.setBounds(400,400,300,30);
        comments_label.setBounds(100,450,300,30);
        comments.setBounds(400,450,300,30);
        submit.setBounds(400,500,300,30);

        jFrame.add(jPanel);
        jFrame.setSize(800,800);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        submit.addActionListener(e -> {
            if (id.getText().isEmpty() || name.getText().isEmpty() || description.getText().isEmpty() ||
                    time.getText().isEmpty() || comments.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Some of the field(s) left empty!");
                jFrame.setVisible(false);
            }
            TaskReport taskReport = new TaskReport(name.getText(), description.getText(), items.getText(), comments.getText(),
                    Long.parseLong(id.getText()), task_staff.getID(), Long.parseLong(time.getText()));
            reports.add(taskReport);
            Task_action(frame);
        });
    }
}
