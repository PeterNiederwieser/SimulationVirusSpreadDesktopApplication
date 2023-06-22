package org.example.simulation.logic.map;

import org.example.simulation.data.configuration.Context;
import org.example.simulation.data.SurfaceType;

import javax.swing.*;
import java.awt.*;

public class MapDisplayer extends JPanel {
    private final Context context;
    private final int MAP_GENERATION_SCALE_FACTOR;
    private final int COLOR_VALUE_RANGE;

    public MapDisplayer(Context context) {
        this.context = context;
        this.MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        this.COLOR_VALUE_RANGE = context.getCOLOR_VALUE_RANGE();
    }

    public void displayMap() {
        JFrame frame = new JFrame("Simulation of virus spreading");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(context.getMAP_WIDTH(), context.getMAP_HEIGHT());
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        SurfaceType[][] map = context.getMap();
        int MAP_HEIGHT = map.length;
        int MAP_WIDTH = map[0].length;
        for (int x = 0; x < MAP_HEIGHT; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                paintSurfaceType(graphics, x, y);
            }
        }
    }

    private void paintSurfaceType(Graphics graphics, int x, int y) {
        int xScaled = x * MAP_GENERATION_SCALE_FACTOR;
        int yScaled = y * MAP_GENERATION_SCALE_FACTOR;
        SurfaceType[][] map = context.getMap();
        SurfaceType surfaceType = map[x][y];
        Color color = getColorForSurfaceType(surfaceType);
        graphics.setColor(color);
        graphics.fillRect(xScaled, yScaled, MAP_GENERATION_SCALE_FACTOR, MAP_GENERATION_SCALE_FACTOR);
    }

    private Color getColorForSurfaceType(SurfaceType surfaceType) {
        switch (surfaceType) {
            case WATER:
                return generateRandomColorInRange(Color.decode("#054177"), COLOR_VALUE_RANGE);
            case ACCESSIBLE_TERRAIN:
                return generateRandomColorInRange(Color.decode("#2C5F2D"), COLOR_VALUE_RANGE);
            case INACCESSIBLE_TERRAIN:
                return generateRandomColorInRange(Color.decode("#97BC62"), COLOR_VALUE_RANGE);
            default:
                return Color.WHITE;
        }
    }

    private Color generateRandomColorInRange(Color referenceColor, int range) {
        int red = referenceColor.getRed() + (int) (Math.random() * (range)) - range;
        int green = referenceColor.getGreen() + (int) (Math.random() * (range)) - range;
        int blue = referenceColor.getBlue() + (int) (Math.random() * (range)) - range;
        return new Color(limitValue(red), limitValue(green), limitValue(blue));
    }

    private int limitValue(int color) {
        int MAX_COLOR_VALUR = context.getMAX_COLOR_VALUE();
        int MIN_COLOR_VALUE = context.getMIN_COLOR_VALUE();
        return Math.min(Math.max(color, MIN_COLOR_VALUE), MAX_COLOR_VALUR);
    }

    private void displayPopulation() {

    }
}
