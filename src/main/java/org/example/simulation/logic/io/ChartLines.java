package org.example.simulation.logic.io;

import org.example.simulation.data.Context;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChartLines {

    public void createChart(Context context) {


        JFrame frame = new JFrame();
        JPanel chartPanel = createChartPanel(context);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createChartPanel(Context context) {
        String chartTitle = "Number of infected / dead animals";
        String xAxisLabel = "time";
        String yAxisLabel = "Number of animals";

        XYDataset dataset = createDataset(context);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, true);

        customizeChart(chart);

        File imageFile = new File("NumberOfInfected/DeadAnimals.png");
        int width = 700;
        int height = 580;

        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return new ChartPanel(chart);
    }

    private XYDataset createDataset(Context context) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Infected animals");
        XYSeries series2 = new XYSeries("Dead animals");

        int numberOfAnimals = context.getPopulation().size();
        List<Integer> infectionNumbersForCharts = context.getInfectionNumbersForCharts();
        List<Integer> lethalInfectionNumbersForCharts = context.getLethalInfectionNumbersForCharts();

        for (int i = 1; i <= infectionNumbersForCharts.size(); i++) {
            series1.add(i, infectionNumbersForCharts.get(i));
        }

        for (int i = 1; i <= lethalInfectionNumbersForCharts.size(); i++) {
            series1.add(i, lethalInfectionNumbersForCharts.get(i));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
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

