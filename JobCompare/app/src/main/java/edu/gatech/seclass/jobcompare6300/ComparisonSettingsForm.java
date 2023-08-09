package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ComparisonSettingsForm extends AppCompatActivity {
    private EditText salary;
    private EditText bonus;
    private EditText stockOptions;
    private EditText leave;
    private EditText homeFund;
    private EditText wellnessFund;
    private ComparisonSettings compSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);

        Button backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.saveButton);

        salary = findViewById((R.id.salaryInput));
        bonus = findViewById((R.id.bonusInput));
        stockOptions = findViewById((R.id.stockOptionsInput));
        leave = findViewById((R.id.leaveTimeInput));
        homeFund = findViewById((R.id.homeProgramFundInput));
        wellnessFund = findViewById((R.id.wellnessFundInput));

        compSettings = new ComparisonSettings(ComparisonSettingsForm.this);

        // Prepopulate the fields
        salary.setText(String.format(Locale.US, "%d", compSettings.getAYS()));
        bonus.setText(String.format(Locale.US, "%d", compSettings.getAYB()));
        stockOptions.setText(String.format(Locale.US, "%d", compSettings.getCSO()));
        leave.setText(String.format(Locale.US, "%d", compSettings.getLT()));
        homeFund.setText(String.format(Locale.US, "%d", compSettings.getHBP()));
        wellnessFund.setText(String.format(Locale.US, "%d", compSettings.getWF()));


        backButton.setOnClickListener(view -> startActivity(new Intent(ComparisonSettingsForm.this, MainActivity.class)));

        saveButton.setOnClickListener(view -> {
            String salaryStr = salary.getText().toString();
            String bonusStr = bonus.getText().toString();
            String stockStr = stockOptions.getText().toString();
            String leaveStr = leave.getText().toString();
            String homeStr = homeFund.getText().toString();
            String wellnessStr = wellnessFund.getText().toString();

            boolean success = false;
            try{
                areInputsNumeric(salaryStr, bonusStr, stockStr, leaveStr, homeStr, wellnessStr);
                int annualSalary = Integer.parseInt(salaryStr);
                int annualBonus = Integer.parseInt(bonusStr);
                int stocks = Integer.parseInt(stockStr);
                int leaveDays = Integer.parseInt(leaveStr);
                int homeProgramFund = Integer.parseInt(homeStr);
                int wellness = Integer.parseInt(wellnessStr);

                success = compSettings.updateComparisonSettings(annualSalary, annualBonus, stocks, leaveDays, homeProgramFund, wellness);

            }catch(NumberFormatException e){
                Toast.makeText(ComparisonSettingsForm.this,"Please enter integer values >= 0", Toast.LENGTH_LONG).show();
            }

            Toast.makeText(ComparisonSettingsForm.this,success ? "Successfully saved the comparison settings" : "There was an error while saving, please try again", Toast.LENGTH_LONG).show();
        });
    }


    private void areInputsNumeric(String salary, String bonus, String stocks, String leave, String home, String wellness) throws NumberFormatException{
        if (salary == null || bonus == null || stocks == null || leave == null || home == null || wellness == null){
            throw new NumberFormatException();
        }

        try{
            int ays = Integer.parseInt(salary);
            int ayb = Integer.parseInt(bonus);
            int st = Integer.parseInt(stocks);
            int lv = Integer.parseInt(leave);
            int hbp = Integer.parseInt(home);
            int wp = Integer.parseInt(wellness);

            if (ays < 0 || ayb < 0 || st < 0 || lv < 0 || hbp < 0 || wp < 0){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
