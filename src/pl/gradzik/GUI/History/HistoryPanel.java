package pl.gradzik.GUI.History;

import pl.gradzik.GUI.ClientsList.ClientInfoPanel;
import pl.gradzik.GUI.ClientsList.ClientsListPanel;
import pl.gradzik.GUI.MainFrame;
import pl.gradzik.GUI.MainPanel;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryPanel extends JPanel
{
    private int width;
    private int height;

    private List<TransactionInfo> transactionInfoPanelsList = new ArrayList<>();

    private TransactionInfo topPanel;

    private MainFrame frame;
    private MainPanel mainPanel;


    public HistoryPanel(MainFrame frame ,MainPanel mainPanel)
    {
        this.mainPanel = mainPanel;
        this.frame = frame;

        width = 1600;
        height = 40;

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        topPanel = new TransactionInfo(40,"ID","Data","Godzina","Wartość tranzakcji","ID klienta","Miejsce tranzakcji","Typ tranzakcji","Cel tranzakcji (ID)");
        topPanel.setFontBold();
        topPanel.setBackground(new Color(210,210,210));
        this.add(topPanel);

        try {
            Connection connection = MySQlConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions_history ORDER BY id DESC;");

            while(resultSet.next() && height <= 40000) // main loop which creates list
            {
                height += 39;
                transactionInfoPanelsList.add(new TransactionInfo(
                        height,
                        resultSet.getString("id"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("transaction_value"),
                        resultSet.getString("client_id"),
                        resultSet.getString("transaction_atm_id"),
                        resultSet.getString("transaction_type"),
                        resultSet.getString("transaction_destination_id")
                ));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        height += 40;

        int counter = 0;

        for(TransactionInfo transaction : transactionInfoPanelsList)
        {
            if (counter == 1)
            {
                transaction.setBackground(new Color(210,210,210));
                counter -= 2;
            }
            counter++;
            this.add(transaction);
        }

        this.setPreferredSize(new Dimension(width,height));

    }


    public void refresh() // new thread
    {

        HistoryPanel thisPanel = this;

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                for(TransactionInfo transaction : transactionInfoPanelsList)
                {
                    thisPanel.remove(transaction);
                }

                transactionInfoPanelsList.clear();
                height = 40;

                try {
                    Connection connection = MySQlConnector.connect();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions_history ORDER BY id DESC;");

                    while(resultSet.next() && height <= 40000) // main loop which creates list
                    {
                        height += 39;
                        transactionInfoPanelsList.add(new TransactionInfo(
                                height,
                                resultSet.getString("id"),
                                resultSet.getString("date"),
                                resultSet.getString("time"),
                                resultSet.getString("transaction_value"),
                                resultSet.getString("client_id"),
                                resultSet.getString("transaction_atm_id"),
                                resultSet.getString("transaction_type"),
                                resultSet.getString("transaction_destination_id")
                        ));
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                height += 40;

                int counter = 0;

                for(TransactionInfo transaction : transactionInfoPanelsList)
                {
                    if (counter == 1)
                    {
                        transaction.setBackground(new Color(210,210,210));
                        counter -= 2;
                    }
                    counter++;
                    thisPanel.add(transaction);
                }

                thisPanel.setPreferredSize(new Dimension(width,height));
                thisPanel.repaint();
                mainPanel.setPreferredSize(new Dimension(width,height));
                mainPanel.repaint();
                frame.refresh();
            }
        });
        t.start();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
