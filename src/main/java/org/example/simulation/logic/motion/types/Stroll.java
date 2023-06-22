package org.example.simulation.logic.motion.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.MotionType;

public class Stroll implements Motion{
    private final Context context;
    private final int MAX_ANIMAL_SPEED;

    public Stroll(Context context) {
        this.context = context;
        MAX_ANIMAL_SPEED = context.getMAX_ANIMAL_SPEED();
    }

    @Override
    public void move(Animal animal) {
        ensureCorrectVelocitySettings(animal);
        performNextStep(animal);
    }

    private void performNextStep(Animal animal) {
        int MAX_TRIALS_OF_DIRECTION_CHANGE = context.getMAX_TRIALS_OF_DIRECTION_CHANGE();
        int numberOfTrials = 0;
        while (numberOfTrials < MAX_TRIALS_OF_DIRECTION_CHANGE) {
            if(isMoveInCurrentDirectionPossible()) {
                setAnimalToNewPosition();
                break;
            } else {
                setNewRandomVelocity();
            }
            numberOfTrials++;
        }
    }

    private void ensureCorrectVelocitySettings(Animal animal) {
        float velocityX = animal.getVelocityX();
        float velocityY = animal.getVelocityY();
        if(velocityX == 0.0 && velocityY == 0.0) {
            setNewAnimalVelocity();
        }
    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getMotionType().equals(MotionType.STROLL);
    }
}
