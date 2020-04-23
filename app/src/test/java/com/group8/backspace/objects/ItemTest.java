package com.group8.backspace.objects;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ItemTest {
    @Test
    public void testItem1() {
        Item item;

        System.out.println("\nStarting testItem");

        item = new Item("New York City", "vr",49);
        assertNotNull(item);
        assertTrue(item.getPrice()==49);
        assertTrue("New York City".equals(item.getItem()));
        assertTrue("vr".equals(item.getType()));


        System.out.println("Finished testItem");
    }
}
