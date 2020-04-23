package com.group8.backspace.logic;

import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.objects.Flight;
import com.group8.backspace.persistence.FlightPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.joda.time.DateTime;

public class AccessFlightsTest {

    private AccessFlights accessFlights;
    private FlightPersistence flightPersistence;

    private Flight flight1;
    private Flight flight2;
    private Flight flight3;
    private Flight flight4;

    private List<Flight> storageTest1;
    private List<Flight> storageTest2;

    @Before
    public void setUp() {
        flightPersistence = mock(FlightPersistence.class);
        accessFlights = new AccessFlights(flightPersistence);

        flight1 = new Flight(1, "earth", "venus", new DateTime(1546797600), new DateTime(1546797700));
        flight2 = new Flight(2, "earth", "venus", new DateTime(1546797600), new DateTime(1546797800));
        flight3 = new Flight(3, "neptune", "venus", new DateTime(1546797600), new DateTime(1546797900));
        flight4 = new Flight(4, "neptune", "venus", new DateTime(1546797600), new DateTime(1546797950));

        storageTest1 = new ArrayList<>();
        storageTest2 = new ArrayList<>();

        storageTest1.add(flight1);
        storageTest1.add(flight2);
        storageTest1.add(flight3);
        storageTest1.add(flight4);

        storageTest2.add(flight1);
        storageTest2.add(flight2);
    }

    @Test
    public void testNull() {
        System.out.println("Start testing null");
        assertNotNull(accessFlights);
        assertNotNull(accessFlights.getFlights());
//        assertNotNull(accessFlights.getCurrentFlights());
        System.out.println("End testing null");
    }

    @Test
    public void testGetFlightByID(){
        System.out.println("Start testing GetFlightByID");
        when(flightPersistence.getFlightByID(1)).thenReturn(flight1);
        Flight test1 = accessFlights.getFlightByID(1);
        assertNotNull(test1);
        assertEquals(test1,flight1);

        verify(flightPersistence).getFlightByID(1);
        System.out.println("End testing GetFlightByID");
    }

    @Test
    public void testGetFlights(){
        System.out.println("Start testing GetCurrentFlights");
        when(flightPersistence.getFlights()).thenReturn(storageTest1);
        List<Flight> curr = accessFlights.getFlights();
        assertNotNull(curr);
        assertEquals(curr,storageTest1);
        verify(flightPersistence).getFlights();
        System.out.println("End testing GetFlightByID");
    }

    @Test
    public void testGetCurrFlightByID(){
        List<Flight> temp = accessFlights.getCurrentFlights();
        Flight test =  accessFlights.getCurrFlightByID(3);
        assertNotNull(test);
        assertNotNull(temp);
        assertEquals(3,test.getFlightID());
    }

    @Test
    public void testGetCurrentFlights(){
        System.out.println("Start testing GetCurrentFlights");

        when(flightPersistence.getFlights()).thenReturn(storageTest1);

        accessFlights.setRand(new Random(){
            public boolean nextBoolean(){
                return true;
            }
            public int nextInt(int i){
                return 1;
            }
        });

        accessFlights.setStatusTime(new DateTime(1546797801));

        List<Flight> test = accessFlights.getCurrentFlights();

        //clone storageTest1 and change the status to the specific Flight
        List<Flight> temp = new ArrayList<>();
        temp.add(flight3);
        temp.add(flight4);
        temp.get(0).setStatus(2);
        temp.get(1).setStatus(2);

        assertNotNull(temp);
        assertEquals(temp,test);

        verify(flightPersistence).getFlights();
        System.out.println("End testing GetFlightByID");
    }

    @Test
    public void testGetFutureFlights(){
        when(flightPersistence.getFlights("earth","venus")).thenReturn(storageTest2);
        List<Flight> temp = new ArrayList<>();
        temp.add(flight2);

        accessFlights.setStatusTime(new DateTime(1546797600));
        List<Flight> test = accessFlights.getFutureFlights("earth","venus");
        assertNotNull(test);
        assertTrue(test.size()==0);

        verify(flightPersistence).getFlights("earth","venus");

        System.out.println("End testing GetFutureFlights");
    }



}
