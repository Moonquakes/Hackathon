import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnknownError {
    private JFrame frame;
    private JPanel rootPanel;
    private JButton OKbt;
    private JLabel Warning;

    public void go(){
        frame = new JFrame("UnknownError");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
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
