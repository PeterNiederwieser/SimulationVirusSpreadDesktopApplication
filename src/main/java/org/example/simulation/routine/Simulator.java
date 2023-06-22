package org.example.simulation.routine;

import org.example.simulation.IO.MapCreator;
import org.example.simulation.configuration.Context;
import org.example.simulation.phase.Phase;

import java.io.IOException;
import java.util.List;

public class Simulator {
    private final Context context;
    private final List<Phase> phases;
    private final MapCreator mapCreator;

    public Simulator(Context context, List<Phase> phases, MapCreator mapCreator) {
        this.context = context;
        this.phases = phases;
        this.mapCreator = mapCreator;
    }
    public void simulate() throws IOException {
        initializeSimulation();
        /*do {
            simulatePhases();
        } while(context.isSimulationOngoing());
*/
    }

    private void simulatePhases() {
        for(Phase phase : phases) {
            phase.perform(context);
        }
    }
    private void initializeSimulation() throws IOException {
        String filePathOfImage = context.getFilePathOfMapImage();
        mapCreator.generateMapFromImage(filePathOfImage);
    }
}
