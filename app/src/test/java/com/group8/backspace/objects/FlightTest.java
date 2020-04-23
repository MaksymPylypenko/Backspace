package com.group8.backspace.objects;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.joda.time.DateTime;

public class FlightTest {

    @Test
    public void testFlight1() {
        Flight flight;
        DateTime departure = new DateTime(2019,11,12,12,12);
        DateTime arrival = new DateTime(2019,12,15,11,20);

        System.out.println("\nStarting testFlight");

        flight = new Flight(12345, "earth","venus",departure,arrival);
        flight.setStatus(1);

        assertNotNull(flight);
        assertTrue(flight.getFlightID()==12345);
        assertTrue("earth".equals(flight.getOrigin()));
        assertTrue("venus".equals(flight.getDestination()));
        assertTrue(flight.getStatus()==1);
        assertFalse(flight.isDead());
        assertFalse(flight.isDelayed());

        System.out.println("Finished testFlight");
    }
}
