import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

public class Hospital {
    private JPanel rootPanel;
    private JPanel IDPanel;
    private JPanel NamePanel;
    private JPanel SexPanel;
    private JPanel BirthPanel;
    private JPanel TelPanel;
    private JPanel CondPanel;
    private JLabel ID;
    private JLabel Name;
    private JLabel Sex;
    private JLabel Birth;
    private JLabel Tel;
    private JLabel Condition;
    private JLabel Sextxt;
    private JLabel Birthtxt;
    private JButton Button;
    private JButton Button1;
    private JButton Button2;
    private JLabel IDtxt;
    private JTextArea TextArea;
    private JTextField TextField1;
    private JTextField TextField;
    private String[] allMessage;

    public Hospital(String[] allMessage){
        this.allMessage=allMessage;
    }

    public void go() throws UnsupportedEncodingException {
        JFrame frame = new JFrame("Hospital");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        IDtxt.setText(allMessage[0]);
        TextField.setText(allMessage[1]);
        Sextxt.setText(allMessage[2]);
        Birthtxt.setText(allMessage[3]);
        TextField1.setText(allMessage[4]);
        TextArea.setText(allMessage[5]);

        Button.addActionListener(new ButtonListener());
        Button1.addActionListener(new Button1Listener());
        Button2.addActionListener(new Button2Listener());

    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name=TextField.getText();
            Database.Processing.update(allMessage[0],name,1);

        }
    }

    class Button1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String phone_number=TextField1.getText();
            Database.Processing.update(allMessage[0],phone_number,2);
        }
    }
    class Button2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String condition=TextArea.getText();
            Database.Processing.update(allMessage[0],condition,3);
        }
    }

}
