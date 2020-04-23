package com.group8.backspace;


import com.group8.backspace.systemTests.AdditionalExpenseTest;
import com.group8.backspace.systemTests.BookReviewTest;
import com.group8.backspace.systemTests.BrowseFlightsTest;
import com.group8.backspace.systemTests.CouponTest;
import com.group8.backspace.systemTests.FlightStatusTest;
import com.group8.backspace.systemTests.PlanetViewTest;
import com.group8.backspace.systemTests.PurchaseTest;
import com.group8.backspace.systemTests.TrackFlightsTest;
import com.group8.backspace.systemTests.TravelClassSelectionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdditionalExpenseTest.class,
        BookReviewTest.class,
        BrowseFlightsTest.class,
        CouponTest.class,
        FlightStatusTest.class,
        PlanetViewTest.class,
        PurchaseTest.class,
        TrackFlightsTest.class,
        TravelClassSelectionTest.class,
})
public class allSystemTests {
}
