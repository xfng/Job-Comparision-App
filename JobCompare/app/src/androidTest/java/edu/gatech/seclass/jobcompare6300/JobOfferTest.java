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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;


@RunWith(AndroidJUnit4.class)
public class JobOfferTest {
    @Rule
    public ActivityScenarioRule EnterJobOffersActivity = new ActivityScenarioRule<>(EnterJobOffers.class);

    @Before
    public void setUp() throws InterruptedException {
        DataHelper db = new DataHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.deleteEverything();

        // Add a current job and 3 other jobs
        JobDetails jb = new JobDetails("Title", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
//        JobDetails jb2 = new JobDetails("Title2", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
//        JobDetails jb3 = new JobDetails("Title3", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
//        JobDetails jb4 = new JobDetails("Title4", "company", "City", "State", 100000, 1300, 12, 1230, 4000, 3000);
//
        db.saveCurrentJob(jb);
//        db.saveJobOffers(jb2);
//        db.saveJobOffers(jb3);
//        db.saveJobOffers(jb4);
    }

    @Test
    /* TC03 - 1 User enters correct job offer details and enters save button */
    public void correctInput() throws InterruptedException {
        try {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Amazon"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("120000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.addButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Successfully saved"));
        }

    }


    @Test
    /* TC03 - 2 User enters incorrect job offer details and enters save button */
    public void incorrectInput() throws InterruptedException {
        try {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Amazon"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("120000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("400"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.addButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Please check the errors in the fields"));
            assertTrue(ex.getMessage().contains("Error occurred while saving, please try again"));
        }
    }



    @Test
    /* TC03 - 3 User hits back button without saving entered details */
    public void withoutSaving() throws InterruptedException {
        onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
        onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
        onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
        onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("120000"));
        onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("30000"));
        onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("20000"));
        onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
        onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.backButton)).perform(click());
        onView(withText("Main Menu")).check(matches(isDisplayed()));

    }


    @Test
    /* TC03 - 4 User is able to enter another job offer */
    public void enterAnotherJob() throws InterruptedException {
        try {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Amazon"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("120000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.addButton)).perform(click());
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Developer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Oracle"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("San Jose"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("130000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("15"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("6000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("6000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.addButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Successfully saved"));
        }
    }

    @Test
    /* TC03 - 5 User is able to compare the offer (if they saved it) with the current job details (if present) */
    public void compareWithCurrentJob() throws InterruptedException {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Amazon"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("120000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.addButton)).perform(click());
            onView(withId(R.id.compareWithCurrentJobButton)).perform(scrollTo(), click());
//            onView(withId(R.id.compareWithCurrentJobButton)).perform(click());
            onView(withText("Compare Jobs")).check(matches(isDisplayed()));
    }
}




