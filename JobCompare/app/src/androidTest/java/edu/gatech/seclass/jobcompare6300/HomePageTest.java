package edu.gatech.seclass.jobcompare6300;

import androidx.core.widget.TextViewCompat;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class HomePageTest {

    @Rule
    public ActivityScenarioRule HomePageActivity = new ActivityScenarioRule<>(MainActivity.class);

    /* TC05 - 1 User is able to choose to job offers for comparison */
    @Test
    public void currentJobFunction() throws InterruptedException {
        onView(withId(R.id.navCurrentJobFormButton)).check(matches(withText("CURRENT JOB")));
    }
    @Test
    public void addJobOffersFunction() throws InterruptedException {
        onView(withId(R.id.navJobOffersFormButton)).check(matches(withText("ADD JOB OFFERS")));
    }
    @Test
    public void compareSettingsFunction() throws InterruptedException {
        onView(withId(R.id.navCompareSettingsButton)).check(matches(withText("COMPARE SETTINGS")));
    }
    @Test
    public void compareJobFunction() throws InterruptedException {
        onView(withId(R.id.navCompareJobsButton)).check(matches(withText("COMPARE JOBS")));
    }

    /* TC05 - 2 User navigates Current job, Enter Job Offer, Compare setting , Compare job buttons from the home screen */
    @Test
    public void navCurrentJobForm() throws InterruptedException {
        onView(withId(R.id.navCurrentJobFormButton)).perform(click());
        onView(withText("Current Job")).check(matches(isDisplayed()));
    }
    @Test
    public void navAddJobOffers() throws InterruptedException {
        onView(withId(R.id.navJobOffersFormButton)).perform(click());
        onView(withText("Enter Job Offers")).check(matches(isDisplayed()));
    }
    @Test
    public void navCompareSettings() throws InterruptedException {
        onView(withId(R.id.navCompareSettingsButton)).perform(click());
        onView(withText("Comparison Settings")).check(matches(isDisplayed()));
    }
    @Test
    public void navCompareJob() throws InterruptedException {
        onView(withId(R.id.navCompareJobsButton)).perform(click());
        onView(withText("Compare Jobs")).check(matches(isDisplayed()));
    }



}
