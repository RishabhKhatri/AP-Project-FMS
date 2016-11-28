import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rishabh on 11/28/2016.
 */
public class TimeKeeper extends Thread {
    private boolean flag;
    private JLabel  date_label, time_label;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    TimeKeeper(JLabel date_label, JLabel time_label) {
        this.date_label = date_label;
        this.time_label = time_label;
        flag=true;
    }

    @Override
    public void run() {
        while (flag) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Calendar calendar = Calendar.getInstance();
                    Date current_time = calendar.getTime();
                    date_label.setText(dateFormat.format(current_time));
                    time_label.setText(timeFormat.format(current_time));
                }
            });
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
