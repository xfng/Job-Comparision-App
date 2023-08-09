package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EnterJobOffers extends AppCompatActivity {

    private EditText title;
    private EditText company;
    private EditText city;
    private EditText state;
    private EditText salary;
    private EditText bonus;
    private EditText stockOptions;
    private EditText leave;
    private EditText homeFund;
    private EditText wellnessFund;

    private JobOffers jobOffers;

    private CurrentJobDetails currentJobDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_job_offers);

        Button backButton = findViewById(R.id.backButton);
        Button addJobOfferButton = findViewById(R.id.addButton);
        Button compareCurrentJobButton = findViewById(R.id.compareWithCurrentJobButton);

        title = findViewById((R.id.titleInput));
        company = findViewById((R.id.companyInput));
        city = findViewById((R.id.cityInput));
        state = findViewById((R.id.stateInput));
        salary = findViewById((R.id.salaryInput));
        bonus = findViewById((R.id.bonusInput));
        stockOptions = findViewById((R.id.stockOptionsInput));
        leave = findViewById((R.id.leaveTimeInput));
        homeFund = findViewById((R.id.homeProgramFundInput));
        wellnessFund = findViewById((R.id.wellnessFundInput));

        DataHelper db = new DataHelper(EnterJobOffers.this);
        jobOffers = new JobOffers(db);
        currentJobDetails = new CurrentJobDetails(db);


        backButton.setOnClickListener(view -> startActivity(new Intent(EnterJobOffers.this, MainActivity.class)));

        // Disable comparing to current job
        compareCurrentJobButton.setEnabled(false);

        compareCurrentJobButton.setOnClickListener(view -> {

            if (currentJobDetails.getCurrentJobDetails() == null) {
                Toast.makeText(EnterJobOffers.this, "Current job details required to compare jobs", Toast.LENGTH_LONG).show();
                return;
            }

            JobDetails jobDetails = getJobDetailFromFields();

            Intent i = new Intent(EnterJobOffers.this, CompareTwoJobs.class);
            i.putExtra(CompareTwoJobs.JOBID_1, "CURRENT");
            i.putExtra(CompareTwoJobs.JOBID_2, "LAST");

            // We need a way to pass down the specific jobs we want to compare here
            startActivity(i);
        });

        addJobOfferButton.setOnClickListener(view -> {
            JobDetails jobDetails = getJobDetailFromFields();
            boolean success = false;
            if (jobDetails != null) {
                success = jobOffers.addJobOffer(jobDetails);
            }



            Toast.makeText(EnterJobOffers.this, success ? "Successfully saved" :  "Error occurred while saving, please try again" , Toast.LENGTH_LONG).show();
            if (success) {
                compareCurrentJobButton.setEnabled(true);
            }
        });
    }

    private void validateFields() {
        String salaryStr = salary.getText().toString();
        String leaveStr = leave.getText().toString();
        String homeStr = homeFund.getText().toString();
        String wellnessStr = wellnessFund.getText().toString();

        EditText[] textFields = new EditText[]{title, company, city, state};
        EditText[] intFields = new EditText[]{salary, bonus, stockOptions, leave, homeFund, wellnessFund};

        boolean hasError = false;

        // Validate the text fields
        for (EditText textField : textFields) {
            String textStr = textField.getText().toString();

            if (textStr.isEmpty()) {
                hasError = true;
                textField.setError("This field cannot be empty");
            }
        }

        // Validate the int fields
        for (EditText intField : intFields) {
            String intStr = intField.getText().toString();

            if (intStr.isEmpty()) {
                hasError = true;
                intField.setError("This field cannot be empty");
            } else {
                try {
                    Integer.parseInt(intStr);
                } catch (Exception e) {
                    intField.setError("This field must hold an integer");
                }
            }
        }

        int annualSalary = Integer.parseInt(salaryStr);
        int leaveDays = Integer.parseInt(leaveStr);
        int homeProgramFund = Integer.parseInt(homeStr);
        int wellness = Integer.parseInt(wellnessStr);

        // Validate the leave date
        if (leaveDays > 365) {
            leave.setError("Leave date cannot be greater than 365");
            hasError = true;
        }

        // Validate fund fields
        if (homeProgramFund > 0.15 * annualSalary) {
            homeFund.setError("The fund amount must be < 15% of the salary");
            hasError = true;
        }

        if (wellness > 5000) {
            wellnessFund.setError("The wellness fund must be > 0 and < 5000");
            hasError = true;
        }

        if (hasError) {
            throw new IllegalArgumentException();
        }
    }

    private JobDetails getJobDetailFromFields() {
        String titleStr = title.getText().toString();
        String companyStr = company.getText().toString();
        String cityStr = city.getText().toString();
        String stateStr = state.getText().toString();
        String salaryStr = salary.getText().toString();
        String bonusStr = bonus.getText().toString();
        String stockStr = stockOptions.getText().toString();
        String leaveStr = leave.getText().toString();
        String homeStr = homeFund.getText().toString();
        String wellnessStr = wellnessFund.getText().toString();

        try {
            validateFields();

            int annualSalary = Integer.parseInt(salaryStr);
            int annualBonus = Integer.parseInt(bonusStr);
            int stocks = Integer.parseInt(stockStr);
            int leaveDays = Integer.parseInt(leaveStr);
            int homeProgramFund = Integer.parseInt(homeStr);
            int wellness = Integer.parseInt(wellnessStr);

            return new JobDetails(titleStr, companyStr, cityStr, stateStr, annualSalary, annualBonus, stocks, leaveDays, homeProgramFund, wellness);
        } catch(NumberFormatException e){
            Toast.makeText(EnterJobOffers.this, "Please enter integer values for the salary, bonus, stock, leave time, home and wellness funds", Toast.LENGTH_LONG).show();
            return null;
        } catch(IllegalArgumentException e){
            Toast.makeText(EnterJobOffers.this, "Please check the errors in the fields", Toast.LENGTH_LONG).show();
            return null;
        }
    }


}
