package org.example.simulation.logic.motion;

import org.example.simulation.data.Animal;
import org.example.simulation.data.MotionType;
import org.example.simulation.logic.motion.types.Motion;

import java.util.NoSuchElementException;
import java.util.Set;

public class MotionSelector {
    private final Set<Motion> motions;

    public MotionSelector(Set<Motion> motions) {
        this.motions = motions;
    }
    public Motion getMotion(Animal animal) {
        return motions.stream()
                .filter(motion -> motion.matches(animal))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Could not find motion for animal."));
    }
}
