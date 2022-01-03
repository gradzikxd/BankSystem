package pl.gradzik.GUI;

import pl.gradzik.GUI.History.TransactionInfo;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainFrame extends JFrame implements ActionListener
{
    private JPanel topPanel;


    private TitleButton mainSiteButton;
    private TitleButton clientsListButton;
    private TitleButton createNewClientButton;
    private TitleButton ATMsDataButton;
    private TitleButton historyButton;
    private JLabel userProfileLabel;

    private MainPanel mainPanel;

    public static MainFrame frame;

    private JScrollPane scrollPane;

    public MainFrame() throws HeadlessException
    {
        frame = this;

        createPanels();
        createButtons();

        scrollPane = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setColumnHeaderView(topPanel);
        scrollPane.setBackground(new Color(0,112,192));

        this.add(scrollPane);
        this.setTitle("System obsługi banku");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        // Troche za późno to odkryłem wiec w kodzie jest dużo niepotrzebengo kodu ;)

        UIManager.put("Button.background", Color.lightGray);

        startRefreshThread();
    }

    private void createPanels()
    {
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1600,35));
        topPanel.setBackground(new Color(0,112,192));
        topPanel.setLayout(null);
        this.add(topPanel);

        mainPanel = new MainPanel(this);
    }

    private void createButtons()
    {
        mainSiteButton = new TitleButton("Strona główna",0);
        topPanel.add(mainSiteButton);
        mainSiteButton.addActionListener(this);
        mainSiteButton.setMode(true);

        clientsListButton = new TitleButton("Lista klientów",150);
        topPanel.add(clientsListButton);
        clientsListButton.addActionListener(this);

        createNewClientButton = new TitleButton("Dodaj klienta",300);
        topPanel.add(createNewClientButton);
        createNewClientButton.addActionListener(this);

        ATMsDataButton = new TitleButton("Stan bankomatów",450);
        topPanel.add(ATMsDataButton);
        ATMsDataButton.addActionListener(this);

        historyButton = new TitleButton("Hisoria tranzakcji ",600);
        topPanel.add(historyButton);
        historyButton.addActionListener(this);

        userProfileLabel = new JLabel("Profil klienta");
        userProfileLabel.setHorizontalAlignment(JLabel.CENTER);
        userProfileLabel.setForeground(new Color(0,112,192));
        userProfileLabel.setBounds(750,5,150,30);
        userProfileLabel.setBackground(new Color(0,112,192));
        userProfileLabel.setFont(new Font("Arial",Font.PLAIN,13));
        topPanel.add(userProfileLabel);

    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mainSiteButton)
        {
            disableUserProfleMode();
            mainSiteButton.setMode(true);
            clientsListButton.setMode(false);
            createNewClientButton.setMode(false);
            ATMsDataButton.setMode(false);
            historyButton.setMode(false);
            mainPanel.setPanel(PanelMode.MAINSITE);

            this.refresh();
        }


        if(e.getSource() == clientsListButton)
        {
            disableUserProfleMode();
            mainSiteButton.setMode(false);
            clientsListButton.setMode(true);
            createNewClientButton.setMode(false);
            ATMsDataButton.setMode(false);
            historyButton.setMode(false);
            mainPanel.setPanel(PanelMode.CLIENTSLIST);

            this.refresh();
        }

        if(e.getSource() == createNewClientButton)
        {
            disableUserProfleMode();
            mainSiteButton.setMode(false);
            clientsListButton.setMode(false);
            createNewClientButton.setMode(true);
            ATMsDataButton.setMode(false);
            historyButton.setMode(false);
            mainPanel.setPanel(PanelMode.ADDCLIENT);

            this.refresh();

        }

        if(e.getSource() == ATMsDataButton)
        {
            disableUserProfleMode();
            mainSiteButton.setMode(false);
            clientsListButton.setMode(false);
            createNewClientButton.setMode(false);
            ATMsDataButton.setMode(true);
            historyButton.setMode(false);
            mainPanel.setPanel(PanelMode.ATMS);

            this.refresh();
        }

        if(e.getSource() == historyButton)
        {
            disableUserProfleMode();
            mainSiteButton.setMode(false);
            clientsListButton.setMode(false);
            createNewClientButton.setMode(false);
            ATMsDataButton.setMode(false);
            historyButton.setMode(true);
            mainPanel.setPanel(PanelMode.HISTORY);

            this.refresh();
        }

    }

    public void setUserProfileMode()
    {
        mainSiteButton.setMode(false);
        clientsListButton.setMode(false);
        createNewClientButton.setMode(false);
        ATMsDataButton.setMode(false);
        historyButton.setMode(false);

        userProfileLabel.setForeground(Color.black);
        userProfileLabel.setBackground(new Color(235,235,235));
        userProfileLabel.setOpaque(true);
        userProfileLabel.repaint();
    }

    private void disableUserProfleMode()
    {
        userProfileLabel.setForeground(new Color(0,112,192));
        userProfileLabel.setBackground(new Color(0,112,192));
    }

    public void refresh()
    {
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void startRefreshThread()
    {
        MainFrame thisFrame = this;

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                while (true)
                {
                    try {
                        thisFrame.getMainPanel().getHistoryPanel().refresh();
                        thisFrame.getMainPanel().getAtmsDataPanel().refresh();
                        thisFrame.getMainPanel().getMainSitePanel().refresh();


                        Thread.sleep(20000); // aktualizauje liste co 20s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setName("Refresh Thread");
        t.start();

    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
