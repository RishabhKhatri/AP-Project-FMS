import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Supervisor extends Person {
    private String Department;
    Supervisor(String Name, long ID, String user_name, String Password, String Contact, String Email, String Department) {
        super(Name, ID, user_name, Password, Contact, Email);
        this.Department = Department;
    }

    public String getDepartment() {
        return Department;
    }

    public void supervisor_login(JFrame jFrame) {
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
        time_label.setBounds(1090, 1, 100, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        leave_request.setBounds(540, 1, 150, 50);
        Tasks.setBounds(690, 1, 100, 50);
        welcome_label.setBounds(100, 300, 800, 70);

        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame);
        });
//        Tasks.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            Tasks_action(jFrame);
//        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame);
        });
//        Logistics.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            logistics(jFrame);
//        });
//        Reports.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            reports(jFrame);
//        });
//        leave_request.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            Leave_request(jFrame);
//        });
    }
    public void staff_list(JFrame jFrame) {
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
        final JLabel date_label = new JLabel();
        final JLabel time_label = new JLabel();
        TimeKeeper timeKeeper = new TimeKeeper(date_label, time_label);
        JLabel[] staff_list = new JLabel[Main.Staff.size()];
        JButton leave_request = new JButton("Leave Request");

        int j=100, k=0;
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment())) {
                staff_list[k++] = new JLabel(Main.Staff.get(i).list_string());
                staff_list[k++].setBorder(border);
                staff_list[k++].setFont(font2);
                jPanel.add(staff_list[k++]);
                staff_list[k++].setBounds(20, j, 800, 200);
                j+=200;
            }
        }
        j=100;k=0;
        JButton[] delete_buttons = new JButton[Main.Staff.size()];
        for (int i=0;i<Main.Staff.size();i++) {
            if (this.getDepartment().equals(Main.Staff.get(i).getDepartment())) {
                delete_buttons[k++] = new JButton("Delete");
                delete_buttons[k++].setFont(font2);
                jPanel.add(delete_buttons[k++]);
                delete_buttons[k++].setBounds(840, j, 100, 50);
                j+=200;
            }
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

        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners

        // Button listeners for delete buttons
        for (int i=0;i<delete_buttons.length;i++) {
            final int temp = i;
            delete_buttons[i].addActionListener(actionEvent -> {
                JButton jButton = (JButton) actionEvent.getSource();
                jButton.setEnabled(false);
                Main.Staff.remove(temp);
                jFrame.setVisible(true);
                System.out.println(Main.Staff.size());
            });
        }
        Home.addActionListener(e -> {
            jFrame.remove(jPanel);
            supervisor_login(jFrame);
        });
        Staff_list.addActionListener(e -> {
            jFrame.remove(jPanel);
            staff_list(jFrame);
        });
//        Tasks.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            Tasks_action(jFrame);
//        });
//        Logistics.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            logistics(jFrame);
//        });
//        Reports.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            reports(jFrame);
//        });
//        Requests.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            request(jFrame);
//        });
//        leave_request.addActionListener(e -> {
//            jFrame.remove(jPanel);
//            leave_request(jFrame);
//        });
    }
}
