import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VMS {
    List<Campaign> campaigns;
    List<User> users;
    private static VMS instance = null;

    // Private constructor as described in Singleton Design Pattern
    private VMS(){
        campaigns = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Returns the instance of the class, or creates a one if it wasn't instantiated
    public static VMS getInstance(){
        if(instance == null) {
            instance = new VMS();
        }

        return instance;
    }

    // Returns a list with all the campaigns
    public List<Campaign> getCampaigns(){
        return campaigns;
    }

    // Return the campaign specified by id
    public Campaign getCampaign(Integer id){
        for(Campaign c : campaigns){
            if(c.id == id)
                return c;
        }

        return null;
    }

    // Add a new campaign
    public boolean addCampaign(Campaign campaign){
        return campaigns.add(campaign);
    }

    // Edit campaign specified by id
    public void updateCampaign(Integer id, Campaign campaign) throws ParseException {
        for(Campaign c: campaigns){
            if(c.id == id){
                c.editCampaign(campaign);
                return;
            }
        }
    }

    // Cancel campaign specified by id
    public void cancelCampaign(Integer id) throws ParseException {
        Campaign c = getCampaign(id);

        // A campaign can be cancelled only if it is NEW or it STARTED
        if(c.campaignStatusType == Campaign.CampaignStatusType.NEW || c.campaignStatusType == Campaign.CampaignStatusType.STARTED){
            c.campaignStatusType = Campaign.CampaignStatusType.CANCELLED;

            // Get current date and format it following the pattern "yyyy-MM-dd HH:mm"
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dtf.format(now));

            // Notify all observers about the cancellation of the campaign
            Notification notification = new Notification(Notification.NotificationType.CANCEL, date, id);
            c.notifyAllObservers(notification);
        }
    }

    // Return a list with all the users
    public List<User> getUsers(){
        return users;
    }

    // Return the user specied by id
    public User getUser(int id){
        for(User u: users){
            if(u.id == id)
                return u;
        }

        return null;
    }

    // Add a new user
    public boolean addUser(User user){
        return users.add(user);
    }
}
