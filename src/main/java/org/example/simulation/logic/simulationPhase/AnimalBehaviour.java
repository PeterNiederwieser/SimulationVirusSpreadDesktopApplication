package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.logic.animalBehaviour.types.Behaviour;

import java.util.List;
import java.util.NoSuchElementException;

public class AnimalBehaviour implements Phase {
    private final List<Behaviour> behaviours;

    public AnimalBehaviour(List<Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    @Override
    public void perform(Context context) {
        List<Animal> population = context.getPopulation();
        population.forEach(animal -> {
            Behaviour animalBehaviour = behaviours.stream()
                    .filter(behaviour -> behaviour.matches(animal))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No behaviour for animal found."));
            animalBehaviour.behave(animal);
        });
    }
}
