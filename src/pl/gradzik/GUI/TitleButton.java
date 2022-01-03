package pl.gradzik.GUI;

import javax.swing.*;
import java.awt.*;

public class TitleButton extends JButton
{
    public TitleButton(String title,int x)
    {
        this.setBounds(x,5,150,30);
        this.setBackground(new Color(0,112,192));
        this.setText(title);
        this.setForeground(Color.white);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setFont(new Font("Arial",Font.PLAIN,13));
    }

    public void setMode(boolean x)   // true == currend mode
    {
        if(x)
        {
            this.setForeground(Color.black);
            this.setBackground(new Color(235,235,235));
        }

        if(!x)
        {
            this.setForeground(Color.white);
            this.setBackground(new Color(0,112,192));
        }
    }

}
