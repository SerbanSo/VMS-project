import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPage extends Page implements ActionListener {
    User user;
    MainPage mainPage;
    VMS vms;

    // Buttons
    JButton returnLoginButton;
    JButton currentCampaignsButton;
    JButton addCampaignButton;
    JButton editCampaignButton;
    JButton cancelCampaignButton;
    JButton detailsCampaignButton;
    JButton returnButton;
    JButton doneAddButton;
    JButton doneEditButton;
    JButton doneCancelButton;
    JButton doneDetailsButton;
    JButton strategyButton;
    JButton doneStrategyButton;

    // Panel
    JPanel panel;
    JScrollPane jScrollPane;

    // Text Fields
    JTextField campaignIDTextField;
    JTextField campaignNameTextField;
    JTextField campaignDescriptionTextField;
    JTextField startDateTextField;
    JTextField endDateTextField;
    JTextField ticketNumberTextField;
    JTextField strategyTextField;

    // Labels
    JLabel campaignIDLabel;
    JLabel campaignNameLabel;
    JLabel campaignDescriptionLabel;
    JLabel startDateLabel;
    JLabel endDateLabel;
    JLabel ticketNumberLabel;
    JLabel strategyLabel;

    public AdminPage(String name, User user, MainPage mainPage, VMS vms){
        super(name, new Dimension(800, 800));
        this.user = user;
        this.mainPage = mainPage;
        this.vms = vms;

        this.mainPage.setVisible(false);

        // Color
        Color backColor = new Color(255, 245, 222);

        // Background Image
        ImageIcon background = new ImageIcon("../Design/Pages/AdminPage.png");
        Image img = background.getImage();
        img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel back = new JLabel(background);
        back.setLabelFor(null);
        back.setLayout(new GridBagLayout());

        add(back);

        // ----------------

        // Buttons
        returnLoginButton = new JButton(new ImageIcon("../Design/Buttons/ReturnLoginAButton.png"));
        initialiseButton(returnLoginButton, "../Design/Buttons/ReturnLoginAButton.png", "../Design/Buttons/ReturnLoginAHoverButton.png", "../Design/Buttons/ReturnLoginAPressButton.png");
        currentCampaignsButton = new JButton(new ImageIcon("../Design/Buttons/ShowCurrentCampaignsButton.png"));
        initialiseButton(currentCampaignsButton, "../Design/Buttons/ShowCurrentCampaignsButton.png", "../Design/Buttons/ShowCurrentCampaignsHoverButton.png", "../Design/Buttons/ShowCurrentCampaignsPressButton.png");
        addCampaignButton = new JButton(new ImageIcon("../Design/Buttons/AddCampaignButton.png"));
        initialiseButton(addCampaignButton, "../Design/Buttons/AddCampaignButton.png", "../Design/Buttons/AddCampaignHoverButton.png", "../Design/Buttons/AddCampaignPressButton.png");
        editCampaignButton = new JButton(new ImageIcon("../Design/Buttons/EditCampaignButton.png"));
        initialiseButton(editCampaignButton, "../Design/Buttons/EditCampaignButton.png", "../Design/Buttons/EditCampaignHoverButton.png", "../Design/Buttons/EditCampaignPressButton.png");
        cancelCampaignButton = new JButton(new ImageIcon("../Design/Buttons/CancelCampaignButton.png"));
        initialiseButton(cancelCampaignButton, "../Design/Buttons/CancelCampaignButton.png", "../Design/Buttons/CancelCampaignHoverButton.png", "../Design/Buttons/CancelCampaignPressButton.png");
        detailsCampaignButton = new JButton(new ImageIcon("../Design/Buttons/ShowDetailsButton.png"));
        initialiseButton(detailsCampaignButton, "../Design/Buttons/ShowDetailsButton.png", "../Design/Buttons/ShowDetailsHoverButton.png", "../Design/Buttons/ShowDetailsPressButton.png");
        returnButton = new JButton(new ImageIcon("../Design/Buttons/ReturnAButton.png"));
        initialiseButton(returnButton, "../Design/Buttons/ReturnAButton.png", "../Design/Buttons/ReturnAHoverButton.png", "../Design/Buttons/ReturnAPressButton.png");
        doneAddButton = new JButton(new ImageIcon("../Design/Buttons/DoneAButton.png"));
        initialiseButton(doneAddButton, "../Design/Buttons/DoneAButton.png", "../Design/Buttons/DoneAHoverButton.png", "../Design/Buttons/DoneAPressButton.png");
        doneEditButton = new JButton(new ImageIcon("../Design/Buttons/DoneAButton.png"));
        initialiseButton(doneEditButton, "../Design/Buttons/DoneAButton.png", "../Design/Buttons/DoneAHoverButton.png", "../Design/Buttons/DoneAPressButton.png");
        doneCancelButton = new JButton(new ImageIcon("../Design/Buttons/DoneAButton.png"));
        initialiseButton(doneCancelButton, "../Design/Buttons/DoneAButton.png", "../Design/Buttons/DoneAHoverButton.png", "../Design/Buttons/DoneAPressButton.png");
        doneDetailsButton = new JButton(new ImageIcon("../Design/Buttons/DoneAButton.png"));
        initialiseButton(doneDetailsButton, "../Design/Buttons/DoneAButton.png", "../Design/Buttons/DoneAHoverButton.png", "../Design/Buttons/DoneAPressButton.png");
        strategyButton = new JButton(new ImageIcon("../Design/Buttons/StrategyButton.png"));
        initialiseButton(strategyButton, "../Design/Buttons/StrategyButton.png", "../Design/Buttons/StrategyHoverButton.png", "../Design/Buttons/StrategyPressButton.png");
        doneStrategyButton = new JButton(new ImageIcon("../Design/Buttons/DoneAButton.png"));
        initialiseButton(doneStrategyButton, "../Design/Buttons/DoneAButton.png", "../Design/Buttons/DoneAHoverButton.png", "../Design/Buttons/DoneAPressButton.png");

        returnButton.setVisible(false);
        doneAddButton.setVisible(false);
        doneEditButton.setVisible(false);
        doneCancelButton.setVisible(false);
        doneDetailsButton.setVisible(false);
        doneStrategyButton.setVisible(false);

        // ---------

        // Add buttons to interface with constraints
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 15, 15);

        gbc.gridx = 1;
        gbc.gridy = -1;

        gbc.gridx = 1;
        gbc.gridy = 7;
        back.add(returnLoginButton, gbc);
        gbc.gridy = 8;
        back.add(currentCampaignsButton, gbc);
        gbc.gridy = 9;
        back.add(addCampaignButton, gbc);
        gbc.gridy = 10;
        back.add(editCampaignButton, gbc);
        gbc.gridy = 11;
        back.add(cancelCampaignButton, gbc);
        gbc.gridy = 12;
        back.add(detailsCampaignButton, gbc);
        gbc.gridy = 13;
        back.add(strategyButton, gbc);
        gbc.gridy = 14;
        back.add(returnButton, gbc);
        gbc.gridy = 15;
        back.add(doneAddButton, gbc);
        gbc.gridy = 17;
        back.add(doneEditButton, gbc);
        gbc.gridy = 18;
        back.add(doneCancelButton, gbc);
        gbc.gridy = 19;
        back.add(doneDetailsButton, gbc);
        gbc.gridy = 20;
        back.add(doneStrategyButton, gbc);

        // -----------------------------------------

        // TextFields
        campaignIDTextField = new JTextField("ID", 10);
        initialiseTextField(campaignIDTextField, backColor);
        campaignNameTextField = new JTextField("Name", 10);
        initialiseTextField(campaignNameTextField, backColor);
        campaignDescriptionTextField = new JTextField("Description", 10);
        initialiseTextField(campaignDescriptionTextField, backColor);
        startDateTextField = new JTextField("2018-08-03 13:00", 10);
        initialiseTextField(startDateTextField, backColor);
        endDateTextField = new JTextField("2018-08-03 13:00", 10);
        initialiseTextField(endDateTextField, backColor);
        ticketNumberTextField = new JTextField("Vouchers Number", 10);
        initialiseTextField(ticketNumberTextField, backColor);
        strategyTextField = new JTextField("Strategy", 10);
        initialiseTextField(strategyTextField, backColor);

        setTextFields(false);

        // -----------

        // Labels
        campaignIDLabel = new JLabel("Campaign ID: ");
        initialiseLabel(campaignIDLabel, backColor);
        campaignNameLabel = new JLabel("Campaign Name: ");
        initialiseLabel(campaignNameLabel, backColor);
        campaignDescriptionLabel = new JLabel("Campaign Description: ");
        initialiseLabel(campaignDescriptionLabel, backColor);
        startDateLabel = new JLabel("Start Date: ");
        initialiseLabel(startDateLabel, backColor);
        endDateLabel = new JLabel("End Date: ");
        initialiseLabel(endDateLabel, backColor);
        ticketNumberLabel = new JLabel("Vouchers Number: ");
        initialiseLabel(ticketNumberLabel, backColor);
        strategyLabel = new JLabel("Strategy: ");
        initialiseLabel(strategyLabel, backColor);

        setLabels(false);

        // ------

        // Add TextFields and Labels to interface (invisible) with constraints
        // TextFields
        gbc.gridx = 1;
        gbc.gridy = 0;
        back.add(campaignIDTextField, gbc);
        gbc.gridy = 1;
        back.add(campaignNameTextField, gbc);
        gbc.gridy = 2;
        back.add(campaignDescriptionTextField, gbc);
        gbc.gridy = 3;
        back.add(startDateTextField, gbc);
        gbc.gridy = 4;
        back.add(endDateTextField, gbc);
        gbc.gridy = 5;
        back.add(ticketNumberTextField, gbc);
        gbc.gridy = 6;
        back.add(strategyTextField, gbc);

        // Labels
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        back.add(campaignIDLabel, gbc);
        gbc.gridy = 1;
        back.add(campaignNameLabel, gbc);
        gbc.gridy = 2;
        back.add(campaignDescriptionLabel, gbc);
        gbc.gridy = 3;
        back.add(startDateLabel, gbc);
        gbc.gridy = 4;
        back.add(endDateLabel, gbc);
        gbc.gridy = 5;
        back.add(ticketNumberLabel, gbc);
        gbc.gridy = 6;
        back.add(strategyLabel, gbc);

        // -------------------------------------------------------------------

        // Panel
        panel = new JPanel();
        panel.setVisible(false);

        // Add panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        back.add(panel, gbc);

        pack();
        setVisible(true);
    }

    // Disable the "done" buttons
    public void disableExtraButtons(){
        doneAddButton.setVisible(false);
        doneEditButton.setVisible(false);
        doneCancelButton.setVisible(false);
        doneDetailsButton.setVisible(false);
        doneStrategyButton.setVisible(false);
    }

    // Disable or enable the button from the front page
    public void setButtons(boolean state){
        returnLoginButton.setVisible(state);
        currentCampaignsButton.setVisible(state);
        addCampaignButton.setVisible(state);
        editCampaignButton.setVisible(state);
        cancelCampaignButton.setVisible(state);
        detailsCampaignButton.setVisible(state);
        strategyButton.setVisible(state);

        returnButton.setVisible(!state);
    }

    // Disable or enable all TextFields
    public void setTextFields(boolean state){
        campaignIDTextField.setVisible(state);
        campaignNameTextField.setVisible(state);
        campaignDescriptionTextField.setVisible(state);
        startDateTextField.setVisible(state);
        endDateTextField.setVisible(state);
        ticketNumberTextField.setVisible(state);
        strategyTextField.setVisible(state);
    }

    // Disable or enable all Labels
    public void setLabels(boolean state){
        campaignIDLabel.setVisible(state);
        campaignNameLabel.setVisible(state);
        campaignDescriptionLabel.setVisible(state);
        startDateLabel.setVisible(state);
        endDateLabel.setVisible(state);
        ticketNumberLabel.setVisible(state);
        strategyLabel.setVisible(state);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(returnLoginButton)){
            // Return to the Login Screen
            this.setVisible(false);
            mainPage.setVisible(true);
            mainPage.setLocation(this.getLocation());
        }
        if(actionEvent.getSource().equals(currentCampaignsButton)){
            // Display the campaign in a table
            setButtons(false);

            // Initialise the column names of the table as well as the data in the table
            String[] columnName = {"ID", "Name", "Description", "Start Date", "End Date", "Total Vouchers", "Current Vouchers"};
            Object[][] data = new Object[vms.getCampaigns().size()][];

            int i = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Add each campaign to the table
            for(Campaign c: vms.getCampaigns()){
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

            pack();
        }
        if(actionEvent.getSource().equals(addCampaignButton)){
            // Display the interface for adding a new Campaign
            setButtons(false);
            setTextFields(true);
            setLabels(true);

            doneAddButton.setVisible(true);

            pack();
        }
        if(actionEvent.getSource().equals(editCampaignButton)){
            // Display the interface for editing a new Campaign
            setButtons(false);
            setTextFields(true);
            setLabels(true);

            doneEditButton.setVisible(true);
            strategyTextField.setVisible(false);
            strategyLabel.setVisible(false);

            pack();
        }
        if(actionEvent.getSource().equals(cancelCampaignButton)){
            // Display the interface for canceling a new Campaign
            setButtons(false);

            doneCancelButton.setVisible(true);
            campaignIDTextField.setVisible(true);
            campaignIDLabel.setVisible(true);

            pack();
        }
        if(actionEvent.getSource().equals(detailsCampaignButton)){
            // Display the interface to access the details of a Campaign
            setButtons(false);

            doneDetailsButton.setVisible(true);
            campaignIDTextField.setVisible(true);
            campaignIDLabel.setVisible(true);

            pack();
        }
        if(actionEvent.getSource().equals(returnButton)){
            // Return to the main display of Admin Page
            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            panel.removeAll();
            panel.setVisible(false);

            pack();
        }
        if(actionEvent.getSource().equals(doneAddButton)){
            // Add the Campaign to VMS

            // Campaign already exists
            if(vms.getCampaign(Integer.parseInt(campaignIDTextField.getText())) != null){
                System.out.println("Campaign already exists!");
                return;
            }

            Date startDate, endDate;
            Strategy strategy;

            // Try to take the Date in the format specified
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDateTextField.getText());
                endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDateTextField.getText());
            } catch (ParseException e) {
                System.out.println("Incorrect Start/End Date!");
                return;
            }

            // Determine the strategy
            switch (strategyTextField.getText()){
                case "A":
                    strategy = new A();
                    break;
                case "B":
                    strategy = new B();
                    break;
                case "C":
                    strategy = new C();
                    break;
                default:
                    System.out.println("Invalid Strategy!");
                    return;
            }

            // Add the new Campaign using the inputs gotten from the Text Fields
            Campaign tmp = new Campaign(Integer.parseInt(campaignIDTextField.getText()), campaignNameTextField.getText(), campaignDescriptionTextField.getText(), startDate, endDate, Integer.parseInt(ticketNumberTextField.getText()), strategy);
            vms.addCampaign(tmp);

            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            pack();
        }
        if(actionEvent.getSource().equals(doneEditButton)){
            // Edit the specified Campaign

            // Campaign does not exists
            if(vms.getCampaign(Integer.parseInt(campaignIDTextField.getText())) == null){
                System.out.println("Campaign does not exist!");
                return;
            }

            Date startDate, endDate;

            // Try to take the Date in the format specified
            try {
                startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDateTextField.getText());
                endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDateTextField.getText());
            } catch (ParseException e) {
                System.out.println("Incorrect Start/End Date!");
                return;
            }

            try {
                // Create the campaign with the inputs from the Text Fields
                Campaign tmp = new Campaign(Integer.parseInt(campaignIDTextField.getText()), campaignNameTextField.getText(), campaignDescriptionTextField.getText(), startDate, endDate, Integer.parseInt(ticketNumberTextField.getText()), null);

                // Edits the campaign
                vms.getCampaign(Integer.parseInt(campaignIDTextField.getText())).editCampaign(tmp);
            } catch (ParseException e) {
                System.out.println("Parse error!");
                return;
            }

            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            pack();
        }
        if(actionEvent.getSource().equals(doneCancelButton)){
            // Cancel the campaign with the specified ID
            int id;

            // Try to parse the int given in the Text Field
            try {
                id = Integer.parseInt(campaignIDTextField.getText());
            } catch (Exception e){
                System.out.println("Not an integer!");
                return;
            }

            // Campaign does not exists
            if(vms.getCampaign(id) == null){
                System.out.println("Campaign does not exist!");
                return;
            }

            // Try to cancel the campaign
            try {
                vms.cancelCampaign(id);
            } catch (ParseException e) {
                System.out.println("Parse error!");
                return;
            }

            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            pack();
        }
        if(actionEvent.getSource().equals(doneDetailsButton)){
            // Open the details for the specified campaign
            int id;

            try{
                id = Integer.parseInt(campaignIDTextField.getText());
            } catch (Exception e){
                System.out.println("Not an integer!");
                return;
            }

            // Campaign does not exists
            if(vms.getCampaign(id) == null){
                System.out.println("Campaign does not exist!");
                return;
            }

            // Open the Campaign Page
            CampaignPage campaignPage = new CampaignPage("CampaignPage", this, vms.getCampaign(id), vms);

            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            pack();
        }
        if(actionEvent.getSource().equals(strategyButton)){
            setButtons(false);

            campaignIDLabel.setVisible(true);
            campaignIDTextField.setVisible(true);
            doneStrategyButton.setVisible(true);
        }
        if(actionEvent.getSource().equals(doneStrategyButton)){
            // Generate the strategy voucher for given campaign
            int id;

            // Try to parse the int given in the Text Field
            try {
                id = Integer.parseInt(campaignIDTextField.getText());
            } catch (Exception e){
                System.out.println("Not an integer!");
                return;
            }

            // Campaign does not exists
            if(vms.getCampaign(id) == null){
                System.out.println("Campaign does not exist!");
                return;
            }

            // Execute the strategy for the campaign
            vms.getCampaign(id).executeStrategy();

            setButtons(true);
            setTextFields(false);
            setLabels(false);
            disableExtraButtons();

            pack();
        }
    }
}
