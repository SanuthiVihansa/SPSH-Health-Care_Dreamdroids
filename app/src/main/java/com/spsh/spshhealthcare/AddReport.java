package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.database.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReport extends AppCompatActivity {

    // Variables declaration
    EditText patientName, age, nic, time, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet;
    TextView date, cost;
    RadioButton male, female;
    double initialCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_add_report);

        this.initialCost = 400.00;

        // Initialize variables
        this.patientName = findViewById(R.id.et_labelForPatientNameAddReport);
        this.age = findViewById(R.id.et_labelForPatientAgeAddReport);
        this.male = findViewById(R.id.radioBtn_maleAddReport);
        this.female = findViewById(R.id.radioBtn_femaleAddReport);
        this.nic = findViewById(R.id.et_labelForNicAddReport);
        this.date = findViewById(R.id.tv_dateAddReport);
        this.time = findViewById(R.id.et_timeAddReport);
        this.cost = findViewById(R.id.tv_costAddReport);
        this.hemoglobin = findViewById(R.id.et_labelForHimoglobinAddReport);
        this.wbc = findViewById(R.id.et_labelForWbcAddReport);
        this.neutrophils = findViewById(R.id.et_labelForNeutrophilsAddReport);
        this.lymphocytes = findViewById(R.id.et_labelForLymphocytesAddReport);
        this.eosinophils = findViewById(R.id.et_labelForEosinophilsAddReport);
        this.rbc = findViewById(R.id.et_labelForRbcAddReport);
        this.pcb = findViewById(R.id.et_labelForPcbAddReport);
        this.platelet = findViewById(R.id.et_labelForPlateletAddReport);

        // Get system date and set
        this.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        this.time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateCost();
            }
        });

    }

    public void onClickBtnSubmit(View view){
        String name = this.patientName.getText().toString();
        String age = this.age.getText().toString();
        String nic = this.nic.getText().toString();
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();

        String cost = this.cost.getText().toString();

        String hemoglobin = this.hemoglobin.getText().toString();
        String wbc = this.wbc.getText().toString();
        String neutrophils = this.neutrophils.getText().toString();
        double lymphocytes = Double.parseDouble(this.lymphocytes.getText().toString());
        double eosinophils = Double.parseDouble(this.eosinophils.getText().toString());
        double rbc = Double.parseDouble(this.rbc.getText().toString());
        double pcb = Double.parseDouble(this.pcb.getText().toString());
        int platelet = Integer.parseInt(this.platelet.getText().toString());
        String gender;

        if(male.isChecked()){
            gender = "Male";
        }
        else{
            gender = "Female";
        }

        if(name.isEmpty()) {
            this.patientName.setError("Name is required!");
        }
        else{

        }

        DBHelper dbHelper = new DBHelper(this);

        long added = dbHelper.addReport(name, Integer.parseInt(age), gender, nic, date, time, Double.parseDouble(cost), Double.parseDouble(hemoglobin), Integer.parseInt(wbc), Double.parseDouble(neutrophils), lymphocytes, eosinophils, rbc, pcb, platelet);

        if(added > 0){
            Toast.makeText(this, "Report Successfully Added !", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LabHome.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, LabHome.class);
        startActivity(intent);
    }

    public void calculateCost(){
        int time = 0;
        double cost = initialCost;
        if(this.time.getText().toString().length() > 1) {
            time = Integer.parseInt(this.time.getText().toString().substring(0, 2));

            if (time > 11){
                cost = cost * 2;
            }

            this.cost.setText(String.valueOf(cost));
        }
    }
}