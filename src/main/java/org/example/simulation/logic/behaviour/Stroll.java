package org.example.simulation.logic.behaviour;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.BehaviourType;
import org.example.simulation.logic.utils.MapFieldUtils;

public class Stroll implements Behaviour {
    private final Context context;
    private final MapFieldUtils mapFieldUtils;

    public Stroll(Context context, MapFieldUtils mapFieldUtils) {
        this.context = context;
        this.mapFieldUtils = mapFieldUtils;
    }

    @Override
    public void behave(Animal animal) {
        ensureCorrectVelocitySettings(animal);
        performNextStep(animal);
    }

    private void performNextStep(Animal animal) {
        int MAX_TRIALS_OF_DIRECTION_CHANGE = context.getMAX_TRIALS_OF_DIRECTION_CHANGE_FOR_SINGLE_MOVE();
        int numberOfTrials = 0;
        int nextX, nextY;
        do {
            nextX = Math.round((float) animal.getX() + animal.getVelocityX());
            nextY = Math.round((float) animal.getY() + animal.getVelocityY());
            if (isMoveInCurrentDirectionPossible(animal, nextX, nextY)) {
                animal.setX(nextX);
                animal.setY(nextY);
                break;
            } else {
                setNewRandomVelocity(animal);
            }
            numberOfTrials++;
        } while (numberOfTrials < MAX_TRIALS_OF_DIRECTION_CHANGE);
    }

    private void setNewRandomVelocity(Animal animal) {
        float maxAnimalSpeed = animal.getMax_speed();
        float speed = (float) Math.random() * (maxAnimalSpeed - context.getMIN_ANIMAL_SPEED()) + context.getMIN_ANIMAL_SPEED();
        float nextVelocityX = (float) (Math.random() * speed * 2) - speed;
        int randomSign = Math.random() < 0.5 ? 1 : -1;
        float nextVelocityY = (float) Math.sqrt(Math.pow(speed, 2) - Math.pow(nextVelocityX, 2)) * randomSign;
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
        return animal.getBehaviourType().equals(BehaviourType.STROLL);
    }
}
