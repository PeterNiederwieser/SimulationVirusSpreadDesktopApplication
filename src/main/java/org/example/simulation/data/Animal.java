package org.example.simulation.data;

public class Animal {
    private int x;
    private int y;
    private float velocityX;
    private float velocityY;
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

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
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

    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
    }
}
