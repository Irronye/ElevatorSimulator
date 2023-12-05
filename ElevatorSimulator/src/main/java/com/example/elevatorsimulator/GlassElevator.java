package com.example.elevatorsimulator;

public class GlassElevator extends Elevator{
    private int currentFloor;

    // Constructor for the subclass-------------------------------------------------------------------------------------------------------------
    public GlassElevator(int elevatorID, String elevatorType, int maxCapacities, double elevatorRequestPercentage) {
        super(elevatorID, elevatorType, maxCapacities, elevatorRequestPercentage);
        this.currentFloor = 1;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean move(direction _direction) {
        if(_direction == direction.UP){

        }
        else if(_direction == direction.DOWN)
        {

        }
        else{
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
    //toString method------------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Elevator{" +
                "elevatorID=" + elevatorID +
                ", elevatorType='" + elevatorType + '\'' +
                ", maxCapacities=" + maxCapacities +
                ", elevatorRequestPercentage=" + elevatorRequestPercentage +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
