/**
 * Created by rishabh on 29/11/16.
 */
public class TaskReport {
    private long ID, taskID, time_taken;
    private String taskName, description, items, comments;
    TaskReport(String taskName, String description, String items, String comments, long ID, long taskID, long time_taken) {
        this.taskID = taskID;
        this.comments = comments;
        this.description = description;
        this.ID = ID;
        this.items = items;
        this.taskName = taskName;
        this.time_taken = time_taken;
    }
    public String list_string() {
        String string;
        string = "<html>ID: "+this.ID+"<br>Task ID: "+this.taskID+"<br>Task Name: "+this.taskName+"<br>Description: "+this.description+
                "<br>Items used: "+this.items+"<br>Time taken: "+this.time_taken+"<br>Comments: "+this.comments+"</html>";
        return string;
    }
}
