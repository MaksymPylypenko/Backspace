package com.group8.backspace.presentation.track_flights;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Flight;
import com.group8.backspace.presentation.util.DateParser;
import com.group8.backspace.presentation.util.list_adapters.FlightListAdapter;

import java.util.List;

/*
    https://www.andrious.com/tutorials/listview-tutorial-with-example-in-android-studio/
    this tutorial helped shape the FlightStatus.java, Listview.java CustomAdapter,java activity_listview.xml,
    and activity_list.xml

    The code copied was the the new customer adapter that overwrites viewlist to show a picture, title, and information

    4 hours was spent on this view
 */

public class FlightStatusList extends AppCompatActivity {
    ListView simpleList;
    String flightName[];

    AccessFlights access = new AccessFlights(Services.getFlightPersistence());
    AccessPlanets pAccess = new AccessPlanets(Services.getPlanetPersistence());
    List<Flight> ongoingFlights = access.getCurrentFlights();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        TextView title = findViewById(R.id.title);
        title.setText(getString(R.string.title_activity_flight_status));

        int flightNum[] = new int[ongoingFlights.size()];
        String flightStats[] = new String[ongoingFlights.size()];
        int flightIcon[] = new int[ongoingFlights.size()];
        Flight currFlight;
        String planetName;
        for(int i = 0; i < ongoingFlights.size(); i++){
            currFlight = ongoingFlights.get(i);
            flightNum[i] = currFlight.getFlightID();

            planetName = pAccess.getPlanetByName(currFlight.getDestination()).getId();
            flightIcon[i] = getResources().getIdentifier(
                    ("ic_".concat(planetName)),"drawable", getPackageName());
//            flightIcon[i] = getResources().getIdentifier(pAccess.getPlanetByName(currFlight.getDestination()).getImgSrc(), "drawable", getPackageName());

            //build flight stats string
            String stats = "Status: ";
            if(currFlight.isDead()){
                stats = stats + "Crew Dead\nFlight Stage: Failure\nETA: Unknown";
            } else {
                if(currFlight.isDelayed()){
                    stats += "Delayed\nFlight Stage: ";
                } else {
                    stats += "On Time\nFlight Stage: ";
                }

                if(currFlight.getStatus() == 1) {
                    stats += "Leaving Orbit\nETA: ";
                } else if( currFlight.getStatus() == 2) {
                    stats += "In Transfer\nETA: ";
                } else if( currFlight.getStatus() == 3){
                    stats += "Deorbiting\nETA: ";
                }

                DateParser date = new DateParser(currFlight.getDeparture());
                date.setDate(currFlight.getArrival());

                stats += date.toString();
            }
            flightStats[i] = stats;
        }
        simpleList = (ListView)findViewById(R.id.ListView);
        FlightListAdapter flightAdapter = new FlightListAdapter(
                FlightStatusList.this, StatusDetail.class, flightNum, flightStats, flightIcon);
        simpleList.setAdapter(flightAdapter);
    }

    public String[] getItem()
    {
        return flightName;
    }
}
