import javax.swing.*;

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

    public static void supervisor_login(Person person, JFrame jFrame) {

    }
}
