package com.group8.backspace.presentation.book_flight.browse_flights;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.application.Services;
import com.group8.backspace.logic.AnalyseDates;
import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Flight;
import com.group8.backspace.presentation.book_flight.travel_class.SelectTravelClass;
import com.group8.backspace.presentation.util.DateParser;


public class FlightDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight_detail);

        //get the flight object selected via the flightNum passed by BookBrowseActivity
        AccessFlights flightAccessor =  new AccessFlights(Services.getFlightPersistence());
        int currFlightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);

        Flight currFlight = flightAccessor.getFlightByID(currFlightNum);
        //Get the objects we want to set
        TextView title = (TextView) findViewById(R.id.detail_title);
        TextView departPlanetName = (TextView) findViewById(R.id.detail_depart_planet);
        TextView destPlanetName = (TextView) findViewById(R.id.detail_destination_planet);
        TextView departTime = (TextView) findViewById(R.id.detail_depart_text);
        TextView totalTime = (TextView) findViewById(R.id.detail_total_time);
        TextView arrivalTime = (TextView) findViewById(R.id.detail_destination_text);
        ImageView departPlanetPic = (ImageView) findViewById(R.id.detail_depart_pic);
        ImageView destPlanetPic = (ImageView) findViewById(R.id.detail_destination_pic);

        //set each text and picture slots to the given values
        String flightTitle = "Flight #"+currFlightNum;
        title.setText(flightTitle);
        String depName = currFlight.getOrigin();
        depName = depName.substring(0,1).toUpperCase() + depName.substring(1);
        departPlanetName.setText(depName);
        String destName = currFlight.getDestination();
        destName = destName.substring(0,1).toUpperCase() + destName.substring(1);
        destPlanetName.setText(destName);

        //use the date handler to get nice strings for textviews
        DateParser date = new DateParser(currFlight.getDeparture());
        departTime.setText(date.toString());

        date.setDate(currFlight.getArrival());
        arrivalTime.setText(date.toString());

        int duration = AnalyseDates.getTravelTimeDays(
                currFlight.getDeparture(), currFlight.getArrival());

        totalTime.setText(String.valueOf(duration).concat(" days"));

        //get the image sources from the flight object
        AccessPlanets pAccess = new AccessPlanets(Services.getPlanetPersistence());
//        String originSrc = pAccess.getPlanetByName(currFlight.getOrigin()).getImgSrc();
//        String destinationSrc = pAccess.getPlanetByName(currFlight.getDestination()).getImgSrc();
        String originSrc = "ic_".concat(pAccess.getPlanetByName(currFlight.getOrigin()).getId());
        String destinationSrc = "ic_".concat(pAccess.getPlanetByName(currFlight.getDestination()).getId());
        departPlanetPic.setImageResource(
                getResources().getIdentifier(originSrc , "drawable", getPackageName()));
        destPlanetPic.setImageResource(
                getResources().getIdentifier(destinationSrc , "drawable", getPackageName()));



        Button btn_travel = (Button) findViewById(R.id.btn_travel);
        btn_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);
                Intent detailIntent = new Intent(
                        FlightDetailActivity.this, SelectTravelClass.class);
                detailIntent.putExtra("FLIGHT_NUM", flightNum);
                startActivity(detailIntent);
            }
        });
    }
}
