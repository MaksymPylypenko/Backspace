package com.group8.backspace.presentation.browse_planets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessPlanets;
import com.group8.backspace.objects.Location;
import com.group8.backspace.presentation.util.list_adapters.PlanetListAdapter;

import java.util.List;

public class PlanetList extends AppCompatActivity {
    ListView simpleList;

    AccessPlanets access = new AccessPlanets(Services.getPlanetPersistence());
    List<Location> planetList = access.getPlanets();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        TextView title = findViewById(R.id.title);
        title.setText(getString(R.string.title_activity_planet_list));

        String planetName[] = new String[planetList.size()];
        String planetDescription[] = new String[planetList.size()];
        int planetIcon[] = new int[planetList.size()];
        for(int i = 0; i < planetList.size(); i++){
            planetName[i] = (planetList.get(i)).getId();
            planetDescription[i] = (planetList.get(i)).getShortDesc();
            planetIcon[i] = getResources().getIdentifier(
                    ("ic_".concat(planetName[i])), "drawable", getPackageName());
            //planetIcon[i] = getResources().getIdentifier((planetList.get(i).getImgSrc()), "drawable", getPackageName());
        }

        simpleList = (ListView)findViewById(R.id.ListView); //makes listview based on info fed in
        PlanetListAdapter customAdapter = new PlanetListAdapter(
                PlanetList.this, planetName, planetDescription, planetIcon);
        simpleList.setAdapter(customAdapter);
    }
}
