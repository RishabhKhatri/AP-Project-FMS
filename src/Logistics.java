/**
 * Created by Rishabh on 11/29/2016.
 */
public class Logistics {
    private long ID, reference_ID;
    private String items, department;
    Logistics(String items, String department, long ID) {
        this.items = items;
        this.department = department;
        this.ID = ID;
    }

    public void setReference_ID(long reference_ID) {
        this.reference_ID = reference_ID;
    }
    public String list_string() {
        String string;
        string = "<html>ID: "+this.ID+"<br>Items: "+this.items+"<br>Department: "+
                this.department+"<br>Reference Task ID: "+this.reference_ID+"</html>";
        return string;
    }
}
