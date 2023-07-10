package org.example.simulation.logic.io;

import org.example.simulation.data.Context;
import org.example.simulation.data.SurfaceType;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Component
public class MapCreator {
    private final Context context;
    private SurfaceType[][] map;

    public MapCreator(Context context) {
        this.context = context;
        this.map = context.getMap();
    }

    public void generateMapFromImage(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int imageHeight = context.getMAP_HEIGHT();
        int imageWidth = context.getMAP_WIDTH();
        map = new SurfaceType[imageHeight][imageWidth];
        for (int x = 0; x < imageHeight; x++) {
            for (int y = 0; y < imageWidth; y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                SurfaceType surfaceType = getSurfaceTypeFromColor(pixelColor);
                map[x][y] = surfaceType;
            }
        }
        context.setMap(map);
    }

    private SurfaceType getSurfaceTypeFromColor(Color pixelColor) {
        int COLOR_VALUE_RANGE = context.getCOLOR_VALUE_RANGE();
        if (isColorInRange(pixelColor, Color.decode("#030574"), COLOR_VALUE_RANGE)) {
            return SurfaceType.WATER;
        } else {
            return SurfaceType.ACCESSIBLE_TERRAIN;
        }
    }

    private boolean isColorInRange(Color colorToCheck, Color referenceColor, int tolerance) {
        int redDifference = Math.abs(colorToCheck.getRed() - referenceColor.getRed());
        int greenDifference = Math.abs(colorToCheck.getGreen() - referenceColor.getGreen());
        int blueDifference = Math.abs(referenceColor.getBlue() - referenceColor.getBlue());
        return redDifference <= tolerance && greenDifference <= tolerance && blueDifference <= tolerance;
    }
}
