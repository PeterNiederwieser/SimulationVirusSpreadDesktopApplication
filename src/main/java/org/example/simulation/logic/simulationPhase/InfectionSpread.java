package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;

import java.util.List;

public class InfectionSpread implements Phase {
    private final PhaseUtils phaseUtils;

    public InfectionSpread(PhaseUtils phaseUtils) {
        this.phaseUtils = phaseUtils;
    }

    @Override
    public void perform(Context context) {
        List<Animal> population = context.getPopulation();
        List<Animal> infectedAnimals = phaseUtils.getInfectedAnimals();
        infectedAnimals.forEach(infectedAnimal -> {
            population.stream()
                    .filter(animal -> animal.getX() != infectedAnimal.getX() && animal.getY() != infectedAnimal.getY())
                    .forEach(otherAnimal -> {
                        if (isOtherAnimalWithinInfectionRadius(otherAnimal, infectedAnimal, context) && otherAnimal.getHealthState().equals(HealthState.HEALTHY)) {
                            changeHealthStateInCaseOfInfection(otherAnimal, context);
                        }
                    });
        });
    }

    private void changeHealthStateInCaseOfInfection(Animal animal, Context context) {
        float PROBABILITY_OF_INFECTION = context.getPROBABILITY_OF_INFECTION();
        if (Math.random() <= PROBABILITY_OF_INFECTION) {
            animal.setHealthState(HealthState.INFECTED);
            animal.setMomentOfInfection(context.getStepNumber());
        }
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
