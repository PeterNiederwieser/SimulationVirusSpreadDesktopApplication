package org.example;

import org.example.simulation.logic.animalBehaviour.types.Behaviour;
import org.example.simulation.logic.animalBehaviour.types.Stroll;
import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.map.ColorHandler;
import org.example.simulation.logic.map.MapCreator;
import org.example.simulation.logic.map.MapDisplayer;
import org.example.simulation.data.Context;

import org.example.simulation.logic.simulationPhase.*;
import org.example.simulation.Simulator;
import org.example.simulation.logic.utils.MapFieldUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        MapFieldUtils mapFieldUtils = new MapFieldUtils(context);
        ColorHandler colorHandler = new ColorHandler(context);
        MapDisplayer mapDisplayer = new MapDisplayer(context, colorHandler);
        MapCreator mapCreator = new MapCreator(context);
        List<Behaviour> behaviours = List.of(
                new Stroll(context, mapFieldUtils)
        );
        List<Phase> phases = List.of(
                new AnimalBehaviour(behaviours),
                new Infections(),
                // new Analysis(),
                new Graphic(mapDisplayer),
                // new Diagrams(),
                // new Movements(),
                new StepIncrement()
        );

        Initializer initializer = new Initializer(context, mapCreator, mapDisplayer, mapFieldUtils);
        Simulator simulator = new Simulator(context, phases, mapCreator, initializer);
        simulator.simulate();
    }
}