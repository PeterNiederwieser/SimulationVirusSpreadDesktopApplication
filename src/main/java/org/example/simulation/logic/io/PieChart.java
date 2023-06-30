package org.example.simulation.logic.io;

import org.example.simulation.data.Context;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChart {
    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public JPanel getChartLinesPanel(Context context) {
        dataset = createDataset(context);
        chart = createChart(dataset);
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(350, 350);
        return chartPanel;
    }
    public void updateChartPie(Context context) {
        updateDataset(context);
    }

    private void updateDataset(Context context) {
        dataset.setValue("not infected yet", context.getTotalNumberOfHealthyAnimals());
        dataset.setValue("currently infected", context.getTotalNumberOfInfectedAnimals());
        dataset.setValue("recovered", context.getTotalNumberOfRecoveredAnimals());
        dataset.setValue("dead",context.getTotalNumberOfDeadAnimals());
    }

    private DefaultPieDataset createDataset(Context context) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("not infected yet", context.getTotalNumberOfHealthyAnimals());
        dataset.setValue("currently infected", context.getTotalNumberOfInfectedAnimals());
        dataset.setValue("recovered", context.getTotalNumberOfRecoveredAnimals());
        dataset.setValue("dead",context.getTotalNumberOfDeadAnimals());
        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Percentage of states",
                dataset,
                true,
                false,
                false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("not infected yet", Color.BLUE);
        plot.setSectionPaint("currently infected", Color.RED);
        plot.setSectionPaint("recovered", Color.YELLOW);
        plot.setSectionPaint("dead", Color.BLACK);


        return chart;
    }
}
