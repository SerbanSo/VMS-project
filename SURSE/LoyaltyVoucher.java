import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LoyaltyVoucher extends Voucher{
    float discount;

    // Constructor
    public LoyaltyVoucher(int id, int voucherCode, String email, int campaignID, float discount) {
        super(id, voucherCode, email, campaignID);

        this.discount = discount;
    }

    // Return a string describing the GiftVoucher
    public String toString() {
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return id + ";" + statusType + ";" + email + ";" + discount + ";" + campaignID + ";" + (usedDate==null? "null":dtf.format(usedDate));
    }
}
