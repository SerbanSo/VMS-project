import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException, ParseException {
        VMS vms = VMS.getInstance();
        int n;
        String buffer;
        Date start, end;

        File campaignsFile = new File(args[0]);
        File usersFile = new File(args[1]);
        File eventsFile = new File(args[2]);

        // Reading the Campaigns from the campaign.txt file
        Scanner sc = new Scanner(campaignsFile);
        n = sc.nextInt();
        sc.nextLine();
        sc.nextLine();

        while (n != 0) {
            buffer = sc.nextLine();
            String[] ss = buffer.split(";");
            start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[3]);
            end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[4]);

            Strategy strategy;
            if (ss[6].compareTo("A") == 0) {
                strategy = new A();
            } else if (ss[6].compareTo("B") == 0) {
                strategy = new B();
            } else {
                strategy = new C();
            }

            Campaign campaign = new Campaign(Integer.parseInt(ss[0]), ss[1], ss[2], start, end, Integer.parseInt(ss[5]), strategy);

            vms.addCampaign(campaign);
            n--;
        }
        sc.close();

        // Reading the Users from the user.txt file
        sc = new Scanner(usersFile);

        n = sc.nextInt();
        sc.nextLine();

        while (n != 0) {
            buffer = sc.nextLine();
            String[] ss = buffer.split(";");
            User.UserType userType;

            if (ss[4].compareTo("ADMIN") == 0) {
                userType = User.UserType.ADMIN;
            } else {
                userType = User.UserType.GUEST;
            }

            User user = new User(Integer.parseInt(ss[0]), ss[1], ss[2], ss[3], userType);

            vms.addUser(user);
            n--;
        }

        sc.close();

        // Reading the Events from the events.txt file
        sc = new Scanner(eventsFile);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sc.nextLine());
        n = sc.nextInt();
        sc.nextLine();

        while (n != 0) {
            buffer = sc.nextLine();
            String[] ss = buffer.split(";");
            int userID = Integer.parseInt(ss[0]);
            User user = vms.getUser(userID);
            String event = ss[1];

            if (event.compareTo("addCampaign") == 0) {
                if (user.type.equals(User.UserType.ADMIN)) {
                    start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[5]);
                    end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[6]);

                    Strategy strategy;
                    if (ss[6].compareTo("A") == 0) {
                        strategy = new A();
                    } else if (ss[6].compareTo("B") == 0) {
                        strategy = new B();
                    } else {
                        strategy = new C();
                    }

                    vms.addCampaign(new Campaign(Integer.parseInt(ss[2]), ss[3], ss[4], start, end, Integer.parseInt(ss[7]), strategy));
                }
            } else if (event.compareTo("editCampaign") == 0) {
                if (user.type.equals(User.UserType.ADMIN)) {
                    start = new SimpleDateFormat("yyy-MM-dd HH:mm").parse(ss[5]);
                    end = new SimpleDateFormat("yyy-MM-dd HH:mm").parse(ss[6]);
                    Campaign c = new Campaign(Integer.parseInt(ss[2]), ss[3], ss[4], start, end, Integer.parseInt(ss[7]), null);
                    vms.updateCampaign(Integer.parseInt(ss[2]), c);
                }
            } else if (event.compareTo("cancelCampaign") == 0) {
                if (user.type.equals(User.UserType.ADMIN)) {
                    vms.cancelCampaign(Integer.parseInt(ss[2]));
                }
            } else if (event.compareTo("generateVoucher") == 0) {
                if(user.type.equals(User.UserType.ADMIN)) {
                    Campaign c = vms.getCampaign(Integer.parseInt(ss[2]));
                    Voucher v = c.generateVoucher(ss[3], ss[4], Integer.parseInt(ss[5]));
                }
            } else if (event.compareTo("redeemVoucher") == 0) {
                if (user.type.equals(User.UserType.ADMIN)) {
                    Date localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[4]);
                    Campaign c = vms.getCampaign(Integer.parseInt(ss[2]));

                    c.redeemVoucher(ss[3], localDate);
                }
            } else if (event.compareTo("getVouchers") == 0) {
                if(user.type.equals(User.UserType.GUEST)){
                    System.out.println(user.vouchers);
                }
            } else if (event.compareTo("getObservers") == 0) {
                if (user.type.equals(User.UserType.ADMIN)) {
                    System.out.println(vms.getCampaign(Integer.parseInt(ss[2])).getObservers());
                }
            } else if (event.compareTo("getNotifications") == 0) {
                if (user.type.equals(User.UserType.GUEST)) {
                    System.out.println(user.printNotifications());
                }
            } else if (event.compareTo("getVoucher") == 0) {
                if (user.type == User.UserType.ADMIN) {
                    Campaign c = vms.getCampaign(Integer.parseInt(ss[2]));

                    Voucher v = c.executeStrategy();
                    if (v != null)
                        System.out.println("[" + v + "]");
                }
            }

            n--;
        }

        sc.close();
    }
}