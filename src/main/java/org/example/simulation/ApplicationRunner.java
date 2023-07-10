package org.example.simulation;

import org.example.simulation.data.Context;
import org.example.simulation.logic.initialisation.Initializer;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationRunner {
    private final Initializer initializer;
    private final Simulator simulator;

    public ApplicationRunner(Initializer initializer, Simulator simulator) {
        this.initializer = initializer;
        this.simulator = simulator;
    }

    public void runProgram(Context context) throws IOException {
        initializer.initializeSimulation();
        while (true) {
            if (context.isSimulationOngoing()) {
                if (context.isShouldSimulationRestart()) {
                    context.setShouldSimulationRestart(false);
                    resetSimulationParameters(context);
                    initializer.reInitializeSimulation();
                }
                simulator.simulate();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void resetSimulationParameters(Context context) {
        context.setStepNumber(1);
        context.setPopulation(new ArrayList<>());
        context.setInfectionNumbersForCharts(new ArrayList<>());
        context.setLethalInfectionNumbersForCharts(new ArrayList<>());
        context.setRecoveredAnimalNumbersForCharts(new ArrayList<>());
        context.setRecoveredAnimalNumbersForCharts(new ArrayList<>());
    }
}
