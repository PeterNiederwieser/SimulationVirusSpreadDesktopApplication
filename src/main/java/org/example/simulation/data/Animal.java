package org.example.simulation.data;

public class Animal {
    private int x;
    private int y;
    private float velocityX;
    private float velocityY;
    private HealthState healthState;
    private MotionType motionType;
    private int momentOfInfection;
    private int startOfSevereIllness;
    private int timeOfPossibleDeathAfterInfection;
    private boolean isGettingSeverelyIll;

    public Animal(int x, int y, HealthState healthState, MotionType motionType, int timeOfPossibleDeathAfterInfection, boolean isDyingInCaseOfInfection) {
        this.x = x;
        this.y = y;
        this.healthState = healthState;
        this.motionType = motionType;
        this.timeOfPossibleDeathAfterInfection = timeOfPossibleDeathAfterInfection;
        this.isGettingSeverelyIll = isDyingInCaseOfInfection;
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

    public int getMomentOfInfection() {
        return momentOfInfection;
    }

    public void setMomentOfInfection(int momentOfInfection) {
        this.momentOfInfection = momentOfInfection;
    }

    public int getTimeOfPossibleDeathAfterInfection() {
        return timeOfPossibleDeathAfterInfection;
    }

    public boolean isGettingSeverelyIll() {
        return isGettingSeverelyIll;
    }

    public int getStartOfSevereIllness() {
        return startOfSevereIllness;
    }

    public void setStartOfSevereIllness(int startOfSevereIllness) {
        this.startOfSevereIllness = startOfSevereIllness;
    }
}
