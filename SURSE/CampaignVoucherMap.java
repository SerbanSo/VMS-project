import java.util.ArrayList;
import java.util.List;

public class CampaignVoucherMap extends ArrayMap<String, List<Voucher>> {

    // Constructor
    public CampaignVoucherMap(){
        super();
    }

    // Add a new voucher
    public boolean addVoucher(Voucher v){
        // If the specified email doesn't exists, add a new Entry with the key being the email
        if(get(v.email) == null){
            ArrayList<Voucher> tmp = new ArrayList<>();
            tmp.add(v);
            put(v.email, tmp);
            return true;
        }
        else
        {
            return get(v.email).add(v);
        }
    }
}
