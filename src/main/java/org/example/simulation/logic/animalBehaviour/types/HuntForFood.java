package org.example.simulation.logic.animalBehaviour.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.BehaviourType;

public class HuntForFood implements org.example.simulation.logic.animalBehaviour.types.Behaviour {
    @Override
    public void behave(Animal animal) {

    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getBehaviourType().equals(BehaviourType.HUNT_FOR_FOOD);
    }
}
