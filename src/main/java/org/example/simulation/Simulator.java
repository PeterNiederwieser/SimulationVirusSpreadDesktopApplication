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
    private final MapCreator mapCreator;
    private final Initializer initializer;

    public Simulator(Context context, List<Phase> phases, MapCreator mapCreator, Initializer initializer) {
        this.context = context;
        this.phases = phases;
        this.mapCreator = mapCreator;
        this.initializer = initializer;
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
        } while (context.isSimulationOngoing());
    }

    private void simulatePhases() {
        for (Phase phase : phases) {
            phase.perform(context);
        }
    }

}
