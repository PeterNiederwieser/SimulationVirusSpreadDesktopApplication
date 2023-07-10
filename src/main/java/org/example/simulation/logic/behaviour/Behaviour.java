package org.example.simulation.logic.behaviour;

import org.example.simulation.data.Animal;

public interface Behaviour {
    void behave(Animal animal);

    boolean matches(Animal animal);
}
