import javax.swing.*;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Staff extends Person {
    private String Department;
    Staff(String Name, long ID, String user_name, String Password, String Contact, String Email, String Department) {
        super(Name, ID, user_name, Password, Contact, Email);
        this.Department = Department;
    }

    public String getDepartment() {
        return Department;
    }

    public static void staff_login(Person person, JFrame jFrame) {

    }
    public String list_string() {
        String string;
        string = "<html>Name: "+this.getName()+"<br>ID: "+this.getID()+"<br>Username: "+
                this.getUser_name()+"<br>Contact: "+this.getContact()+"<br>Email: "+
                this.getEmail()+"<br>Department: "+this.getDepartment()+"</html>";
        return string;
    }
}
