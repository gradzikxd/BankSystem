package pl.gradzik.GUI.History;

import javax.swing.*;
import java.awt.*;

public class TransactionInfo extends JPanel
{

    private JLabel idLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel valueLabel;
    private JLabel clientIdLabel;
    private JLabel atmIdLabel;
    private JLabel typeLabel;
    private JLabel destinationIdLabel;

    String atmString;

    public TransactionInfo(int position, String id ,String date,String time, String value, String clinetId, String atmId, String type, String destinationId)
    {
        this.setBackground(new Color(235,235,235));
        this.setOpaque(true);
        this.setBounds(0,position,1600,40);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        this.setEnabled(true);

        if(atmId.equals("0"))
        {
            atmString = "SOB";
        }
        else
        {
            atmString = "Bankomat nr: " + atmId;
        }

        if(destinationId != null)
        {
            if(destinationId.equals("0"))
            {
                destinationId = "";
            }
        }

        try
        {
            float floatValue = Float.parseFloat(value);

            if((int)floatValue == floatValue)
            {
                value = "" + (int)floatValue;
            }
        }
        catch (NumberFormatException ex)
        {
        }

        try{
            int number = Integer.parseInt(atmId);

            if(number == 0)
            {
                atmString = "System obsługi banku";
            }
            else
            {
                atmString = "Bankomat nr: " + atmId;
            }

        }
        catch (NumberFormatException ex)
        {
            atmString = atmId;
        }

        idLabel = new JLabel(id);
        idLabel.setVerticalTextPosition(JLabel.CENTER);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setFont(new Font("Arial",Font.PLAIN,15));
        idLabel.setBounds(0,0,60,40);
        idLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(idLabel);

        dateLabel = new JLabel(date);
        dateLabel.setVerticalTextPosition(JLabel.CENTER);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial",Font.PLAIN,15));
        dateLabel.setBounds(59,0,220,40);
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(dateLabel);

        timeLabel = new JLabel(time);
        timeLabel.setVerticalTextPosition(JLabel.CENTER);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font("Arial",Font.PLAIN,15));
        timeLabel.setBounds(278,0,220,40);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(timeLabel);

        valueLabel = new JLabel(value + " PLN");
        valueLabel.setVerticalTextPosition(JLabel.CENTER);
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        valueLabel.setFont(new Font("Arial",Font.PLAIN,15));
        valueLabel.setBounds(497,0,220,40);
        valueLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(valueLabel);

        clientIdLabel = new JLabel(clinetId);
        clientIdLabel.setVerticalTextPosition(JLabel.CENTER);
        clientIdLabel.setHorizontalAlignment(JLabel.CENTER);
        clientIdLabel.setFont(new Font("Arial",Font.PLAIN,15));
        clientIdLabel.setBounds(716,0,220,40);
        clientIdLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(clientIdLabel);

        atmIdLabel = new JLabel(atmString);
        atmIdLabel.setVerticalTextPosition(JLabel.CENTER);
        atmIdLabel.setHorizontalAlignment(JLabel.CENTER);
        atmIdLabel.setFont(new Font("Arial",Font.PLAIN,15));
        atmIdLabel.setBounds(935,0,220,40);
        atmIdLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(atmIdLabel);

        typeLabel = new JLabel(type);
        typeLabel.setVerticalTextPosition(JLabel.CENTER);
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.setFont(new Font("Arial",Font.PLAIN,15));
        typeLabel.setBounds(1154,0,220,40);
        typeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(typeLabel);

        destinationIdLabel = new JLabel(destinationId);
        destinationIdLabel.setVerticalTextPosition(JLabel.CENTER);
        destinationIdLabel.setHorizontalAlignment(JLabel.CENTER);
        destinationIdLabel.setFont(new Font("Arial",Font.PLAIN,15));
        destinationIdLabel.setBounds(1373,0,220,40);
        destinationIdLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(destinationIdLabel);

    }


    public void setFontBold()
    {
        idLabel.setFont(new Font("Arial",Font.BOLD,15));
        dateLabel.setFont(new Font("Arial",Font.BOLD,15));
        timeLabel.setFont(new Font("Arial",Font.BOLD,15));
        valueLabel.setFont(new Font("Arial",Font.BOLD,15));
        clientIdLabel.setFont(new Font("Arial",Font.BOLD,15));
        atmIdLabel.setFont(new Font("Arial",Font.BOLD,15));
        typeLabel.setFont(new Font("Arial",Font.BOLD,15));
        destinationIdLabel.setFont(new Font("Arial",Font.BOLD,15));
    }

}
