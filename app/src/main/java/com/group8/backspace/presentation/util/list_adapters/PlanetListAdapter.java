package com.group8.backspace.presentation.util.list_adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.presentation.browse_planets.Info;

public class PlanetListAdapter extends BaseAdapter {
    Context context;
    String Item[]; //reference
    String SubItem[]; //description
    int flags[]; //images
    LayoutInflater inflter;

    public PlanetListAdapter(Context applicationContext, String[] Item, String[] SubItem , int[] flags) { //constructor takes in and sets info
        this.context = context;
        this.Item = Item;
        this.SubItem = SubItem;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return Item.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup){  //creates view

        view = inflter.inflate(R.layout.activity_listview, null);
        TextView item = (TextView) view.findViewById(R.id.item); //creates textviews and imageviews based on the xml
        TextView subitem = (TextView) view.findViewById(R.id.subitem);
        ImageView image = (ImageView) view.findViewById(R.id.imageID);
        item.setText(Item[i].substring(0,1).toUpperCase() + Item[i].substring(1)); //sets info and image
        subitem.setText(SubItem[i]);
        image.setImageResource(flags[i]);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //makes a button, that when pressed goes to info.class the title of the textview clicked
                Intent intent = new Intent(v.getContext(), Info.class);
                intent.putExtra("planetName", Item[i]);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }
}