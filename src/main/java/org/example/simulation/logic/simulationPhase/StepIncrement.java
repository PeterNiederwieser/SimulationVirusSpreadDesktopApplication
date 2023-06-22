package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.configuration.Context;

import java.util.concurrent.TimeUnit;

public class StepIncrement implements Phase {
    @Override
    public void perform(Context context) {
        int delay = context.getDELAY();
        int stepNumber = context.getStepNumber();
        context.setStepNumber(++stepNumber);
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
