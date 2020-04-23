package com.group8.backspace.stubs;

import com.group8.backspace.objects.Location;
import com.group8.backspace.persistence.PlanetPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlanetPersistenceStub implements PlanetPersistence {
    List<Location> locations;

    public PlanetPersistenceStub(){
        locations = new ArrayList<Location>();

        Location newLocation1 = new Location("earth");
        Location newLocation2 = new Location("venus");
        locations.add(newLocation1);
        locations.add(newLocation2);
    }

    @Override
    public List<Location> getPlanets() {
        return Collections.unmodifiableList(locations);
    }

    @Override
    public Location getPlanetByName(String locationName) {
        Location plantFound = null;
        for(Location location : locations) {
            if(location.getId().equals(locationName)) {
                plantFound = location;
            }
        }
        return plantFound;
    }
}
