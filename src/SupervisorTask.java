import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rishabh on 11/29/2016.
 */
public class SupervisorTask {
    private String Description, Name, Status, Equipments;
    private String deadline;
    private Supervisor supervisor_for_task;
    private long ID;
//    private ArrayList<Person> staff_for_task;
    SupervisorTask(String Description, String Name, String Status, String Equipments, String deadline,
                   Supervisor supervisor_for_task) {
        this.Description = Description;
        this.Name = Name;
        this.Status = Status;
        this.Equipments = Equipments;
        this.deadline = deadline;
        this.supervisor_for_task = supervisor_for_task;
    }
    public String list_string() {
        String string;
        string = "<html>Name: "+this.Name+"<br>ID: "+this.ID+"<br>Description: "+
                this.Description+"<br>Status: "+this.Status+"<br>Equipments: "+
                this.Equipments+"<br>Deadline: "+this.deadline+"<br>Supervisor: "+this.supervisor_for_task.getName()+"</html>";
        return string;
    }
}
