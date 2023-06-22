package org.example.simulation.logic.map;

import org.example.simulation.data.Animal;
import org.example.simulation.data.SurfaceType;
import org.example.simulation.data.Context;

import java.awt.*;

public class ColorHandler {
    private final Context context;

    public ColorHandler(Context context) {
        this.context = context;
    }

    public Color getColorForSurfaceType(SurfaceType surfaceType) {
        int COLOR_VALUE_RANGE = context.getCOLOR_VALUE_RANGE();
        switch (surfaceType) {
            case WATER:
                return generateRandomColorInRange(context.getCOLOR_WATER(), COLOR_VALUE_RANGE);
            case ACCESSIBLE_TERRAIN:
                return generateRandomColorInRange(context.getCOLOR_ACCESSIBLE_TERRAIN(), COLOR_VALUE_RANGE);
            case INACCESSIBLE_TERRAIN:
                return generateRandomColorInRange(context.getCOLOR_INACCESSIBLE_TERRAIN(), COLOR_VALUE_RANGE);
            default:
                return Color.WHITE;
        }
    }

    private Color generateRandomColorInRange(Color referenceColor, int range) {
        int red = referenceColor.getRed() + (int) (Math.random() * (range)) - range;
        int green = referenceColor.getGreen() + (int) (Math.random() * (range)) - range;
        int blue = referenceColor.getBlue() + (int) (Math.random() * (range)) - range;
        return new Color(limitColorValue(red), limitColorValue(green), limitColorValue(blue));
    }

    private int limitColorValue(int color) {
        int MAX_COLOR_VALUR = context.getMAX_COLOR_VALUE();
        int MIN_COLOR_VALUE = context.getMIN_COLOR_VALUE();
        return Math.min(Math.max(color, MIN_COLOR_VALUE), MAX_COLOR_VALUR);
    }

    public Color getAnimalColor(Animal animal) {
        return switch (animal.getHealthState()) {
            case HEALTHY -> context.getCOLOR_HEALTHY_ANIMAL();
            case INFECTED -> context.getCOLOR_INFECTED_ANIMAL();
            case RECOVERED -> context.getCOLOR_RECOVERED_ANIMAL();
        };
    }
}
