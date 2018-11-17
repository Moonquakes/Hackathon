import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resource{
    private JLabel success;
    private JButton button;
    private JButton logIn;
    private JButton buttonback;
    private String id;
    private String password;
    private String name;
    private String telephone;
    private int age;
    private String sex;
    private JTextField field=new JTextField(20);
    private JTextField fieldpa=new JTextField(20);
    private JTextField fieldname=new JTextField(20);
    private JTextField fieldte=new JTextField(20);

    public void go(){
        JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        JPanel pane11=new JPanel();
        JPanel pane2=new JPanel();
        JPanel pane3=new JPanel();
        JPanel pane4=new JPanel();
        JPanel pane5=new JPanel();
        JPanel pane6=new JPanel();
        JLabel nullText=new JLabel("         ");
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        buttonback=new JButton("返回登录");
        button=new JButton("确认");
        logIn=new JButton("返回");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login=new Login();
                login.go();
            }
        });
        Font size=new Font("serif",Font.BOLD,20);
        button.setFont(size);
        logIn.setFont(size);
        Font sizetext=new Font("serif",Font.BOLD,20);

        JLabel text=new JLabel("身份证号：");
        text.setFont(sizetext);
        field.setFont(sizetext);
        pane11.add(text);
        pane11.add(field);

       /* field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                char ch = event.getKeyChar();
                if (ch < '0' || ch > '9') {
                    event.consume();
                }
            }
        });*/



        JLabel text2=new JLabel(" 密码8位 ：");
        text2.setFont(sizetext);
        fieldpa.setFont(sizetext);
        pane2.add(text2);
        pane2.add(fieldpa);

        JLabel text3=new JLabel("姓     名：");
        text3.setFont(sizetext);
        fieldname.setFont(sizetext);
        pane3.add(text3);
        pane3.add(fieldname);

        JLabel text4=new JLabel("电     话：");
        text4.setFont(sizetext);
        fieldte.setFont(sizetext);
        pane4.add(text4);
        pane4.add(fieldte);

        pane5.add(button);
        pane5.add(logIn);

        pane6.add(nullText);

        panel.add(pane6);
        panel.add(pane11);
        panel.add(pane2);
        panel.add(pane3);
        panel.add(pane4);
        panel.add(pane5);



        frame.getContentPane().add(BorderLayout.CENTER,panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button.addActionListener(new Feild());

    }
    class Feild implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            id=field.getText();
            telephone=fieldte.getText();
            name=fieldname.getText();
            password=fieldpa.getText();
            try {
                start();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
    }


    public String getName() {
        return name;
    }
    public String getTelephone() {
        return telephone;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void start() throws UnsupportedEncodingException {
        JFrame frame=new JFrame();
        if(id.length()==18&&telephone.length()==11&&password.length()==8) {
            PseudoRandomGenerator pseudoRandomGenerator = new PseudoRandomGenerator(PseudoRandomGenerator.toLong_key(password));
            String cipherID = pseudoRandomGenerator.intENC(id);
            if(Database.Processing.ifExist(cipherID)){
                success=new JLabel("该用户名已被注册！");
                buttonback = new JButton("返回登录");
                buttonback.addActionListener(new ButtonBackLoginListener());
            }else {
                success = new JLabel("注册成功！" + getName());
                if (Integer.parseInt(String.valueOf(getId().charAt(getId().length() - 2))) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                //得到当前的年份
                String birth_Year = getId().substring(6, 10);
                String birth_Mouth = getId().substring(10, 12);
                String birth_Day = getId().substring(12, 14);
                Calendar cal = Calendar.getInstance();
                int cYear = cal.get(Calendar.YEAR);
                int cMouth = cal.get(Calendar.MONTH);
                int cDay = cal.get(Calendar.DAY_OF_MONTH);

                age = cYear - Integer.parseInt(birth_Year);
                if ((cMouth - Integer.parseInt(birth_Mouth)) < 0) {
                    age = age - 1;
                } else if ((cMouth - Integer.parseInt(birth_Mouth)) == 0) {
                    if ((cDay - Integer.parseInt(birth_Day)) > 0) {
                        age = age - 1;
                    } else {
                        age = (cYear) - Integer.parseInt(birth_Year);
                    }
                } else if (((cMouth) - Integer.parseInt(birth_Mouth)) > 0) {
                    age = (cYear) - Integer.parseInt(birth_Year);
                }
                String birthdate = birth_Year + "" + birth_Mouth + "" + birth_Day;

                String cipherName = pseudoRandomGenerator.stringENC(name);
                String cipherSex = pseudoRandomGenerator.stringENC(sex);
                String cipherBirthdate = pseudoRandomGenerator.intENC(birthdate);
                String cipherTelephone = pseudoRandomGenerator.intENC(telephone);
                String cipherPassword = pseudoRandomGenerator.intENC(password);

                Database.Processing.register(cipherID, cipherName, cipherSex, cipherBirthdate, cipherTelephone, "无", cipherPassword);

                buttonback = new JButton("返回登录");
                buttonback.addActionListener(new ButtonBackLoginListener());
            }

            /**String[] allMessage={id,name,sex,brithdate,telephone,"无",password};
            Encrypt encrypt = new Encrypt(id,password);
            PseudoRandomGenerator psg = new PseudoRandomGenerator(encrypt.long_key);
            String[] allCipherMessage = psg.ENC(allMessage);

            Database.Processing.register(allCipherMessage[0],allCipherMessage[1],allCipherMessage[2],allCipherMessage[3],allCipherMessage[4],"无",allCipherMessage[6]);
             */
        }else{
            success=new JLabel("注册失败，重新注册");
            buttonback=new JButton("返回注册");
            buttonback.addActionListener(new ButtonBackListener());
        }


        Font size = new Font("serif", Font.BOLD, 30);
        success.setFont(size);
        buttonback.setFont(size);

        JPanel set=new JPanel();
        JPanel xxx=new JPanel();
        JPanel xxx2=new JPanel();
        JPanel xxx3=new JPanel();
        JPanel xxx4=new JPanel();
        JLabel TEXT=new JLabel("      ");
        xxx.add(TEXT);
        xxx2.add(success);
        xxx3.add(TEXT);
        xxx4.add(buttonback);
        set.add(xxx);
        set.add(xxx2);
        set.add(xxx3);
        set.add(xxx4);
        set.setLayout(new BoxLayout(set,BoxLayout.Y_AXIS));
        frame.getContentPane().add(BorderLayout.CENTER,set);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,215);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    class ButtonBackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Resource resource=new Resource();
            resource.go();
        }
    }
    class ButtonBackLoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Login login=new Login();
            login.go();
        }
    }
    public int getAge() {
        return age;
    }
    public String getSex() {
        return sex;
    }
}
