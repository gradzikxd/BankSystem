package pl.gradzik.GUI.MainSite;

import pl.gradzik.GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock extends JPanel {
    private JLabel timeLabel;
    private JLabel dateLabel;


    public Clock(int x, int y) {
        this.setBackground(new Color(235, 235, 235));
        this.setLayout(null);
        this.setBounds(x, y, 450, 200);

        //actual time
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObjTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter myFormatObjDate = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String formattedTime = myDateObj.format(myFormatObjTime); //time after format
        String formattedDate = myDateObj.format(myFormatObjDate); //time after format


        timeLabel = new JLabel(formattedTime);
        timeLabel.setBounds(10, 10, 430, 130);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        this.add(timeLabel);

        dateLabel = new JLabel(formattedDate);
        dateLabel.setBounds(195, 90, 430, 130);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(dateLabel);


        startClockThread();

    }

    private void startClockThread() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObjTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                    DateTimeFormatter myFormatObjDate = DateTimeFormatter.ofPattern("dd.MM.YYYY");
                    String formattedTime = myDateObj.format(myFormatObjTime); //time after format
                    String formattedDate = myDateObj.format(myFormatObjDate); //Date after format

                    timeLabel.setText(formattedTime);
                    dateLabel.setText(formattedDate);

                    try {
                        Thread.sleep(1000);

                        if(MainFrame.frame.getMainPanel().getCurrentPanel() instanceof MainSitePanel) //poniższy kod ma za zadanie ustawianie prawdidłowego rozmiaru MainSite
                        {
                            MainFrame.frame.getMainPanel().setPreferredSize(new Dimension(1600, 820));
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        t.start();


    }






}
