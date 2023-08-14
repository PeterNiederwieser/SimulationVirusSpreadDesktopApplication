package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.logic.io.ChartLines;
import org.example.simulation.logic.io.PieChart;

public class Diagrams implements Phase {
    private final ChartLines chartLines;
    private final PieChart pieChart;

    public Diagrams(ChartLines chartLines, PieChart pieChart) {
        this.chartLines = chartLines;
        this.pieChart = pieChart;
    }

    @Override
    public void perform(Context context) {
        if (context.getStepNumber() % 24 == 0 && context.isChartDataShown()) {
            chartLines.updateChart(context);
            pieChart.updateChartPie(context);
        }
    }
}
