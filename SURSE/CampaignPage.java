import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CampaignPage extends Page implements ActionListener {
    AdminPage adminPage;
    Campaign campaign;
    VMS vms;

    // Buttons
    JButton returnAdminButton;
    JButton showVouchersButton;
    JButton generateVoucherButton;
    JButton redeemVoucherButton;
    JButton returnButton;
    JButton doneGenerateButton;
    JButton doneRedeemButton;
    JButton multipleDistributionButton;

    // TextFields
    JTextField emailTextField;
    JTextField voucherTypeTextField;
    JTextField valueTextField;
    JTextField idTextField;
    JTextField localDateTextField;

    // Labels
    JLabel emailLabel;
    JLabel voucherTypeLabel;
    JLabel valueLabel;
    JLabel idLabel;
    JLabel localDateLabel;

    // Current Vouchers List
    DefaultListModel<Voucher> currentVoucherList;

    // Panel
    JPanel panel;
    JScrollPane jScrollPane;

    public CampaignPage(String name, AdminPage adminPage, Campaign campaign, VMS vms){
        super(name, new Dimension(800, 800));
        this.adminPage = adminPage;
        this.campaign = campaign;
        this.vms = vms;

        this.adminPage.setVisible(false);

        // Color
        Color backColor = new Color(51, 58, 86);

        // Background Image
        ImageIcon background = new ImageIcon("../Design/Pages/CampaignPage.png");
        Image img = background.getImage();
        img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel back = new JLabel(background);
        back.setLabelFor(null);
        back.setLayout(new GridBagLayout());

        add(back);

        // ----------------

        // Buttons
        returnAdminButton = new JButton(new ImageIcon("../Design/Buttons/ReturnAdminButton.png"));
        initialiseButton(returnAdminButton, "../Design/Buttons/ReturnAdminButton.png", "../Design/Buttons/ReturnAdminHoverButton.png", "../Design/Buttons/ReturnAdminPressButton.png");
        showVouchersButton = new JButton(new ImageIcon("../Design/Buttons/ShowVouchersCButton.png"));
        initialiseButton(showVouchersButton, "../Design/Buttons/ShowVouchersCButton.png", "../Design/Buttons/ShowVouchersCHoverButton.png", "../Design/Buttons/ShowVouchersCPressButton.png");
        generateVoucherButton = new JButton(new ImageIcon("../Design/Buttons/GenerateVoucherButton.png"));
        initialiseButton(generateVoucherButton, "../Design/Buttons/GenerateVoucherButton.png", "../Design/Buttons/GenerateVoucherHoverButton.png", "../Design/Buttons/GenerateVoucherPressButton.png");
        redeemVoucherButton = new JButton(new ImageIcon("../Design/Buttons/RedeemVoucherButton.png"));
        initialiseButton(redeemVoucherButton, "../Design/Buttons/RedeemVoucherButton.png", "../Design/Buttons/RedeemVoucherHoverButton.png", "../Design/Buttons/RedeemVoucherPressButton.png");
        multipleDistributionButton = new JButton(new ImageIcon("../Design/Buttons/MVouchersButton.png"));
        initialiseButton(multipleDistributionButton, "../Design/Buttons/MVouchersButton.png", "../Design/Buttons/MVouchersHoverButton.png", "../Design/Buttons/MVouchersPressButton.png");
        returnButton = new JButton(new ImageIcon("../Design/Buttons/ReturnCButton.png"));
        initialiseButton(returnButton, "../Design/Buttons/ReturnCButton.png", "../Design/Buttons/ReturnCHoverButton.png", "../Design/Buttons/ReturnCPressButton.png");
        doneGenerateButton = new JButton(new ImageIcon("../Design/Buttons/DoneCButton.png"));
        initialiseButton(doneGenerateButton, "../Design/Buttons/DoneCButton.png", "../Design/Buttons/DoneCHoverButton.png", "../Design/Buttons/DoneCPressButton.png");
        doneRedeemButton = new JButton(new ImageIcon("../Design/Buttons/DoneCButton.png"));
        initialiseButton(doneRedeemButton, "../Design/Buttons/DoneCButton.png", "../Design/Buttons/DoneCHoverButton.png", "../Design/Buttons/DoneCPressButton.png");

        returnButton.setVisible(false);
        doneGenerateButton.setVisible(false);
        doneRedeemButton.setVisible(false);
        // ---------

        // Add Buttons to interface with constraints
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 15, 15);

        gbc.gridx = 1;
        gbc.gridy = 5;
        back.add(returnAdminButton, gbc);
        gbc.gridy = 6;
        back.add(showVouchersButton, gbc);
        gbc.gridy = 7;
        back.add(generateVoucherButton, gbc);
        gbc.gridy = 8;
        back.add(redeemVoucherButton, gbc);
        gbc.gridy = 9;
        back.add(returnButton, gbc);
        gbc.gridy = 10;
        back.add(doneGenerateButton, gbc);
        gbc.gridy = 11;
        back.add(doneRedeemButton, gbc);
        gbc.gridy = 12;
        back.add(multipleDistributionButton, gbc);

        // ------------------------------------------

        // TextFields
        emailTextField = new JTextField("USER_1_MAIL", 10);
        initialiseTextField(emailTextField, backColor);
        voucherTypeTextField = new JTextField("Voucher Type", 10);
        initialiseTextField(voucherTypeTextField, backColor);
        valueTextField = new JTextField("Value", 10);
        initialiseTextField(valueTextField, backColor);
        localDateTextField = new JTextField("Date", 10);
        initialiseTextField(localDateTextField, backColor);
        idTextField = new JTextField("ID", 10);
        initialiseTextField(idTextField, backColor);

        setTextFields(false);

        // ---------

        // Labels
        emailLabel = new JLabel("Email: ");
        initialiseLabel(emailLabel, backColor);
        voucherTypeLabel = new JLabel("Voucher Type: ");
        initialiseLabel(voucherTypeLabel, backColor);
        valueLabel = new JLabel("Value: ");
        initialiseLabel(valueLabel, backColor);
        localDateLabel = new JLabel("Date: ");
        initialiseLabel(localDateLabel, backColor);
        idLabel = new JLabel("ID: ");
        initialiseLabel(idLabel, backColor);

        setLabels(false);

        // -------

        // Add TextFields and Labels to interface (invisible) with constraints
        // TextFields
        gbc.gridx = 1;
        gbc.gridy = 0;
        back.add(emailTextField, gbc);
        gbc.gridy = 1;
        back.add(voucherTypeTextField, gbc);
        gbc.gridy = 2;
        back.add(valueTextField, gbc);
        gbc.gridy = 3;
        back.add(idTextField, gbc);
        gbc.gridy = 4;
        back.add(localDateTextField, gbc);

        // Labels
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        back.add(emailLabel, gbc);
        gbc.gridy = 1;
        back.add(voucherTypeLabel, gbc);
        gbc.gridy = 2;
        back.add(valueLabel, gbc);
        gbc.gridy = 3;
        back.add(idLabel, gbc);
        gbc.gridy = 4;
        back.add(localDateLabel, gbc);

        // ------------------------------------------------------------------

        // Panel
        panel = new JPanel();
        panel.setVisible(false);

        // Add panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        back.add(panel, gbc);

        // List
        currentVoucherList = new DefaultListModel<>();

        pack();
        setVisible(true);
    }

    // Disable or enable the button from the front page
    public void setButtons(boolean state){
        returnAdminButton.setVisible(state);
        showVouchersButton.setVisible(state);
        generateVoucherButton.setVisible(state);
        redeemVoucherButton.setVisible(state);
        multipleDistributionButton.setVisible(state);

        returnButton.setVisible(!state);
    }

    // Disable all button which aren't on the front page
    public void disableExtraButtons(){
        doneGenerateButton.setVisible(false);
        doneRedeemButton.setVisible(false);
    }

    // Disable or enable all TextFields
    public void setTextFields(boolean state){
        emailTextField.setVisible(state);
        voucherTypeTextField.setVisible(state);
        valueTextField.setVisible(state);
        idTextField.setVisible(state);
        localDateTextField.setVisible(state);
    }

    // Disable or enable all Labels
    public void setLabels(boolean state){
        emailLabel.setVisible(state);
        voucherTypeLabel.setVisible(state);
        valueLabel.setVisible(state);
        localDateLabel.setVisible(state);
        idLabel.setVisible(state);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(returnAdminButton)){
            // Return to the Admin Screen
            this.setVisible(false);
            adminPage.setVisible(true);
            adminPage.setLocation(this.getLocation());
        }
        if(actionEvent.getSource().equals(showVouchersButton)){
            // Display the vouchers in a table
            setButtons(false);

            // Create a list with all the vouchers in the campaign
            List<Voucher> tmpList = new ArrayList<>();
            for (ArrayMap<String, List<Voucher>>.ArrayMapEntry<String, List<Voucher>> stringListArrayMapEntry : campaign.campaignVouchers.list) {
                tmpList.addAll(stringListArrayMapEntry.value);
            }

            // Initialise the column names of the table as well as the data in the table
            String[] columnName = {"ID", "Type", "Mail", "Voucher", "Value", "Campaign ID", "Used Date"};
            Object[][] data = new Object[tmpList.size()][];

            int i = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Add each voucher to the table
            for(Voucher v: tmpList){
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

            pack();
            System.out.println(tmpList);
        }
        if(actionEvent.getSource().equals(generateVoucherButton)){
            // Display the interface for generating a new Voucher
            setButtons(false);
            setTextFields(true);
            setLabels(true);

            doneGenerateButton.setVisible(true);

            pack();
        }
        if(actionEvent.getSource().equals(redeemVoucherButton)){
            // Display the interface for redeeming a Voucher
            setButtons(false);

            idTextField.setVisible(true);
            idLabel.setVisible(true);
            localDateTextField.setVisible(true);
            localDateLabel.setVisible(true);

            returnButton.setVisible(true);
            doneRedeemButton.setVisible(true);

            pack();
        }
        if(actionEvent.getSource().equals(returnButton)){
            // Return to the main display of Campaign Page
            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            panel.removeAll();
            panel.setVisible(false);

            pack();
        }
        if(actionEvent.getSource().equals(doneGenerateButton)){
            // Generate a new Voucher
            setButtons(true);
            setTextFields(false);
            setLabels(false);

            doneGenerateButton.setVisible(false);

            campaign.generateVoucher(emailTextField.getText(), voucherTypeTextField.getText(), Float.parseFloat(valueTextField.getText()));

            pack();
        }
        if(actionEvent.getSource().equals(doneRedeemButton)){
            // Redeem specified voucher
            setButtons(true);
            setTextFields(false);
            setLabels(false);

            doneRedeemButton.setVisible(false);

            Date localDate;
            try {
                localDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(localDateTextField.getText());
            } catch (ParseException e) {
                System.out.println("Invalid Date format!");
                return;
            }

            // Redeem the voucher
            campaign.redeemVoucher(idTextField.getText(), localDate);

            pack();
        }
        if(actionEvent.getSource().equals(multipleDistributionButton)){
            // Generate vouchers for emails in a selected file
            System.out.println("Select the file to get vouchers!");

            // Select the file
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);

            if(returnVal ==  JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();

                Scanner sc;
                try {
                    sc = new Scanner(file);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                    return;
                }

                // Try to read the input from the file
                try {
                    int n = Integer.parseInt(sc.nextLine());
                    while (n != 0) {
                        String[] buffer = sc.nextLine().split(";");

                        campaign.generateVoucher(buffer[0], buffer[1], Float.parseFloat(buffer[2]));
                        n--;
                    }
                } catch (InputMismatchException e){
                    System.out.println("Wrong input! Maybe wrong file.");
                    return;
                } catch (NoSuchElementException e){
                    System.out.println("Corrupted file!");
                    return;
                }
            }
            else{
                System.out.println("Selecting file command cancelled!");
            }
        }
    }
}
