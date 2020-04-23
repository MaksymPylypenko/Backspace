package com.group8.backspace.presentation.book_flight.travel_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.group8.backspace.R;

import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessItems;
import com.group8.backspace.objects.Item;
import com.group8.backspace.presentation.book_flight.ReviewBooking;

public class SelectTravelClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_select_travel_class);

        AccessItems access = new AccessItems(Services.getItemPersistence());

        final Item economy = access.getItemByName("hyper sleep");
        final Item business = access.getItemByName("activities");
        TextView textEconomyPrice =  findViewById(R.id.textEconomyPrice);
        TextView textBusinessPrice =  findViewById(R.id.textBusinessPrice);
        textEconomyPrice.setText(economy.getPrice()+" $ per day ");
        textBusinessPrice.setText("min "+business.getPrice()+" $ per day ");

        ImageButton btn_TravelClassEconomy = findViewById(R.id.imageEconomy);
        ImageButton btn_TravelClassBusiness = findViewById(R.id.imageBusiness);

        TextView tip_Nutrient = findViewById(R.id.textNutrientTip);
        TextView tip_VR = findViewById(R.id.textVRTip);
        TextView tip_Suit = findViewById(R.id.textSuitTip);
        TextView tip_Meal = findViewById(R.id.textMealTip);
        TextView tip_Energy = findViewById(R.id.textEnergyTip);

        btn_TravelClassEconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);
                int classPrice = economy.getPrice();
                Intent detailIntent = new Intent(
                        SelectTravelClass.this, ReviewBooking.class);
                detailIntent.putExtra("Class_Name", "hyper_sleep");
                detailIntent.putExtra("FLIGHT_NUM", flightNum);
                detailIntent.putExtra("Class_Price", classPrice);
                detailIntent.putExtra("Items_Price", 0);
                startActivity(detailIntent);
            }
        });

        btn_TravelClassBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);
                //need a method to set class price
                int classPrice = business.getPrice();
                Intent detailIntent = new Intent(
                        SelectTravelClass.this, SelectDailyExpenses.class);
                detailIntent.putExtra("FLIGHT_NUM", flightNum);
                detailIntent.putExtra("Class_Name", "activities");
                detailIntent.putExtra("Class_Price", classPrice);
                startActivity(detailIntent);
            }
        });


        // Tooltips

        tip_Energy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Preserve spacecraft's energy levels during the flight",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tip_Nutrient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Necessary nutrients will be provided to your body during the sleep",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tip_VR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Spent time in the West-world or the NYC simulation",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tip_Suit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Flexibility and oxygen capacity of different suits comes at a price",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tip_Meal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Food in tubes, irradiated meat, freeze dried drink mixes etc.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
