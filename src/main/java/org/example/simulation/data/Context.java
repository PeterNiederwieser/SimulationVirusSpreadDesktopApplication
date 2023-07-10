package org.example.simulation.data;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Component
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
    private boolean isChartDataShown = false;
    private int totalNumberOfInfectedAnimals;
    private int totalNumberOfHealthyAnimals;
    private int totalNumberOfRecoveredAnimals;
}
