package ru.nsu.fit.g16201.kinopoisklite;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.service.autofill.Validators.or;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void shouldChangeTabAfterMenuClick() {
        onView(withId(R.id.action_explore)).perform(click());
        onView(withText("Lists!")).check(matches(not(isDisplayed())));
        onView(withId(R.id.rmDescription)).check(matches(not(isDisplayed())));


        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.rmDescription)).check(matches(isDisplayed()));

        onView(withId(R.id.action_lists)).perform(click());
        onView(withText("Lists!")).check(matches(isDisplayed()));

    }

}