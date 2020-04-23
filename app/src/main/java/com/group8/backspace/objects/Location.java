package com.group8.backspace.objects;

public class Location {

    private String id;
    private String years;
    private String minTemp;
    private String maxTemp;
    private String population;
    private String distance;
    private String description;
    private String shortDesc;

    public Location(String id) {
        this.id = id;
    }

    //Accessors
    public String getId(){
        return this.id;
    }

    public String getYears() { return years; }

    public String getMin() { return minTemp; }

    public String getMax() { return maxTemp; }

    public String getPopulation() { return population; }

    public String getDistance(){ return distance; }

    public String getDescription() { return description; }

    public String getShortDesc() { return this.shortDesc; }

    //Mutators
    public void setYears(String newYears) { this.years = newYears; }

    public void setMinTemp(String newMin) { this.minTemp = newMin; }

    public void setMaxTemp(String newMax) {this.maxTemp = newMax; }

    public void setPopulation(String newPopulation) { this.population = newPopulation; }

    public void setDistance(String newDistance) { this.distance = newDistance; }

    public void setDescription(String newDescription) { this.description = newDescription; }

    public void setShortDesc(String newShortDesc) { this.shortDesc = newShortDesc; }
}
