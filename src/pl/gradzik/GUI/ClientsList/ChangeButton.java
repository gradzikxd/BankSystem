package pl.gradzik.GUI.ClientsList;

import pl.gradzik.GUI.MainFrame;
import pl.gradzik.MySQlConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeButton extends JButton implements ActionListener
{
    int id;
    private String TabelName;
    private String DBColumnName;

    MainFrame frame;


    public ChangeButton(MainFrame frame, int id, int position, String TabelName, String DBColumnName)
    {
        this.frame = frame;
        this.id = id;
        this.TabelName = TabelName;
        this.DBColumnName = DBColumnName;

        this.setText("zmień");
        this.addActionListener(this);
        this.setFocusable(false);
        this.setBounds(270,position,100,30);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this)
        {

            UIManager.put("OptionPane.cancelButtonText", "Anuluj");
            UIManager.put("OptionPane.okButtonText", "zmień");

            float money;

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250,30));
            JLabel label = new JLabel("Podaj nową wartość:");
            panel.add(label);

            String newValue = JOptionPane.showInputDialog(frame,
                    panel,
                    "",
                    JOptionPane.PLAIN_MESSAGE);


            try {

                Connection connection = MySQlConnector.connect();
                Statement statement = connection.createStatement();

                if(TabelName.equals("clients"))
                {
                    statement.executeUpdate // zmiana salda
                            (
                                    "update "+TabelName+"\n" +
                                            "set "+ DBColumnName +" = '" + newValue + "'\n" +
                                            "where id = " + id +";"
                            );
                }
                else
                {
                    statement.executeUpdate // zmiana salda
                            (
                                    "update "+TabelName+"\n" +
                                            "set "+ DBColumnName +" = '" + newValue + "'\n" +
                                            "where client_id = " + id +";"
                            );
                }

                frame.getMainPanel().openProfilePanel(new ClientProfilePanel(id,frame));
                frame.getMainPanel().getClientsListPanel().refresh();

            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
