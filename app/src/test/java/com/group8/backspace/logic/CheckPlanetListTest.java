package com.group8.backspace.logic;

import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Location;
import com.group8.backspace.persistence.PlanetPersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckPlanetListTest {
    private PlanetPersistence planetPersistence;
    private CheckPlanetList checkPlanetList;

    List<Location>  storageTest;
    Location location1;
    Location location2;

    @Before
    public void setUp() {
        planetPersistence = mock(PlanetPersistence.class);
        checkPlanetList = new CheckPlanetList(planetPersistence);

        storageTest = new ArrayList<>();

        location1 = new Location("earth");
        location2 = new Location("neptune");

        storageTest.add(location1);
        storageTest.add(location2);
    }

    @Test
    public void testNull() {
        System.out.println("\nStarting test null");
        assertNotNull(checkPlanetList);
        assertNotNull(checkPlanetList.getDestination());
        System.out.println("Finished test null");
    }

    @Test
    public void testHasOrigin(){
        System.out.println("Start testing HasOrigin");
        when(planetPersistence.getPlanets()).thenReturn(storageTest);
        checkPlanetList.setDestination("neptune");
//        System.out.println(checkPlanetList.hasOrigin());
        assertTrue(checkPlanetList.hasOrigin());
        verify(planetPersistence).getPlanets();
        System.out.println("End testing HasOrigin");
    }
}
