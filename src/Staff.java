import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Staff extends Person {
    private String Department;
    private boolean valid = false, leave_request = false;
    private ArrayList<Task_staff> myTasks = new ArrayList<>();
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

    public static void staff_login(JFrame jFrame) {

    }
    public void staff_list(JFrame jFrame) {
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

        // Add components to JPanel
        jPanel.add(name_label);
        jPanel.add(Home);
        jPanel.add(Logistics);
        jPanel.add(date_label);
        jPanel.add(time_label);
        jPanel.add(Tasks);

        // Set Layout of components
        Home.setBounds(1,1,100,50);
        Tasks.setBounds(100, 1, 100, 50);
        Logistics.setBounds(200,1,120,50);
        my_leaves.setBounds(320, 1, 150, 50);
        name_label.setBounds(500,1,150,50);
        date_label.setBounds(650, 1, 150, 50);
        time_label.setBounds(800, 1, 100, 50);

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
                staff_list(jFrame);
            });
        }
        for (int i=0;i<complete_buttons.length;i++) {
            final int temp = i;
            complete_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                myTasks.get(temp).setStatus("Complete");
                jFrame.remove(jPanel);
                staff_list(jFrame);
            });
        }
        for (int i=0;i<generate_task.length;i++) {
            final int temp = i;
            generate_task[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                generate_task(myTasks.get(temp));
                jFrame.remove(jPanel);
                staff_list(jFrame);
            });
        }
//        my_leaves.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            accept_leave_request(jFrame);
//        });
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_login(jFrame);
        });
//        Logistics.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            logistics(jFrame);
//        });
//        Reports.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            reports(jFrame);
//        });
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
    public void generate_task(Task_staff task_staff) {

    }
}
