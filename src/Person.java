import java.io.Serializable;

/**
 * Created by Rishabh Khatri(2015077) and Ravi Sharma(2015165) on 6/11/16.
 */
public abstract class Person implements Serializable {
	private String name, user_name, password, contact, email;
	private long ID;
	private boolean valid = false;
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

    public String getPassword() {
        return password;
    }

    public boolean getValid() {
	    return this.valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String list_string() {
        String string = "123";
        return string;
    }
}
