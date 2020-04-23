package com.group8.backspace.logic.accessors;
import com.group8.backspace.persistence.ItemPersistence;
import com.group8.backspace.objects.Item;
import java.util.List;


public class AccessItems {

    private ItemPersistence itemPersistence;

//    public AccessItems() {
//        itemPersistence = Services.getItemPersistence();
//    }

    public AccessItems(final ItemPersistence itemPersistence) {
        this.itemPersistence = itemPersistence;
    }

    public Item getItemByName(String itemName) {
        return itemPersistence.getItem(itemName);
    }

    public int getTotalPrice(List<Item> selectedItems) {
        int price = 0;
        for (Item item : selectedItems) {
            price += item.getPrice();
        }
        return price;
    }
}
