package org.example.simulation.logic.simulationPhase.utils;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;

import java.util.List;

public class PhaseUtils {
    private final Context context;

    public PhaseUtils(Context context) {
        this.context = context;
    }
    public List<Animal> getInfectedAnimals() {
        return context.getPopulation().stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.INFECTED) || animal.getHealthState().equals(HealthState.SEVERELY_ILL))
                .toList();
    }
}
