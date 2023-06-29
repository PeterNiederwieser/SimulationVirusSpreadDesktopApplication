package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.data.HealthState;

public class Analysis implements Phase{
    @Override
    public void perform(Context context) {
        /*int numberOfInfectedAnimals = context.getPopulation()
                .stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.INFECTED) || animal.getHealthState().equals(HealthState.SEVERELY_ILL))
                .toList()
                .size();

        int numberOfUninfectedAnimals = context.getPopulation()
                .stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.HEALTHY))
                .toList()
                .size();

        int numberOfRecoveredAnimals = context.getPopulation()
                .stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.RECOVERED))
                .toList()
                .size();*/

        if (context.getStepNumber() % 24 == 0) {
            context.getInfectionNumbersForCharts().add(context.getNumberOfNewInfectionsInCurrentTimeInterval());
            context.setNumberOfNewInfectionsInCurrentTimeInterval(0);
            context.getLethalInfectionNumbersForCharts().add(context.getNumberOfAnimalDeathsInCurrentTimeInterval());
            context.setNumberOfAnimalDeathsInCurrentTimeInterval(0);
        }
    }
}
