package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;

import java.util.List;

public class SevereIllness implements Phase {

    private final PhaseUtils phaseUtils;

    public SevereIllness(PhaseUtils phaseUtils) {
        this.phaseUtils = phaseUtils;
    }

    @Override
    public void perform(Context context) {
        List<Animal> infectedAnimals = phaseUtils.getInfectedAnimals();
        infectedAnimals.forEach(animal -> {
            if (isAnimalGettingSeverelyIll(animal, context)) {
                animal.setHealthState(HealthState.SEVERELY_ILL);
                animal.setStartOfSevereIllness(context.getStepNumber());
            }
        });
    }

    private boolean isAnimalGettingSeverelyIll(Animal animal, Context context) {
        int timeAfterInfection = context.getStepNumber() - animal.getMomentOfInfection();
        return (animal.getHealthState().equals(HealthState.INFECTED) && timeAfterInfection >= animal.getTimeOfPossibleDeathAfterInfection() && animal.isGettingSeverelyIll());
    }
}
