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
        System.out.println("new method call isAreaInaccessible: _______________________________");
        for (int x = nextX; x <= nextX + context.getANIMAL_SIZE(); x++) {
            for (int y = nextY; y <= nextY + context.getANIMAL_SIZE(); y++) {
                if (isFieldOutOfMap(x, y)){
                    System.out.println("fieldOutOfMap");
                    return true;
                }
                if (map[x][y].equals(SurfaceType.WATER) || map[x][y].equals(SurfaceType.INACCESSIBLE_TERRAIN)) {
                    System.out.println("x in method isAreaInaccessible = " + x);
                    System.out.println("y in method isAreaInaccessible = " + y);
                    System.out.println("map[x][y] in method isAreaInaccessible= " + map[x][y]);
                    return true;
                }
            }
        }
        System.out.println("End of method call: result: false _____________________________________________");
        return false;
    }

    public boolean isFieldOccupied(Animal currentAnimal, int nextX, int nextY) {
        List<Animal> otherAnimals = getOtherAnimals(currentAnimal);
        for (Animal otherAnimal : otherAnimals) {
            double distance = Math.sqrt(Math.pow(nextX - otherAnimal.getX(), 2) + Math.pow(nextY - otherAnimal.getY(), 2));
            if (distance < context.getANIMAL_SIZE()) {
                return true;
            }
        }
        System.out.println("is field occupied: " + "false");
        return false;
    }

    public boolean isFieldOutOfMap(int nextX, int nextY) {
        SurfaceType[][] map = context.getMap();
        return nextX >= map.length || nextY >= map[0].length;
    }


    private List<Animal> getOtherAnimals(Animal currentAnimal) {
        if(currentAnimal == null) {
            return context.getPopulation();
        }
        return context.getPopulation()
                .stream()
                .filter(animal -> animal.getX() != currentAnimal.getX() && animal.getY() != currentAnimal.getY())
                .toList();
    }
}
