package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.logic.io.ScreenDisplayer;

public class Graphic implements Phase {
    private final ScreenDisplayer screenDisplayer;

    public Graphic(ScreenDisplayer screenDisplayer) {
        this.screenDisplayer = screenDisplayer;
    }

    @Override
    public void perform(Context context) {
        screenDisplayer.repaint();
    }
}
