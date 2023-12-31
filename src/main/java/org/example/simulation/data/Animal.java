package org.example.simulation.data;

public class Animal {
    private int x;
    private int y;
    private float max_speed;
    private float velocityX;
    private float velocityY;
    private HealthState healthState;
    private BehaviourType behaviourType;
    private int momentOfInfection;
    private int startOfSevereIllness;
    private int timeOfPossibleDeathAfterInfection;
    private boolean isGettingSeverelyIll;

    public Animal(int x, int y, float max_speed, HealthState healthState, BehaviourType behaviourType, int timeOfPossibleDeathAfterInfection, boolean isDyingInCaseOfInfection) {
        this.x = x;
        this.y = y;
        this.max_speed = max_speed;
        this.healthState = healthState;
        this.behaviourType = behaviourType;
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

    public int getMomentOfInfection() {
        return momentOfInfection;
    }

    public void setMomentOfInfection(int momentOfInfection) {
        this.momentOfInfection = momentOfInfection;
    }

    public int getTimeOfPossibleDeathAfterInfection() {
        return timeOfPossibleDeathAfterInfection;
    }

    public void setTimeOfPossibleDeathAfterInfection(int timeOfPossibleDeathAfterInfection) {
        this.timeOfPossibleDeathAfterInfection = timeOfPossibleDeathAfterInfection;
    }

    public boolean isGettingSeverelyIll() {
        return isGettingSeverelyIll;
    }

    public void setGettingSeverelyIll(boolean gettingSeverelyIll) {
        isGettingSeverelyIll = gettingSeverelyIll;
    }

    public int getStartOfSevereIllness() {
        return startOfSevereIllness;
    }

    public void setStartOfSevereIllness(int startOfSevereIllness) {
        this.startOfSevereIllness = startOfSevereIllness;
    }

    public float getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(float max_speed) {
        this.max_speed = max_speed;
    }

    public BehaviourType getBehaviourType() {
        return behaviourType;
    }

    public void setBehaviourType(BehaviourType behaviourType) {
        this.behaviourType = behaviourType;
    }
}
