package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button currentJobFormButton;
    private Button jobOffersFormButton;
    private Button compareSettingsButton;
    private Button compareJobsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentJobFormButton = (Button) findViewById(R.id.navCurrentJobFormButton);
        jobOffersFormButton = (Button) findViewById(R.id.navJobOffersFormButton);
        compareSettingsButton = (Button) findViewById(R.id.navCompareSettingsButton);
        compareJobsButton = (Button) findViewById(R.id.navCompareJobsButton);

        DataHelper db = new DataHelper(MainActivity.this);

        currentJobFormButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CurrentJobForm.class)));

        jobOffersFormButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, EnterJobOffers.class)));

        compareSettingsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ComparisonSettingsForm.class)));

        compareJobsButton.setOnClickListener(view -> {
            int jobCount = 0;
            JobDetails details = db.getCurrentJob();
            if (details != null) {
                jobCount++;
            }
            ArrayList<JobDetails> jobOffersList = db.getJobOffersList();
            if (jobOffersList != null) {
                jobCount+=jobOffersList.size();
                }
            if (jobCount < 2) {
                Toast.makeText(getApplicationContext(),
                        "Enter Current Job and 1 Job Offer or 2 Job Offers",
                        Toast.LENGTH_LONG).show();
                return;
            }
            startActivity(new Intent(MainActivity.this, CompareJobForm.class));
        });
    }

}