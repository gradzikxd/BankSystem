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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainSitePanel extends JPanel
{

    private int width;
    private int height;

    private Clock clock;

    private int clientsSum;
    private int clientsToday;
    private int clientsInThisMonth;
    private int payment;
    private int payout;

    private JLabel clientLabel;
    private JLabel clientSumLabel;
    private JLabel clientsTodayLabel;
    private JLabel clientsInThisMonthLabel;

    private JLabel atmsLabel;
    private JLabel atmsPaymentSumTodayLabel;
    private JLabel atmsPayoutSumTodayLabel;




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

            //Nowi w tym miesiącu

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObjMonth = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter myFormatObjYear = DateTimeFormatter.ofPattern("YYYY");
            String month = myDateObj.format(myFormatObjMonth);
            String year = myDateObj.format(myFormatObjYear);

            ResultSet resultSet3 = statement.executeQuery("select count(client_id) as sum from  additional_information\n" +
                    "                    where month(creation_date) = '"+month+"' And year(creation_date) = '"+year+"' ;");
            resultSet3.next();
            clientsInThisMonth = resultSet3.getInt("sum");

            //wpłaty

            ResultSet resultSet4 = statement.executeQuery("select sum(transaction_value) as sum from `bank-system`.transactions_history\n" +
                    "where transaction_type = \"wpłata\" and date = '"+date+"';");
            resultSet4.next();
            payment = resultSet4.getInt("sum");

            //wypłaty

            ResultSet resultSet5 = statement.executeQuery("select sum(transaction_value) as sum from `bank-system`.transactions_history\n" +
                    "where transaction_type = \"wypłata\" and date = '"+date+"';");
            resultSet5.next();
            payout = resultSet5.getInt("sum");


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

    clientsTodayLabel = new JLabel("Nowi Klienci dzisiaj: " + String.valueOf(clientsToday));
    clientsTodayLabel.setBounds(0,129,250,40);
    clientsTodayLabel.setFont(new Font("Arial",Font.PLAIN,15));
    clientsTodayLabel.setVerticalTextPosition(JLabel.CENTER);
    clientsTodayLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(clientsTodayLabel);

    clientsInThisMonthLabel = new JLabel("Nowi Klienci w tym miesiącu: " + String.valueOf(clientsInThisMonth));
    clientsInThisMonthLabel.setBounds(0,168,250,40);
    clientsInThisMonthLabel.setFont(new Font("Arial",Font.PLAIN,15));
    clientsInThisMonthLabel.setVerticalTextPosition(JLabel.CENTER);
    clientsInThisMonthLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(clientsInThisMonthLabel);


    atmsLabel = new JLabel("Informacje o Bankomatach");
    atmsLabel.setFont(new Font("Arial",Font.PLAIN,15));
    atmsLabel.setVerticalTextPosition(JLabel.CENTER);
    atmsLabel.setBorder(new EmptyBorder(10,10,10,10) );
    atmsLabel.setBounds(0,217,380,30);
    atmsLabel.setForeground(Color.white);
    atmsLabel.setBackground(new Color(0,112,192));
    atmsLabel.setOpaque(true);
    this.add(atmsLabel);

    atmsPaymentSumTodayLabel = new JLabel("Suma dzisiejszych wpłat: " + String.valueOf(payment));
    atmsPaymentSumTodayLabel.setBounds(0,257,250,40);
    atmsPaymentSumTodayLabel.setFont(new Font("Arial",Font.PLAIN,15));
    atmsPaymentSumTodayLabel.setVerticalTextPosition(JLabel.CENTER);
    atmsPaymentSumTodayLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(atmsPaymentSumTodayLabel);

    atmsPayoutSumTodayLabel = new JLabel("Suma dzisiejszych wypłat: " + String.valueOf(payout));
    atmsPayoutSumTodayLabel.setBounds(0,296,250,40);
    atmsPayoutSumTodayLabel.setFont(new Font("Arial",Font.PLAIN,15));
    atmsPayoutSumTodayLabel.setVerticalTextPosition(JLabel.CENTER);
    atmsPayoutSumTodayLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
    this.add(atmsPayoutSumTodayLabel);




}

public void refresh()
{
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

        //Nowi w tym miesiącu

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObjMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter myFormatObjYear = DateTimeFormatter.ofPattern("YYYY");
        String month = myDateObj.format(myFormatObjMonth);
        String year = myDateObj.format(myFormatObjYear);

        ResultSet resultSet3 = statement.executeQuery("select count(client_id) as sum from  additional_information\n" +
                "                    where month(creation_date) = '"+month+"' And year(creation_date) = '"+year+"' ;");
        resultSet3.next();
        clientsInThisMonth = resultSet3.getInt("sum");

        //wpłaty

        ResultSet resultSet4 = statement.executeQuery("select sum(transaction_value) as sum from `bank-system`.transactions_history\n" +
                "where transaction_type = \"wpłata\" and date = '"+date+"';");
        resultSet4.next();
        payment = resultSet4.getInt("sum");

        //wypłaty

        ResultSet resultSet5 = statement.executeQuery("select sum(transaction_value) as sum from `bank-system`.transactions_history\n" +
                "where transaction_type = \"wypłata\" and date = '"+date+"';");
        resultSet5.next();
        payout = resultSet5.getInt("sum");

        clientSumLabel.setText("Wszyscy Klienci: " + String.valueOf(clientsSum));
        clientsTodayLabel.setText("Nowi Klienci dzisiaj: " + String.valueOf(clientsToday));
        clientsInThisMonthLabel.setText("Nowi Klienci w tym miesiącu: " + String.valueOf(clientsInThisMonth));

        atmsPaymentSumTodayLabel.setText("Suma dzisiejszych wpłat: " + String.valueOf(payment));
        atmsPayoutSumTodayLabel.setText("Suma dzisiejszych wypłat: " + String.valueOf(payout));


    } catch (SQLException e) {
        e.printStackTrace();
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
