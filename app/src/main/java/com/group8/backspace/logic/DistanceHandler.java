package com.group8.backspace.logic;
import com.group8.backspace.objects.Location;

public class DistanceHandler {

    private Location origin;
    private Location destination;

    public DistanceHandler(Location origin, Location destination ){
        this.origin = origin;
        this.destination = destination;
    }

    public double getDistance(){
        double sunToOrigin = Double.parseDouble(origin.getDistance());
        double sunToDestination = Double.parseDouble(destination.getDistance());
        double distance =  Math.abs(sunToOrigin - sunToDestination );
        return distance;
    }
}