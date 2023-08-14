package org.example.simulation;

import org.example.simulation.data.Context;
import org.example.simulation.logic.simulationPhase.Phase;

import java.util.List;

public class Simulator {
    private final Context context;
    private final List<Phase> phases;

    public Simulator(Context context, List<Phase> phases) {
        this.context = context;
        this.phases = phases;
    }

    public void simulate() {
        do {
            if (!context.isSimulationPaused()) {
                simulatePhases();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (context.isSimulationOngoing() && !context.isShouldSimulationRestart());
    }

    private void simulatePhases() {
        for (Phase phase : phases) {
            phase.perform(context);
        }
    }
}
