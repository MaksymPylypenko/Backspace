package com.group8.backspace.presentation.book_flight.browse_flights;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.application.Services;
import com.group8.backspace.logic.CheckPlanetList;
import com.group8.backspace.logic.accessors.AccessPlanets;

public class SelectOrigin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_select_planet);

        TextView title = findViewById(R.id.textView1);
        title.setText(getString(R.string.title_activity_select_origin));

        findViewById(R.id.btn_mercury).setOnClickListener(this);
        findViewById(R.id.btn_venus).setOnClickListener(this);
        findViewById(R.id.btn_earth).setOnClickListener(this);
        findViewById(R.id.btn_mars).setOnClickListener(this);
        findViewById(R.id.btn_jupiter).setOnClickListener(this);
        findViewById(R.id.btn_saturn).setOnClickListener(this);
        findViewById(R.id.btn_uranus).setOnClickListener(this);
        findViewById(R.id.btn_neptune).setOnClickListener(this);
        findViewById(R.id.btn_pluto).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        CheckPlanetList checkOrigin = new CheckPlanetList(Services.getPlanetPersistence()); //check planet object
        String destination = ""; //hold destination
        try {
            destination = getIntent().getStringExtra("destination"); //tries to get destination
        }
        catch (NullPointerException e) {
        }
        checkOrigin.setDestination(destination); //sets destination

        if(checkOrigin.hasOrigin()) { //if sent here by PlanetList and already has an destination
            Intent intent = new Intent(SelectOrigin.this, BrowseFlightsCalendar.class);
            intent.putExtra("origin", (String) v.getTag());
            intent.putExtra("destination", getIntent().getStringExtra("destination"));
            startActivity(intent);
        }
        else { //if sent here by main activity, and needs a destination
            Intent intent = new Intent(SelectOrigin.this, SelectDestination.class);
            intent.putExtra("origin", (String) v.getTag());
            startActivity(intent);
        }
    }

}
