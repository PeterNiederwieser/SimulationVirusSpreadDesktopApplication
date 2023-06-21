package org.example.simulation.configuration;

import org.example.simulation.data.Animal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Context {
    private final float PROBABILITY_OF_INFECTION = 0.8F;
    private final float TIME_OF_RECOVERY = 1000F;
    private final int MAP_WIDTH = 1500;
    private final int MAP_HEIGHT = 1000;
    private final int NUMBER_OF_ANIMALS = 50;
    private final int DELAY = 1;
    private int stepNumber = 1;
    private boolean isSimulationOngoing = true;
    private final List<Animal> population;


    public Context() {
        this.population = createPopulation();
    }

    private List<Animal> createPopulation() {
        return IntStream.range(1, NUMBER_OF_ANIMALS)
                .mapToObj(index -> new Animal())
                .collect(Collectors.toList());
    }

    public float getPROBABILITY_OF_INFECTION() {
        return PROBABILITY_OF_INFECTION;
    }

    public float getTIME_OF_RECOVERY() {
        return TIME_OF_RECOVERY;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getNUMBER_OF_ANIMALS() {
        return NUMBER_OF_ANIMALS;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public List<Animal> getPopulation() {
        return population;
    }

    public int getDELAY() {
        return DELAY;
    }

    public boolean isSimulationOngoing() {
        return isSimulationOngoing;
    }
}
