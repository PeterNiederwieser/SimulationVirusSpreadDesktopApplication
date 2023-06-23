package org.example.simulation.logic.initialisation;

import org.example.simulation.data.*;
import org.example.simulation.logic.map.MapCreator;
import org.example.simulation.logic.map.MapDisplayer;
import org.example.simulation.logic.utils.MapFieldUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Initializer {
    private final Context context;
    private final MapCreator mapCreator;
    private final MapDisplayer mapDisplayer;
    private final MapFieldUtils mapFieldUtils;
    private List<Position> initialPositions = new ArrayList<>();

    public Initializer(Context context, MapCreator mapCreator, MapDisplayer mapDisplayer, MapFieldUtils mapFieldUtils) {
        this.context = context;
        this.mapCreator = mapCreator;
        this.mapDisplayer = mapDisplayer;
        this.mapFieldUtils = mapFieldUtils;
    }

    public void initializeSimulation() throws IOException {
        String filePathOfImage = context.getFilePathOfMapImage();
        mapCreator.generateMapFromImage(filePathOfImage);
        setInitializedPopulation();
        initializeStartingStateOfInfections();
        mapDisplayer.displayMap();
    }

    private void initializeStartingStateOfInfections() {
        List<Animal> population = context.getPopulation();
        int NUMBER_OF_INITIAL_INFECTIONS = context.getNUMBER_OF_INITIAL_INFECTIONS();
        for (int i = 0; i < NUMBER_OF_INITIAL_INFECTIONS; i++) {
            population.get(i).setHealthState(HealthState.INFECTED);
        }
    }

    private void setInitializedPopulation() {
        int NUMBER_OF_ANIMALS = context.getNUMBER_OF_ANIMALS();
        List<Animal> population = context.getPopulation();
        for (int i = 0; i < NUMBER_OF_ANIMALS; i++) {
            Position position = getRandomInitialPosition();
            population.add(new Animal(position.x(), position.y(), HealthState.HEALTHY, MotionType.STROLL));
        }
    }

    private Position getRandomInitialPosition() {
        int MAP_HEIGHT = context.getMAP_HEIGHT();
        int MAP_WIDTH = context.getMAP_WIDTH();
        int x, y;
        do {
            x = (int) Math.round(Math.random() * MAP_HEIGHT);
            y = (int) Math.round(Math.random() * MAP_WIDTH);
        } while(mapFieldUtils.isAreaInaccessible(x,y) || mapFieldUtils.isFieldOccupied(null, x, y));
        return new Position(x,y);
    }
}
