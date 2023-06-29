package org.example.simulation.logic.io;

import org.example.simulation.data.*;
import org.example.simulation.logic.initialisation.Initializer;
import org.example.simulation.logic.utils.MapFieldUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class MapDisplayer extends JPanel {
    private final Context context;
    private final int MAP_GENERATION_SCALE_FACTOR;
    private final ColorHandler colorHandler;
    private final MapFieldUtils mapFieldUtils;

    public MapDisplayer(Context context, ColorHandler colorHandler, MapFieldUtils mapFieldUtils) {
        this.context = context;
        this.MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        this.colorHandler = colorHandler;
        this.mapFieldUtils = mapFieldUtils;
    }

    public void displayMap() {
        setDoubleBuffered(true);
        JFrame frame = new JFrame("Simulation of virus spreading");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        JPanel controlPanel = createControlPanel();
        frame.add(controlPanel, BorderLayout.EAST);
        frame.setSize(context.getFRAME_WIDTH(), context.getFRAME_HEIGHT());
        frame.setVisible(true);
        SwingUtilities.invokeLater(this::repaint);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setSize(100, context.getFRAME_HEIGHT());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 100));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        JLabel labelHeading = new JLabel("Virus Spread Simulation");
        labelHeading.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelHeading.setFont(new Font("Calibri", Font.BOLD, 18));
        controlPanel.add(labelHeading);

        JSlider sliderNumberOfAnimals = new JSlider(JSlider.HORIZONTAL, 0, 1000, context.getNUMBER_OF_ANIMALS());
        sliderNumberOfAnimals.setMinorTickSpacing(100);
        sliderNumberOfAnimals.setMajorTickSpacing(200);
        sliderNumberOfAnimals.setPaintTicks(true);
        sliderNumberOfAnimals.setPaintLabels(true);
        sliderNumberOfAnimals.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            int value = source.getValue();
            context.setNUMBER_OF_ANIMALS(value);
            actualizePopulation();
        });
        JLabel labelNumberOfAnimals = new JLabel("Number of animals:     ");
        JPanel panelSliderNumberOfAnimals = new JPanel();
        panelSliderNumberOfAnimals.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSliderNumberOfAnimals.add(labelNumberOfAnimals);
        panelSliderNumberOfAnimals.add(sliderNumberOfAnimals);
        controlPanel.add(panelSliderNumberOfAnimals);

        JSlider sliderInitialInfections = new JSlider(JSlider.HORIZONTAL, 0, 100, context.getNUMBER_OF_INITIAL_INFECTIONS());
        sliderInitialInfections.setMinorTickSpacing(10);
        sliderInitialInfections.setMajorTickSpacing(20);
        sliderInitialInfections.setPaintTicks(true);
        sliderInitialInfections.setPaintLabels(true);
        sliderInitialInfections.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            int value = source.getValue();
            context.setNUMBER_OF_INITIAL_INFECTIONS(value);
            actualizeInfections(value);
        });
        JLabel labelInitialInfections = new JLabel("Initial infections:          ");
        JPanel panelSliderInitialInfections = new JPanel();
        panelSliderInitialInfections.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSliderInitialInfections.add(labelInitialInfections);
        panelSliderInitialInfections.add(sliderInitialInfections);
        controlPanel.add(panelSliderInitialInfections);

        JSlider sliderInfectiousness = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) context.getPROBABILITY_OF_INFECTION() * 100);
        sliderInfectiousness.setMinorTickSpacing(10);
        sliderInfectiousness.setMajorTickSpacing(20);
        sliderInfectiousness.setPaintTicks(true);
        sliderInfectiousness.setPaintLabels(true);
        sliderInfectiousness.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            float value = source.getValue();
            context.setPROBABILITY_OF_INFECTION(value / 100);
        });
        JLabel labelInfectiousness = new JLabel("Virus-Infectiousness:  ");
        JPanel panelSliderInfectiousness = new JPanel();
        panelSliderInfectiousness.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSliderInfectiousness.add(labelInfectiousness);
        panelSliderInfectiousness.add(sliderInfectiousness);
        controlPanel.add(panelSliderInfectiousness);

        JSlider sliderLethality = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) context.getPROBABILITY_OF_FATAL_INFECTION_COURSE() * 100);
        sliderLethality.setMinorTickSpacing(10);
        sliderLethality.setMajorTickSpacing(20);
        sliderLethality.setPaintTicks(true);
        sliderLethality.setPaintLabels(true);
        sliderLethality.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            float value = source.getValue();
            context.setPROBABILITY_OF_FATAL_INFECTION_COURSE(value / 100);
        });
        JLabel labelLethality = new JLabel("Mortality rate:              ");
        JPanel panelSliderLethality = new JPanel();
        panelSliderLethality.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSliderLethality.add(labelLethality);
        panelSliderLethality.add(sliderLethality);
        controlPanel.add(panelSliderLethality);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(event -> {
            if (!context.isSimulationOngoing()) {
                context.setSimulationOngoing(true);
                buttonStart.setText("Restart");
            } else {
                context.setShouldSimulationRestart(true);
            }
        });
        buttonStart.setPreferredSize(new Dimension(100, 25));
        JButton buttonPause = new JButton("Stop");
        buttonPause.setPreferredSize(new Dimension(100, 25));
        buttonPause.addActionListener(event -> {
            boolean isSimulationPaused = context.isSimulationPaused();
            context.setSimulationPaused(!isSimulationPaused);
            String newButtonText = context.isSimulationPaused() ? "Continue" : "Stop";
            buttonPause.setText(newButtonText);
        });
        JButton buttonEnd = new JButton("End");
        buttonEnd.addActionListener(event -> {
            System.exit(0);
        });
        buttonEnd.setPreferredSize(new Dimension(100, 25));

        panelButtons.add(buttonStart);
        panelButtons.add(buttonPause);
        panelButtons.add(buttonEnd);
        controlPanel.add(panelButtons);

        return controlPanel;
    }

    private void actualizeInfections(int newNumberOfInfections) {
        System.out.println("newNumberOfInfections = " + newNumberOfInfections);
        int actualNumberOfInfections = context.getPopulation().stream()
                .filter(animal -> animal.getHealthState().equals(HealthState.INFECTED))
                .toList().size();
        System.out.println("actualNumberOfInfections = " + actualNumberOfInfections);
        int difference = newNumberOfInfections - actualNumberOfInfections;
        if (difference < 0) {
            for (int i = 1; i <= difference * (-1); i++) {
                Animal infectedAnimal = context.getPopulation().stream()
                        .filter(animal -> animal.getHealthState().equals(HealthState.INFECTED))
                        .findFirst()
                        .get();
                infectedAnimal.setHealthState(HealthState.HEALTHY);
            }
        }
        if (difference > 0) {
            for (int i = 1; i <= difference; i++) {
                Animal healthyAnimal = context.getPopulation().stream()
                        .filter(animal -> animal.getHealthState().equals(HealthState.HEALTHY))
                        .findFirst()
                        .get();
                healthyAnimal.setHealthState(HealthState.INFECTED);
            }
        }
        repaint();
    }

    private void actualizePopulation() {
        List<Animal> population = context.getPopulation();
        int newNumberOfAnimals = context.getNUMBER_OF_ANIMALS();
        int actualNumberOfAnimals = population.size();
        int difference = newNumberOfAnimals - actualNumberOfAnimals;
        if (difference < 0) {
            for (int i = 1; i <= difference * (-1); i++) {
                Animal healthyAnimal = context.getPopulation().stream()
                        .filter(animal -> animal.getHealthState().equals(HealthState.HEALTHY))
                        .findFirst()
                        .get();
                context.getPopulation().remove(healthyAnimal);
                this.repaint();
            }
        }
        if (difference > 0) {
            for (int i = 1; i <= difference; i++) {
                Position position = getRandomInitialPosition();
                int timeOfPossibleSevereIllnessAfterInfection = Math.max((int) Math.round(Math.random() * context.getTIME_OF_RECOVERY()), context.getMIN_TIME_FOR_SEVERE_ILLNESS_AFTER_INFECTION());
                boolean isGettingSeverelyIll = Math.random() <= context.getPROBABILITY_OF_FATAL_INFECTION_COURSE();
                population.add(new Animal(position.x(), position.y(), context.getMAX_ANIMAL_SPEED(), HealthState.HEALTHY, BehaviourType.STROLL, timeOfPossibleSevereIllnessAfterInfection, isGettingSeverelyIll));
                this.repaint();
            }
        }
        actualizeInfections(context.getNUMBER_OF_INITIAL_INFECTIONS());
    }

    private Position getRandomInitialPosition() {
        int MAP_HEIGHT = context.getMAP_HEIGHT();
        int MAP_WIDTH = context.getMAP_WIDTH();
        int x, y;
        do {
            x = (int) Math.round(Math.random() * MAP_HEIGHT);
            y = (int) Math.round(Math.random() * MAP_WIDTH);
        } while (mapFieldUtils.isAreaInaccessible(x, y) || mapFieldUtils.isFieldOccupied(null, x, y));
        return new Position(x, y);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        displaySurface(graphics);
        displayPopulation(graphics);
    }

    private void displaySurface(Graphics graphics) {
        ImageIcon imageIcon = new ImageIcon("map.png");
        Image image = imageIcon.getImage();
        graphics.drawImage(image, 0, 0, null);
        /*SurfaceType[][] map = context.getMap();
        int MAP_HEIGHT = map.length;
        int MAP_WIDTH = map[0].length;
        for (int x = 0; x < MAP_HEIGHT; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                paintSurfaceType(graphics, x, y);
            }
        }*/
    }

    private void paintSurfaceType(Graphics graphics, int x, int y) {
        int xScaled = x * MAP_GENERATION_SCALE_FACTOR;
        int yScaled = y * MAP_GENERATION_SCALE_FACTOR;
        SurfaceType[][] map = context.getMap();
        SurfaceType surfaceType = map[x][y];
        Color color = colorHandler.getColorForSurfaceType(surfaceType);
        graphics.setColor(color);
        graphics.fillRect(xScaled, yScaled, MAP_GENERATION_SCALE_FACTOR, MAP_GENERATION_SCALE_FACTOR);
    }

    public void displayPopulation(Graphics graphics) {
        List<Animal> population = context.getPopulation();
        int ANIMAL_SIZE = context.getANIMAL_SIZE();
        population.forEach(animal -> {
            int x = animal.getX() * MAP_GENERATION_SCALE_FACTOR;
            int y = animal.getY() * MAP_GENERATION_SCALE_FACTOR;
            Color animalColor = colorHandler.getAnimalColor(animal);
            graphics.setColor(animalColor);
            graphics.fillOval(x, y, ANIMAL_SIZE, ANIMAL_SIZE);
        });
    }

}
