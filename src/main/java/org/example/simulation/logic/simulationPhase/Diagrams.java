package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.logic.io.ChartLines2;
import org.example.simulation.logic.io.PieChart;

public class Diagrams implements Phase {
    private final ChartLines2 chartLines2;
    private final PieChart pieChart;

    public Diagrams(ChartLines2 chartLines2, PieChart pieChart) {
        this.chartLines2 = chartLines2;
        this.pieChart = pieChart;
    }

    @Override
    public void perform(Context context) {
        if (context.getStepNumber() % 24 == 0 && context.isChartDataShown()) {
            chartLines2.updateChart(context);
            pieChart.updateChartPie(context);
        }
    }
}
