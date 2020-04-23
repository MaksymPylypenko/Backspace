package com.group8.backspace.IntegrationTests;

import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessItems;
import com.group8.backspace.objects.Item;
import com.group8.backspace.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessItemsIT {
    private AccessItems accessItems;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
//        final ItemPersistence persistence = new ItemPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessItems = Services.getAccessItem();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @After
    public void tearDown() {
        System.out.println("Done!");
        accessItems = null;
        System.out.println("clear " + tempDB.delete());
        Services.resetAccess();
    }

    @Test
    public void testGetItemByName(){
        final Item item;
        item = new Item("activities","travel class",99);
        Item test = accessItems.getItemByName("activities");
        assertNotNull(test);
        assertEquals(item.getPrice(),test.getPrice());
        System.out.println("End testing GetItemByName");
    }

    @Test
    public void testGetTotalPrice(){
        Item item1 = new Item("activities","travel class",99);
        Item item2 = new Item("hyper sleep","travel class",10);
        List<Item> test = new ArrayList<>();
        test.add(item1);
        test.add(item2);

        int totalPrice = accessItems.getTotalPrice(test);
        assertEquals(109,totalPrice);
        System.out.println("End testing GetTotalPrice");
    }
}
