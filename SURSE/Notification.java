import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notification {
    int campaignID;
    Date notificationDate;
    NotificationType type;
    List<Integer> vouchers;

    enum NotificationType{
        EDIT,
        CANCEL
    }

    // Constructor
    public Notification(NotificationType type, Date notificationDate, int campaignID){
        this.type = type;
        this.notificationDate = notificationDate;
        this.campaignID = campaignID;
        this.vouchers = new ArrayList<>();
    }

    // Return a string describing the current Notification
    public String toString(){
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return campaignID + ";" + dtf.format(notificationDate) + ";" + type;
    }
}
