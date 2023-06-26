package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;

import java.util.concurrent.TimeUnit;

public class StepIncrement implements Phase {
    @Override
    public void perform(Context context) {
        int delay = context.getDELAY_IN_MS();
        int stepNumber = context.getStepNumber();
        context.setStepNumber(++stepNumber);
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
