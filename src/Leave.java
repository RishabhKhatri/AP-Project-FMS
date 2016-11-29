import java.util.Date;

/**
 * Created by rishabh on 29/11/16.
 */
public class Leave {
    private String toWhom, Reason;
    private Date from, to;
    private boolean valid;
    Leave(String toWhom, String Reason, Date from, Date to) {
        this.toWhom = toWhom;
        this.Reason = Reason;
        this.from = from;
        this.to = to;
    }
    public String list_string() {
        String string;
        string = "<html>Reason: "+this.Reason+"<br>From: "+from+"<br>To: "+this.to+"</html>";
        return string;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
