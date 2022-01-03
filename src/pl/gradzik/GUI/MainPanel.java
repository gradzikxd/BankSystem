package pl.gradzik.GUI;

import pl.gradzik.GUI.AtmsInfo.ATMsDataPanel;
import pl.gradzik.GUI.ClientsList.ClientProfilePanel;
import pl.gradzik.GUI.ClientsList.ClientsListPanel;
import pl.gradzik.GUI.CreateNewClient.CreateNewClientPanel;
import pl.gradzik.GUI.History.HistoryPanel;
import pl.gradzik.GUI.MainSite.MainSitePanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{

    private JPanel currentPanel;


    private MainSitePanel mainSitePanel;
    private ClientsListPanel clientsListPanel;
    private CreateNewClientPanel createNewClientPanel;
    private ATMsDataPanel atmsDataPanel;
    private HistoryPanel historyPanel;

    public MainFrame frame;

    public MainPanel(MainFrame frame)
    {
        this.frame = frame;

        mainSitePanel = new MainSitePanel();
        clientsListPanel =  new ClientsListPanel(frame,this);
        createNewClientPanel = new CreateNewClientPanel();
        atmsDataPanel = new ATMsDataPanel();
        historyPanel = new HistoryPanel(frame,this);

        createNewClientPanel.setClientsListPanel(clientsListPanel);

        currentPanel = mainSitePanel;

        this.add(currentPanel);
        this.setPreferredSize(new Dimension(1600,820));
        this.setBackground(new Color(235,235,235));
    }


    public void setPanel(PanelMode mode)
    {

        if(mode == PanelMode.MAINSITE)
        {
            this.remove(currentPanel);
            currentPanel = mainSitePanel;
            currentPanel.repaint();
            this.setPreferredSize(new Dimension(mainSitePanel.getWidth(),mainSitePanel.getHeight()));
            this.add(currentPanel);
            this.repaint();
        }

        if(mode == PanelMode.CLIENTSLIST)
        {
            this.remove(currentPanel);
            currentPanel = clientsListPanel;
            currentPanel.repaint();
            this.setPreferredSize(new Dimension(clientsListPanel.getWidth(),clientsListPanel.getHeight()));
            this.add(currentPanel);
            this.repaint();

        }

        if(mode == PanelMode.ADDCLIENT)
        {
            this.remove(currentPanel);
            currentPanel = createNewClientPanel;
            currentPanel.repaint();
            this.setPreferredSize(new Dimension(createNewClientPanel.getWidth(),createNewClientPanel.getHeight()));
            this.add(currentPanel);
            this.repaint();

        }

        if(mode == PanelMode.ATMS)
        {
            this.remove(currentPanel);
            currentPanel = atmsDataPanel;
            currentPanel.repaint();
            this.setPreferredSize(new Dimension(atmsDataPanel.getWidth(),atmsDataPanel.getHeight()));
            this.add(currentPanel);
            this.repaint();
        }

        if(mode == PanelMode.HISTORY)
        {
            this.remove(currentPanel);
            currentPanel = historyPanel;
            currentPanel.repaint();
            this.setPreferredSize(new Dimension(historyPanel.getWidth(),historyPanel.getHeight()));
            this.add(currentPanel);
            this.repaint();
        }
    }


    public void openProfilePanel(ClientProfilePanel panel) // prototype
    {
        this.remove(currentPanel);
        currentPanel = panel;
        currentPanel.repaint();
        this.setPreferredSize(new Dimension(panel.getWidth(),panel.getHeight()));
        this.add(currentPanel);
        this.repaint();
        frame.setUserProfileMode(); //
        frame.refresh();
    }

    public MainFrame getFrame() {
        return frame;
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    public ClientsListPanel getClientsListPanel() {
        return clientsListPanel;
    }

    public ATMsDataPanel getAtmsDataPanel() {
        return atmsDataPanel;
    }

    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public MainSitePanel getMainSitePanel() {
        return mainSitePanel;
    }


}

