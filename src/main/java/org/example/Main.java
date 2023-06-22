package org.example;

import org.example.simulation.IO.MapCreator;
import org.example.simulation.IO.MapDisplayer;
import org.example.simulation.configuration.Context;
import org.example.simulation.phase.*;
import org.example.simulation.routine.Simulator;

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
        Simulator simulator = new Simulator(context, phases, mapCreator);
        //simulator.simulate();
        mapCreator.generateMapFromImage(filePathOfMapImage);
        mapDisplayer.displayMap();
    }
}