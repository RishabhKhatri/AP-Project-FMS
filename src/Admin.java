import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Admin extends Person {
    private ArrayList<SupervisorTask> tasks = new ArrayList<>();
    private ArrayList<Person> registration_staff;
    Admin(String Name, long ID, String user_name, String Password, String Contact, String Email) {
        super(Name, ID, user_name, Password, Contact, Email);
    }

    public void RegistrationRequest_Staff(Person person) {
        registration_staff.add(person);
    }

    public void admin_login(JFrame jFrame) {
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

        // Set Layout of components
        name_label.setBounds(650,1,150,50);
        date_label.setBounds(800, 1, 150, 50);
        time_label.setBounds(970, 1, 100, 50);
        Home.setBounds(1,1,100,50);
        Staff_list.setBounds(100,1,100,50);
        Logistics.setBounds(200,1,120,50);
        Reports.setBounds(320,1,100,50);
        Requests.setBounds(420,1,120,50);
        Tasks.setBounds(520, 1, 100, 50);
        welcome_label.setBounds(100, 300, 800, 70);

        // Setup frame
        jFrame.add(jPanel);
        timeKeeper.start();
        jFrame.setVisible(true);

        // Button Listeners
        Home.addActionListener(e -> admin_login(jFrame));
        Tasks.addActionListener(e -> {
            welcome_label.setVisible(false);
            JLabel[] task_list = new JLabel[tasks.size()];
            JButton new_task = new JButton("New Task");
            int j=100;
            for (int i=0;i<tasks.size();i++) {
                task_list[i] = new JLabel(tasks.get(i).list_string());
                task_list[i].setBorder(border);
                task_list[i].setFont(font2);
                jPanel.add(task_list[i]);
                task_list[i].setBounds(20, j, 800, 200);
                j+=200;
            }
            jPanel.add(new_task);
            new_task.setBounds(50, 100, 150, 50);
            jFrame.setVisible(true);
            new_task.addActionListener(actionEvent -> {
                JFrame jFrame1 = new JFrame("Assign Task");
                JLabel title = new JLabel("Task Form");
                JLabel description_label = new JLabel("Description(Not more than 40 letters)");
                JLabel name_label1 = new JLabel("Name");
                JLabel equipments_label = new JLabel("Equipments");
                JLabel deadline_label = new JLabel("Deadline");
                JLabel supervisor_label = new JLabel("Supervisor");
                JButton submit = new JButton("Assign Task");

                JPanel jPanel1 = new JPanel(null);

                JTextField Description = new JTextField(40);
                JTextField Name = new JTextField(20);
                JTextField Equipments = new JTextField(40);
                JTextField Deadline = new JTextField(8);
                String[] supervisors = new String[5];
                for (int i=0;i<5;i++) {
                    supervisors[i] = Main.supervisors.get(i).getName();
                }
                JComboBox<String> supervisorJComboBox = new JComboBox<String>(supervisors);

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

                // layout
                title.setBounds(200,1,200,70);
                name_label1.setBounds(100 , 100, 200,50);
                Name.setBounds(350 , 100, 300,50);
                deadline_label.setBounds(100 , 150, 200,50);
                Description.setBounds(350 , 150, 300,50);
                equipments_label.setBounds(100 , 200, 200,50);
                Equipments.setBounds(350 , 200, 300,50);
                deadline_label.setBounds(100 , 250, 200,50);
                Deadline.setBounds(350 , 250, 300,50);
                supervisor_label.setBounds(100 , 300, 200,50);
                supervisorJComboBox.setBounds(350 , 300, 200,50);
                submit.setBounds(350, 350, 150, 50);

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
                                if (Main.supervisors.get(i).getName().equals((String)supervisorJComboBox.getSelectedItem())) {
                                    tasks.add(new SupervisorTask(Description.getText(), Name.getText(), "Pending", Equipments.getText(),
                                            Deadline.getText(), Main.supervisors.get(i)));
                                    System.out.println(tasks.size());
                                    jFrame1.setVisible(false);
                                }
                            }
                        }
                    }
                });
            });
        });
        Staff_list.addActionListener(e -> {
            welcome_label.setVisible(false);
            JLabel[] staff_list = new JLabel[Main.Staff.size()];
            int j=100;
            for (int i=0;i<Main.Staff.size();i++) {
                staff_list[i] = new JLabel(Main.Staff.get(i).list_string());
                staff_list[i].setBorder(border);
                staff_list[i].setFont(font2);
                jPanel.add(staff_list[i]);
                staff_list[i].setBounds(20, j, 800, 200);
                j+=200;
            }
            j=100;
            JButton[] delete_buttons = new JButton[Main.Staff.size()];
            for (int i=0;i<Main.Staff.size();i++) {
                delete_buttons[i] = new JButton("Delete");
                delete_buttons[i].setFont(font2);
                jPanel.add(delete_buttons[i]);
                delete_buttons[i].setBounds(840, j, 100, 50);
                j+=200;
            }
            jFrame.setVisible(true);

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
        });
        Logistics.addActionListener(e -> {

        });
        Reports.addActionListener(e -> {

        });
        Requests.addActionListener(e -> {

        });
    }
}
