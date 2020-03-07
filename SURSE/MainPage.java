import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;

public class MainPage extends Page implements ActionListener {
    VMS vms;
    User user;
    TimerTask timerTask;
    Timer timer;

    // Buttons
    JButton startButton;
    JButton loginButton;

    // TextFields
    JTextField loginUsername;
    JTextField loginPassword;

    // Labels
    JLabel userName;
    JLabel password;

    // Constructor, initialise the graphic interface
    public MainPage(String name){
        super(name, new Dimension(1600, 800));

        // Implementation of TimerTask to determine when a campaign starts or ends
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Date now = new Date();
                System.out.println(now);
                for(Campaign c:vms.getCampaigns()){
                    if(c.campaignStatusType.equals(Campaign.CampaignStatusType.NEW) && now.compareTo(c.startDate) > 0){
                        System.out.println("Campaign: " + c + " has started!");
                        c.campaignStatusType = Campaign.CampaignStatusType.STARTED;
                    }
                    if(!c.campaignStatusType.equals(Campaign.CampaignStatusType.EXPIRED) && !c.campaignStatusType.equals(Campaign.CampaignStatusType.CANCELLED) && now.compareTo(c.endDate) > 0){
                        System.out.println("Campaign: " + c + " has expired!");
                        c.campaignStatusType = Campaign.CampaignStatusType.EXPIRED;
                    }
                }
            }
        };

        // Color
        Color backColor = new Color(51, 58, 86);

        // Left Image
        ImageIcon background = new ImageIcon("../Design/Pages/MainPageLeft.png");
        Image img = background.getImage();
        img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel backLeft = new JLabel(background);
        backLeft.setLabelFor(null);
        backLeft.setLayout(new FlowLayout());

        // ---------------------

        // Right Image
        background = new ImageIcon("../Design/Pages/MainPageRight.png");
        img = background.getImage();
        img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel backRight = new JLabel(background);
        backRight.setLayout(new GridBagLayout());

        // ---------------------

        // Add to Frame the images
        add(backLeft);
        add(backRight);

        // -----------------------

        // Buttons
        startButton = new JButton(new ImageIcon("../Design/Buttons/StartAppButton.png"));
        initialiseButton(startButton, "../Design/Buttons/StartAppButton.png", "../Design/Buttons/StartAppButtonHover.png", "../Design/Buttons/StartAppButtonPress.png");
        loginButton = new JButton(new ImageIcon("../Design/Buttons/LoginButton.png"));
        initialiseButton(loginButton, "../Design/Buttons/LoginButton.png", "../Design/Buttons/LoginButtonHover.png", "../Design/Buttons/LoginButtonPress.png");

        loginButton.setVisible(false);

        // --------

        // JTextFields
        loginUsername = new JTextField("USER_1", 10);
        initialiseTextField(loginUsername, backColor);
        loginPassword = new JTextField("USER_PASS", 10);
        initialiseTextField(loginPassword, backColor);

        loginUsername.setVisible(false);
        loginPassword.setVisible(false);

        // ----------

        // Label
        userName = new JLabel("Username:");
        initialiseLabel(userName, backColor);
        password = new JLabel("Password:");
        initialiseLabel(password, backColor);

        userName.setVisible(false);
        password.setVisible(false);

        // -----

        // Add buttons with constraints
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        backRight.add(startButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backRight.add(userName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        backRight.add(loginUsername, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backRight.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        backRight.add(loginPassword, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        backRight.add(loginButton, gbc);

        // -----------------------------

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(startButton)){
            System.out.println("Loading Campaigns and Users...");
            vms = VMS.getInstance();

            int n;
            String buffer;
            Date start = null, end = null;

            File campaignsFile = new File("../campaigns.txt");
            File usersFile = new File("../users.txt");

            // Reading campaign from campaign.txt file
            Scanner sc = null;
            try {
                sc = new Scanner(campaignsFile);
            } catch (FileNotFoundException e) {
                System.out.println("No such file");
                e.printStackTrace();
            }
            n = sc.nextInt();
            sc.nextLine();
            sc.nextLine();

            while(n != 0){
                buffer = sc.nextLine();
                String[] ss = buffer.split(";");
                try {
                    start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ss[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Strategy strategy;
                if(ss[6].compareTo("A") == 0){
                    strategy = new A();
                }
                else if(ss[6].compareTo("B") == 0){
                    strategy = new B();
                }
                else {
                    strategy = new C();
                }

                Campaign campaign = new Campaign(Integer.parseInt(ss[0]), ss[1], ss[2], start, end, Integer.parseInt(ss[5]), strategy);

                vms.addCampaign(campaign);
                n--;
            }
            sc.close();

            // ---------------------------------

            // Reading the Users from users.txt file
            try {
                sc = new Scanner(usersFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            n = sc.nextInt();
            sc.nextLine();

            while (n != 0){
                buffer = sc.nextLine();
                String[] ss = buffer.split(";");
                User.UserType userType;

                if(ss[4].compareTo("ADMIN") == 0){
                    userType = User.UserType.ADMIN;
                }
                else{
                    userType = User.UserType.GUEST;
                }

                User user = new User(Integer.parseInt(ss[0]), ss[1], ss[2], ss[3], userType);

                vms.addUser(user);
                n--;
            }

            sc.close();

            // ---------------------------------

            System.out.println("Done!");

            // Enable Login Form
            startButton.setVisible(false);
            loginUsername.setVisible(true);
            loginPassword.setVisible(true);
            loginButton.setVisible(true);
            userName.setVisible(true);
            password.setVisible(true);

            // -----------------

            // TimerTask
            // Timer starts after 5s and has a period of 10s
            timer = new Timer();
            timer.schedule(timerTask, 5000, 10000);
        }

        if(actionEvent.getSource().equals(loginButton)){
            System.out.println("Trying to login...");

            user = getLoginUser(loginUsername.getText(), loginPassword.getText());

            if(user != null){
                if(user.type.equals(User.UserType.ADMIN)){
                    System.out.println("Loading ADMIN page...");

                    AdminPage adminPage = new AdminPage("AdminPage", user, this, vms);
                }
                else if(user.type.equals(User.UserType.GUEST)){
                    System.out.println("Loading GUEST page...");

                    GuestPage guestPage = new GuestPage("GuestPage", user, this);
                }
            }
        }
    }

    // Determine if the Login credentials correspond to an user
    public User getLoginUser(String name, String password){
        for(User u: vms.getUsers()){
            if(u.name.compareTo(name) == 0){
                if(u.password.compareTo(password) == 0){
                    System.out.println("Login successful!");
                    return u;
                }
            }
        }

        System.out.println("Login failed!");
        return null;
    }
}
