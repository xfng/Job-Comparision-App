package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompareJobForm extends AppCompatActivity {
    private Button backButton;
    private Button compareButton;
    private JobDetails jobDetails;
    private JobOffers jobOffers;
    private EditText jobID1;
    private EditText jobID2;

  /*  private Button compareSettingsButton;
    private Button compareJobsButton;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);
        backButton = (Button) findViewById(R.id.backButton);
        compareButton = (Button) findViewById(R.id.compareButton);

        jobID1 = (EditText) findViewById(R.id.cj_JobID1);
        jobID2 = (EditText) findViewById(R.id.cj_JobID2);

        //Get the DataHelper and access the JobOffersList, get the Current Position, and ComparisonSettings
        DataHelper dataHelper = new DataHelper(CompareJobForm.this);
        JobOffers jobs = new JobOffers(dataHelper);


        ArrayList<JobDetails> rankedJobList = jobs.getRankedList(CompareJobForm.this);
        ArrayList<String[]> detailArray = jobs.getJobDetailArray(rankedJobList, CompareJobForm.this);

        RecyclerView recyclerView = findViewById(R.id.jobDisplayRecycler);

        CompareJobsRecyclerViewAdapter jobsRecyclerViewAdapter = new CompareJobsRecyclerViewAdapter(this, detailArray);

        recyclerView.setAdapter(jobsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CompareJobForm.this,"Main Menu", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CompareJobForm.this, MainActivity.class));
            }
        });

       compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(CompareJobForm.this,"Compare Two Job Offers", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CompareJobForm.this, CompareTwoJobs.class);
                // Add logic to select values to pass to CompareJobs


                int job1;
                int job2;
                if(!jobID1.getText().toString().isEmpty() && !jobID2.getText().toString().isEmpty()){
                    try{
                        job1 = Integer.parseInt(jobID1.getText().toString());
                        job2 = Integer.parseInt(jobID2.getText().toString());
                        int max = detailArray.size();
                        if(job1 < 0 || job2 < 0 || job1 >= max || job2 >= max){
                            throw new NumberFormatException();
                        }

                        i.putExtra(CompareTwoJobs.JOBID_1, Integer.toString(job1));
                        i.putExtra(CompareTwoJobs.JOBID_2, Integer.toString(job2));
                        startActivity(i);
                    } catch(NumberFormatException e){
                        Toast.makeText(CompareJobForm.this, "Invaliid Job ID(s)",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CompareJobForm.this,"Please enter two valid JobIDs to compare", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}
