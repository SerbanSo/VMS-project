import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User implements Observer{
    int id;
    String name;
    String email;
    String password;
    UserType type;
    UserVoucherMap vouchers;
    List<Notification> notifications;

    enum UserType{
        ADMIN,
        GUEST
    }

    // Every user represents an observer as described by the Observer Design Pattern
    // Add new notification to the list
    @Override
    public void update(Notification notification) {

        Notification tmp = new Notification(notification.type, notification.notificationDate, notification.campaignID);

        for(Voucher v: vouchers.get(notification.campaignID)){
            tmp.vouchers.add(v.id);
        }

        notifications.add(tmp);
    }

    // Constructor
    public User(int id, String name, String password, String email, UserType type){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;

        vouchers = new UserVoucherMap();
        notifications = new ArrayList<>();
    }

    // Return a string containing all the notifications of the current User
    public String printNotifications(){
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for(Notification n: notifications){
            sb.append(n.campaignID + ";");

            Iterator<Voucher> v = vouchers.get(n.campaignID).iterator();

            sb.append("[");
            while (v.hasNext()){
                sb.append(v.next().id + (v.hasNext()? ", ":""));
            }
            sb.append("]");

            DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sb.append(";" + dtf.format(n.notificationDate) + ";" + n.type);

        }
        sb.append("]");

        return sb.toString();
    }

    // Return a string describing the current User
    public String toString(){
        return "[" + id + ";" + name + ";" + email + ";" + type.toString() + "]";
    }
}
