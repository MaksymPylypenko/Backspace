package com.group8.backspace.logic;
import com.group8.backspace.objects.Flight;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class AnalyseDatesTest {

    ArrayList<Flight> flights = new ArrayList<Flight>();

    @Before
    public void setUp() {
        flights.add(new Flight(1, "earth", "venus",
                new DateTime(1546797600), new DateTime(1546797700)));

        flights.add(new Flight(2, "earth", "venus",
                new DateTime(1546797600), new DateTime(1546797800)));

        flights.add(new Flight(3, "neptune", "venus",
                new DateTime(1546797600), new DateTime(1546797900)));

        flights.add(new Flight(4, "neptune", "venus",
                new DateTime(1546797600), new DateTime(1546797950)));
    }

    @Test
    public void testUniqueDepartures(){
        System.out.println("\nStart unique departures test");
        AnalyseDates analyseDates = new AnalyseDates(flights);
        ArrayList<DateTime> uniqueDates = analyseDates.getUniqueDepartures();

        assertEquals( 1, uniqueDates.size() );
        System.out.println("End unique departures test");
    }

    @Test
    public void testFlightsForDeparture() {
        System.out.println("\nStart flights for the same departure test");
        AnalyseDates analyseDates = new AnalyseDates(flights);
        ArrayList<Flight> similarFlights = analyseDates.getFlightsForDeparture(
                new DateTime(1546797600));

        assertEquals( 4, similarFlights.size() );
        System.out.println("End flights for the same departure test");
    }


    @Test
    public void testDuration(){
        System.out.println("\n Start duration test");

        int durationTest = AnalyseDates.getTravelTimeDays(
                new DateTime("2019-04-10"),new DateTime( "2019-04-11"));

        assertEquals(1,durationTest);
        System.out.println("End testing duration");
    }
}
