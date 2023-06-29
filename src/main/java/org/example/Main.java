package org.example;

import org.example.simulation.ApplicationRunner;
import org.example.simulation.DefaultContext;
import org.example.simulation.logic.behaviour.Behaviour;
import org.example.simulation.logic.behaviour.Rest;
import org.example.simulation.logic.behaviour.Stroll;
import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.io.*;
import org.example.simulation.data.Context;

import org.example.simulation.logic.simulationPhase.*;
import org.example.simulation.Simulator;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;
import org.example.simulation.logic.utils.MapFieldUtils;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        ChartLines chartLines = new ChartLines();
        ChartLines2 chartLines2 = new ChartLines2();
        DefaultContext defaultContext = new DefaultContext();
        ColorHandler colorHandler = new ColorHandler(context);
        MapPrinter mapPrinter = new MapPrinter(colorHandler);
        PhaseUtils phaseUtils = new PhaseUtils(context);
        MapFieldUtils mapFieldUtils = new MapFieldUtils(context);
        MapDisplayer mapDisplayer = new MapDisplayer(context, colorHandler, mapFieldUtils, chartLines2);
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
                new Analysis(),
                new Diagrams(chartLines2),
                new Graphic(mapDisplayer),
                new StepIncrement()
        );
        Initializer initializer = new Initializer(context, mapCreator, mapDisplayer, mapFieldUtils, mapPrinter);
        Simulator simulator = new Simulator(context, phases, mapCreator, initializer);
        ApplicationRunner applicationRunner = new ApplicationRunner(initializer, simulator);
        applicationRunner.runProgram(context, defaultContext);
    }
}