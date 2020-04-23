package com.group8.backspace.systemTests;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.group8.backspace.R;
import com.group8.backspace.presentation.MainActivity;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class PurchaseTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Before
    public void setup() {
        onView(ViewMatchers.withId(R.id.btn_book)).perform(click());
        onView(withId(R.id.btn_earth)).perform(click());
        onView(withId(R.id.btn_saturn)).perform(click());
        onView(withId(R.id.btn_calendar_view)).perform(click());

        final float x = 0.1F;
        final float y = 0.8F;
        onView(withId(R.id.calendar)).perform(scrollTo(),clickPercent(x,y));

        onView(withId(R.id.btn_travel)).perform(click());
        onView(withId(R.id.imageEconomy)).perform(click());
        onView(withId(R.id.btn_purchase)).perform(click());
    }

    @Test
    public void purchaseTest(){
        // purchase succeed
        onView(withId(R.id.cardNum)).perform(typeText("1234567890123456"));
        closeSoftKeyboard();
        onView(withId(R.id.expiry_date)).perform(typeText("0419"));
        closeSoftKeyboard();
        onView(withId(R.id.security_code)).perform(typeText("011"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_purchase)).perform(click());
        onView(withId(R.id.condition)).check(matches(withText("Purchase succeed")));
        //purchase failed
        onView(withId(R.id.cardNum)).perform(typeText("1"));
        closeSoftKeyboard();
        onView(withId(R.id.expiry_date)).perform(typeText("1"));
        closeSoftKeyboard();
        onView(withId(R.id.security_code)).perform(typeText("1"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_purchase)).perform(click());
        onView(withId(R.id.condition)).check(matches(withText("Purchase failed. Please provide a valid credit card.")));
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
