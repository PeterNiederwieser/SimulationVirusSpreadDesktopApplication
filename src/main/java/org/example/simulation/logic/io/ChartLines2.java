package org.example.simulation.logic.io;

import org.example.simulation.data.Context;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartLines2 {
    private XYSeries series1;
    private XYSeries series2;
    private XYSeriesCollection dataset;
    private JFreeChart chart;

    public void createChart(Context context) {
        dataset = createDataset(context);
        chart = createChart(dataset);

        JFrame frame = new JFrame();
        JPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void updateChart(Context context) {
        updateDataset(context);
    }

    private XYSeriesCollection createDataset(Context context) {
        dataset = new XYSeriesCollection();
        series1 = new XYSeries("Infected animals");
        series2 = new XYSeries("Dead animals");
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        updateDataset(context);
        return dataset;
    }

    private void updateDataset(Context context) {
        List<Integer> infectionNumbersForCharts = context.getInfectionNumbersForCharts();
        List<Integer> lethalInfectionNumbersForCharts = context.getLethalInfectionNumbersForCharts();

        series1.clear();
        series2.clear();

        for (int i = 0; i < infectionNumbersForCharts.size(); i++) {
            series1.add(i + 1, infectionNumbersForCharts.get(i));
        }

        for (int i = 0; i < lethalInfectionNumbersForCharts.size(); i++) {
            series2.add(i + 1, lethalInfectionNumbersForCharts.get(i));
        }
    }

    private JFreeChart createChart(XYDataset dataset) {
        String chartTitle = "Number of infected / dead animals";
        String xAxisLabel = "time";
        String yAxisLabel = "Number of animals";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, true);

        customizeChart(chart);

        return chart;
    }

    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));

        plot.setOutlinePaint(Color.BLACK);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.WHITE);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
    }
}