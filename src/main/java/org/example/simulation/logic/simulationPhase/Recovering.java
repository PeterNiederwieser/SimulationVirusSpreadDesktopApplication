package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;

import java.util.List;

public class Recovering implements Phase {
    private final PhaseUtils phaseUtils;

    public Recovering(PhaseUtils phaseUtils) {
        this.phaseUtils = phaseUtils;
    }

    @Override
    public void perform(Context context) {
        List<Animal> infectedAnimals = phaseUtils.getInfectedAnimalsWithoutSeverelyIllAnimals();
        infectedAnimals.forEach(animal -> {
            if (isAnimalRecovered(animal, context)) {
                animal.setHealthState(HealthState.RECOVERED);
            }
        });
    }

    private boolean isAnimalRecovered(Animal animal, Context context) {
        int currentStepNumber = context.getStepNumber();
        int timeSinceInfection = currentStepNumber - animal.getMomentOfInfection();
        return (timeSinceInfection >= context.getTIME_OF_RECOVERY());
    }
}
