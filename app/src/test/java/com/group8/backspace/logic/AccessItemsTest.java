package com.group8.backspace.logic;


import com.group8.backspace.logic.accessors.AccessItems;
import com.group8.backspace.objects.Item;
import com.group8.backspace.persistence.ItemPersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import static org.mockito.Mockito.*;

public class AccessItemsTest {
    private AccessItems accessItems;

    private ItemPersistence itemPersistence;

    private ArrayList<Item> storageTest;

    Item item1;
    Item item2;
    @Before
    public void setUp() {
        itemPersistence = mock(ItemPersistence.class);
        accessItems = new AccessItems(itemPersistence);
        item1 = new Item("hyper sleep","travel class",10);
        item2 = new Item("activities","travel class",20);
        storageTest = new ArrayList<>();

        storageTest.add(item1);
        storageTest.add(item2);

    }

    @Test
    public void testNull() {
        System.out.println("\nStarting test null");
        assertNotNull(accessItems);

        System.out.println("Finished test null");
    }

    @Test
    public void testGetItem(){
        System.out.println("Start testing GetItem");
        when(itemPersistence.getItem("hyper sleep")).thenReturn(item1);
        Item test1 = accessItems.getItemByName("hyper sleep");
        assertNotNull(test1);
        assertEquals(test1,item1);
        verify(itemPersistence).getItem("hyper sleep");
        System.out.println("End testing GetItem");
    }

    @Test
    public void testGetTotalPrice(){
        System.out.println("Start testing GetTotalPrice");
        int price = accessItems.getTotalPrice(storageTest);
        assertNotNull(price);
        assertEquals(10+20,price);
        System.out.println("End testing GetTotalPrice");
    }

}
