package org.example.simulation.logic.io;

import org.example.simulation.data.Context;
import org.example.simulation.data.SurfaceType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapPrinter {
    int PIXEL_SCALE = 50;
    private final ColorHandler colorHandler;

    public MapPrinter(ColorHandler colorHandler) {
        this.colorHandler = colorHandler;
    }

    public void printMap(Context context) {
        int IMAGE_HEIGHT = context.getMAP_HEIGHT();
        int IMAGE_WIDTH = context.getMAP_WIDTH();
        int MAP_GENERATION_SCALE_FACTOR = context.getMAP_GENERATION_SCALE_FACTOR();
        SurfaceType[][] map = context.getMap();
        BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                int xScaled = x * MAP_GENERATION_SCALE_FACTOR;
                int yScaled = y * MAP_GENERATION_SCALE_FACTOR;
                SurfaceType surfaceType = map[x][y];
                Color color = colorHandler.getColorForSurfaceType(surfaceType);
                graphics2D.setColor(color);
                graphics2D.fillRect(xScaled, yScaled, MAP_GENERATION_SCALE_FACTOR, MAP_GENERATION_SCALE_FACTOR);
            }
        }

        graphics2D.dispose();

        File file = new File("map.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
