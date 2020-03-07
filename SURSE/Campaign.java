import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Campaign implements Subject{
    int idValue;
    int id;
    int totalVouchers;
    int currentVouchers;

    String name;
    String description;

    Date startDate;
    Date endDate;

    CampaignStatusType campaignStatusType;
    CampaignVoucherMap campaignVouchers;

    List<User> users;

    Strategy strategyType;

    enum CampaignStatusType{
        NEW,
        STARTED,
        EXPIRED,
        CANCELLED
    }

    // Every campaign represents a subject as described by the Observer Design Pattern
    // Add new Observer
    @Override
    public boolean addObserver(User user) {
        return users.add(user);
    }

    // Remove specified Observer
    @Override
    public void removeObserver(User user) {
        users.remove(user);
    }

    // Notify All Observers
    @Override
    public void notifyAllObservers(Notification notification) {
        for(User u: users){
            u.update(notification);
        }
    }

    // Constructor
    public Campaign(int id, String name, String description, Date startDate, Date endDate, int totalVouchers, Strategy strategy){
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalVouchers =totalVouchers;
        this.currentVouchers = totalVouchers;
        this.campaignStatusType = CampaignStatusType.NEW;
        this.idValue = 1;
        this.strategyType = strategy;

        campaignVouchers = new CampaignVoucherMap();

        // Anonymous ArrayList so the toString method can match the desired output format
        users = new ArrayList<>(){
            @Override
            public String toString(){
                StringBuilder sb = new StringBuilder();

                sb.append("[");
                for(User u: this){
                    sb.append(u.toString());
                }
                sb.append("]");

                return sb.toString();
            }
        };
    }

    // Return the vouchers
    public CampaignVoucherMap getVouchers(){
        return campaignVouchers;
    }

    // Return the voucher specified by code==id or null if it doesn't exist
    public Voucher getVoucher(String code){
        // Search for the voucher in every user's vouchers
        for(Map.Entry<String, List<Voucher>> s: campaignVouchers.entrySet()){
            for(Voucher v: s.getValue()){
                if(v.id == Integer.parseInt(code)) {
                    return v;
                }
            }
        }

        return null;
    }

    // Generate a voucher for the given email address
    public Voucher generateVoucher(String email, String voucherType, float value){
        // If the campaign is cancelled or expired
        if(campaignStatusType == CampaignStatusType.CANCELLED || campaignStatusType == CampaignStatusType.EXPIRED){
            System.out.println("Campaign no longer running!");
            return null;
        }

        // If there can't be generated any more tickets
        if(currentVouchers == 0){
            System.out.println("Can't generate any more vouchers!");
            return null;
        }

        User user = null;

        // Determine if the email belongs to a user
        for(User u: VMS.getInstance().getUsers()){
            if(u.email.compareTo(email) == 0){
                user = u;
                break;
            }
        }

        // If the email doesn't belong to any user
        if(user == null){
            System.out.println("Wrong email!");
            return null;
        }

        // Determine the kind of voucher to generate
        Voucher v;
        if(voucherType.compareTo("GiftVoucher") == 0){
            v = new GiftVoucher(idValue, idValue, email, id, value);
        }
        else{
            v = new LoyaltyVoucher(idValue, idValue, email, id, value);
        }

        // Add voucher to the campaign
        campaignVouchers.addVoucher(v);

        // Add voucher to the user
        user.vouchers.addVoucher(v);

        // Add user to observers
        if(!getObservers().contains(user))
            addObserver(user);

        idValue++;
        currentVouchers--;
        return v;
    }

    // Redeem the vouchers specified by code==id, on the specified date
    public boolean redeemVoucher(String code, Date date){
        // If the campaign expired before the redeem date of the voucher
        if(date.compareTo(endDate) > 0){
            System.out.println("Can't redeem voucher! Campaign expired!");
            return false;
        }

        Voucher v = getVoucher(code);

        // If the voucher exists, set it as used
        try {
            v.usedDate = date;
            v.statusType = Voucher.VoucherStatusType.USED;
            System.out.println("Voucher used successfully");
        }
        catch (Exception NullPointerException){
            System.out.println("Voucher does not exist.");
            return false;
        }

        return true;
    }

    // Return a list of the Observers
    public List<User> getObservers(){
        return users;
    }

    public void editCampaign(Campaign campaign) throws ParseException {
        // Can't edit a campaign if there are more vouchers distributed than the new total of vouchers
        if(campaign.totalVouchers - this.totalVouchers + this.currentVouchers < 0){
            System.out.println("Can't edit campaign! More vouchers were distributed than the total number!");
            return;
        }

        // If the campaign didn't start
        if(this.campaignStatusType == CampaignStatusType.NEW){
            this.name = campaign.name;
            this.description = campaign.description;
            this.startDate = campaign.startDate;
            this.endDate = campaign.endDate;
            this.totalVouchers = campaign.totalVouchers;
        }

        // If the campaign started
        if(this.campaignStatusType == CampaignStatusType.STARTED){
            this.totalVouchers = campaign.totalVouchers;
            this.endDate = campaign.endDate;
        }

        // Update the number of the available for distribution vouchers
        this.currentVouchers = campaign.totalVouchers - this.totalVouchers + this.currentVouchers;

        // Get current date and format it following the pattern "yyyy-MM-dd HH:mm"
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dtf.format(now));

        // Notify all observers about the edit of the campaign
        Notification newNotification = new Notification(Notification.NotificationType.EDIT, date, id);
        notifyAllObservers(newNotification);
    }

    // Execute the marketing strategy
    public Voucher executeStrategy(){
        return strategyType.execute(this);
    }

    // Return a string describing the campaign
    public String toString(){
        return id + ", " + name + ", " + description + '\n';
    }
}
