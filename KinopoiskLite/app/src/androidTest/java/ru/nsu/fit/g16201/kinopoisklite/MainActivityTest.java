package ru.nsu.fit.g16201.kinopoisklite;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.InstrumentationConnection;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.service.autofill.Validators.or;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldChangeTabAfterMenuClick() {
        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));


        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));

    }

    @Test
    public void shouldBeProperlyRecreated() {

        getInstrumentation().runOnMainSync(() -> rule.getActivity().recreate());

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));


        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));


        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));

    }


    @Test
    public void onShowAll() {

        onView(withTagValue(is("showAllButtonPopular"))).perform(click());

        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));

        onView(withId(R.id.action_explore)).perform(click());

    }

    @Test
    public void onShowMovieInfoFromExplore() {

        onView(withTagValue(is("recyclerViewPopular"))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));

        onView(withId(R.id.action_explore)).perform(click());

    }

    @Test
    public void onShowMovieInfoFromShowAll()
    {

        onView(withTagValue(is("showAllButtonPopular"))).perform(click());
        onView(withTagValue(is("showAllRecyclerView"))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));



        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));

        onView(withId(R.id.action_explore)).perform(click());
    }


    @Test
    public void onShowMovieInfoFromMovie() {
        onView(withTagValue(is("showAllButtonPopular"))).perform(click());
        onView(withTagValue(is("showAllRecyclerView"))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.similar_movie_collection)).perform(scrollTo());
        onView(withTagValue(is("recyclerViewSimilar"))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.action_explore)).perform(click());
    }


    @Test
    public void onShowShowAllFromMovie() {
        onView(withTagValue(is("showAllButtonPopular"))).perform(click());
        onView(withTagValue(is("showAllRecyclerView"))).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.similar_movie_collection)).perform(scrollTo());
        onView(withTagValue(is("showAllButtonSimilar"))).perform(click());

        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.next_button)).check(matches(not(isDisplayed())));
    }


    @Test
    public void onClickNext() {

        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.next_button)).perform(click());

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.action_random)).perform(click());

    }

    @Test
    public void onShowMovieInfoFromRandom() {
        onView(withId(R.id.action_random)).perform(click());
        onView(withTagValue(is("MoviePosterRandom"))).perform(click());

        onView(withId(R.id.action_explore)).perform(click());
        onView(withId(R.id.action_random)).perform(click());
        onView(withId(R.id.action_random)).perform(click());
    }

}