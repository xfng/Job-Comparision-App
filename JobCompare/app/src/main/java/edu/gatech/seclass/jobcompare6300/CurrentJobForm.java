package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;


public class CurrentJobForm extends AppCompatActivity {


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

    private CurrentJobDetails currentJobDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job_form);


        DataHelper db = new DataHelper(CurrentJobForm.this);
        currentJobDetails = new CurrentJobDetails(db);

        Button backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.saveButton);

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

        // Make sure to set the values of the EditTexts with the
        // actual values from the currentJobDetails
        JobDetails cjd = currentJobDetails.getCurrentJobDetails();
        if (cjd != null) {
            title.setText(cjd.getTitle());
            company.setText(cjd.getCompany());
            city.setText(cjd.getCity());
            state.setText(cjd.getState());
            salary.setText(String.format(Locale.US, "%d", cjd.getYearlySalary()));
            bonus.setText(String.format(Locale.US, "%d", cjd.getYearlyBonus()));
            stockOptions.setText(String.format(Locale.US, "%d", cjd.getNumStocks()));
            leave.setText(String.format(Locale.US, "%d", cjd.getLeaveTime()));
            homeFund.setText(String.format(Locale.US, "%d", cjd.getHomeBuyingFund()));
            wellnessFund.setText(String.format(Locale.US, "%d", cjd.getWellnessFund()));
        }

        backButton.setOnClickListener(view -> startActivity(new Intent(CurrentJobForm.this, MainActivity.class)));

        saveButton.setOnClickListener(view -> {
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
                        } catch(Exception e) {
                            intField.setError("This field must hold an integer");
                        }
                    }
                }

                int annualSalary = Integer.parseInt(salaryStr);
                int annualBonus = Integer.parseInt(bonusStr);
                int stocks = Integer.parseInt(stockStr);
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


                JobDetails jobDetails = new JobDetails(titleStr, companyStr, cityStr, stateStr, annualSalary, annualBonus, stocks, leaveDays, homeProgramFund, wellness);
                boolean success = currentJobDetails.updateCurrentDetails(jobDetails);

                Toast.makeText(CurrentJobForm.this, success ? "Successfully saved" :  "Error occurred while saving, please try again" , Toast.LENGTH_LONG).show();

                if (success) {
                    startActivity(new Intent(CurrentJobForm.this, MainActivity.class));
                }
            } catch(NumberFormatException e){
                Toast.makeText(CurrentJobForm.this, "Please enter integer values for the salary, bonus, stock, leave time, home and wellness funds", Toast.LENGTH_LONG).show();
            } catch(IllegalArgumentException e){
                Toast.makeText(CurrentJobForm.this, "Please check the errors in the fields", Toast.LENGTH_LONG).show();
            }

        });
    }
}
