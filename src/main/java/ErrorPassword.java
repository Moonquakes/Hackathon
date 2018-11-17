import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPassword {
    private JFrame frame;
    private JPanel rootPanel;
    private JLabel errorPassword;
    private JButton OKbt;

    public void go(){
        frame = new JFrame("ErrorPassword");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,215);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        OKbt.addActionListener(new OKbtListener());
    }

    class OKbtListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}
