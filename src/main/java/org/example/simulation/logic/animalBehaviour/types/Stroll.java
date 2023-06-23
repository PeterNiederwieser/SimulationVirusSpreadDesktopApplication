package org.example.simulation.logic.animalBehaviour.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.MotionType;
import org.example.simulation.logic.utils.MapFieldUtils;

public class Stroll implements Behaviour {
    private final Context context;
    private final MapFieldUtils mapFieldUtils;
    private final int MAX_ANIMAL_SPEED;

    public Stroll(Context context, MapFieldUtils mapFieldUtils) {
        this.context = context;
        this.MAX_ANIMAL_SPEED = context.getMAX_ANIMAL_SPEED();
        this.mapFieldUtils = mapFieldUtils;
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
                System.out.println("isMoveInCurrentDirectionPossible(animal, nextX, nextY) = " + isMoveInCurrentDirectionPossible(animal, nextX, nextY));
                animal.setX(nextX);
                animal.setY(nextY);
                System.out.println("animal moved");
                break;
            } else {
                setNewRandomVelocity(animal);
            }
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
        if (mapFieldUtils.isFieldOutOfMap(nextX, nextY) || mapFieldUtils.isFieldOccupied(animal, nextX, nextY)) {
            return false;
        }
        return !mapFieldUtils.isAreaInaccessible(nextX, nextY);
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
