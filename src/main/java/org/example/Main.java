package org.example;

import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.map.MapCreator;
import org.example.simulation.logic.map.MapDisplayer;
import org.example.simulation.data.configuration.Context;

import org.example.simulation.logic.simulationPhase.*;
import org.example.simulation.logic.routine.Simulator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePathOfMapImage = "src/main/resources/MapImage_by_DALL·E .png";
        Context context = new Context();
        List<Phase> phases = List.of(
                new AnimalBehaviour(),
                new Infections(),
                new Analysis(),
                new Graphic(),
                new Diagrams(),
                new Movements(),
                new StepIncrement()
        );
        MapDisplayer mapDisplayer = new MapDisplayer(context);
        MapCreator mapCreator = new MapCreator(context);
        Initializer initializer = new Initializer(context, mapCreator, mapDisplayer);
        Simulator simulator = new Simulator(context, phases, mapCreator, initializer);
        simulator.simulate();
    }
}