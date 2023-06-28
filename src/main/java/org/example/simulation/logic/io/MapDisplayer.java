package org.example.simulation.logic.io;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.SurfaceType;

import javax.swing.*;
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
        frame.setSize(context.getMAP_WIDTH(), context.getMAP_HEIGHT());
        frame.add(this);
        frame.setVisible(true);
        SwingUtilities.invokeLater(this::repaint);
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
