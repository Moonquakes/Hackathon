package CIS;
import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;

public class LineChart3 {
    ChartPanel frame1;
    public LineChart3(){
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("病床周转次数", "日期", "次数/月",xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

    }
    private static XYDataset createDataset() {  //这个数据集有点多，但都不难理解

        TimeSeries timeseries1 = new TimeSeries("病床周转次数",
                org.jfree.data.time.Month.class);
        timeseries1.add(new Month(1, 2018), 15.59999999999999D);
        timeseries1.add(new Month(2, 2018), 12.59999999999999D);
        timeseries1.add(new Month(3, 2018), 17.2D);
        timeseries1.add(new Month(4, 2018), 11.2D);
        timeseries1.add(new Month(5, 2018), 10.09999999999999D);
        timeseries1.add(new Month(6, 2018), 12.59999999999999D);
        timeseries1.add(new Month(7, 2018), 20.2D);
        timeseries1.add(new Month(8, 2018), 12.5D);
        timeseries1.add(new Month(9, 2018), 15.7D);
        timeseries1.add(new Month(10, 2018), 22.5D);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries1);
        return timeseriescollection;
    }
    public ChartPanel getChartPanel(){
        return frame1;

    }
}
