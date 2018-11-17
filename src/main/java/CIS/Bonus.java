package CIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Bonus {
    private JFrame frame;
    private JPanel rootPanel;
    private JButton AvgWorkingDayBt;
    private JLabel AvgWorkingDay;
    private JButton UsingRateBt;
    private JLabel UsingRate;
    private JButton AvgTurnoverBt;
    private JLabel AvgTurnover;
    private JButton AvgDaysBt;
    private JLabel AvgDays;
    public static void main(String[] args) {
        Bonus bonus=new Bonus();
        bonus.go();
    }
    public void go(){
        frame = new JFrame("Bonus");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,625);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AvgWorkingDayBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("平均病床工作日");
                frame.setLayout(new GridLayout(1,2,10,10));
                frame.add(new LineChart1().getChartPanel());    //添加折线图
                frame.setBounds(50, 50, 800, 600);
                frame.setVisible(true);

            }
        });
        UsingRateBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("病床使用率");
                frame.setLayout(new GridLayout(1,2,10,10));
                frame.add(new LineChart2().getChartPanel());    //添加折线图
                frame.setBounds(50, 50, 800, 600);
                frame.setVisible(true);
            }
        });
        AvgTurnoverBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("病床周转次数");
                frame.setLayout(new GridLayout(1,2,10,10));
                frame.add(new LineChart3().getChartPanel());    //添加折线图
                frame.setBounds(50, 50, 800, 600);
                frame.setVisible(true);
            }
        });
        AvgDaysBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame("出院者平均住院日数");
                frame.setLayout(new GridLayout(1,2,10,10));
                frame.add(new LineChart4().getChartPanel());    //添加折线图
                frame.setBounds(50, 50, 800, 600);
                frame.setVisible(true);
            }
        });
    }
    //平均病床工作日（一年内）
    public static int calAvgWorkingDay(int bedNum,int[] workingDays){ //数组存放每张床一年间工作天数
        int allDays=0;
        for(int i=0;i<workingDays.length;i++){
            allDays=allDays+workingDays[i];
        }
        return allDays/bedNum;
    }
    //病床使用率
    public static double calUsingRate(int todayUsingbedNum,int allBedNum){
        return todayUsingbedNum/allBedNum;
    }
    //病床周转次数（30天内）
    public static double calAvgTurnover(int outPatientNum,int[] usingBedNum){ //数组中存放30天内每天使用床位数
        int allUsingBedNum=0;
        for(int i=0;i<usingBedNum.length;i++){
            allUsingBedNum=allUsingBedNum+usingBedNum[i];
        }
        return (outPatientNum*30)/allUsingBedNum;
    }
    //出院者平均住院日数（30天内）
    public static double calAvgDays(int[] inBedDays,int outPatientNum){//数组中存放30天内每个出院患者住院天数
        int allInBedDay=0;
        for(int i=0;i<inBedDays.length;i++){
            allInBedDay=allInBedDay+inBedDays[i];
        }
        return allInBedDay/outPatientNum;
    }

}
