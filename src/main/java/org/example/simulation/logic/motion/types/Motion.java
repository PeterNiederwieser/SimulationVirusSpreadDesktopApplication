package org.example.simulation.logic.motion.types;

import org.example.simulation.data.Animal;
import org.example.simulation.data.Context;
import org.example.simulation.data.MotionType;

public interface Motion {
    void move(Context context);
    boolean matches(Animal animal);
}
