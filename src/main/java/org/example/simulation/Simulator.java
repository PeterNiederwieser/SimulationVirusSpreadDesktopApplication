package org.example.simulation;

import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.io.MapCreator;
import org.example.simulation.data.Context;
import org.example.simulation.logic.simulationPhase.Phase;

import java.io.IOException;
import java.util.List;

public class Simulator {
    private final Context context;
    private final List<Phase> phases;

    public Simulator(Context context, List<Phase> phases) {
        this.context = context;
        this.phases = phases;
    }

    public void simulate() throws IOException {
        // initializer.initializeSimulation();
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
