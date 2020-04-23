package com.group8.backspace.logic;

public class CalculatePrices {

    private final int FUEL_UNIT_PRICE = 3000;
    private int classDailyPrice;
    private int itemsDailyPrice;

    private int duration;
    private double totalDistance;
    private int prepaidDays;

    public CalculatePrices(double totalDistance, int duration,
                           int classDailyPrice, int itemsDailyPrice){
        this.totalDistance = totalDistance;
        this.duration = duration;
        this.prepaidDays = this.duration;
        this.classDailyPrice = classDailyPrice;
        this.itemsDailyPrice = itemsDailyPrice;
    }

    public void setPrepaidDays(int percentage){
        if (percentage > 100) {
            this.prepaidDays = duration;
        }
        if (percentage <= 0) {
            this.prepaidDays = 0;
        }
        else{
            double multiplier = (double) percentage / 100;
            this.prepaidDays = (int)(this.duration*multiplier);
        }
    }

    public int getPrepaidDays(){ return prepaidDays; }

    // Calculate price dynamically
    public int calculateFuelPrice(){
        return (int)(totalDistance*FUEL_UNIT_PRICE);
    }

    public int calculateClassPrice(){
        return classDailyPrice*duration;
    }

    public int calculatePrepaidPrice(){
        return itemsDailyPrice*prepaidDays;
    }

    public int calculateTotalPrice(){
        return calculateFuelPrice() + calculateClassPrice() + calculatePrepaidPrice();
    }


}

