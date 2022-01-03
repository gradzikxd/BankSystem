package pl.gradzik.GUI.CreateNewClient;

import pl.gradzik.GUI.ClientsList.ClientInfoPanel;
import pl.gradzik.GUI.ClientsList.ClientsListPanel;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Random;

public class CreateNewClientPanel extends JPanel implements ActionListener, Runnable
{
    private int width;
    private int height;

    private JLabel titleLabel;

    private JLabel topLabel;
    private JLabel midLabel;
    private JLabel bottomLabel;

    private InputPanel namePanel;
    private InputPanel lastnamePanel;
    private InputPanel birthDatePanel;
    private InputPanel cityPanel;
    private InputPanel streetPanel;
    private InputPanel adressPanel;
    private InputPanel peselPanel;

    private InputPanel phoneNumberPanel;
    private InputPanel emailAdressPanel;

    private String chosenSex;

    private JLabel sexLabel;
    private JComboBox sexBox;

    private JLabel accountCreatedLabel;

    private JButton createAccoundButton;
    private JButton clearButton;

    private ClientsListPanel clientsListPanel;

    public CreateNewClientPanel()
    {
        width = 1600;
        height = 820;

        this.setBackground(new Color(235,235,235));
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        accountCreatedLabel = new JLabel("Pomyślnie utworzono nowego Klienta!");
        accountCreatedLabel.setBounds(300,475,900,40);
        accountCreatedLabel.setFont(new Font("Arial",Font.PLAIN,30));
        accountCreatedLabel.setVisible(false);
        this.add(accountCreatedLabel);

        titleLabel = new JLabel("Utwórz nowego klienta");
        titleLabel.setBounds(460,20,600,50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,40));
        this.add(titleLabel);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        topLabel = new JLabel("Informacje obowiązkowe");
        topLabel.setBackground(new Color(0,112,192));
        topLabel.setOpaque(true);
        topLabel.setFont(new Font("Arial",Font.PLAIN,15));
        topLabel.setBorder(new EmptyBorder(10,10,10,10));
        topLabel.setForeground(Color.white);
        topLabel.setBounds(0,90,1600,30);
        this.add(topLabel);

        namePanel = new InputPanel(10,140,"Imie:");
        this.add(namePanel);

        lastnamePanel = new InputPanel(10,180,"Nazwisko:");
        this.add(lastnamePanel);

        birthDatePanel = new InputPanel(10,220,"Data urodzin:");
        birthDatePanel.setFieldText("rrrr-mm-dd");
        this.add(birthDatePanel);

        cityPanel = new InputPanel(350,140,"Miejscowość:");
        this.add(cityPanel);

        streetPanel = new InputPanel(350,180,"Ulica*:");
        this.add(streetPanel);

        adressPanel = new InputPanel(350,220,"Adres:");
        this.add(adressPanel);

        peselPanel = new InputPanel(690,140,"PESEL:");
        this.add(peselPanel);

        sexLabel = new JLabel("Płeć:");
        sexLabel.setBounds(690,180,90,25);
        sexLabel.setHorizontalAlignment(JLabel.RIGHT);
        sexLabel.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(sexLabel);

        String [] sex =
                {
                        "Mężczyzna",
                        "Kobieta",
                        "Inna"
                };

        chosenSex = "Mężczyzna";

        sexBox = new JComboBox(sex);
        sexBox.setBounds(790,180,200,25);

        sexBox.addActionListener(this);
        this.add(sexBox);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        midLabel = new JLabel("Informacje dodatkowe");
        midLabel.setBackground(new Color(0,112,192));
        midLabel.setBorder(new EmptyBorder(10,10,10,10));
        midLabel.setOpaque(true);
        midLabel.setFont(new Font("Arial",Font.PLAIN,15));
        midLabel.setForeground(Color.white);
        midLabel.setBounds(0,270,1600,30);
        this.add(midLabel);

        phoneNumberPanel = new InputPanel(10,320,"Nr telefonu:");
        this.add(phoneNumberPanel);

        emailAdressPanel = new InputPanel(10,360,"Adres Email:");
        this.add(emailAdressPanel);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        bottomLabel = new JLabel();
        bottomLabel.setBackground(new Color(0,112,192));
        bottomLabel.setOpaque(true);
        bottomLabel.setBounds(0,410,1600,30);
        this.add(bottomLabel);


        createAccoundButton = new JButton("Utwórz konto");
        createAccoundButton.setBounds(80,470,200,50);
        createAccoundButton.addActionListener(this);
        createAccoundButton.setBackground(Color.lightGray);
        createAccoundButton.setFocusable(false);
       // createAccound.setBorderPainted(false);
        this.add(createAccoundButton);

    }


    public void setClientsListPanel(ClientsListPanel clientsListPanel) {
        this.clientsListPanel = clientsListPanel;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == createAccoundButton)
        {
            new Thread(this).start();
        }
        //comboBox
        if(e.getSource() == sexBox)
        {
            if(sexBox.getSelectedIndex() == 0)
            {
                chosenSex = "Mężczyzna";
            }
            if(sexBox.getSelectedIndex() == 1)
            {
                chosenSex = "Kobieta";
            }
            if(sexBox.getSelectedIndex() == 2)
            {
                chosenSex = "Inna";
            }
        }
    }

    @Override
    public void run()  //creates new user
    {

        try {
            Connection connection = MySQlConnector.connect();
            Statement statement = connection.createStatement();

            String id;

            if(namePanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj imie");
                return;
            }
            if(lastnamePanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj nazwisko");
                return;
            }
            if(birthDatePanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj date urodzin");
                return;
            }
            if(cityPanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj Miejscowość");
                return;
            }
            if(adressPanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj adres");
                return;
            }
            if(peselPanel.getFieldText().isEmpty())
            {
                createAccoundFailed("podaj PESEL");
                return;
            }

            String cardNumber;
            String cardCVV;

            do
            {
                cardNumber = "";
                cardCVV = "";

                Random random = new Random();

                for (int i = 1; i <= 3 ; i++)
                {
                    int x = random.nextInt(10);

                    while (x == 0)
                    {
                        x = random.nextInt(10);
                    }

                    cardCVV += x;
                }

                for (int i = 1; i <= 16; i++)
                {
                    cardNumber += random.nextInt(10);

                    if(i % 4 ==0) cardNumber += " ";

                }
            }
            while(isCardNumberExisted(cardNumber)); // zabezpiecznie przed powstaniem 2 takich samych numerów karty


            String accountNumber;

            do
            {
                accountNumber = "";

                String country = "49";
                String bank_id = "2010";
                String bank_branch = "2840";

                accountNumber += country;
                accountNumber += " ";
                accountNumber += bank_id;
                accountNumber += " ";
                accountNumber += bank_branch;
                accountNumber += " ";

                Random random = new Random();

                for (int i = 1; i <= 16; i++)
                {
                    accountNumber += random.nextInt(10);

                    if(i % 4 ==0) accountNumber += " ";

                }
            }
            while(isAccountNumberExisted(accountNumber)); // zabezpiecznie przed powstaniem 2 takich samych numerów konta


            statement.executeUpdate // tworzenie klienta
                    (
                    "INSERT INTO clients (name,last_name,birth_date,city,street,address,PESEL,sex) values ('"
                            + namePanel.getFieldText() + "','"
                            + lastnamePanel.getFieldText() + "','"
                            + birthDatePanel.getFieldText() + "','"
                            + cityPanel.getFieldText() + "','"
                            + streetPanel.getFieldText() + "','"
                            + adressPanel.getFieldText() + "','"
                            + peselPanel.getFieldText() + "','"
                            + chosenSex + "');"
                    );


            ResultSet resultSet = statement.executeQuery
                    (
                            "select * from clients\n" +
                                    "where id = \n" +
                                    "(select max(id) from clients);"
                    );

            resultSet.next();

            id = resultSet.getString("id");

            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime()); //aktualna data

            statement.executeUpdate //tworzenie informacji dodatkowych
                    (
                            "INSERT INTO additional_information (client_id,phone_number,Email_adress,creation_date) values ('"
                                    + resultSet.getString("id") + "','"
                                    + phoneNumberPanel.getFieldText() + "','"
                                    + emailAdressPanel.getFieldText() + "','"
                                    + date + "');"
                    );

            statement.executeUpdate //tworzenie konta bankowego
                    (
                            "INSERT INTO balance (client_id, account_number, balance) values ('"
                                    + id + "','"
                                    + accountNumber + "','"
                                    + 0 + "');"
                    );




            statement.executeUpdate //tworzenie karty do konta
                    (
                            "INSERT INTO cards (client_id, card_number, CVV, card_pin) values ('"
                                    + id + "','"
                                    + cardNumber + "','"
                                    + cardCVV + "','"
                                    + 1234 + "');"
                    );

           clientsListPanel.refresh();

            namePanel.clearFlied();
            lastnamePanel.clearFlied();
            birthDatePanel.clearFlied();
            birthDatePanel.setFieldText("rrrr-mm-dd");
            cityPanel.clearFlied();
            streetPanel.clearFlied();
            adressPanel.clearFlied();
            peselPanel.clearFlied();
            phoneNumberPanel.clearFlied();
            emailAdressPanel.clearFlied();

            accountCreatedLabel.setVisible(true);
            this.repaint();
            Thread.sleep(2000);
            accountCreatedLabel.setVisible(false);
            this.repaint();

        }catch (Exception e)
        {
            createAccoundFailed("");
            e.printStackTrace();
        }

    }

    private void createAccoundFailed(String reason)
    {
        try {
            accountCreatedLabel.setText("Nie udało sie stworzyć konta, " + reason );
            accountCreatedLabel.setVisible(true);
            this.repaint();

            Thread.sleep(2000);

            accountCreatedLabel.setVisible(false);
            accountCreatedLabel.setText("Pomyślnie utworzono nowego Klienta!");
            this.repaint();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    private boolean isAccountNumberExisted(String accountNumber) throws SQLException
    {

        Connection connection = MySQlConnector.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery
                (
                        "select * from balance"
                );

        while (resultSet.next())
        {
            if(resultSet.getString("account_number").equals(accountNumber))
            {
                return true;
            }
        }

        return false;
    }


    private boolean isCardNumberExisted(String cardNumber) throws SQLException
    {

        Connection connection = MySQlConnector.connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery
                (
                        "select * from cards"
                );

        while (resultSet.next())
        {
            if(resultSet.getString("card_number").equals(cardNumber))
            {
                return true;
            }
        }

        return false;
    }
}


