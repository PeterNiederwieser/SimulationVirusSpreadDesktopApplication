package org.example.simulation.data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private float PROBABILITY_OF_INFECTION = 1.0F;
    private float PROBABILITY_OF_FATAL_INFECTION_COURSE = 0.4F;
    private final int MIN_TIME_FOR_SEVERE_ILLNESS_AFTER_INFECTION = 100;
    private final int DURATION_OF_SEVERE_ILLNESS = 200;
    private final int TIME_OF_RECOVERY = 200;
    private int NUMBER_OF_ANIMALS = 200;
    private int NUMBER_OF_INITIAL_INFECTIONS = 20;
    private final int INFECTION_RADIUS = 10;
    private final int ANIMAL_SIZE = 10;
    private final float MAX_ANIMAL_SPEED = 2.5F;
    private final float MAX_INFECTED_ANIMAL_SPEED = 2F;
    private final float MAX_SEVERELY_ILL_ANIMAL_SPEED = 1F;
    private final float MIN_ANIMAL_SPEED = 1F;
    private final int MAX_TRIALS_OF_DIRECTION_CHANGE_FOR_SINGLE_MOVE = 200;
    private final int DELAY_IN_MS = 40;
    private final int COLOR_VALUE_RANGE = 40;
    private final int MAP_GENERATION_SCALE_FACTOR = 1;
    private final int MAP_WIDTH = 800;
    private final int MAP_HEIGHT = 800;
    private final int WINDOW_HEIGHT_CORRECTION = 37;
    private final int MIN_COLOR_VALUE = 0;
    private final int MAX_COLOR_VALUE = 255;
    private final int FRAME_WIDTH = 1850;
    private final int FRAME_HEIGHT = 1200;
    private List<Integer> infectionNumbersForCharts = new ArrayList<>();
    private List<Integer> lethalInfectionNumbersForCharts = new ArrayList<>();
    private List<Integer> uninfectedAnimalNumbersForCharts = new ArrayList<>();
    private List<Integer> recoveredAnimalNumbersForCharts = new ArrayList<>();
    private int totalNumberOfDeadAnimals = 0;
    private final Color COLOR_HEALTHY_ANIMAL = Color.decode("#38f5f5");
    private final Color COLOR_INFECTED_ANIMAL = Color.decode("#fa602d");
    private final Color COLOR_RECOVERED_ANIMAL = Color.decode("#f5e616");
    private final Color COLOR_SEVERELY_ILL_ANIMAL = Color.BLACK;
    private final Color COLOR_WATER = Color.decode("#054177");
    private final Color COLOR_ACCESSIBLE_TERRAIN = Color.decode("#97BC62");
    private final Color COLOR_INACCESSIBLE_TERRAIN = Color.decode("#2C5F2D");
    private int stepNumber = 1;
    private boolean isSimulationOngoing = false;
    private boolean isSimulationPaused = false;
    private boolean shouldSimulationRestart = false;
    private final String filePathOfMapImage = "src/main/resources/MapImage_by_DALL·E .png";
    private List<Animal> population = new ArrayList<>();
    private SurfaceType[][] map;
    private String textForButtonPause = "Stop";
    private int numberOfAnimalDeathsInCurrentTimeInterval;
    private int numberOfNewInfectionsInCurrentTimeInterval;
    private boolean isChartDataShown = true;

    private int totalNumberOfInfectedAnimals;
    private int totalNumberOfHealthyAnimals;
    private int totalNumberOfRecoveredAnimals;

    public float getPROBABILITY_OF_INFECTION() {
        return PROBABILITY_OF_INFECTION;
    }

    public int getTIME_OF_RECOVERY() {
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

    public int getDELAY_IN_MS() {
        return DELAY_IN_MS;
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

    public float getMAX_ANIMAL_SPEED() {
        return MAX_ANIMAL_SPEED;
    }

    public int getMAX_TRIALS_OF_DIRECTION_CHANGE() {
        return MAX_TRIALS_OF_DIRECTION_CHANGE_FOR_SINGLE_MOVE;
    }

    public int getINFECTION_RADIUS() {
        return INFECTION_RADIUS;
    }

    public float getPROBABILITY_OF_FATAL_INFECTION_COURSE() {
        return PROBABILITY_OF_FATAL_INFECTION_COURSE;
    }

    public int getMAX_TRIALS_OF_DIRECTION_CHANGE_FOR_SINGLE_MOVE() {
        return MAX_TRIALS_OF_DIRECTION_CHANGE_FOR_SINGLE_MOVE;
    }

    public int getMIN_TIME_FOR_SEVERE_ILLNESS_AFTER_INFECTION() {
        return MIN_TIME_FOR_SEVERE_ILLNESS_AFTER_INFECTION;
    }

    public int getDURATION_OF_SEVERE_ILLNESS() {
        return DURATION_OF_SEVERE_ILLNESS;
    }

    public Color getCOLOR_SEVERELY_ILL_ANIMAL() {
        return COLOR_SEVERELY_ILL_ANIMAL;
    }

    public float getMAX_INFECTED_ANIMAL_SPEED() {
        return MAX_INFECTED_ANIMAL_SPEED;
    }

    public float getMAX_SEVERELY_ILL_ANIMAL_SPEED() {
        return MAX_SEVERELY_ILL_ANIMAL_SPEED;
    }

    public float getMIN_ANIMAL_SPEED() {
        return MIN_ANIMAL_SPEED;
    }

    public int getWINDOW_HEIGHT_CORRECTION() {
        return WINDOW_HEIGHT_CORRECTION;
    }

    public int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public void setPROBABILITY_OF_INFECTION(float PROBABILITY_OF_INFECTION) {
        this.PROBABILITY_OF_INFECTION = PROBABILITY_OF_INFECTION;
    }

    public void setPROBABILITY_OF_FATAL_INFECTION_COURSE(float PROBABILITY_OF_FATAL_INFECTION_COURSE) {
        this.PROBABILITY_OF_FATAL_INFECTION_COURSE = PROBABILITY_OF_FATAL_INFECTION_COURSE;
    }

    public void setSimulationOngoing(boolean simulationOngoing) {
        isSimulationOngoing = simulationOngoing;
    }

    public boolean isSimulationPaused() {
        return isSimulationPaused;
    }

    public void setSimulationPaused(boolean simulationPaused) {
        isSimulationPaused = simulationPaused;
    }

    public String getTextForButtonPause() {
        return textForButtonPause;
    }

    public void setTextForButtonPause(String textForButtonPause) {
        this.textForButtonPause = textForButtonPause;
    }

    public boolean isShouldSimulationRestart() {
        return shouldSimulationRestart;
    }

    public void setShouldSimulationRestart(boolean shouldSimulationRestart) {
        this.shouldSimulationRestart = shouldSimulationRestart;
    }

    public void setNUMBER_OF_ANIMALS(int NUMBER_OF_ANIMALS) {
        this.NUMBER_OF_ANIMALS = NUMBER_OF_ANIMALS;
    }

    public void setNUMBER_OF_INITIAL_INFECTIONS(int NUMBER_OF_INITIAL_INFECTIONS) {
        this.NUMBER_OF_INITIAL_INFECTIONS = NUMBER_OF_INITIAL_INFECTIONS;
    }

    public List<Integer> getInfectionNumbersForCharts() {
        return infectionNumbersForCharts;
    }

    public List<Integer> getLethalInfectionNumbersForCharts() {
        return lethalInfectionNumbersForCharts;
    }

    public List<Integer> getUninfectedAnimalNumbersForCharts() {
        return uninfectedAnimalNumbersForCharts;
    }

    public List<Integer> getRecoveredAnimalNumbersForCharts() {
        return recoveredAnimalNumbersForCharts;
    }

    public void setInfectionNumbersForCharts(List<Integer> infectionNumbersForCharts) {
        this.infectionNumbersForCharts = infectionNumbersForCharts;
    }

    public void setLethalInfectionNumbersForCharts(List<Integer> lethalInfectionNumbersForCharts) {
        this.lethalInfectionNumbersForCharts = lethalInfectionNumbersForCharts;
    }

    public void setUninfectedAnimalNumbersForCharts(List<Integer> uninfectedAnimalNumbersForCharts) {
        this.uninfectedAnimalNumbersForCharts = uninfectedAnimalNumbersForCharts;
    }

    public void setRecoveredAnimalNumbersForCharts(List<Integer> recoveredAnimalNumbersForCharts) {
        this.recoveredAnimalNumbersForCharts = recoveredAnimalNumbersForCharts;
    }

    public int getNumberOfAnimalDeathsInCurrentTimeInterval() {
        return numberOfAnimalDeathsInCurrentTimeInterval;
    }

    public void setNumberOfAnimalDeathsInCurrentTimeInterval(int numberOfAnimalDeathsInCurrentTimeInterval) {
        this.numberOfAnimalDeathsInCurrentTimeInterval = numberOfAnimalDeathsInCurrentTimeInterval;
    }

    public int getNumberOfNewInfectionsInCurrentTimeInterval() {
        return numberOfNewInfectionsInCurrentTimeInterval;
    }

    public void setNumberOfNewInfectionsInCurrentTimeInterval(int numberOfNewInfectionsInCurrentTimeInterval) {
        this.numberOfNewInfectionsInCurrentTimeInterval = numberOfNewInfectionsInCurrentTimeInterval;
    }

    public boolean isChartDataShown() {
        return isChartDataShown;
    }

    public void setChartDataShown(boolean chartDataShown) {
        this.isChartDataShown = chartDataShown;
    }

    public int getTotalNumberOfDeadAnimals() {
        return totalNumberOfDeadAnimals;
    }

    public void setTotalNumberOfDeadAnimals(int totalNumberOfDeadAnimals) {
        this.totalNumberOfDeadAnimals = totalNumberOfDeadAnimals;
    }

    public int getTotalNumberOfInfectedAnimals() {
        return totalNumberOfInfectedAnimals;
    }

    public void setTotalNumberOfInfectedAnimals(int totalNumberOfInfectedAnimals) {
        this.totalNumberOfInfectedAnimals = totalNumberOfInfectedAnimals;
    }

    public int getTotalNumberOfHealthyAnimals() {
        return totalNumberOfHealthyAnimals;
    }

    public void setTotalNumberOfHealthyAnimals(int totalNumberOfHealthyAnimals) {
        this.totalNumberOfHealthyAnimals = totalNumberOfHealthyAnimals;
    }

    public int getTotalNumberOfRecoveredAnimals() {
        return totalNumberOfRecoveredAnimals;
    }

    public void setTotalNumberOfRecoveredAnimals(int totalNumberOfRecoveredAnimals) {
        this.totalNumberOfRecoveredAnimals = totalNumberOfRecoveredAnimals;
    }
}
