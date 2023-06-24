package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;

import java.util.List;
import java.util.stream.Collectors;

public class Infections implements Phase {
    @Override
    public void perform(Context context) {
        List<Animal> population = context.getPopulation();
        List<Animal> infectedAnimals = population.stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.INFECTED))
                .toList();

        infectedAnimals.forEach(infectedAnimal -> {
            population.stream()
                    .filter(animal -> animal.getX() != infectedAnimal.getX() && animal.getY() != infectedAnimal.getY())
                    .forEach(otherAnimal -> {
                        if (isOtherAnimalWithinInfectionRadius(otherAnimal, infectedAnimal, context) && otherAnimal.getHealthState().equals(HealthState.HEALTHY)) {
                            otherAnimal.setHealthState(getNextHealthStateOfOtherAnimal(context));
                        }
                    });
        });
    }

    private HealthState getNextHealthStateOfOtherAnimal(Context context) {
        float PROBABILITY_OF_INFECTION = context.getPROBABILITY_OF_INFECTION();
        return Math.random() <= PROBABILITY_OF_INFECTION ? HealthState.INFECTED : HealthState.HEALTHY;
    }

    private boolean isOtherAnimalWithinInfectionRadius(Animal otherAnimal, Animal infectedAnimal, Context context) {
        int INFECTION_RADIUS = context.getINFECTION_RADIUS();
        return (getDistanceBetweenAnimals(otherAnimal, infectedAnimal, context) <= (double) INFECTION_RADIUS);
    }

    public double getDistanceBetweenAnimals(Animal animal1, Animal animal2, Context context) {
        int ANIMAL_SIZE = context.getANIMAL_SIZE();
        return Math.sqrt(Math.pow(animal1.getX() - animal2.getX(), 2) + Math.pow(animal1.getY() - animal2.getY(), 2));
    }
}
