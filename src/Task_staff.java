import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rishabh on 29/11/16.
 */
public class Task_staff {
    private static int r=1000001;
    private String Description, Name, Status, Equipments;
    private String deadline;
    private ArrayList<Staff> staffs;
    private long ID;
    Task_staff(String Description, String Name, String Status, String Equipments, String deadline,
                   ArrayList<Staff> staffs) {
        Random random = new Random();
        r += random.nextInt(10)+1;
        this.ID = r;
        this.Description = Description;
        this.Name = Name;
        this.Status = Status;
        this.Equipments = Equipments;
        this.deadline = deadline;
        this.staffs = staffs;
    }
    public String list_string() {
        String string;
        string = "<html>Name: "+this.Name+"<br>ID: "+this.ID+"<br>Description: "+
                this.Description+"<br>Status: "+this.Status+"<br>Equipments: "+
                this.Equipments+"<br>Deadline: "+this.deadline+"<br>Staff members: ";
        for (int i=0;i<staffs.size();i++) {
            if (i==staffs.size()-1) {
                string = string+staffs.get(i).getName()+"</html>";
            }
            else {
                string = string+staffs.get(i).getName()+", ";
            }
        }
        return string;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public long getID() {
        return ID;
    }

    public String getEquipments() {
        return Equipments;
    }
}
