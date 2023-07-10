package org.example;

import org.example.simulation.Runner;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}