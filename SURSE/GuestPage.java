import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuestPage extends Page implements ActionListener {
    User user;
    MainPage mainPage;

    // Buttons
    JButton returnLoginButton;
    JButton campaignsShowButton;
    JButton vouchersShowButton;
    JButton returnButton;
    JButton showNotificationsButton;

    // Campaign and voucher Lists
    List<Campaign> campaignList;
    List<Voucher> voucherList;
    List<Notification> notificationsList;

    // Panel
    JPanel panel;
    JScrollPane jScrollPane;

    // Constructor, initialise the graphic interface
    public GuestPage(String name, User user, MainPage mainPage){
        super(name, new Dimension(800, 800));
        this.user = user;
        this.mainPage = mainPage;

        // Cover the previous page
        this.mainPage.setVisible(false);

        // Background Image
        ImageIcon background = new ImageIcon("../Design/Pages/GuestPage.png");
        Image img = background.getImage();
        img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel back = new JLabel(background);
        back.setLabelFor(null);
        back.setLayout(new GridBagLayout());

        add(back);

        // ----------------

        // Initialise Buttons
        returnLoginButton = new JButton(new ImageIcon("../Design/Buttons/ReturnLoginGButton.png"));
        initialiseButton(returnLoginButton,"../Design/Buttons/ReturnLoginGButton.png", "../Design/Buttons/ReturnLoginGHoverButton.png", "../Design/Buttons/ReturnLoginGPressButton.png");
        campaignsShowButton = new JButton(new ImageIcon("../Design/Buttons/ShowCampaignsButton.png"));
        initialiseButton(campaignsShowButton, "../Design/Buttons/ShowCampaignsButton.png","../Design/Buttons/ShowCampaignsHoverButton.png", "../Design/Buttons/ShowCampaignsPressButton.png");
        vouchersShowButton = new JButton(new ImageIcon("../Design/Buttons/ShowVouchersButton.png"));
        initialiseButton(vouchersShowButton, "../Design/Buttons/ShowVouchersButton.png", "../Design/Buttons/ShowVouchersHoverButton.png", "../Design/Buttons/ShowVouchersPressButton.png");
        returnButton = new JButton(new ImageIcon("../Design/Buttons/ReturnGButton.png"));
        initialiseButton(returnButton, "../Design/Buttons/ReturnGButton.png", "../Design/Buttons/ReturnGHoverButton.png", "../Design/Buttons/ReturnGPressButton.png");
        showNotificationsButton = new JButton(new ImageIcon("../Design/Buttons/ShowNotButton.png"));
        initialiseButton(showNotificationsButton, "../Design/Buttons/ShowNotButton.png", "../Design/Buttons/ShowNotHoverButton.png", "../Design/Buttons/ShowNotPressButton.png");

        returnButton.setVisible(false);

        // -----------------

        // Add Buttons and constraints
        // Position the button on the screen
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 1;
        back.add(returnLoginButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        back.add(campaignsShowButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        back.add(vouchersShowButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        back.add(returnButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        back.add(showNotificationsButton, gbc);

        // --------------------------

        // Processing for display the campaign list, voucher list and notification list
        campaignList = new ArrayList<>();
        voucherList = new ArrayList<>();
        notificationsList = new ArrayList<>();

        Iterator<ArrayMap<Integer, List<Voucher>>.ArrayMapEntry<Integer, List<Voucher>>> i = user.vouchers.list.iterator();
        System.out.println(user.vouchers);

        while (i.hasNext()){
            ArrayMap<Integer, List<Voucher>>.ArrayMapEntry<Integer, List<Voucher>> arrayMapEntry = i.next();
            campaignList.add(0, mainPage.vms.getCampaign(arrayMapEntry.getKey()));
            voucherList.addAll(arrayMapEntry.getValue());
        }

        notificationsList.addAll(user.notifications);

        // ----------------------------------------------------------------------------

        // Panel
        panel = new JPanel();
        panel.setVisible(false);

        // Add Panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        back.add(panel, gbc);

        pack();
        setVisible(true);
    }

    // Display or cover the buttons
    public void setButtons(boolean state){
        campaignsShowButton.setVisible(state);
        vouchersShowButton.setVisible(state);
        showNotificationsButton.setVisible(state);
        returnLoginButton.setVisible(state);

        returnButton.setVisible(!state);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(returnLoginButton)){
            // Return to the Login Screen
            this.setVisible(false);
            mainPage.setVisible(true);
            mainPage.setLocation(this.getLocation());
        }
        if(actionEvent.getSource().equals(campaignsShowButton)){
            // Display the campaign in a table
            setButtons(false);

            // Initialise the column names of the table as well as the data in the table
            String[] columnName = {"ID", "Name", "Description", "Start Date", "End Date", "Total Vouchers", "Current Vouchers"};
            Object[][] data = new Object[campaignList.size()][];

            int i = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Add each campaign to the table
            for(Campaign c: campaignList){
                data[i] = new Object[7];

                data[i][0] = c.id;
                data[i][1] = c.name;
                data[i][2] = c.description;
                data[i][3] = dateFormat.format(c.startDate);
                data[i][4] = dateFormat.format(c.endDate);
                data[i][5] = c.totalVouchers;
                data[i][6] = c.currentVouchers;

                i++;
            }

            JTable table = new JTable(data, columnName);
            initialiseTableCampaign(table);
            // -------------------------------------------------------------------------

            // Make the table scrollable
            jScrollPane = new JScrollPane(table);
            jScrollPane.setPreferredSize(new Dimension(700, 450));

            // Add the scrollable table to the panel
            panel.add(jScrollPane);
            panel.setVisible(true);

            System.out.println(campaignList);
        }
        if(actionEvent.getSource().equals(vouchersShowButton)){
            // Display the vouchers in a table
            setButtons(false);

            // Initialise the column names of the table as well as the data in the table
            String[] columnName = {"ID", "Type", "Mail", "Voucher","Value", "Campaign ID", "Used Date"};
            Object[][] data = new Object[voucherList.size()][];

            int i = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Add each vouchers to the table
            for(Voucher v: voucherList){
                data[i] = new Object[7];

                data[i][0] = v.id;
                data[i][1] = v.statusType;
                data[i][2] = v.email;
                data[i][3] = v instanceof GiftVoucher ? "GiftVoucher" : "LoyaltyVoucher";
                data[i][4] = v instanceof GiftVoucher ? ((GiftVoucher) v).sum : ((LoyaltyVoucher) v).discount;
                data[i][5] = v.campaignID;
                data[i][6] = v.usedDate == null ? "null":dateFormat.format(v.usedDate);

                i++;
            }

            JTable table = new JTable(data, columnName);
            initialiseTableVoucher(table);
            // -------------------------------------------------------------------------

            // Make the table scrollable
            jScrollPane = new JScrollPane(table);
            jScrollPane.setPreferredSize(new Dimension(500, 400));

            // Add the scrollable table to the panel
            panel.add(jScrollPane);
            panel.setVisible(true);

            System.out.println(voucherList);
        }
        if(actionEvent.getSource().equals(showNotificationsButton)){
            // Display the notifications in a table
            setButtons(false);

            // Initialise the column names of the table as well as the data in the table
            String[] columnName = {"ID", "Voucher ID", "Date", "Type"};
            Object[][] data = new Object[notificationsList.size()][];

            int i = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Add each notifications to the table
            for(Notification n: notificationsList){
                data[i] = new Object[4];

                data[i][0] = n.campaignID;
                data[i][1] = n.vouchers;
                data[i][2] = dateFormat.format(n.notificationDate);
                data[i][3] = n.type;

                i++;
            }

            JTable table = new JTable(data, columnName);
            initialiseTableNotification(table);
            // -------------------------------------------------------------------------

            // Make the table scrollable
            jScrollPane = new JScrollPane(table);
            jScrollPane.setPreferredSize(new Dimension(400, 450));

            // Add the scrollable table to the panel
            panel.add(jScrollPane);
            panel.setVisible(true);

            System.out.println(user.notifications);
        }
        if(actionEvent.getSource().equals(returnButton)){
            // Return to the main display of Guest Page
            setButtons(true);

            panel.removeAll();
            panel.setVisible(false);
        }
    }
}
