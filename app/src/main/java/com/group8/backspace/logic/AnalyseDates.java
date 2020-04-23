package com.group8.backspace.logic;
import com.group8.backspace.objects.Flight;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;

public class AnalyseDates {

    final List<Flight> flights;

    public AnalyseDates(List<Flight> flights){
        this.flights = flights;
    }

    public ArrayList<DateTime> getUniqueDepartures()
    {
        ArrayList<DateTime> dates = new ArrayList<DateTime>();

        for (Flight flight : flights) {
            DateTime date = flight.getDeparture();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        return dates;
    }

    public ArrayList<Flight> getFlightsForDeparture(DateTime date)
    {
        ArrayList<Flight> similarFlights = new ArrayList<Flight>();
        DateTime curr;
        for (Flight flight : flights) {
            curr = flight.getDeparture();
            if (date.getYear() == curr.getYear() &&
                    date.getMonthOfYear() == curr.getMonthOfYear() &&
                    date.getDayOfMonth() == curr.getDayOfMonth()){
                similarFlights.add(flight);
            }
        }
        return similarFlights;
    }

    public static int getTravelTimeDays(DateTime departure, DateTime arrival) {
        int duration = Days.daysBetween(departure.toLocalDate(), arrival.toLocalDate()).getDays();
        return duration;
    }


}