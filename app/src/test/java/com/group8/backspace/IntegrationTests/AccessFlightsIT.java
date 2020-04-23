package com.group8.backspace.IntegrationTests;


import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.objects.Flight;
import com.group8.backspace.utils.TestUtils;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AccessFlightsIT {
    private AccessFlights accessFlights;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
//        final FlightPersistence persistence = new FlightPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessFlights = Services.getAccessFlight();
        accessFlights.setStatusTime(new DateTime("2019-04-01"));
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @After
    public void tearDown() {
        System.out.println("Done!");
        accessFlights = null;
        System.out.println("clear " + tempDB.delete());
        Services.resetAccess();
    }


    @Test
    public void testGetFlightByID(){
        final Flight flight;
        flight = accessFlights.getFlightByID(37);
        assertNotNull("The flight #37 should not be null", flight);
        assertEquals("get element fail","jupiter",flight.getOrigin());

        System.out.println("End testing GetFlightByID");
    }

    @Test
    public void testGetFlights(){
        List<Flight> test = accessFlights.getFlights();
        assertEquals("incorrect size", 2916,test.size());
        System.out.println("End testing GetFlights");
    }

    @Test
    public void testGetCurrentFlights(){
        List<Flight> test = accessFlights.getCurrentFlights();
        assertEquals("incorrect size", 44,test.size());
        System.out.println("End testing getCurrentFlights");

//        System.out.println(test.get(10).getFlightID());
    }

    @Test
    public void testGetCurrFlightByID(){
        final Flight flight;
        flight = accessFlights.getCurrFlightByID(511);
        assertNotNull("The flight #511 should not be null", flight);
        System.out.println("End testing getCurrentFlightsByID");
    }

    @Test
    public void testGetFutrueFlight(){
        List<Flight> test = accessFlights.getFutureFlights("earth","mars");
        System.out.println(test.size());
        assertEquals("incorrect size", 27,test.size());

        System.out.println("End testing GetFutrueFlight");
    }
}
