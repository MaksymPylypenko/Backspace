package com.group8.backspace;

import com.group8.backspace.logic.AccessFlightsTest;
import com.group8.backspace.logic.AccessItemsTest;
import com.group8.backspace.logic.AccessPlanetsTest;
import com.group8.backspace.logic.AnalyseDatesTest;
import com.group8.backspace.logic.CalculatePriceTest;
import com.group8.backspace.logic.DistanceHandlerTest;
import com.group8.backspace.objects.FlightTest;
import com.group8.backspace.objects.ItemTest;
import com.group8.backspace.objects.LocationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessFlightsTest.class,
        AccessItemsTest.class,
        LocationTest.class,
        FlightTest.class,
        ItemTest.class,
        AccessPlanetsTest.class,
        AnalyseDatesTest.class,
        DistanceHandlerTest.class,
        CalculatePriceTest.class
})
public class AllTest {

}