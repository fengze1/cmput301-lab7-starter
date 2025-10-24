package com.example.androiduitesting;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onView(withText("Toronto")).perform(click());

        // Check if ShowActivity is launched
        intended(hasComponent(ShowActivity.class.getName()));

        // Verify ShowActivity is displayed by checking its elements
        onView(withId(R.id.textView_city_name)).check(matches(isDisplayed()));
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onView(withText("Vancouver")).perform(click());

        // Check if the correct city name is displayed in ShowActivity
        onView(withId(R.id.textView_city_name)).check(matches(withText("Vancouver")));
    }

    @Test
    public void testBackButton() {
        // Add a city and go to ShowActivity
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Montreal"));
        onView(withId(R.id.button_confirm)).perform(click());
        onView(withText("Montreal")).perform(click());

        // Verify we're in ShowActivity first
        onView(withId(R.id.textView_city_name)).check(matches(isDisplayed()));

        // Click back button
        onView(withId(R.id.button_back)).perform(click());

        // Verify we're back in MainActivity by checking if MainActivity elements are visible
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
        onView(withId(R.id.button_clear)).check(matches(isDisplayed()));
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
