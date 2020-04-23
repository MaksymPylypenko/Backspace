package com.group8.backspace.logic;

import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Location;
import com.group8.backspace.persistence.PlanetPersistence;

import java.util.List;

public class CheckPlanetList {

    String destination;
    PlanetPersistence access;
    public CheckPlanetList(PlanetPersistence planetPersistencelanets){
        destination = "";
        access = planetPersistencelanets;
    }

    public void setDestination(String origin) {
        this.destination = origin;
    }

    public String getDestination() {
        return destination;
    }

    public boolean hasOrigin() { //null value not possible by this method call
        boolean toReturn = false;
        List<Location> planetList = access.getPlanets();

        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).getId().equals(destination)){
                toReturn = true;
            }
        }

        return toReturn;
    }
}