package com.group8.backspace.stubs;
import com.group8.backspace.objects.Item;
import com.group8.backspace.persistence.ItemPersistence;

public class ItemPersistenceStub implements ItemPersistence {
    @Override
    public Item getItem(String name) {
        if(name.equals("hyper sleep")) {
            return new Item(name,"travel class",10);
        }
        else if(name.equals("activities")) {
            return new Item(name,"travel class",99);
        }
        else if(name.equals("new york city")) {
            return new Item(name,"vr",49);
        }
        else if(name.equals("west world")) {
            return new Item(name,"vr",69);
        }
        else if(name.equals("food paste")) {
            return new Item(name,"meal",0);
        }
        else if(name.equals("irradiated meat")) {
            return new Item(name,"meal",20);
        }
        else if(name.equals("dried drinks")) {
            return new Item(name,"meal",10);
        }
        else if(name.equals("dark matter")) {
            return new Item(name,"engine",3000);
        }
        else if(name.equals("methane")) {
            return new Item(name,"engine",1000);
        }
        else if(name.equals("liquid oxygen")) {
            return new Item(name,"engine",2000);
        }
        else {
            throw new NullPointerException();
        }
    }
}
