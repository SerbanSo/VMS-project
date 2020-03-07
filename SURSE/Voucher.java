import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Voucher {
    int id;
    int voucherCode;
    int campaignID;

    VoucherStatusType statusType;

    Date usedDate;

    String email;

    enum VoucherStatusType{
        USED,
        UNUSED
    }

    // Constructor
    public Voucher(int id, int voucherCode, String email,int campaignID){
        this.id = id;
        this.voucherCode = voucherCode;
        this.statusType = VoucherStatusType.UNUSED;
        this.usedDate = null;
        this.email = email;
        this.campaignID = campaignID;
    }

    // Return a string describing the current voucher
    public String toString(){
        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return id + ";" + statusType + ";" + email + ";" + campaignID + ";" + (usedDate==null? "null":dtf.format(usedDate));
    }
}
