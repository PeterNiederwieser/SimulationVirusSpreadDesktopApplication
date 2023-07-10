package org.example.simulation.logic.utils;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.SurfaceType;

import java.util.List;

public class MapFieldUtils {
    private final Context context;

    public MapFieldUtils(Context context) {
        this.context = context;
    }

    public boolean isAreaInaccessible(int nextX, int nextY) {
        SurfaceType[][] map = context.getMap();
        for (int x = nextX; x <= nextX + context.getANIMAL_SIZE(); x++) {
            for (int y = nextY; y <= nextY + context.getANIMAL_SIZE(); y++) {
                if (isFieldOutOfMap(x, y)) {
                    return true;
                }
                if (map[x][y].equals(SurfaceType.WATER) || map[x][y].equals(SurfaceType.INACCESSIBLE_TERRAIN)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFieldOccupied(Animal currentAnimal, int nextX, int nextY) {
        List<Animal> otherAnimals = getOtherAnimals(currentAnimal);
        for (Animal otherAnimal : otherAnimals) {
            int distance = (int) Math.ceil(Math.sqrt(Math.pow(nextX - otherAnimal.getX(), 2) + Math.pow(nextY - otherAnimal.getY(), 2)));
            if (distance < context.getANIMAL_SIZE()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFieldOutOfMap(int nextX, int nextY) {
        SurfaceType[][] map = context.getMap();
        return nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length;
    }

    private List<Animal> getOtherAnimals(Animal currentAnimal) {
        if (currentAnimal == null) {
            return context.getPopulation();
        }
        return context.getPopulation()
                .stream()
                .filter(animal -> !(animal.getX() == currentAnimal.getX() && animal.getY() == currentAnimal.getY()))
                .toList();
    }
}
