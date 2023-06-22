package org.example.simulation.logic.motion.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.MotionType;

public class Stroll implements Motion{

    @Override
    public void move(Context context) {

    }

    @Override
    public boolean matches(Animal animal) {
        return animal.getMotionType().equals(MotionType.STROLL);
    }

}
