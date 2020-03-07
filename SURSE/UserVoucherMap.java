import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserVoucherMap extends ArrayMap<Integer, List<Voucher>>{

    // Constructor
    public UserVoucherMap(){
        super();
    }

    // Add a new voucher
    public boolean addVoucher(Voucher v){
        // If the specified campaign ID doesn't exists, add a new Entry with the key being the campaign ID
        if(get(v.campaignID) == null){
            ArrayList<Voucher> tmp = new ArrayList<>();
            tmp.add(v);
            put(v.campaignID, tmp);
            return true;
        }
        else
        {
            return get(v.campaignID).add(v);
        }
    }

    // Return a string containing all the vouchers for every campaign
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator<ArrayMapEntry<Integer, List<Voucher>>> i = list.iterator();

        sb.append("[");
        // For every campaign
        while (i.hasNext()){
            Iterator<Voucher> j = i.next().value.iterator();

            // For every voucher in the current campaign
            while (j.hasNext()){
                sb.append("[" + j.next() + "]" + ((j.hasNext() || i.hasNext())? ", ":""));
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
