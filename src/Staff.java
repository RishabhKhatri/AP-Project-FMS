/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 4/11/16.
 */
public class Staff extends Person {
    private String department;
    Staff(String Name, long ID, String user_name, String Password, String Contact, String Email, String department) {
        super(Name, ID, user_name, Password, Contact, Email);
        this.department = department;
    }
}
