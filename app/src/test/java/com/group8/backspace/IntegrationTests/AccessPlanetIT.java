package com.group8.backspace.IntegrationTests;

import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.utils.TestUtils;

import com.group8.backspace.objects.Location;

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

public class AccessPlanetIT {
    private AccessPlanets accessPlanets;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.accessPlanets = Services.getAccessPlanet();
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
        accessPlanets = null;
        System.out.println("clear " + tempDB.delete());
        Services.resetAccess();
    }

    @Test
    public void testGetPlanets(){
        List<Location> test = accessPlanets.getPlanets();
//        System.out.println(test.size());
        assertEquals("incorrect size", 9,test.size());
        System.out.println("End testing GetPlanets");
    }

    @Test
    public void testGetPlanetByName(){
        Location test = accessPlanets.getPlanetByName("mars");
        assertNotNull(test);
        assertEquals("mars",test.getId());
        System.out.println("End testing GetPlanets");
    }
}
