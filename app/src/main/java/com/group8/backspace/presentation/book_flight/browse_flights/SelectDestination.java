package com.group8.backspace.presentation.book_flight.browse_flights;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.group8.backspace.R;

public class SelectDestination extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_select_planet);

        TextView title = findViewById(R.id.textView1);
        title.setText(getString(R.string.title_activity_select_destination));

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
        Intent intent = new Intent(getBaseContext(), BrowseFlightsList.class);
        intent.putExtra("origin", getIntent().getStringExtra("origin"));
        intent.putExtra("destination", (String) v.getTag());
        startActivity(intent);
    }
}
