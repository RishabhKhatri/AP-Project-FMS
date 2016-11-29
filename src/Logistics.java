/**
 * Created by Rishabh on 11/29/2016.
 */
public class Logistics {
    private long ID, reference_ID;
    private String items;
    private boolean valid = false;
    Logistics(String items, long ID, long reference_ID) {
        this.items = items;
        this.ID = ID;
        this.reference_ID = reference_ID;
    }

    public String list_string() {
        String string;
        string = "<html>ID: "+this.ID+"<br>Items: "+this.items+"<br>Reference Task ID: "+this.reference_ID+"</html>";
        return string;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
