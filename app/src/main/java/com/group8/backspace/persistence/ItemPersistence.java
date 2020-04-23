package com.group8.backspace.persistence;
import com.group8.backspace.objects.Item;

public interface ItemPersistence {
    Item getItem(String name);
}
