package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

    EditText patientName, age, nic, time, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet;
    TextView date, cost;
    RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_report);

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

        this.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    public void onClickBtnSubmit(View view){
        String name = this.patientName.getText().toString();
        int age = Integer.parseInt(this.age.getText().toString());
        String nic = this.nic.getText().toString();
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();

        double cost = Double.parseDouble(this.cost.getText().toString());

        double hemoglobin = Double.parseDouble(this.hemoglobin.getText().toString());
        int wbc = Integer.parseInt(this.wbc.getText().toString());
        double neutrophils = Double.parseDouble(this.neutrophils.getText().toString());
        double lymphocytes = Double.parseDouble(this.lymphocytes.getText().toString());
        double eosinophils = Double.parseDouble(this.eosinophils.getText().toString());
        double rbc = Double.parseDouble(this.rbc.getText().toString());
        double pcb = Double.parseDouble(this.pcb.getText().toString());
        int platelet = Integer.parseInt(this.platelet.getText().toString());
        String gender;

        if(male.isChecked()){
            gender = "male";
        }
        else{
            gender = "female";
        }

        DBHelper dbHelper = new DBHelper(this);

        long added = 1;
        //long added = dbHelper.addReport(name, age, gender, nic, date, time, cost, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet);

        if(added > 0){
            Toast.makeText(this, "Report Successfully Added !", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LabHome.class);
            startActivity(intent);

        }
        else{

        }

    }
}