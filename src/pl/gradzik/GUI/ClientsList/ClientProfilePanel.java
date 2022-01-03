package pl.gradzik.GUI.ClientsList;

import pl.gradzik.GUI.MainFrame;
import pl.gradzik.GUI.PanelMode;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class ClientProfilePanel extends JPanel implements ActionListener
{

    private int width;
    private int height;

    private int id;
    private String name;
    private String lastName;
    private String birthDate;
    private String city;
    private String street;
    private String address;
    private String pesel;
    private String sex;
    private String phoneNumber;
    private String emailAddress;
    private String cardNumber;
    private String cardCVV;
    private String cardPIN;
    private String accountNumber;
    private float balance; // saldo

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel lastNameLabel;
    private JLabel birthDateLabel;
    private JLabel cityLabel;
    private JLabel streetLabel;
    private JLabel addressLabel;
    private JLabel peselLabel;
    private JLabel sexLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailAddressLabel;
    private JLabel cardNumberLabel;
    private JLabel cardCVVLabel;
    private JLabel cardPINLabel;
    private JLabel accountNumberLabel;
    private JLabel balanceLabel;

    private JLabel clientLabel;
    private JLabel contactLabel;
    private JLabel cardInfoLabel;

    private JButton depositButton;
    private JButton payOutButton;
    private JButton deleteButton;

    private ChangeButton lastNameButton;
    private ChangeButton cityButton;
    private ChangeButton streetButton;
    private ChangeButton addressButton;
    private ChangeButton phoneNumberButton;
    private ChangeButton emailButton;
    private ChangeButton cardPINButton;

    private MainFrame frame;

    Connection connection;

    public ClientProfilePanel(int id, MainFrame mainFrame) throws SQLException {
        this.frame = mainFrame;
        this.id = id;
        width = 1600;
        height = 820;

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        connection = MySQlConnector.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from clients where id = " + id + ";");
        resultSet.next();
        name = resultSet.getString("name");
        lastName = resultSet.getString("last_name");
        birthDate = resultSet.getString("birth_date");
        city = resultSet.getString("city");
        street = resultSet.getString("street");
        address = resultSet.getString("address");
        pesel = resultSet.getString("PESEL");
        sex = resultSet.getString("sex");


        ResultSet resultSet2 = statement.executeQuery("select * from balance where client_id = " + id + ";"); //
        resultSet2.next();
        accountNumber = resultSet2.getString("account_number");
        balance = resultSet2.getFloat("balance");


        ResultSet resultSet3 = statement.executeQuery("select * from additional_information where client_id = " + id + ";"); //
        resultSet3.next();
        phoneNumber = resultSet3.getString("phone_number");
        emailAddress = resultSet3.getString("Email_adress");


        ResultSet resultSet4 = statement.executeQuery("select * from cards where client_id = " + id + ";"); //
        resultSet4.next();
        cardNumber = resultSet4.getString("card_number");
        cardCVV = resultSet4.getString("CVV");
        cardPIN = resultSet4.getString("card_pin");

        if(balance < 0) balance = 0;

      createLabels();
      createButtons();

    }

    private void createLabels()
    {
        clientLabel = new JLabel("Informacje o Kliencie");
        clientLabel.setFont(new Font("Arial",Font.PLAIN,15));
        clientLabel.setVerticalTextPosition(JLabel.CENTER);
        clientLabel.setBorder(new EmptyBorder(10,10,10,10) );
        clientLabel.setBounds(9,20,380,30);
        clientLabel.setForeground(Color.white);
        clientLabel.setBackground(new Color(0,112,192));
        clientLabel.setOpaque(true);
        this.add(clientLabel);

        idLabel = new JLabel("ID Klienta: " + String.valueOf(id));
        idLabel.setBounds(9,59,250,40);
        idLabel.setFont(new Font("Arial",Font.PLAIN,15));
        idLabel.setVerticalTextPosition(JLabel.CENTER);
        idLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(idLabel);

        nameLabel = new JLabel("Imie: " + name);
        nameLabel.setBounds(9,98,250,40);
        nameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        nameLabel.setVerticalTextPosition(JLabel.CENTER);
        nameLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(nameLabel);

        lastNameLabel = new JLabel("Nazwisko: " + lastName);
        lastNameLabel.setBounds(9,137,250,40);
        lastNameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        lastNameLabel.setVerticalTextPosition(JLabel.CENTER);
        lastNameLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(lastNameLabel);

        birthDateLabel = new JLabel("Data urodzenia: " + birthDate);
        birthDateLabel.setBounds(9,176,250,40);
        birthDateLabel.setFont(new Font("Arial",Font.PLAIN,15));
        birthDateLabel.setVerticalTextPosition(JLabel.CENTER);
        birthDateLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(birthDateLabel);

        cityLabel = new JLabel("Miasto: " + city);
        cityLabel.setBounds(9,215,250,40);
        cityLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cityLabel.setVerticalTextPosition(JLabel.CENTER);
        cityLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(cityLabel);

        streetLabel = new JLabel("Ulica: " + street);
        streetLabel.setBounds(9,254,250,40);
        streetLabel.setFont(new Font("Arial",Font.PLAIN,15));
        streetLabel.setVerticalTextPosition(JLabel.CENTER);
        streetLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(streetLabel);

        addressLabel = new JLabel("adres: " + address);
        addressLabel.setBounds(9,293,250,40);
        addressLabel.setFont(new Font("Arial",Font.PLAIN,15));
        addressLabel.setVerticalTextPosition(JLabel.CENTER);
        addressLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(addressLabel);

        peselLabel = new JLabel("PESEL: " + pesel);
        peselLabel.setBounds(9,332,250,40);
        peselLabel.setFont(new Font("Arial",Font.PLAIN,15));
        peselLabel.setVerticalTextPosition(JLabel.CENTER);
        peselLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(peselLabel);

        sexLabel = new JLabel("Płeć: " + sex);
        sexLabel.setBounds(9,371,250,40);
        sexLabel.setFont(new Font("Arial",Font.PLAIN,15));
        sexLabel.setVerticalTextPosition(JLabel.CENTER);
        sexLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(sexLabel);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        contactLabel = new JLabel("Informacje kontaktowe");
        contactLabel.setFont(new Font("Arial",Font.PLAIN,15));
        contactLabel.setVerticalTextPosition(JLabel.CENTER);
        contactLabel.setBorder(new EmptyBorder(10,10,10,10) );
        contactLabel.setBounds(9,420,380,30);
        contactLabel.setForeground(Color.white);
        contactLabel.setBackground(new Color(0,112,192));
        contactLabel.setOpaque(true);
        this.add(contactLabel);

        phoneNumberLabel = new JLabel("Numer Telefonu: " + phoneNumber);
        phoneNumberLabel.setBounds(9,459,250,40);
        phoneNumberLabel.setFont(new Font("Arial",Font.PLAIN,15));
        phoneNumberLabel.setVerticalTextPosition(JLabel.CENTER);
        phoneNumberLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(phoneNumberLabel);

        emailAddressLabel = new JLabel("Email: " + emailAddress);
        emailAddressLabel.setBounds(9,498,250,40);
        emailAddressLabel.setFont(new Font("Arial",Font.PLAIN,15));
        emailAddressLabel.setVerticalTextPosition(JLabel.CENTER);
        emailAddressLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(emailAddressLabel);

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        cardInfoLabel = new JLabel("Informacje o karcie");
        cardInfoLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cardInfoLabel.setVerticalTextPosition(JLabel.CENTER);
        cardInfoLabel.setBorder(new EmptyBorder(10,10,10,10) );
        cardInfoLabel.setBounds(9,548,380,30);
        cardInfoLabel.setForeground(Color.white);
        cardInfoLabel.setBackground(new Color(0,112,192));
        cardInfoLabel.setOpaque(true);
        this.add(cardInfoLabel);

        cardNumberLabel = new JLabel("Nr Karty: " + cardNumber);
        cardNumberLabel.setBounds(9,587,250,40);
        cardNumberLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cardNumberLabel.setVerticalTextPosition(JLabel.CENTER);
        cardNumberLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(cardNumberLabel);

        cardCVVLabel = new JLabel("CVV: " + cardCVV);
        cardCVVLabel.setBounds(9,626,250,40);
        cardCVVLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cardCVVLabel.setVerticalTextPosition(JLabel.CENTER);
        cardCVVLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(cardCVVLabel);

        cardPINLabel = new JLabel("PIN: " + cardPIN);
        cardPINLabel.setBounds(9,665,250,40);
        cardPINLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cardPINLabel.setVerticalTextPosition(JLabel.CENTER);
        cardPINLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(cardPINLabel);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        accountNumberLabel = new JLabel("Nr Konta: " + accountNumber);
        accountNumberLabel.setBounds(900,20,550,50);
        accountNumberLabel.setFont(new Font("Arial",Font.PLAIN,25));
        accountNumberLabel.setVerticalTextPosition(JLabel.CENTER);
        accountNumberLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(accountNumberLabel);

        balanceLabel= new JLabel("Saldo: " + balance + " PLN");
        balanceLabel.setBounds(900,69,550,50);
        balanceLabel.setFont(new Font("Arial",Font.PLAIN,25));
        balanceLabel.setVerticalTextPosition(JLabel.CENTER);
        balanceLabel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),new EmptyBorder(10,10,10,10) ));
        this.add(balanceLabel);

    }


    private void createButtons()
    {
        depositButton = new JButton("Wpłać");
        depositButton.setBackground(Color.lightGray);
        depositButton.setFont(new Font("Arial",Font.PLAIN,20));
        depositButton.setBounds(950,130,200,50);
        depositButton.setFocusable(false);
        depositButton.addActionListener(this);
        this.add(depositButton);

        payOutButton = new JButton("Wypłać");
        payOutButton.setBackground(Color.lightGray);
        payOutButton.setFont(new Font("Arial",Font.PLAIN,20));
        payOutButton.setBounds(1200,130,200,50);
        payOutButton.setFocusable(false);
        payOutButton.addActionListener(this);
        this.add(payOutButton);

        deleteButton = new JButton("Usuń konto");
        deleteButton.setBackground(Color.lightGray);
        deleteButton.setFont(new Font("Arial",Font.PLAIN,20));
        deleteButton.setBounds(1350,740,200,50);
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(this);
        this.add(deleteButton);


        lastNameButton = new ChangeButton(frame,id,142,"clients","last_name");
        this.add(lastNameButton);

        cityButton = new ChangeButton(frame,id,220,"clients","city");
        this.add(cityButton);

        streetButton = new ChangeButton(frame,id,259,"clients","street");
        this.add(streetButton);

        addressButton = new ChangeButton(frame,id,298,"clients","address");
        this.add(addressButton);

        phoneNumberButton = new ChangeButton(frame,id,464,"additional_information","phone_number");
        this.add(phoneNumberButton);

        emailButton = new ChangeButton(frame,id,503,"additional_information","Email_adress");
        this.add(emailButton);

        cardPINButton = new ChangeButton(frame,id,671,"cards","card_pin");
        this.add(cardPINButton);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == depositButton)
        {

            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            UIManager.put("OptionPane.okButtonText", "Wpłać");

            float money;

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250,30));
            JLabel label = new JLabel("Podaj kwote do wpłaty:");

            panel.add(label);

            String moneyS = JOptionPane.showInputDialog(frame,
                    panel,
                    "Wpłata",
                    JOptionPane.PLAIN_MESSAGE);

            try{
                money = Float.parseFloat(moneyS);
            }
            catch (NumberFormatException ex){
                money = 0;
            }

            if(money < 0) money = 0;


            deposit(money);
        }

        if(e.getSource() == payOutButton)
        {
            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            UIManager.put("OptionPane.okButtonText", "Wypłać");

            float money;

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250,30));
            JLabel label = new JLabel("Podaj kwote do wypłaty:");
            panel.add(label);

            String moneyS = JOptionPane.showInputDialog(frame,
                    panel,
                    "Wypłata",
                    JOptionPane.PLAIN_MESSAGE);

            try{
                money = Float.parseFloat(moneyS);
            }
            catch (NumberFormatException ex){
                money = 0;
            }

            if(money < 0) money = 0;

            payOut(money);
        }

        if(e.getSource() == deleteButton)
        {
            UIManager.put("OptionPane.noButtonText", "Nie");
            UIManager.put("OptionPane.yesButtonText", "Tak");

            int input = JOptionPane.showConfirmDialog(frame,
                    "     Czy na pewno chcesz usunąć konto?", "Usuwanie konta", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

            System.out.println(input);

            try
            {
                if (input == 0) deleteUser();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
    }


    private void deposit(float money)
    {
        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate // zmiana salda
                    (
                            "update balance\n" +
                                    "set balance = balance + " + money + "\n" +
                                    "where client_id = " + id +";"
                    );

            balance += money;
            balanceLabel.setText("Saldo: " + balance + " PLN");

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); //aktualna data

            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime()); //aktualny czas

            statement.executeUpdate // dodawanie tranzakcji do histori
                    (
                            "INSERT INTO `bank-system`.transactions_history \n" +
                                    "(date,time,transaction_value,client_id, transaction_atm_id,transaction_type,transaction_destination_id) \n" +
                                    "values ('"+ date +"','"+ time +"'," + money + ","+ id +", 0,\"wpłata\", null);"
                    );

            frame.getMainPanel().getHistoryPanel().refresh();
            frame.getMainPanel().openProfilePanel(new ClientProfilePanel(id,frame));

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void payOut(float money)
    {

        if(balance - money < 0)
        {
            UIManager.put("OptionPane.okButtonText", "Ok");


            JOptionPane.showMessageDialog(new Panel(), "Podana kwota jest za duża, nie udało sie wypłacić pieniędzy", "Uwaga",
                    JOptionPane.PLAIN_MESSAGE);
            return;
        }

        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate // zmiana salda
                    (
                            "update balance\n" +
                                    "set balance = balance - " + money + "\n" +
                                    "where client_id = " + id +";"
                    );

            balance -= money;
            balanceLabel.setText("Saldo: " + balance + " PLN");

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); //aktualna data

            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime()); //aktualny czas

            statement.executeUpdate // dodawanie tranzakcji do histori
                    (
                            "INSERT INTO `bank-system`.transactions_history \n" +
                                    "(date,time,transaction_value,client_id, transaction_atm_id,transaction_type,transaction_destination_id) \n" +
                                    "values ('"+ date +"','"+ time +"'," + money + ", "+ id +", 0,\"wypłata\", null);"
                    );

            frame.getMainPanel().getHistoryPanel().refresh();
            frame.getMainPanel().openProfilePanel(new ClientProfilePanel(id,frame));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteUser() throws SQLException
    {
        Statement statement = connection.createStatement();

        statement.executeUpdate
                (
                       "DELETE FROM clients\n" +
                               "where id = "+ id +";"
                );


        statement.executeUpdate
                (
                        "DELETE FROM balance\n" +
                                "where client_id = "+ id +";"
                );


        statement.executeUpdate
                (
                        "DELETE FROM additional_information\n" +
                                "where client_id = "+ id +";"
                );

        statement.executeUpdate
                (
                        "DELETE FROM cards\n" +
                                "where client_id = "+ id +";"
                );

        frame.getMainPanel().setPanel(PanelMode.CLIENTSLIST);
        frame.getMainPanel().getClientsListPanel().refresh();


    }


}
