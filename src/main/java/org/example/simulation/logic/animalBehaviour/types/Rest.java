package org.example.simulation.logic.animalBehaviour.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.BehaviourType;

public class Rest implements Behaviour{
    @Override
    public void behave(Animal animal) {

    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getBehaviourType().equals(BehaviourType.REST);
    }
}
