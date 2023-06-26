package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;

import java.util.List;

public class Dying implements Phase {
    private final PhaseUtils phaseUtils;

    public Dying(PhaseUtils phaseUtils) {
        this.phaseUtils = phaseUtils;
    }

    @Override
    public void perform(Context context) {
        List<Animal> infectedAnimals = phaseUtils.getInfectedAnimals();
        infectedAnimals.forEach(animal -> {
            if (isAnimalDying(animal, context)) {
                System.out.println("________________________________________________________________________________");
                List<Animal> population = context.getPopulation();
                population.remove(animal);
                context.setPopulation(population);
            }
        });
    }

    private boolean isAnimalDying(Animal animal, Context context) {
        return (animal.getHealthState().equals(HealthState.SEVERELY_ILL) && (context.getStepNumber() - animal.getStartOfSevereIllness()) >= context.getDURATION_OF_SEVERE_ILLNESS());
    }
}
