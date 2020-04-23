package com.group8.backspace.persistence;

import com.group8.backspace.objects.Location;

import java.util.List;

public interface PlanetPersistence {
    List<Location> getPlanets();

    Location getPlanetByName(String locationName);
}
