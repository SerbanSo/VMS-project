import java.util.Iterator;
import java.util.Random;

public interface Strategy {
    Voucher execute(Campaign c);
}

class A implements Strategy{

    @Override
    public Voucher execute(Campaign c) {
        Random random = new Random();
        User winner = c.getObservers().get(random.nextInt(c.users.size()));
        Voucher v = c.generateVoucher(winner.email, "GiftVoucher", 100);

        return v;
    }
}

class B implements Strategy{

    @Override
    public Voucher execute(Campaign c) {
        int max = 0;
        User winner = c.getObservers().get(0);

        for(User u: c.getObservers()){
            int a = 0;

            for(Voucher v: u.vouchers.get(c.id)){
                if(v.statusType.equals(Voucher.VoucherStatusType.USED)){
                    a++;
                }
            }

            if(a > max){
                winner = u;
                max = a;
            }
        }

        Voucher v = c.generateVoucher(winner.email, "LoyaltyVoucher", 50);

        return v;
    }
}

class C implements Strategy {

    @Override
    public Voucher execute(Campaign c) {
        Iterator<User> i = c.getObservers().iterator();

        User winner = i.next();
        int min = winner.vouchers.get(c.id).size();

        while (i.hasNext()){
            User tmp = i.next();

            if(tmp.vouchers.get(c.id).size() < min){
                winner = tmp;
                min = tmp.vouchers.get(c.id).size();
            }
        }

        Voucher v = c.generateVoucher(winner.email, "GiftVoucher", 100);

        return v;
    }
}
