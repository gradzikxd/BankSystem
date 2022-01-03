package pl.gradzik.GUI.AtmsInfo;

import pl.gradzik.GUI.ClientsList.ClientInfoPanel;
import pl.gradzik.GUI.History.HistoryPanel;
import pl.gradzik.GUI.History.TransactionInfo;
import pl.gradzik.GUI.MainFrame;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ATMsDataPanel extends JPanel
{


    private int width;
    private int height;

    private List<ATMsInfoPanel> atmInfoPanelsList = new ArrayList<>();

    private ATMsInfoPanel topPanel;

    public ATMsDataPanel()
    {
        width = 1600;
        height = 40;

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        topPanel = new ATMsInfoPanel(40,"ID","100PLN","50PLN","20PLN","10PLN","Suma pieniędzy");
        topPanel.setFontBold();
        topPanel.setBackground(new Color(210,210,210));
        this.add(topPanel);


        try {
            Connection connection = MySQlConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from atms");

            while(resultSet.next()) // main loop which creates list
            {
                height += 39;
                atmInfoPanelsList.add(new ATMsInfoPanel(
                        height,
                        resultSet.getString("ATM_id"),
                        resultSet.getString("100PLN"),
                        resultSet.getString("50PLN"),
                        resultSet.getString("20PLN"),
                        resultSet.getString("10PLN"),
                        ""
                 ));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        height += 40;

        int counter = 0;

        for(ATMsInfoPanel atm : atmInfoPanelsList)
        {
            if (counter == 1)
            {
                atm.setBackground(new Color(210,210,210));
                counter -= 2;
            }
            counter++;
            this.add(atm);
        }

        this.setPreferredSize(new Dimension(width,height));


    }

    public void refresh()
    {
        ATMsDataPanel thisPanel = this;

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                for(ATMsInfoPanel transaction : atmInfoPanelsList)
                {
                    thisPanel.remove(transaction);
                }

                atmInfoPanelsList.clear();
                height = 40;

                try {
                    Connection connection = MySQlConnector.connect();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from atms");


                    while(resultSet.next()) // main loop which creates list
                    {

                        height += 39;
                        atmInfoPanelsList.add(new ATMsInfoPanel(
                                height,
                                resultSet.getString("ATM_id"),
                                resultSet.getString("100PLN"),
                                resultSet.getString("50PLN"),
                                resultSet.getString("20PLN"),
                                resultSet.getString("10PLN"),
                                ""
                        ));
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                height += 40;

                int counter = 0;

                for(ATMsInfoPanel transaction : atmInfoPanelsList)
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
                MainFrame.frame.getMainPanel().setPreferredSize(new Dimension(width,height));
                MainFrame.frame.getMainPanel().repaint();
                MainFrame.frame.refresh();
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
