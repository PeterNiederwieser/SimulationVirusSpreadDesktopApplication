package org.example.simulation.logic.animalBehaviour.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.MotionType;
import org.example.simulation.data.SurfaceType;

import java.util.List;

public class Stroll implements Behaviour {
    private final Context context;
    private final int MAX_ANIMAL_SPEED;
    private final SurfaceType[][] map;

    public Stroll(Context context) {
        this.context = context;
        this.map = context.getMap();
        this.MAX_ANIMAL_SPEED = context.getMAX_ANIMAL_SPEED();
    }

    @Override
    public void behave(Animal animal) {
        ensureCorrectVelocitySettings(animal);
        performNextStep(animal);
    }

    private void performNextStep(Animal animal) {
        int MAX_TRIALS_OF_DIRECTION_CHANGE = context.getMAX_TRIALS_OF_DIRECTION_CHANGE();
        int numberOfTrials = 0;
        int nextX = (int) ((float) animal.getX() + animal.getVelocityX());
        int nextY = (int) ((float) animal.getY() + animal.getVelocityY());
        while (numberOfTrials < MAX_TRIALS_OF_DIRECTION_CHANGE) {
            if (isMoveInCurrentDirectionPossible(animal, nextX, nextY)) {
                animal.setX(nextX);
                animal.setY(nextY);
                break;
            } else {
                setNewRandomVelocity(animal);
            }
            System.out.println("trial: " + numberOfTrials);
            numberOfTrials++;
        }
        System.out.println("Max trials reached __________________________________________");
    }

    private void setNewRandomVelocity(Animal animal) {
        float nextVelocityX = Math.round(Math.random()*MAX_ANIMAL_SPEED);
        float nextVelocityY = (float) Math.sqrt(Math.pow(MAX_ANIMAL_SPEED,2) - Math.pow(nextVelocityX,2));
        animal.setVelocityX(nextVelocityX);
        animal.setVelocityY(nextVelocityY);
    }

    private boolean isMoveInCurrentDirectionPossible(Animal animal, int nextX, int nextY) {
        if (isFieldOutOfMap(nextX, nextY) || isFieldOccupied(animal, nextX, nextY)) {
            return false;
        }
        return !isFieldInaccessible(nextX, nextY);
    }

    private boolean isFieldInaccessible(int nextX, int nextY) {
        for (int x = nextX; x < nextX + context.getANIMAL_SIZE(); x++) {
            for (int y = nextY; y < nextY + context.getANIMAL_SIZE(); y++) {
                if (map[x][y].equals(SurfaceType.WATER) || map[x][y].equals(SurfaceType.INACCESSIBLE_TERRAIN)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isFieldOccupied(Animal currentAnimal, int nextX, int nextY) {
        List<Animal> otherAnimals = getOtherAnimals(currentAnimal);
        for (Animal otherAnimal : otherAnimals) {
            double distance = Math.sqrt(Math.pow(nextX - otherAnimal.getX(), 2) + Math.pow(nextY - otherAnimal.getY(), 2));
            if (distance < context.getANIMAL_SIZE()) {
                return true;
            }
        }
        return false;
    }

    private List<Animal> getOtherAnimals(Animal currentAnimal) {
        return context.getPopulation()
                .stream()
                .filter(animal -> animal.getX() != currentAnimal.getX() && animal.getY() != currentAnimal.getY())
                .toList();
    }

    private boolean isFieldOutOfMap(int nextX, int nextY) {
        return nextX < map[0].length && nextY < map.length;
    }

    private void ensureCorrectVelocitySettings(Animal animal) {
        float velocityX = animal.getVelocityX();
        float velocityY = animal.getVelocityY();
        if (velocityX == 0.0 && velocityY == 0.0) {
            setNewRandomVelocity(animal);
        }
    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getMotionType().equals(MotionType.STROLL);
    }
}
