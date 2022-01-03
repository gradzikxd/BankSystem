package pl.gradzik.GUI.AtmsInfo;

import javax.swing.*;
import java.awt.*;

public class ATMsInfoPanel extends JPanel
{

    private JLabel idLabel;
    private JLabel pln100;
    private JLabel pln50;
    private JLabel pln20;
    private JLabel pln10;
    private JLabel plnAll;

    private int pln100Count;
    private int pln50Count;
    private int pln20Count;
    private int pln10Count;

    private int moneySum;

    String atmString;

    public ATMsInfoPanel(int position, String id ,String pln100S,String pln50S, String pln20S, String pln10S, String atmStringS)
    {
        this.atmString = atmStringS;
        this.setBackground(new Color(235,235,235));
        this.setOpaque(true);
        this.setBounds(0,position,1600,40);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        this.setEnabled(true);

        moneySum = 0;

        try
        {
            pln100Count = Integer.parseInt(pln100S);
            pln50Count = Integer.parseInt(pln50S);
            pln20Count = Integer.parseInt(pln20S);
            pln10Count = Integer.parseInt(pln10S);

            while (pln100Count > 0)
            {
                moneySum += 100;
                pln100Count--;
            }
            while (pln50Count > 0)
            {
                moneySum += 50;
                pln50Count--;
            }
            while (pln20Count > 0)
            {
                moneySum += 20;
                pln20Count--;
            }
            while (pln10Count > 0)
            {
                moneySum += 10;
                pln10Count--;
            }

            atmString = String.valueOf(moneySum) + " PLN";
        }
        catch (NumberFormatException e)
        {
            pln100Count = 0;
            pln50Count = 0;
            pln20Count = 0;
            pln10Count = 0;
        }



        idLabel = new JLabel(id);
        idLabel.setVerticalTextPosition(JLabel.CENTER);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setFont(new Font("Arial",Font.PLAIN,15));
        idLabel.setBounds(0,0,100,40);
        idLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(idLabel);

        pln100 = new JLabel(pln100S);
        pln100.setVerticalTextPosition(JLabel.CENTER);
        pln100.setHorizontalAlignment(JLabel.CENTER);
        pln100.setFont(new Font("Arial",Font.PLAIN,15));
        pln100.setBounds(99,0,300,40);
        pln100.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pln100);

        pln50 = new JLabel(pln50S);
        pln50.setVerticalTextPosition(JLabel.CENTER);
        pln50.setHorizontalAlignment(JLabel.CENTER);
        pln50.setFont(new Font("Arial",Font.PLAIN,15));
        pln50.setBounds(398,0,300,40);
        pln50.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pln50);

        pln20 = new JLabel(pln20S);
        pln20.setVerticalTextPosition(JLabel.CENTER);
        pln20.setHorizontalAlignment(JLabel.CENTER);
        pln20.setFont(new Font("Arial",Font.PLAIN,15));
        pln20.setBounds(697,0,300,40);
        pln20.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pln20);

        pln10 = new JLabel(pln10S);
        pln10.setVerticalTextPosition(JLabel.CENTER);
        pln10.setHorizontalAlignment(JLabel.CENTER);
        pln10.setFont(new Font("Arial",Font.PLAIN,15));
        pln10.setBounds(996,0,300,40);
        pln10.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(pln10);

        plnAll = new JLabel(this.atmString);
        plnAll.setVerticalTextPosition(JLabel.CENTER);
        plnAll.setHorizontalAlignment(JLabel.CENTER);
        plnAll.setFont(new Font("Arial",Font.PLAIN,15));
        plnAll.setBounds(1295,0,300,40);
        plnAll.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(plnAll);

    }

    public void setFontBold()
    {
        idLabel.setFont(new Font("Arial",Font.BOLD,15));
        pln100.setFont(new Font("Arial",Font.BOLD,15));
        pln50.setFont(new Font("Arial",Font.BOLD,15));
        pln20.setFont(new Font("Arial",Font.BOLD,15));
        pln10.setFont(new Font("Arial",Font.BOLD,15));
        plnAll.setFont(new Font("Arial",Font.BOLD,15));

    }


}
