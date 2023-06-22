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
    private final int MAP_HEIGHT;
    private final int MAP_WIDTH;
    private final int MAP_GENERATION_SCALE_FACTOR;
    private SurfaceType[][] map;

    public MapDisplayer(Context context) {
        this.context = context;
        this.MAP_HEIGHT = context.getMAP_HEIGHT();
        this.MAP_WIDTH = context.getMAP_WIDTH();
        this.MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        this.map = context.getMap();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int x = 0; x < MAP_HEIGHT; x++) {
            for (int y = 0; y < MAP_WIDTH; y++) {
                int xScaled = x * MAP_GENERATION_SCALE_FACTOR;
                int yScaled = y * MAP_GENERATION_SCALE_FACTOR;
                SurfaceType surfaceType = map[x][y];
                Color color = getColorForSurfaceType(surfaceType);
                graphics.setColor(color);
                graphics.fillRect(x, y, MAP_GENERATION_SCALE_FACTOR, MAP_GENERATION_SCALE_FACTOR);
            }
        }
    }

    private void generateMapFromImage(String imagePath) throws IOException {
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

    private SurfaceType getSurfaceTypeFromColor(Color pixelColor) {
        if(isColorInRange(pixelColor, Color.GREEN, 20)) {
            return SurfaceType.ACCESSIBLE_TERRAIN;
        } else if (isColorInRange(pixelColor, Color.BLUE, 20)){
            return SurfaceType.WATER;
        } else {
            return SurfaceType.INACCESSIBLE_TERRAIN;
        }
    }
}
