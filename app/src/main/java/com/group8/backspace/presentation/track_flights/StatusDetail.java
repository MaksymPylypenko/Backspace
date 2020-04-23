package com.group8.backspace.presentation.track_flights;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Flight;
import com.group8.backspace.objects.Location;

public class StatusDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status_detail);

        int flightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);

        AccessFlights accessF = new AccessFlights(Services.getFlightPersistence());
        AccessPlanets accessP = new AccessPlanets(Services.getPlanetPersistence());

        Flight currFlight = accessF.getCurrFlightByID(flightNum);
        Location origin = accessP.getPlanetByName(currFlight.getOrigin());
        Location destination = accessP.getPlanetByName(currFlight.getDestination());

        final String displayName = "Flight#".concat(Integer.toString(currFlight.getFlightID()));
        int originSrc =  getResources().getIdentifier(
                "ic_".concat(origin.getId()), "drawable", getPackageName());
        int destSrc =  getResources().getIdentifier(
                "ic_".concat(destination.getId()), "drawable", getPackageName());

        TextView titleView = (TextView) findViewById(R.id.flightNum);
        ImageView departView = (ImageView) findViewById(R.id.departPic);
        ImageView destView = (ImageView) findViewById(R.id.destPic);

        TextView statusView = (TextView) findViewById(R.id.statusText);
        ImageView stage1View = (ImageView) findViewById(R.id.stage1Pic);
        ImageView stage2View = (ImageView) findViewById(R.id.stage2Pic);
        ImageView stage3View = (ImageView) findViewById(R.id.stage3Pic);

        titleView.setText(displayName);
        departView.setImageResource(originSrc);
        destView.setImageResource(destSrc);

        String status;
        if(currFlight.isDead()) {
            status = "Crew dead";
            statusView.setText(status);
            stage2View.setImageResource(R.drawable.ic_qmark);
        }else{
            if(currFlight.isDelayed()){
                status = "Delayed";
            } else {
                status = "On time";
            }
            statusView.setText(status);

            if(currFlight.getStatus() == 1){
                stage1View.setImageResource(R.drawable.ic_book_todo);
                stage2View.setImageResource(R.drawable.ic_3_dots);
            }else if( currFlight.getStatus() == 3){
                stage3View.setImageResource(R.drawable.ic_book_todo);
                stage2View.setImageResource(R.drawable.ic_3_dots);
            }
        }
    }
}
