package org.example.simulation.logic.io;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.SurfaceType;

import javax.swing.*;
import javax.swing.text.FlowView;
import java.awt.*;
import java.util.List;

public class MapDisplayer extends JPanel {
    private final Context context;
    private final int MAP_GENERATION_SCALE_FACTOR;
    private final ColorHandler colorHandler;

    public MapDisplayer(Context context, ColorHandler colorHandler) {
        this.context = context;
        this.MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        this.colorHandler = colorHandler;
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

        JSlider sliderNumberOfAnimals = new JSlider(JSlider.HORIZONTAL, 10, 1000, context.getNUMBER_OF_ANIMALS());
        sliderInfectiousness.setMinorTickSpacing(10);
        sliderInfectiousness.setMajorTickSpacing(100);
        sliderInfectiousness.setPaintTicks(true);
        sliderInfectiousness.setPaintLabels(true);
        sliderInfectiousness.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            int value = source.getValue();
            context.setNUMBER_OF_ANIMALS(value);
        });

        JLabel label = new JLabel("Virus-Infectiousness : ");
        JPanel panelSlider = new JPanel();
        panelSlider.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSlider.add(label);
        panelSlider.add(sliderInfectiousness);
        controlPanel.add(panelSlider);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton buttonStart = new JButton("Restart");
        buttonStart.addActionListener(event -> {
            buttonStart.setText("Restart");
        });
        buttonStart.setPreferredSize(new Dimension(100,25));
        JButton buttonPause = new JButton("Stop");
        buttonPause.setPreferredSize(new Dimension(100,25));
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
        buttonEnd.setPreferredSize(new Dimension(100,25));

        panelButtons.add(buttonStart);
        panelButtons.add(buttonPause);
        panelButtons.add(buttonEnd);
        controlPanel.add(panelButtons);

        return controlPanel;
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
