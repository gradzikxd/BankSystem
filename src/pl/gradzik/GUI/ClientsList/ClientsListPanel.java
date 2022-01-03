package pl.gradzik.GUI.ClientsList;

import pl.gradzik.GUI.MainFrame;
import pl.gradzik.GUI.MainPanel;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientsListPanel extends JPanel implements ActionListener
{
    private ClientInfoPanel topPanel;

    private List<ClientInfoPanel> clientInfoPanelsList = new ArrayList<>();

    private int width;
    private int height;

    private MainPanel mainPanel;

    private JTextField searchField;
    private JButton searchButton;
    private MainFrame frame;


    public ClientsListPanel(MainFrame frame ,MainPanel mainPanel)
    {
        this.mainPanel = mainPanel;
        this.frame = frame;

        width = 1600;
        height = 40;

        topPanel = new ClientInfoPanel(mainPanel,40,"ID","Imie","Nazwisko","Data urodzenia","Miasto","Ulica","Adres","PESEL");
        topPanel.setFontBold();
        topPanel.removeButton();
        topPanel.setBackground(new Color(210,210,210));
        this.add(topPanel);

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        searchField = new JTextField();
        searchField.setBounds(1253,5,200,25);
        searchField.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(searchField);

        searchButton = new JButton("Szukaj");
        searchButton.setBounds(1458,5,100,24);
        searchButton.setBackground(Color.lightGray);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        this.add(searchButton);

        try {
            Connection connection = MySQlConnector.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients");

            while(resultSet.next()) // main loop which creates list
            {
                height += 39;
                clientInfoPanelsList.add(new ClientInfoPanel(
                        mainPanel,
                        height,
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("address"),
                        resultSet.getString("PESEL")
                ));
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        height += 40;

        int counter = 0;

        for(ClientInfoPanel client : clientInfoPanelsList)
        {
            if (counter == 1)
            {
                client.setBackground(new Color(210,210,210));
                counter -= 2;
            }
            counter++;
            this.add(client);
        }

        this.setPreferredSize(new Dimension(width,height));
    }


    public void refresh() // new thread
    {

        ClientsListPanel thisPanel = this;

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                for(ClientInfoPanel client : clientInfoPanelsList)
                {
                    thisPanel.remove(client);
                }

                clientInfoPanelsList.clear();
                height = 40;

                try {
                    Connection connection = MySQlConnector.connect();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from clients");

                    while(resultSet.next()) // main loop which creates list
                    {
                        height += 39;
                        clientInfoPanelsList.add(new ClientInfoPanel(
                                mainPanel,
                                height,
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("birth_date"),
                                resultSet.getString("city"),
                                resultSet.getString("street"),
                                resultSet.getString("address"),
                                resultSet.getString("PESEL")
                        ));

                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                height += 40;

                int counter = 0;

                for(ClientInfoPanel client : clientInfoPanelsList)
                {
                    if (counter == 1)
                    {
                        client.setBackground(new Color(210,210,210));
                        counter -= 2;
                    }
                    counter++;
                    thisPanel.add(client);
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

    private void search(String text) // new thread
    {
        ClientsListPanel thisPanel = this;

        List<ClientInfoPanel> resultsList = new ArrayList<>();

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                for(ClientInfoPanel client : clientInfoPanelsList)
                {
                    thisPanel.remove(client);
                    client.setBackground(new Color(235,235,235));
                    if(client.getToSearchString().indexOf(text) !=-1? true: false)
                    {
                        resultsList.add(client);
                    }
                }

                height = 40;

                int counter = 0;

                for(ClientInfoPanel client : resultsList)
                {
                    if (counter == 1)
                    {
                        client.setBackground(new Color(210,210,210));
                        counter -= 2;
                    }
                    counter++;
                    height += 39;
                    client.setHeight(height);
                    thisPanel.add(client);
                }
                height += 40;

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
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == searchButton)
        {
            if(searchField.getText().isEmpty())
            {
                refresh();
                return;
            }

            search(searchField.getText());
        }
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
