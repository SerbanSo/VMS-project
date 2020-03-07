import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GiftVoucher extends Voucher {
    float sum;

    // Constructor
    public GiftVoucher(int id, int voucherCode, String email, int campaignID, float sum) {
        super(id, voucherCode, email, campaignID);

        this.sum = sum;
    }

    // Return a string describing the GiftVoucher
    public String toString(){
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return id + ";" + statusType + ";" + email + ";" + sum + ";" + campaignID + ";" + (usedDate==null ? "null":dtf.format(usedDate));
    }
}
