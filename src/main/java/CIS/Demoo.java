package CIS;

import CIS.evaluate;

import javax.swing.*;
import java.awt.*;


public class Demoo {
    public static void main(String[] args){
        evaluate[] list=new evaluate[5];
        list[0]= new evaluate(10,4000,true);
        list[1]= new evaluate(7,3000,true);
        list[2]= new evaluate(14,6000,true);
        list[3]= new evaluate(20,7000,false);
        list[4]= new evaluate(10,3000,true);
        int aLLdays=0;
        int aLLmoney=0;
        int aLLalive=0;
        for (int i = 0; i <list.length; i++) {
            aLLdays =  list[i].getDays()+aLLdays;
            aLLmoney = list[i].getMoney() + aLLmoney;
            if (list[i].isAlive()) {
                aLLalive = aLLalive + 1;
            }
        }
        int averageDAYS = aLLdays / list.length;
        int averAGEMONEY = aLLmoney / list.length;
        double alivell = (double)aLLalive /(double)list.length;
        JFrame frame=new JFrame();
        JLabel test=new JLabel("            心肌梗塞");
        JLabel hosday=new JLabel("    平均住院率:    "+averageDAYS);
        JLabel hosmon=new JLabel("    平 均 费 用:    "+averAGEMONEY);
        JLabel hoslive=new JLabel("      存  活  率:     "+alivell);
        JPanel panel=new JPanel();
        Font size=new Font("serif",Font.BOLD,35);
        test.setFont(size);
        hosday.setFont(size);
        hosmon.setFont(size);
        hoslive.setFont(size);


        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(test);
        panel.add(hosday);
        panel.add(hosmon);
        panel.add(hoslive);

        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setVisible(true);



        System.out.print("心肌梗塞： 平均住院日：" + averageDAYS + "  平均费用：" + averAGEMONEY + "    存活率：" + alivell);
    }
}
