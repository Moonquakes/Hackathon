package CIS;
import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

public class LineChart2 {
    ChartPanel frame1;
    public LineChart2(){
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("病床使用率", "日期", "百分比",xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

    }
    private static XYDataset createDataset() {  //这个数据集有点多，但都不难理解
        TimeSeries timeseries = new TimeSeries("病床使用率",
                org.jfree.data.time.Day.class);
        timeseries.add(new Day(7,9,2018), 0.84);
        timeseries.add(new Day(17,9,2018), 0.76);
        timeseries.add(new Day(27,9,2018), 0.80);
        timeseries.add(new Day(7,10,2018), 0.83);
        timeseries.add(new Day(17,10,2018), 0.82);
        timeseries.add(new Day(27,10,2018), 0.88);
        timeseries.add(new Day(7,11,2018), 0.86);
        timeseries.add(new Day(17,11,2018), 0.87);



        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        return timeseriescollection;
    }
    public ChartPanel getChartPanel(){
        return frame1;

    }
}
