package org.example.simulation.logic.animalBehaviour;

import org.example.simulation.data.Animal;
import org.example.simulation.logic.animalBehaviour.types.Behaviour;

import java.util.NoSuchElementException;
import java.util.Set;

public class MotionSelector {
    private final Set<Behaviour> motions;

    public MotionSelector(Set<Behaviour> motions) {
        this.motions = motions;
    }
    public Behaviour getMotion(Animal animal) {
        return motions.stream()
                .filter(motion -> motion.matches(animal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Could not find motion for animal."));
    }
}
