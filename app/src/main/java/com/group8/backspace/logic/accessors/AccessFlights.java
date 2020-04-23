package com.group8.backspace.logic.accessors;

import com.group8.backspace.objects.Flight;
import com.group8.backspace.persistence.FlightPersistence;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AccessFlights {

    private FlightPersistence flightPersistence;
    private static ArrayList<Flight> currFlights = null;
    private Random rand;
    private DateTime statusTime;


    public AccessFlights(final FlightPersistence flightPersistence) {
        this.flightPersistence = flightPersistence;
        this.rand = new Random();
        this.statusTime = helper();
    }

    public List<Flight> getFlights() {
        return Collections.unmodifiableList(flightPersistence.getFlights());
    }

    public List<Flight> getCurrentFlights() {
        if(currFlights == null) {
            List<Flight> flights = flightPersistence.getFlights();
            currFlights = new ArrayList<>();

            for (Flight flight : flights) {
                if ((flight.getDeparture().compareTo(statusTime) < 0) &&
                        (flight.getArrival().compareTo(statusTime) > 0)) {
                    if (flight.getStatus() == 0) {
                        int isDead = rand.nextInt(17);
                        boolean onTime = rand.nextBoolean();
                        if (isDead == 0) {
                            flight.kill();
                        } else {
                            if (!onTime) {
                                flight.delay();
                            }
                            DateTime depart = flight.getDeparture();
                            DateTime eta = flight.getArrival();

                            long quarterTime = (eta.getMillis() - depart.getMillis()) / 4;
                            if ((quarterTime + depart.getMillis()) > statusTime.getMillis()) {
                                flight.setStatus(1); //leaving
                            } else if ((eta.getMillis() - quarterTime) < statusTime.getMillis()) {
                                flight.setStatus(3); //arriving
                            } else {
                                flight.setStatus(2); //middle of trip
                            }
                        }
                    }
                    currFlights.add(flight);
                }
            }
        }
        return Collections.unmodifiableList(currFlights);
    }

    public List<Flight> getFutureFlights(String origin, String destination){
        ArrayList<Flight> futureFlights = new ArrayList<>();
        List<Flight> flights = flightPersistence.getFlights(origin, destination);
        DateTime now = statusTime;

        for (Flight flight : flights) {
            if (flight.getDeparture().compareTo(now) > 0) {
                futureFlights.add(flight);
            }
        }

        return Collections.unmodifiableList(futureFlights);
    }

    public Flight getFlightByID(int searchFlightNum) {
        return flightPersistence.getFlightByID(searchFlightNum);
    }

    public Flight getCurrFlightByID(int searchFlightNum) {
        for(Flight currFlight : currFlights) {
            if(currFlight.getFlightID() == searchFlightNum) {
                return currFlight;
            }
        }
        return null;
    }

    public DateTime helper(){
        return DateTime.now();
    }

    public void setRand(Random random){
        this.rand = random;
    }

    public void setStatusTime(DateTime time) {
        this.statusTime = time;
    }

}
