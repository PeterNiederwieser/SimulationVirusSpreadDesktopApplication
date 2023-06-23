package org.example.simulation.logic.animalBehaviour.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.MotionType;

public class HuntForFood implements Behaviour {
    @Override
    public void behave(Animal animal) {

    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getMotionType().equals(MotionType.HUNT_FOR_FOOD);
    }
}
