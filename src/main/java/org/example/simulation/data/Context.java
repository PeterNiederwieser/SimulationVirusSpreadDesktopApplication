package org.example.simulation.data;

import org.example.simulation.data.Animal;
import org.example.simulation.data.SurfaceType;

import java.awt.*;
import java.util.List;

public class Context {
    private final float PROBABILITY_OF_INFECTION = 0.8F;
    private final float TIME_OF_RECOVERY = 1000F;
    private final int NUMBER_OF_ANIMALS = 50;
    private final int NUMBER_OF_INITIAL_INFECTIONS = 10;
    private final int ANIMAL_SIZE = 10;
    private final int DELAY = 1;
    private final int COLOR_VALUE_RANGE = 40;
    private final int MAP_GENERATION_SCALE_FACTOR = 2;
    private final int MAP_WIDTH = 1000;
    private final int MAP_HEIGHT = 1000;
    private final int MIN_COLOR_VALUE = 0;
    private final int MAX_COLOR_VALUE = 255;
    private final Color COLOR_HEALTHY_ANIMAL = Color.decode("#38f5f5");
    private final Color COLOR_INFECTED_ANIMAL = Color.decode("#fa602d");
    private final Color COLOR_RECOVERED_ANIMAL = Color.decode("#f5e616");
    private final Color COLOR_WATER = Color.decode("#054177");
    private final Color COLOR_ACCESSIBLE_TERRAIN = Color.decode("#2C5F2D");
    private final Color COLOR_INACCESSIBLE_TERRAIN = Color.decode("#97BC62");
    private int stepNumber = 1;
    private boolean isSimulationOngoing = true;
    private final String filePathOfMapImage = "src/main/resources/MapImage_by_DALL·E .png";
    private List<Animal> population;
    private SurfaceType[][] map;

    public float getPROBABILITY_OF_INFECTION() {
        return PROBABILITY_OF_INFECTION;
    }

    public float getTIME_OF_RECOVERY() {
        return TIME_OF_RECOVERY;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getNUMBER_OF_ANIMALS() {
        return NUMBER_OF_ANIMALS;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public List<Animal> getPopulation() {
        return population;
    }

    public int getDELAY() {
        return DELAY;
    }

    public boolean isSimulationOngoing() {
        return isSimulationOngoing;
    }

    public SurfaceType[][] getMap() {
        return map;
    }

    public void setMap(SurfaceType[][] map) {
        this.map = map;
    }

    public int getMAP_GENERATION_SCALE_FACTOR() {
        return MAP_GENERATION_SCALE_FACTOR;
    }

    public int getMAX_COLOR_VALUE() {
        return MAX_COLOR_VALUE;
    }

    public int getMIN_COLOR_VALUE() {
        return MIN_COLOR_VALUE;
    }

    public int getCOLOR_VALUE_RANGE() {
        return COLOR_VALUE_RANGE;
    }

    public String getFilePathOfMapImage() {
        return filePathOfMapImage;
    }

    public void setPopulation(List<Animal> population) {
        this.population = population;
    }

    public int getANIMAL_SIZE() {
        return ANIMAL_SIZE;
    }

    public Color getCOLOR_HEALTHY_ANIMAL() {
        return COLOR_HEALTHY_ANIMAL;
    }

    public Color getCOLOR_INFECTED_ANIMAL() {
        return COLOR_INFECTED_ANIMAL;
    }

    public Color getCOLOR_RECOVERED_ANIMAL() {
        return COLOR_RECOVERED_ANIMAL;
    }

    public Color getCOLOR_WATER() {
        return COLOR_WATER;
    }

    public Color getCOLOR_ACCESSIBLE_TERRAIN() {
        return COLOR_ACCESSIBLE_TERRAIN;
    }

    public Color getCOLOR_INACCESSIBLE_TERRAIN() {
        return COLOR_INACCESSIBLE_TERRAIN;
    }

    public int getNUMBER_OF_INITIAL_INFECTIONS() {
        return NUMBER_OF_INITIAL_INFECTIONS;
    }
}
