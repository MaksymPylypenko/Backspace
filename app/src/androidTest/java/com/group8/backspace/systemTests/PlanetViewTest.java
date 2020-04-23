package com.group8.backspace.systemTests;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group8.backspace.R;
import com.group8.backspace.presentation.MainActivity;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlanetViewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Test
    public void viewPlanet(){
        onView(ViewMatchers.withId(R.id.btn_planets)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(0).onChildView(withId(R.id.item)).check(matches(withText("Earth")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(1).onChildView(withId(R.id.item)).check(matches(withText("Jupiter")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(2).onChildView(withId(R.id.item)).check(matches(withText("Mars")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(3).onChildView(withId(R.id.item)).check(matches(withText("Mercury")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(4).onChildView(withId(R.id.item)).check(matches(withText("Neptune")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(5).onChildView(withId(R.id.item)).check(matches(withText("Pluto")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(6).onChildView(withId(R.id.item)).check(matches(withText("Saturn")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(7).onChildView(withId(R.id.item)).check(matches(withText("Uranus")));
        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(8).onChildView(withId(R.id.item)).check(matches(withText("Venus")));

        onData(anything()).inAdapterView(withId(R.id.ListView)).atPosition(0).perform(click());
        moveToPurchasePage();


    }

    public void moveToPurchasePage(){
        onView(withId(R.id.planetFromLocations)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.planetToLocations)).perform(click());
    }

}
