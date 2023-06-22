package org.example.simulation.logic.initialisation;

import org.example.simulation.data.Animal;
import org.example.simulation.data.configuration.Context;
import org.example.simulation.logic.map.MapCreator;
import org.example.simulation.logic.map.MapDisplayer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Initializer {
    private final Context context;
    private final MapCreator mapCreator;
    private final MapDisplayer mapDisplayer;

    public Initializer(Context context, MapCreator mapCreator, MapDisplayer mapDisplayer) {
        this.context = context;
        this.mapCreator = mapCreator;
        this.mapDisplayer = mapDisplayer;
    }

    public void initializeSimulation() throws IOException {
        String filePathOfImage = context.getFilePathOfMapImage();
        mapCreator.generateMapFromImage(filePathOfImage);
        context.setPopulation(getInitializedPopulation());
        mapDisplayer.displayMap();
    }

    private List<Animal> getInitializedPopulation() {
        int NUMBER_OF_ANIMALS = context.getNUMBER_OF_ANIMALS();
        int MAP_WITH = context.getMAP_WIDTH();
        int MAP_HEIGHT = context.getMAP_HEIGHT();
        return IntStream.range(1, NUMBER_OF_ANIMALS)
                .mapToObj(index -> {
                    float x = getRandomInitialPositionCoordinate(MAP_WITH);
                    float y = getRandomInitialPositionCoordinate(MAP_HEIGHT);
                    return new Animal(x,y);})
                .collect(Collectors.toList());
    }

    private float getRandomInitialPositionCoordinate(int maxValue) {
        return (float) Math.round(Math.random() * maxValue);
    }
}