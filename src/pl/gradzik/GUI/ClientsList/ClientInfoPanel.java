package pl.gradzik.GUI.ClientsList;

import pl.gradzik.GUI.MainPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientInfoPanel extends JPanel implements ActionListener
{
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel lastNameLabel;
    private JLabel birthDateLabel;
    private JLabel cityLabel;
    private JLabel streetLabel;
    private JLabel adressLabel;
    private JLabel peselLabel;

    private JButton openProfileButton;

    private MainPanel mainPanel;

    private String toSearchString = "";

    private int id;

    public ClientInfoPanel(MainPanel mainPanel,int position, String id ,String name,String lastName, String birthDate, String city, String street, String adress, String sex)
    {

        try{
            this.id = Integer.parseInt(id);
        }
        catch (NumberFormatException ex)
        {
            this.id = 0;
        }

        this.mainPanel = mainPanel;
        this.setBackground(new Color(235,235,235));
        this.setOpaque(true);
        this.setBounds(0,position,1600,40);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        this.setEnabled(true);

        toSearchString += id + " ";
        toSearchString += name + " ";
        toSearchString += lastName + " ";
        toSearchString += birthDate + " ";
        toSearchString += city + " ";
        toSearchString += street + " ";
        toSearchString += adress + " ";
        toSearchString += sex + " ";

        idLabel = new JLabel(id);
        idLabel.setVerticalTextPosition(JLabel.CENTER);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setFont(new Font("Arial",Font.PLAIN,15));
        idLabel.setBounds(0,0,60,40);
        idLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(idLabel);

        nameLabel = new JLabel(name);
        nameLabel.setVerticalTextPosition(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        nameLabel.setBounds(59,0,200,40);
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(nameLabel);

        lastNameLabel = new JLabel(lastName);
        lastNameLabel.setVerticalTextPosition(JLabel.CENTER);
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        lastNameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        lastNameLabel.setBounds(258,0,200,40);
        lastNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lastNameLabel);

        birthDateLabel = new JLabel(birthDate);
        birthDateLabel.setVerticalTextPosition(JLabel.CENTER);
        birthDateLabel.setHorizontalAlignment(JLabel.CENTER);
        birthDateLabel.setFont(new Font("Arial",Font.PLAIN,15));
        birthDateLabel.setBounds(457,0,200,40);
        birthDateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(birthDateLabel);

        cityLabel = new JLabel(city);
        cityLabel.setVerticalTextPosition(JLabel.CENTER);
        cityLabel.setHorizontalAlignment(JLabel.CENTER);
        cityLabel.setFont(new Font("Arial",Font.PLAIN,15));
        cityLabel.setBounds(656,0,200,40);
        cityLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(cityLabel);

        streetLabel = new JLabel(street);
        streetLabel.setVerticalTextPosition(JLabel.CENTER);
        streetLabel.setHorizontalAlignment(JLabel.CENTER);
        streetLabel.setFont(new Font("Arial",Font.PLAIN,15));
        streetLabel.setBounds(855,0,200,40);
        streetLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(streetLabel);

        adressLabel = new JLabel(adress);
        adressLabel.setVerticalTextPosition(JLabel.CENTER);
        adressLabel.setHorizontalAlignment(JLabel.CENTER);
        adressLabel.setFont(new Font("Arial",Font.PLAIN,15));
        adressLabel.setBounds(1054,0,200,40);
        adressLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(adressLabel);

        peselLabel = new JLabel(sex);
        peselLabel.setVerticalTextPosition(JLabel.CENTER);
        peselLabel.setHorizontalAlignment(JLabel.CENTER);
        peselLabel.setFont(new Font("Arial",Font.PLAIN,15));
        peselLabel.setBounds(1253,0,200,40);
        peselLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(peselLabel);

        openProfileButton = new JButton("Profil");
        openProfileButton.setBackground(Color.lightGray);
        openProfileButton.setBounds(1460,5,100,30);
        openProfileButton.setFocusable(false);
        openProfileButton.addActionListener(this);
        this.add(openProfileButton);
    }


    public void setFontBold()
    {
        idLabel.setFont(new Font("Arial",Font.BOLD,15));
        nameLabel.setFont(new Font("Arial",Font.BOLD,15));
        lastNameLabel.setFont(new Font("Arial",Font.BOLD,15));
        birthDateLabel.setFont(new Font("Arial",Font.BOLD,15));
        cityLabel.setFont(new Font("Arial",Font.BOLD,15));
        streetLabel.setFont(new Font("Arial",Font.BOLD,15));
        adressLabel.setFont(new Font("Arial",Font.BOLD,15));
        peselLabel.setFont(new Font("Arial",Font.BOLD,15));
    }

    public String getToSearchString() {
        return toSearchString;
    }

    public void setHeight(int y)
    {
        this.setBounds(0,y,1600,40);
    }

    public void removeButton()
    {
        this.remove(openProfileButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == openProfileButton)
        {
            try
            {
                mainPanel.openProfilePanel(new ClientProfilePanel(id,mainPanel.getFrame()));
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        }
    }


}
