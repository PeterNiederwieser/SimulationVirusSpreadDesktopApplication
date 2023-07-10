package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.logic.simulationPhase.utils.PhaseUtils;

import java.util.List;

public class Dying implements Phase {
    private final PhaseUtils phaseUtils;

    public Dying(PhaseUtils phaseUtils) {
        this.phaseUtils = phaseUtils;
    }

    @Override
    public void perform(Context context) {
        List<Animal> severelyIllAnimals = phaseUtils.getSeverelyIllAnimals();
        severelyIllAnimals.forEach(animal -> {
            if (isAnimalDying(animal, context)) {
                List<Animal> population = context.getPopulation();
                population.remove(animal);
                context.setTotalNumberOfDeadAnimals(context.getTotalNumberOfDeadAnimals() + 1);
                context.setNumberOfAnimalDeathsInCurrentTimeInterval(context.getNumberOfAnimalDeathsInCurrentTimeInterval()+1);
            }
        });
    }

    private boolean isAnimalDying(Animal animal, Context context) {
        return ((context.getStepNumber() - animal.getStartOfSevereIllness()) >= context.getDURATION_OF_SEVERE_ILLNESS());
    }
}
