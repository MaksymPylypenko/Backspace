package com.group8.backspace.stubs;

import com.group8.backspace.objects.Flight;
import com.group8.backspace.persistence.FlightPersistence;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightPersistenceStub implements FlightPersistence {

    List<Flight> flights;

    public FlightPersistenceStub() {
        flights = new ArrayList<Flight>();

        Flight flight1 = new Flight(1, "earth", "venus", new DateTime(2019,3,25,0,0), new DateTime(2019,5,20,0,0));
        flights.add(flight1);
    }


    @Override
    public List<Flight> getFlights() {
        return Collections.unmodifiableList(flights);
    }

    @Override
    public List<Flight> getFlights(String origin, String destination) {
        ArrayList<Flight> flights_r = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                flights_r.add(flight);
            }
        }
        return flights_r;
    }

    @Override
    public Flight getFlightByID(int flightSearchNum ) {
        Flight foundFlight = null;
        for(Flight flight : flights) {
            if(flight.getFlightID() == flightSearchNum ) {
                foundFlight = flight;
            }
        }
        return foundFlight;

    }

}