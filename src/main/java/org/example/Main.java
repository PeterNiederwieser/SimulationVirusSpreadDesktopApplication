package org.example;

import org.example.simulation.configuration.Context;
import org.example.simulation.phase.*;
import org.example.simulation.routine.Simulator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
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
        Simulator simulator = new Simulator(context, phases);
        simulator.simulate();
    }
}