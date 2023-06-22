package org.example;

import org.example.simulation.IO.MapCreator;
import org.example.simulation.IO.MapDisplayer;
import org.example.simulation.configuration.Context;
import org.example.simulation.phase.*;
import org.example.simulation.routine.Simulator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePathOfMapImage = "src/main/resources/MapImage_by_DALL·E .png";
        Context context = new Context();
        JFrame frame = new JFrame("Simulation of virus spreading");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);


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
        frame.add(mapDisplayer);
        mapDisplayer.generateMapFromImage(filePathOfMapImage);
        // mapDisplayer.paintComponent(frame.getGraphics());
        frame.setVisible(true);
/*        Simulator simulator = new Simulator(context, phases);
        simulator.simulate();*/
    }
}