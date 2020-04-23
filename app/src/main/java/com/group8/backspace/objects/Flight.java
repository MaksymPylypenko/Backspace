package com.group8.backspace.objects;

import org.joda.time.DateTime;


public class Flight {

    private int flightID;

    private String origin;
    private String destination;

    private DateTime departure;
    private DateTime arrival;

    private int status;
    private boolean delayed;
    private boolean dead;

    public Flight(int flightID, String origin, String destination,
                  DateTime departure, DateTime arrival) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;

        this.departure = departure;
        this.arrival = arrival;

        this.status = 0;
        this.delayed = false;
        this.dead = false;
    }

    public int getFlightID() { return flightID; }

    public String getOrigin() { return origin; }

    public String getDestination() { return destination; }

    public DateTime getDeparture() { return departure; }

    public DateTime getArrival() { return arrival; }

    public int getStatus(){ return status; }

    public void setStatus( int newStat ){ this.status = newStat; }

    public boolean isDelayed() { return delayed; }

    public void delay() { this.delayed = true; }

    public boolean isDead() { return dead; }

    public void kill() { this.dead = true; }
}
