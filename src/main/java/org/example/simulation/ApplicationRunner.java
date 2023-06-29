package org.example.simulation;

import org.example.simulation.data.Context;
import org.example.simulation.logic.initialisation.Initializer;

import java.io.IOException;

public class ApplicationRunner {
    private final Initializer initializer;
    private final Simulator simulator;

    public ApplicationRunner(Initializer initializer, Simulator simulator) {
        this.initializer = initializer;
        this.simulator = simulator;
    }

    public void runProgram(Context context) throws IOException {
        initializer.initializeSimulation();
        do {
            if (context.isShouldSimulationRestart()) {
                context.setShouldSimulationRestart(false);
                initializer.initializeSimulation();
            }
            simulator.simulate();
        } while (context.isSimulationOngoing());
    }

    private void resetSimulationParameters() {

    }

}
