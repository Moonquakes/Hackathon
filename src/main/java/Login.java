import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

public class Login {
    private JFrame frame;
    private JPanel rootPanel;
    private JPanel titlePanel;
    private JLabel title;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton RegisteBt;
    private JPanel UpPanel;
    private JPanel DownPanel;
    private JLabel ID;
    private JLabel Password;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LoginBt;

    public static void main(String[] args) {
        Login login=new Login();
        login.go();
    }

    public void go(){
        frame = new JFrame("Login");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        RegisteBt.addActionListener(new RegisteListener());
        LoginBt.addActionListener(new LoginListener());

    }

    class RegisteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Resource resource=new Resource();
            frame.dispose();
            resource.go();
        }
    }

    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String IDnumber=textField1.getText();
            String password=passwordField1.getText();
            if(password.length()!=8){
                ErrorPassword errorPassword=new ErrorPassword();
                errorPassword.go();
            }else {

                PseudoRandomGenerator pseudoRandomGenerator = new PseudoRandomGenerator(PseudoRandomGenerator.toLong_key(password));
                String cipherID = pseudoRandomGenerator.intENC(IDnumber);
                String cipherPassword = pseudoRandomGenerator.intENC(password);


                String[] allMessage = Database.Processing.seek(cipherID, cipherPassword);

                if (Character.isDigit(allMessage[0].charAt(0))) {
                    Hospital hospital = new Hospital(allMessage, pseudoRandomGenerator);
                    frame.dispose();
                    try {
                        hospital.go();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (allMessage[0].equals("该用户不存在！")) {
                        DoNotExist doNotExist = new DoNotExist();
                        doNotExist.go();
                    } else if (allMessage[0].equals("发生未知异常！")) {
                        UnknownError unknownError = new UnknownError();
                        unknownError.go();
                    } else if (allMessage[0].equals("你输入的密码有误！")) {
                        ErrorPassword errorPassword = new ErrorPassword();
                        errorPassword.go();
                    }
                }
            }
        }
    }

}
