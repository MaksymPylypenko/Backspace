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

public class FlightListAdapter extends BaseAdapter {
    Context context;
    int Item[]; //flight numbers
    String SubItem[]; //description
    int flags[]; //images
    LayoutInflater inflter;
    Class next;

    public FlightListAdapter(Context applicationContext, Class next,
                             int[] Item, String[] SubItem , int[] flags) { //constructor takes in and sets info
        this.context = context;
        this.Item = Item;
        this.SubItem = SubItem;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
        this.next = next;
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
    public View getView(final int i, View view, ViewGroup viewGroup) { //creates view
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView item = (TextView) view.findViewById(R.id.item); //creates textviews and imageviews based on the xml
        TextView subitem = (TextView) view.findViewById(R.id.subitem);
        ImageView image = (ImageView) view.findViewById(R.id.imageID);
        String flightName = "Flight #"+Item[i];
        item.setText(flightName); //sets info and image
        subitem.setText(SubItem[i]);
        image.setImageResource(flags[i]);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //makes a button, that when pressed goes to StatusDetail.class the title of the textview clicked
//                Intent intent = new Intent(v.getContext(), next);
                Intent intent = new Intent(v.getContext(), next);
                intent.putExtra("FLIGHT_NUM", Item[i]);
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }
}
