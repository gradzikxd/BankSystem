package pl.gradzik.GUI.MainSite;

import pl.gradzik.MySQlConnector;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class MainSitePanel extends JPanel
{

    private int width;
    private int height;

    private Clock clock;

    private int clientsSum;
    private int clientsToday;
    private int clientsInThisMonth;

    private JLabel clientLabel;
    private JLabel clientSumLabel;




    public MainSitePanel()
    {
        width = 1600;
        height = 820;

        clock = new Clock(1100,30);
        this.add(clock);

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);


        try {
            Connection connection = MySQlConnector.connect();
            Statement statement = connection.createStatement();

            //Suma klientów
            ResultSet resultSet = statement.executeQuery("select count(id) as sum from clients;");
            resultSet.next();
            clientsSum = resultSet.getInt("sum");

            //Nowi dzisiaj
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); //aktualna data

            ResultSet resultSet2 = statement.executeQuery("select count(client_id) as sum from additional_information" +
                    " where creation_date = '"+ date +"';");
            resultSet2.next();
            clientsToday = resultSet2.getInt("sum");

            //
            ResultSet resultSet3 = statement.executeQuery("select count(client_id) as sum from additional_information" +
                    " where creation_date = '"+ date +"';");
            resultSet3.next();
            clientsToday = resultSet3.getInt("sum");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        createLabels();

    }

private void createLabels()
{

    clientLabel = new JLabel("Informacje o Klientach");
    clientLabel.setFont(new Font("Arial",Font.PLAIN,15));
    clientLabel.setVerticalTextPosition(JLabel.CENTER);
    clientLabel.setBorder(new EmptyBorder(10,10,10,10) );
    clientLabel.setBounds(0,50,380,30);
    clientLabel.setForeground(Color.white);
    clientLabel.setBackground(new Color(0,112,192));
    clientLabel.setOpaque(true);
    this.add(clientLabel);

    clientSumLabel = new JLabel("Wszyscy Klienci: " + String.valueOf(clientsSum));
    clientSumLabel.setBounds(0,90,250,40);
    clientSumLabel.setFont(new Font("Arial",Font.PLAIN,15));
    clientSumLabel.setVerticalTextPosition(JLabel.CENTER);
    clientSumLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(clientSumLabel);

    clientSumLabel = new JLabel("Nowi Klienci dzisiaj: " + String.valueOf(clientsToday));
    clientSumLabel.setBounds(0,129,250,40);
    clientSumLabel.setFont(new Font("Arial",Font.PLAIN,15));
    clientSumLabel.setVerticalTextPosition(JLabel.CENTER);
    clientSumLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(clientSumLabel);


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
