package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DataBaseTest {

    private DataHelper jobInfo;

    @Before
    public void setUp(){
        Context context = ApplicationProvider.getApplicationContext();
        jobInfo = new DataHelper(context);
        jobInfo.getWritableDatabase();
    }

    @After
    public void finish() {
        jobInfo.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(jobInfo);
    }

    @Test
    public void testCurrentJobTableSuccess(){
        JobDetails job = new JobDetails("job1","company1","A","CA",1000,1000,1000,
                10,1000,1000);
        jobInfo.saveCurrentJob(job);
        assertEquals(jobInfo.getCurrentJob().getCity(), job.getCity());
        assertEquals(jobInfo.getCurrentJob().getCompany(), job.getCompany());
        assertEquals(jobInfo.getCurrentJob().getState(), job.getState());
        assertEquals(jobInfo.getCurrentJob().getLeaveTime(), job.getLeaveTime());
    }

    @Test
    public void testJobOffersTableSuccess(){
        JobDetails job = new JobDetails("job2","company2","B","TX",2000,2000,
                5,2000,2000,2000);
        jobInfo.saveJobOffers(job);
        assertEquals(jobInfo.getJobOffersList().size(), 1);
        assertEquals(jobInfo.getLastJobOffer().getCity(), job.getCity());
        assertEquals(jobInfo.getLastJobOffer().getCompany(), job.getCompany());
        assertEquals(jobInfo.getLastJobOffer().getState(), job.getState());
        assertEquals(jobInfo.getLastJobOffer().getLeaveTime(), job.getLeaveTime());
    }

    @Test
    public void testComparisonSettingsTableSuccess(){
        ComparisonSettings settings = new ComparisonSettings(5,4,3,2,1,0);
        jobInfo.saveComparisonSettings(settings);
        assertEquals(jobInfo.getComparisonSettings().getAYS(), settings.getAYS());
        assertEquals(jobInfo.getComparisonSettings().getAYB(), settings.getAYB());
        assertEquals(jobInfo.getComparisonSettings().getCSO(), settings.getCSO());
        assertEquals(jobInfo.getComparisonSettings().getLT(), settings.getLT());
        assertEquals(jobInfo.getComparisonSettings().getHBP(), settings.getHBP());
        assertEquals(jobInfo.getComparisonSettings().getWF(), settings.getWF());

    }

}