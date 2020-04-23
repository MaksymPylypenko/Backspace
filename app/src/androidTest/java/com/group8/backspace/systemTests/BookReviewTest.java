package com.group8.backspace.systemTests;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.CalendarView;

import com.group8.backspace.R;
import com.group8.backspace.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookReviewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Before
    public void setUp(){
        onView(ViewMatchers.withId(R.id.btn_book)).perform(click());
        onView(withId(R.id.btn_earth)).perform(click());
        onView(withId(R.id.btn_saturn)).perform(click());
        onView(withId(R.id.btn_calendar_view)).perform(click());

        final float x = 0.1F;
        final float y = 0.8F;
        onView(withId(R.id.calendar)).perform(scrollTo(),clickPercent(x,y));
        onView(withId(R.id.btn_travel)).perform(click());
        onView(withId(R.id.imageBusiness)).perform(click());

        //select NYC and Irradiated meat as extra expense
        onView(withId(R.id.checkNYC)).perform(click());
        onView(withId(R.id.checkMeat)).perform(click());
        onView(withId(R.id.btn_purchase))
                .perform(scrollTo(), click());
    }

    @Test
    public void bookReviewTest(){

        flightReviewTest();
        classReviewTest();
        totalPriceReviewTest();
    }

    public void flightReviewTest(){
        onView(withId(R.id.origin_name)).check(matches(withText("Earth")));
        onView(withId(R.id.destination_name)).check(matches(withText("Saturn")));
        final String FUEL_PRICE = "25617 $";
        onView(withId(R.id.fuel_price)).check(matches(withText(FUEL_PRICE)));

        onView(withId(R.id.btn_origin)).perform(click());
        onView(withId(R.id.btn_mars)).perform(click());
        onView(withId(R.id.btn_uranus)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(withId(R.id.btn_destination)).perform(click());
        onView(withId(R.id.btn_earth)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());

    }

    public void classReviewTest(){
        final String CLASS_PRICE = "594 $";
        onView(withId(R.id.class_price)).check(matches(withText(CLASS_PRICE)));

        onView(withId(R.id.btn_travel_class)).perform(click());
        onView(withId(R.id.imageEconomy)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    public void totalPriceReviewTest(){
        final String TOTAL_PRICE = "26211 $";
        onView(withId(R.id.btn_purchase)).check(matches(withText(TOTAL_PRICE)));
    }

    public static ViewAction clickPercent(final float pctX, final float pctY){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);
                        int w = view.getWidth();
                        int h = view.getHeight();

                        float x = w * pctX;
                        float y = h * pctY;

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }


}
