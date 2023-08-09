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
import static org.hamcrest.core.StringContains.containsString;
import static java.util.EnumSet.allOf;

import android.widget.TextView;


@RunWith(AndroidJUnit4.class)
public class CurrentJobTest {

    @Rule
    public ActivityScenarioRule currentJobFormActivity = new ActivityScenarioRule<>(CurrentJobForm.class);


    @Before
    public void setUp() throws InterruptedException {
        DataHelper db = new DataHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.deleteEverything();
    }

    @Test
    /* TC02 - 1 User enters correct Current job details and enters save button */
    public void correctInput() throws InterruptedException {
        try {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Google"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("San Jose"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("130000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("25000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("5000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("5000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Successfully saved"));
        }

    }

    @Test
    /* TC02 - 2 User enters incorrect Current job details and enters save button  */
    public void inCorrectInput() throws InterruptedException {
        try {
            onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Engineer I"));
            onView(withId(R.id.companyInput)).perform(clearText(), replaceText("Google"));
            onView(withId(R.id.cityInput)).perform(clearText(), replaceText("San Jose"));
            onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("130000"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("25000"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("30000"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("20"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("6000"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("6000000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButton)).perform(click());
        } catch (ExceptionInInitializerError ex) {
            assertTrue(ex.getMessage().contains("Please check the errors in the fields"));
            assertTrue(ex.getMessage().contains("Error occurred while saving, please try again"));
        }
    }

    @Test
    /* TC02 - 3 User hits back button without saving entered details */
    public void withoutSaving() throws InterruptedException {
        onView(withId(R.id.titleInput)).perform(clearText(), replaceText("Software Developer I"));
        onView(withId(R.id.companyInput)).perform(clearText(), replaceText("SAP"));
        onView(withId(R.id.cityInput)).perform(clearText(), replaceText("Palo Alto"));
        onView(withId(R.id.stateInput)).perform(clearText(), replaceText("California"));
        onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("135000"));
        onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("25000"));
        onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("30000"));
        onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("4500"));
        onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.backButton)).perform(click());
        onView(withText("Main Menu")).check(matches(isDisplayed()));


    }





}
