package edu.gatech.seclass.jobcompare6300;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class CompareJobTest {
    @Rule
    public ActivityScenarioRule compareJobFormActivity = new ActivityScenarioRule<>(CompareJobForm.class);

    @Before
    public void setUp() throws InterruptedException {
        DataHelper db = new DataHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.deleteEverything();
        JobDetails jb = new JobDetails("Title", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
        JobDetails jb2 = new JobDetails("Title2", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
        db.saveCurrentJob(jb);
        db.saveJobOffers(jb2);
    }

    /* TC05 - 1 User is able to choose to job offers for comparison*/
    @Test
    public void jobIDCol() throws InterruptedException {
        onView(withText("Job ID")).check(matches(isDisplayed()));
    }

    @Test
    public void titleCol() throws InterruptedException {
        onView(withText("Job Title")).check(matches(isDisplayed()));
    }

    @Test
    public void companyCol() throws InterruptedException {
        onView(withText("Company")).check(matches(isDisplayed()));
    }

    @Test
    public void currentCol() throws InterruptedException {
        onView(withText("Current?")).check(matches(isDisplayed()));
    }
}

