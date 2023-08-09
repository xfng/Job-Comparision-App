package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CompareTwoJobs extends AppCompatActivity {
    private Button backButton;
    private Button compareButton;
    private EditText job1IDText;
    private EditText job2IDText;
    private TextView job1Company;
    private TextView job1Position;
    private TextView job1Salary;
    private TextView job1Bonus;
    private TextView job1Stocks;
    private TextView job1Leave;
    private TextView job1HBP;
    private TextView job1WF;
    private TextView job1Rating;
    private TextView job2Company;
    private TextView job2Position;
    private TextView job2Salary;
    private TextView job2Bonus;
    private TextView job2Stocks;
    private TextView job2Leave;
    private TextView job2HBP;
    private TextView job2WF;
    private TextView job2Rating;

    public static final String JOBID_1 = "JobID1";
    public static final String JOBID_2 = "JobID2";
    private String jobID1;
    private String jobID2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataHelper dataHelper = new DataHelper(CompareTwoJobs.this);

        setContentView(R.layout.compare_two_jobs);

        // Initialize Buttons
        backButton = (Button) findViewById(R.id.backButton);
        compareButton = findViewById(R.id.compareJobsButton);

        // Initialize Job1 TextView features
        job1Company = (TextView) findViewById(R.id.nameJob1);
        job1Position = (TextView) findViewById(R.id.roleJob1);
        job1Salary = (TextView) findViewById(R.id.salaryJob1);
        job1Bonus = (TextView) findViewById(R.id.bonusJob1);
        job1Stocks = (TextView) findViewById(R.id.job1Shares);
        job1Leave = (TextView) findViewById(R.id.leaveJob1);
        job1HBP = (TextView) findViewById(R.id.hbpJob1);
        job1WF = (TextView) findViewById(R.id.wpJob1);
        job1Rating = (TextView) findViewById(R.id.ratingJob1);

        // Initialize Job2 TextView features
        job2Company = (TextView) findViewById(R.id.nameJob2);
        job2Position = (TextView) findViewById(R.id.roleJob2);
        job2Salary = (TextView) findViewById(R.id.salaryJob2);
        job2Bonus = (TextView) findViewById(R.id.bonusJob2);
        job2Stocks = (TextView) findViewById(R.id.job2Shares);
        job2Leave = (TextView) findViewById(R.id.leaveJob2);
        job2HBP = (TextView) findViewById(R.id.hbpJob2);
        job2WF = (TextView) findViewById(R.id.wpJob2);
        job2Rating = (TextView) findViewById(R.id.ratingJob2);

        // Initialize the Column header labels
        TextView title1 = (TextView) findViewById(R.id.job1Title);
        TextView title2 = (TextView) findViewById(R.id.job2Title);

        // Initialize Intent and passed String values
        Intent i = getIntent();
        jobID1 = i.getStringExtra(JOBID_1);
        jobID2 = i.getStringExtra(JOBID_2);

        JobDetails job1;
        JobDetails job2;

        // If this class is accessed via the EnterJobOffers form,
        // compare the current job offer to the most recently added job offer
        if(jobID1.equals("CURRENT") || jobID2.equals("LAST")){
            job1 = dataHelper.getCurrentJob();
            job2 = dataHelper.getLastJobOffer();
            ComparisonSettings compSettings = dataHelper.getComparisonSettings();

            job1.calculateRating(compSettings);
            job2.calculateRating(compSettings);

            title1.setText("Current Job");
            title2.setText("Last Added");
        } else {
            JobOffers jobOffers= new JobOffers(dataHelper);
            ArrayList<JobDetails> sortedJobList = jobOffers.getRankedList(CompareTwoJobs.this);
            validIntegers(jobID1, jobID2, sortedJobList.size()-1);
            job1 = sortedJobList.get(Integer.parseInt(jobID1));
            job2 = sortedJobList.get(Integer.parseInt(jobID2));

            title1.setText("Job ID: " + jobID1);
            title2.setText("Job ID: "+ jobID2);
        }

        updateTableDisplay(job1, job2);


        backButton.setOnClickListener(view -> startActivity(new Intent(CompareTwoJobs.this, MainActivity.class)));

        compareButton.setOnClickListener(view -> {
            if(jobID1.equals("CURRENT")){
                startActivity(new Intent(CompareTwoJobs.this, EnterJobOffers.class));
            } else{
                startActivity(new Intent(CompareTwoJobs.this, CompareJobForm.class));
            }
        });
    }

    private boolean validIntegers(String job1, String job2, int length){
        if (job1 == null || job2 == null){
            throw new NumberFormatException();
        }
        int j1;
        int j2;
        try{
            j1 = Integer.parseInt(job1);
            j2 = Integer.parseInt(job2);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        if (j1 > length || j2 > length || j1 < 0 || j2 < 0){
            throw new IllegalArgumentException();
        }
        return true;
    }

    private void updateTableDisplay(JobDetails job1, JobDetails job2){

        // Initialize Job1 TextView features
        job1Company.setText(job1.getCompany());
        job1Position.setText(job1.getTitle());
        job1Salary.setText(Integer.toString(job1.getYearlySalary()));
        job1Bonus.setText(Integer.toString(job1.getYearlyBonus()));
        job1Stocks.setText(Integer.toString(job1.getNumStocks()));
        job1Leave.setText(Integer.toString(job1.getLeaveTime()));
        job1HBP.setText(Integer.toString(job1.getHomeBuyingFund()));
        job1WF.setText(Integer.toString(job1.getWellnessFund()));
        job1Rating.setText(Double.toString(job1.getRating()));

        // Initialize Job2 TextView features
        job2Company.setText(job2.getCompany());
        job2Position.setText(job2.getTitle());
        job2Salary.setText(Integer.toString(job2.getYearlySalary()));
        job2Bonus.setText(Integer.toString(job2.getYearlyBonus()));
        job2Stocks.setText(Integer.toString(job2.getNumStocks()));
        job2Leave.setText(Integer.toString(job2.getLeaveTime()));
        job2HBP.setText(Integer.toString(job2.getHomeBuyingFund()));
        job2WF.setText(Integer.toString(job2.getWellnessFund()));
        job2Rating.setText(Double.toString(job2.getRating()));

    }
}
