package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {
    public static final String TABLE_CURRENT_JOB = "CURRENT_JOB";
    public static final String TABLE_JOB_OFFERS = "JOB_OFFERS";
    public static final String TABLE_SETTINGS = "SETTINGS";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_COMPANY = "COMPANY";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_STATE = "STATE";
    public static final String COLUMN_YEARLY_SALARY = "YEARLY_SALARY";
    public static final String COLUMN_YEARLY_BONUS = "YEARLY_BONUS";
    public static final String COLUMN_LEAVE_TIME = "LEAVE_TIME";
    public static final String COLUMN_NUM_STOCKS = "NUM_STOCKS";
    public static final String COLUMN_HOME_BUYING_FUND = "HOME_BUYING_FUND";
    public static final String COLUMN_WELLNESS_FUND = "WELLNESS_FUND";
    public static final String COLUMN_AYS = "AYS";
    public static final String COLUMN_AYB = "AYB";
    public static final String COLUMN_LT = "LT";
    public static final String COLUMN_CSO = "CSO";
    public static final String COLUMN_HBP = "HBP";
    public static final String COLUMN_WF = "WF";

    public DataHelper(@Nullable Context context) {
        super(context, "jobinfo.db", null, 1);
    }

    // Initial Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCurrentJob = "CREATE TABLE " + TABLE_CURRENT_JOB + " (" + COLUMN_TITLE + " NOT NULL, "
                + COLUMN_COMPANY + " NOT NULL, " + COLUMN_CITY + " String NOT NULL, " + COLUMN_STATE
                + " String NOT NULL, " +
                COLUMN_YEARLY_SALARY + " Integer NOT NULL, " + COLUMN_YEARLY_BONUS + " Integer NOT NULL, "
                + COLUMN_LEAVE_TIME + " Integer NOT NULL, " + COLUMN_NUM_STOCKS + " Integer NOT NULL, "
                + COLUMN_HOME_BUYING_FUND + " Integer NOT NULL, " +
                COLUMN_WELLNESS_FUND + " Integer NOT NULL);";
        db.execSQL(createCurrentJob);

        String createJobOffers = "CREATE TABLE  " + TABLE_JOB_OFFERS + " (" + "id INTEGER Primary Key ASC, " + COLUMN_TITLE +
                " NOT NULL, " + COLUMN_COMPANY + " NOT NULL, " + COLUMN_CITY + " String NOT NULL, " + COLUMN_STATE
                + " String NOT NULL, " +
                COLUMN_YEARLY_SALARY + " Integer NOT NULL, " + COLUMN_YEARLY_BONUS + " Integer NOT NULL, "
                + COLUMN_LEAVE_TIME + " Integer NOT NULL, " + COLUMN_NUM_STOCKS + " Integer NOT NULL, "
                + COLUMN_HOME_BUYING_FUND + " Integer NOT NULL, " +
                COLUMN_WELLNESS_FUND + " Integer NOT NULL);";
        db.execSQL(createJobOffers);

        String createComparisonSettings = "CREATE TABLE " + TABLE_SETTINGS + " (" + COLUMN_AYS + " NOT NULL, " + COLUMN_AYB + " NOT NULL, " + COLUMN_CSO + " NOT NULL, " +
                "" + COLUMN_LT + " NOT NULL, " + COLUMN_HBP + " NOT NULL, " + COLUMN_WF + " NOT NULL);";
        db.execSQL(createComparisonSettings);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // Enter or Edit Current Job
    public boolean saveCurrentJob(JobDetails job) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_TITLE, job.getTitle());
        row.put(COLUMN_COMPANY, job.getCompany());
        row.put(COLUMN_CITY, job.getCity());
        row.put(COLUMN_STATE, job.getState());
        row.put(COLUMN_YEARLY_SALARY, job.getYearlySalary());
        row.put(COLUMN_YEARLY_BONUS, job.getYearlyBonus());
        row.put(COLUMN_LEAVE_TIME, job.getLeaveTime());
        row.put(COLUMN_NUM_STOCKS, job.getNumStocks());
        row.put(COLUMN_HOME_BUYING_FUND, job.getHomeBuyingFund());
        row.put(COLUMN_WELLNESS_FUND, job.getWellnessFund());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CURRENT_JOB, null, null);
        long insertResult = db.insert(TABLE_CURRENT_JOB, COLUMN_TITLE, row);

        if (insertResult == -1) {
            return false;
        }

        db.close();
        return true;
    }

    // Get Current Job
    public JobDetails getCurrentJob() {
        JobDetails currentJob = null;
        String query = "SELECT * FROM " + TABLE_CURRENT_JOB;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery(query, null);

        int titleCol = output.getColumnIndex(COLUMN_TITLE);
        int companyCol = output.getColumnIndex(COLUMN_COMPANY);
        int cityCol = output.getColumnIndex(COLUMN_CITY);
        int stateCol = output.getColumnIndex(COLUMN_STATE);
        int yearlySalaryCol = output.getColumnIndex(COLUMN_YEARLY_SALARY);
        int yearlyBonusCol = output.getColumnIndex(COLUMN_YEARLY_BONUS);
        int leaveTimeCol = output.getColumnIndex(COLUMN_LEAVE_TIME);
        int numStocksCol = output.getColumnIndex(COLUMN_NUM_STOCKS);
        int homeBuyingFundCol = output.getColumnIndex(COLUMN_HOME_BUYING_FUND);
        int wellnessFundCol = output.getColumnIndex(COLUMN_WELLNESS_FUND);

        if (output.moveToFirst()) {
            currentJob = new JobDetails(output.getString(titleCol), output.getString(companyCol),
                    output.getString(cityCol), output.getString(stateCol),
                    output.getInt(yearlySalaryCol), output.getInt(yearlyBonusCol),
                    output.getInt(leaveTimeCol), output.getInt(numStocksCol),
                    output.getInt(homeBuyingFundCol), output.getInt(wellnessFundCol));
        }

        output.close();
        db.close();
        return currentJob;
    }

    // Save Job offer
    public boolean saveJobOffers(JobDetails job) {
        ContentValues row = new ContentValues();
        row.put(COLUMN_TITLE, job.getTitle());
        row.put(COLUMN_COMPANY, job.getCompany());
        row.put(COLUMN_CITY, job.getCity());
        row.put(COLUMN_STATE, job.getState());
        row.put(COLUMN_YEARLY_SALARY, job.getYearlySalary());
        row.put(COLUMN_YEARLY_BONUS, job.getYearlyBonus());
        row.put(COLUMN_LEAVE_TIME, job.getLeaveTime());
        row.put(COLUMN_NUM_STOCKS, job.getNumStocks());
        row.put(COLUMN_HOME_BUYING_FUND, job.getHomeBuyingFund());
        row.put(COLUMN_WELLNESS_FUND, job.getWellnessFund());

        SQLiteDatabase db = this.getWritableDatabase();
        long insertResult = db.insert(TABLE_JOB_OFFERS, COLUMN_TITLE, row);

        db.close();
        return insertResult != -1;
    }

    // Read the last job offer
    public JobDetails getLastJobOffer() {
        JobDetails lastJobOffer = null;
        String query = "SELECT * FROM " + TABLE_JOB_OFFERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery(query, null);

        int titleCol = output.getColumnIndex(COLUMN_TITLE);
        int companyCol = output.getColumnIndex(COLUMN_COMPANY);
        int cityCol = output.getColumnIndex(COLUMN_CITY);
        int stateCol = output.getColumnIndex(COLUMN_STATE);
        int yearlySalaryCol = output.getColumnIndex(COLUMN_YEARLY_SALARY);
        int yearlyBonusCol = output.getColumnIndex(COLUMN_YEARLY_BONUS);
        int leaveTimeCol = output.getColumnIndex(COLUMN_LEAVE_TIME);
        int numStocksCol = output.getColumnIndex(COLUMN_NUM_STOCKS);
        int homeBuyingFundCol = output.getColumnIndex(COLUMN_HOME_BUYING_FUND);
        int wellnessFundCol = output.getColumnIndex(COLUMN_WELLNESS_FUND);

        if (output.moveToLast()) {
            lastJobOffer = new JobDetails(output.getString(titleCol), output.getString(companyCol),
                    output.getString(cityCol),
                    output.getString(stateCol), output.getInt(yearlySalaryCol),
                    output.getInt(yearlyBonusCol), output.getInt(leaveTimeCol), output.getInt(numStocksCol),
                    output.getInt(homeBuyingFundCol), output.getInt(wellnessFundCol));
        }

        output.close();
        db.close();
        return lastJobOffer;

    }

    // Get the list of all job offers
    public ArrayList<JobDetails> getJobOffersList() {
        JobDetails job;
        ArrayList<JobDetails> jobOffersList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_JOB_OFFERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor output = db.rawQuery(query, null);

        int titleCol = output.getColumnIndex(COLUMN_TITLE);
        int companyCol = output.getColumnIndex(COLUMN_COMPANY);
        int cityCol = output.getColumnIndex(COLUMN_CITY);
        int stateCol = output.getColumnIndex(COLUMN_STATE);
        int yearlySalaryCol = output.getColumnIndex(COLUMN_YEARLY_SALARY);
        int yearlyBonusCol = output.getColumnIndex(COLUMN_YEARLY_BONUS);
        int leaveTimeCol = output.getColumnIndex(COLUMN_LEAVE_TIME);
        int numStocksCol = output.getColumnIndex(COLUMN_NUM_STOCKS);
        int homeBuyingFundCol = output.getColumnIndex(COLUMN_HOME_BUYING_FUND);
        int wellnessFundCol = output.getColumnIndex(COLUMN_WELLNESS_FUND);

        while (output.moveToNext()) {
            job = new JobDetails(output.getString(titleCol), output.getString(companyCol), output.getString(cityCol),
                    output.getString(stateCol), output.getInt(yearlySalaryCol),
                    output.getInt(yearlyBonusCol), output.getInt(leaveTimeCol), output.getInt(numStocksCol),
                    output.getInt(homeBuyingFundCol), output.getInt(wellnessFundCol));
            jobOffersList.add(job);
        }

        output.close();
        db.close();
        return jobOffersList;

    }

    // delete job offer
    public boolean deleteJobOffer(JobDetails Job) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_JOB_OFFERS,
                COLUMN_TITLE + " = " + Job.getTitle() + " AND " + COLUMN_COMPANY + "=" + Job.getCompany(), null);
        db.close();

        return result != 0;
    }

    /* Save or Edit all Comparison Settings */
    public boolean saveComparisonSettings(ComparisonSettings settings) {
        ContentValues row = new ContentValues();

        row.put(COLUMN_AYS, settings.getAYS());
        row.put(COLUMN_AYB, settings.getAYB());
        row.put(COLUMN_CSO, settings.getCSO());
        row.put(COLUMN_LT, settings.getLT());
        row.put(COLUMN_HBP, settings.getHBP());
        row.put(COLUMN_WF, settings.getWF());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETTINGS, null, null);
        long insertResult = db.insert(TABLE_SETTINGS, null, row);

        return insertResult != -1;
    }

    /* Get settings for ranking */
    public ComparisonSettings getComparisonSettings() {
        ComparisonSettings settings = null;
        String query = "SELECT * FROM " + TABLE_SETTINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery(query, null);

        int AYSCol = output.getColumnIndex(COLUMN_AYS);
        int AYBCol = output.getColumnIndex(COLUMN_AYB);
        int CSOCol = output.getColumnIndex(COLUMN_CSO);
        int LTCol = output.getColumnIndex(COLUMN_LT);
        int HBPCol = output.getColumnIndex(COLUMN_HBP);
        int WFCol = output.getColumnIndex(COLUMN_WF);

        if (output.moveToFirst()) {
            settings = new ComparisonSettings(output.getInt(AYSCol), output.getInt(AYBCol),
                    output.getInt(CSOCol), output.getInt(LTCol), output.getInt(HBPCol), output.getInt(WFCol));
        }

        output.close();
        db.close();
        return settings;
    }

    public void deleteEverything() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CURRENT_JOB, null, null);
        db.delete(TABLE_SETTINGS, null, null);
        db.delete(TABLE_JOB_OFFERS, null, null);
    }

}
