package org.example.simulation;

import org.example.simulation.data.Context;
import org.example.simulation.logic.initialisation.Initializer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
@Service
public class Runner implements ApplicationRunner {
    private final Initializer initializer;
    private final Simulator simulator;
    private final Context context;

    public Runner(Initializer initializer, Simulator simulator, Context context) {
        this.initializer = initializer;
        this.simulator = simulator;
        this.context = context;
    }

    public void runProgram(Context context) throws IOException {
        System.out.println("run");
        initializer.initializeSimulation();
        System.out.println("initializer");
        while (true) {
            System.out.println("in while loop");
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("test");
        runProgram(context);
    }
}
