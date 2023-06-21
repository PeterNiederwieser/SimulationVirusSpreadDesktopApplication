package org.example.simulation.routine;

import org.example.simulation.configuration.Context;
import org.example.simulation.phase.Phase;

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
            simulatePhases();
        } while(context.isSimulationOngoing());

    }

    private void simulatePhases() {
        for(Phase phase : phases) {
            phase.perform(context);
        }
    }
}
