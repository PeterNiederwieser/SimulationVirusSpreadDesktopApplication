package org.example.simulation.IO;

import org.example.simulation.configuration.Context;
import org.example.simulation.data.SurfaceType;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapDisplayer extends JPanel {
    private final Context context;
    private final int MAP_GENERATION_SCALE_FACTOR;
    private final int COLOR_VALUE_RANGE;
    private SurfaceType[][] map;

    public MapDisplayer(Context context) {
        this.context = context;
        this.MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        this.map = context.getMap();
        this.COLOR_VALUE_RANGE = context.getCOLOR_VALUE_RANGE();
    }

    public void generateMapFromImage(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        map = new SurfaceType[imageHeight][imageWidth];
        for (int x = 0; x < imageHeight; x++) {
            for (int y = 0; y < imageWidth; y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                SurfaceType surfaceType = getSurfaceTypeFromColor(pixelColor);
                map[x][y] = surfaceType;
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int MAP_HEIGHT = map.length;
        int MAP_WIDTH = map[0].length;
        for (int x = 0; x < MAP_HEIGHT; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                int xScaled = x * MAP_GENERATION_SCALE_FACTOR;
                int yScaled = y * MAP_GENERATION_SCALE_FACTOR;
                SurfaceType surfaceType = map[x][y];
                Color color = getColorForSurfaceType(surfaceType);
                graphics.setColor(color);
                graphics.fillRect(xScaled, yScaled, MAP_GENERATION_SCALE_FACTOR, MAP_GENERATION_SCALE_FACTOR);
            }
        }
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

    private SurfaceType getSurfaceTypeFromColor(Color pixelColor) {
        if(isColorInRange(pixelColor, Color.decode("#66ed7d"), COLOR_VALUE_RANGE)) {
            return SurfaceType.ACCESSIBLE_TERRAIN;
        } else if (isColorInRange(pixelColor, Color.decode("#030574"), COLOR_VALUE_RANGE)){
            return SurfaceType.WATER;
        } else {
            return SurfaceType.INACCESSIBLE_TERRAIN;
        }
    }

    private boolean isColorInRange(Color colorToCheck, Color referenceColor, int tolerance) {
        int redDifference = Math.abs(colorToCheck.getRed() - referenceColor.getRed());
        int greenDifference = Math.abs(colorToCheck.getGreen() - referenceColor.getGreen());
        int blueDifference = Math.abs(referenceColor.getBlue() - referenceColor.getBlue());
        return redDifference <= tolerance && greenDifference <= tolerance && blueDifference <= tolerance;
    }
}
