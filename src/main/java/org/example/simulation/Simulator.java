package org.example.simulation;

import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.map.MapCreator;
import org.example.simulation.data.Context;
import org.example.simulation.logic.simulationPhase.Phase;

import java.io.IOException;
import java.util.List;

public class Simulator {
    private final Context context;
    private final List<Phase> phases;
    private final Initializer initializer;

    public Simulator(Context context, List<Phase> phases, Initializer initializer) {
        this.context = context;
        this.phases = phases;
        this.initializer = initializer;
    }

    public void simulate() throws IOException {
        initializer.initializeSimulation();
        do {
            simulatePhases();
        } while (context.isSimulationOngoing());
    }

    private void simulatePhases() {
        for (Phase phase : phases) {
            phase.perform(context);
        }
    }
}
