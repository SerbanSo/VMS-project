import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class Page extends JFrame {
    Font customFont;
    Font tableCustomFont;
    Dimension buttonDimension;

    public Page(String name, Dimension pageDimension){
        super(name);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(pageDimension);
        setLayout(new GridLayout());


        // Create the font to use - century gothic
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("../Fonts/GOTHIC.TTF")).deriveFont(21f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (Exception e){
            System.out.println("Can't find the Font!");
        }

        try {
            tableCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("../Fonts/GOTHIC.TTF")).deriveFont(15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(tableCustomFont);
        } catch (Exception e){
            System.out.println("Can't find the Font!");
        }
        // --------

        // Maximum dimension of the buttons
        buttonDimension = new Dimension(220, 63);

    }

    // Initialise the button: Style, Border, Image, etc. and adds a Mouse Listener
    public void initialiseButton(JButton button, String imgPath, String imgHoverPath, String imgPressPath){

        button.setPreferredSize(new Dimension(220, 63));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener((ActionListener) this);
        button.setFont(new Font("serif", Font.BOLD, 20));
        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(new ImageIcon(imgHoverPath));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setIcon(new ImageIcon(imgPressPath));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon(imgPath));
            }
        });
    }

    // Initialise the TextFields: Font, Transparent, Border
    public void initialiseTextField(JTextField textField, Color color){
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        textField.setOpaque(false);
        textField.setFont(customFont);
        textField.setForeground(color);
        textField.setCaretColor(color);
    }

    // Initialise the Labels: Font, Transparent
    public void initialiseLabel(JLabel label, Color color){
        label.setFont(customFont);
        label.setOpaque(false);
        label.setForeground(color);
    }

    // Initialise Campaign Table
    public void initialiseTableCampaign(JTable table){
        table.setPreferredSize(new Dimension(700, 450));
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(tableCustomFont);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(25);

    }

    // Initialise Voucher Table
    public void initialiseTableVoucher(JTable table){
        table.setPreferredSize(new Dimension(500, 400));
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(tableCustomFont);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(75);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(40);
    }

    // Initialise Voucher Table
    public void initialiseTableNotification(JTable table){
        table.setPreferredSize(new Dimension(400, 400));
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(tableCustomFont);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(75);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);
    }
}
