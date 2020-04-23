package com.group8.backspace.logic;

import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Location;
import com.group8.backspace.persistence.PlanetPersistence;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.*;

public class AccessPlanetsTest {
    private PlanetPersistence planetPersistence;
    private AccessPlanets accessPlanets;

    Location location1;
    Location location2;
    ArrayList<Location> storageTest;
    @Before
    public void setUp() {
        planetPersistence = mock(PlanetPersistence.class);
        accessPlanets = new AccessPlanets(planetPersistence);

        storageTest = new ArrayList<>();

        location1 = new Location("earth");
        location2 = new Location("neptune");

        storageTest.add(location1);
        storageTest.add(location2);
    }

    @Test
    public void testNull() {
        System.out.println("\nStarting test null");
        assertNotNull(accessPlanets);
        assertNotNull(accessPlanets.getPlanets());
        System.out.println("Finished test null");
    }

    @Test
    public void testGetPlanetByName(){
        System.out.println("Start testing GetItem");
        when(planetPersistence.getPlanetByName("earth")).thenReturn(location1);
        Location test1 = accessPlanets.getPlanetByName("earth");
        assertNotNull(test1);
        assertEquals(test1,location1);
        verify(planetPersistence).getPlanetByName("earth");
        System.out.println("End testing GetItem");
    }

}
