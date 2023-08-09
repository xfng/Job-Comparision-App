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

@RunWith(AndroidJUnit4.class)
public class ComparisonSettingsTest {
    @Rule
    public ActivityScenarioRule ComparisonSettingsActivity = new ActivityScenarioRule<>(ComparisonSettingsForm.class);


    @Before
    public void setUp() throws InterruptedException {
        DataHelper db = new DataHelper(InstrumentationRegistry.getInstrumentation().getTargetContext());
        db.deleteEverything();
    }

    @Test
    /* TC04 - 1 User enters correct integer weights to Compare job settings and enters save button */
    public void correctInput() throws InterruptedException {
        try {
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("2"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("2"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("2"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("1"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("1"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("1"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Successfully saved the comparison settings"));
        }
    }

    @Test
    /* TC04 - 2 User enters incorrect integer weights to Compare job settings and enters save button */
    public void inCorrectInput() throws InterruptedException {
        try {
            onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("ttt"));
            onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("bbb"));
            onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("2"));
            onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("1"));
            onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("1"));
            onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("1"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButton)).perform(click());
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("Please enter integer values"));
            assertTrue(ex.getMessage().contains("There was an error while saving, please try again"));
        }


    }

    @Test
    /* TC04 - 3 User hits back button without saving entered details */
    public void withoutSaving() throws InterruptedException {
        onView(withId(R.id.salaryInput)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.bonusInput)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.stockOptionsInput)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.leaveTimeInput)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.homeProgramFundInput)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.wellnessFundInput)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.backButton)).perform(click());
        onView(withText("Main Menu")).check(matches(isDisplayed()));
    }


}
