package org.example;

import org.example.simulation.logic.animalBehaviour.types.Behaviour;
import org.example.simulation.logic.animalBehaviour.types.Rest;
import org.example.simulation.logic.animalBehaviour.types.Stroll;
import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.io.ColorHandler;
import org.example.simulation.logic.io.MapCreator;
import org.example.simulation.logic.io.MapDisplayer;
import org.example.simulation.data.Context;

import org.example.simulation.logic.io.MapPrinter;
import org.example.simulation.logic.simulationPhase.*;
import org.example.simulation.Simulator;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;
import org.example.simulation.logic.utils.MapFieldUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        ColorHandler colorHandler = new ColorHandler(context);
        MapPrinter mapPrinter = new MapPrinter(colorHandler);
        PhaseUtils phaseUtils = new PhaseUtils(context);
        MapFieldUtils mapFieldUtils = new MapFieldUtils(context);
        MapDisplayer mapDisplayer = new MapDisplayer(context, colorHandler);
        MapCreator mapCreator = new MapCreator(context);
        List<Behaviour> behaviours = List.of(
                new Stroll(context, mapFieldUtils),
                new Rest()
        );
        List<Phase> phases = List.of(
                new AnimalBehaviour(behaviours),
                new Recovering(phaseUtils),
                new SevereIllness(phaseUtils),
                new Dying(phaseUtils),
                new InfectionSpread(phaseUtils),
                new Graphic(mapDisplayer),
                new StepIncrement()
        );

        Initializer initializer = new Initializer(context, mapCreator, mapDisplayer, mapFieldUtils, mapPrinter);
        Simulator simulator = new Simulator(context, phases, mapCreator, initializer);
        simulator.simulate();
    }
}