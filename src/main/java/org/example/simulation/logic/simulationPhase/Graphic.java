package org.example.simulation.logic.simulationPhase;

import org.example.simulation.data.Context;
import org.example.simulation.logic.map.MapDisplayer;

public class Graphic implements Phase {
    private final MapDisplayer mapDisplayer;

    public Graphic(MapDisplayer mapDisplayer) {
        this.mapDisplayer = mapDisplayer;
    }

    @Override
    public void perform(Context context) {
        mapDisplayer.repaint();
    }
}
