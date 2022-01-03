package pl.gradzik.GUI.CreateNewClient;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel
{

    private JLabel label;
    private JTextField field;

    public InputPanel(int x, int y, String text)
    {
        this.setBackground(new Color(235,235,235));
        this.setBounds(x,y,300,25);
        this.setLayout(null);


        label = new JLabel(text);
        label.setBounds(0,0,90,25);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(label);

        field = new JTextField("");
        field.setBounds(100,0,200,25);
        field.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(field);

    }

    public String getFieldText()
    {
        return field.getText();
    }

    public void clearFlied()
    {
        field.setText("");
    }

    public void setFieldText(String text)
    {
        field.setText(text);
    }
}
