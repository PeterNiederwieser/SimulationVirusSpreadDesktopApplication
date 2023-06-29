package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.logic.io.ChartLines2;

public class Diagrams implements Phase {
    private final ChartLines2 chartLines2;

    public Diagrams(ChartLines2 chartLines2) {
        this.chartLines2 = chartLines2;
    }

    @Override
    public void perform(Context context) {
        if (context.getStepNumber() % 24 == 0) {
            chartLines2.updateChart(context);
        }
    }
}
