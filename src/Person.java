/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public abstract class Person {
	private String name, user_name, password, contact, email;
	private long ID;
	Person(String Name, long ID, String user_name, String Password, String Contact, String Email) {
	    this.name = Name;
	    this.ID = ID;
	    this.user_name = user_name;
	    this.password = Password;
	    this.contact = Contact;
	    this.email = Email;
    }

    public String getEmail() {
        return email;
    }

    public long getID() {
        return ID;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getUser_name() {
        return user_name;
    }
}
