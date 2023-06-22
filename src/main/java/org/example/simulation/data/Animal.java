package org.example.simulation.data;

import org.example.simulation.logic.motion.types.Motion;

public class Animal {
    private int x;
    private int y;
    private float xVelocity;
    private float yVelocity;
    private HealthState healthState;
    private MotionType motionType;

    public Animal(int x, int y, HealthState healthState, MotionType motionType) {
        this.x = x;
        this.y = y;
        this.healthState = healthState;
        this.motionType = motionType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public HealthState getHealthState() {
        return healthState;
    }

    public void setHealthState(HealthState healthState) {
        this.healthState = healthState;
    }

    public MotionType getMotionType() {
        return motionType;
    }
}
