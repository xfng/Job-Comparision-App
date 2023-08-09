package edu.gatech.seclass.jobcompare6300;

import androidx.core.widget.TextViewCompat;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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


@RunWith(AndroidJUnit4.class)
public class CompareResultTest {
    @Rule
    public ActivityScenarioRule CompareResultActivity = new ActivityScenarioRule<>(CompareJobForm.class);

    @Before
    public void setUp() throws InterruptedException {
        DataHelper db = new DataHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.deleteEverything();

        // Add a current job and 3 other jobs
        JobDetails jb = new JobDetails("Title", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
        JobDetails jb2 = new JobDetails("Title2", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
        JobDetails jb3 = new JobDetails("Title3", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
        JobDetails jb4 = new JobDetails("Title4", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);

        db.saveCurrentJob(jb);
        db.saveJobOffers(jb2);
        db.saveJobOffers(jb3);
        db.saveJobOffers(jb4);
    }

    @Test
    /* TC06 - 1 User selects two jobs to compare and trigger the comparison */
    public void correctInput() throws InterruptedException {
        onView(withId(R.id.cj_JobID1)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.cj_JobID2)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.compareButton)).perform(click());
        onView(withText("Compare Jobs")).check(matches(isDisplayed()));
    }

    @Test
    /* TC06 - 2 User offers to perform another comparison */
    public void performAnotherComparison() throws InterruptedException {
        onView(withId(R.id.cj_JobID1)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.cj_JobID2)).perform(clearText(), replaceText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.compareButton)).perform(click());
        onView(withId(R.id.compareJobsButton)).perform(click());
        onView(withId(R.id.cj_JobID1)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.cj_JobID2)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.compareButton)).perform(click());
        onView(withText("Compare Jobs")).check(matches(isDisplayed()));
    }

    @Test
    /* TC06 - 3 User hits back button */
    public void backMainMenu() throws InterruptedException {
        onView(withId(R.id.cj_JobID1)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.cj_JobID2)).perform(clearText(), replaceText("3"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.backButton)).perform(click());
        onView(withText("Main Menu")).check(matches(isDisplayed()));
    }
}
