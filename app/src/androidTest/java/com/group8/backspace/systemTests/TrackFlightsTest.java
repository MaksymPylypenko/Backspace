package com.group8.backspace.systemTests;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group8.backspace.R;
import com.group8.backspace.objects.Flight;

import com.group8.backspace.application.Services;
import com.group8.backspace.logic.accessors.AccessFlights;
import com.group8.backspace.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class TrackFlightsTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Test
    public void trackFlights(){
        onView(ViewMatchers.withId(R.id.btn_track)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(0).perform(click());
        AccessFlights flights = new AccessFlights(Services.getFlightPersistence());
        List<Flight> list = flights.getCurrentFlights();
        String flightID = "Flight#".concat(Integer.toString(list.get(0).getFlightID()));
        onView(withId(R.id.flightNum)).check(matches(withText(flightID)));
    }
}
