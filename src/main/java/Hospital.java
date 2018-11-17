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
    private JButton ReturnLoginBt;
    private String[] allMessage;
    private PseudoRandomGenerator pseudoRandomGenerator;

    public Hospital(String[] allMessage,PseudoRandomGenerator pseudoRandomGenerator){
        this.allMessage=allMessage;
        this.pseudoRandomGenerator=pseudoRandomGenerator;
    }

    public void go() throws UnsupportedEncodingException {
        JFrame frame = new JFrame("Hospital");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String[] plaintext = new String[7];
        plaintext[0] = pseudoRandomGenerator.intDEC(allMessage[0]);
        plaintext[1] = pseudoRandomGenerator.stringDEC(allMessage[1]);
        plaintext[2] = pseudoRandomGenerator.stringDEC(allMessage[2]);
        plaintext[3] = pseudoRandomGenerator.intDEC(allMessage[3]);
        plaintext[4] = pseudoRandomGenerator.intDEC(allMessage[4]);
        plaintext[5] = allMessage[5];

        plaintext[3]=plaintext[3].substring(0,4)+"-"+plaintext[3].substring(4,6)+"-"+plaintext[3].substring(6,8);

        IDtxt.setText(plaintext[0]);
        TextField.setText(plaintext[1]);
        Sextxt.setText(plaintext[2]);
        Birthtxt.setText(plaintext[3]);
        TextField1.setText(plaintext[4]);
        TextArea.setText(allMessage[5]);

        Button.addActionListener(new ButtonListener());
        Button1.addActionListener(new Button1Listener());
        Button2.addActionListener(new Button2Listener());
        ReturnLoginBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login=new Login();
                login.go();
            }
        });
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name=TextField.getText();
            String cipherName = pseudoRandomGenerator.stringENC(name);
            Database.Processing.update(allMessage[0],cipherName,1);
        }
    }

    class Button1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String phone_number = TextField1.getText();
            String cipherPhoneNumber = pseudoRandomGenerator.intENC(phone_number);
            Database.Processing.update(allMessage[0], cipherPhoneNumber, 2);
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
