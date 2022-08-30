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

import java.util.ArrayList;

public class UpdateReport extends AppCompatActivity {

    EditText name, age, nic, time, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet;
    RadioButton male, female;
    TextView date, cost;
    int reportId;
    String gender;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_update_report);

        Intent intent = getIntent();
        this.reportId = Integer.parseInt(intent.getStringExtra("reportId"));

        this.dbHelper = new DBHelper(this);
        ArrayList reportDetails = dbHelper.getReport(reportId);

        this.name = findViewById(R.id.et_labelForPatientNameUpdateReport);
        this.age = findViewById(R.id.et_labelForPatientAgeUpdateReport);
        this.male = findViewById(R.id.radioBtn_maleUpdateReport);
        this.female = findViewById(R.id.radioBtn_femaleUpdateReport);
        this.nic = findViewById(R.id.et_labelForNicUpdateReport);
        this.date = findViewById(R.id.tv_dateUpdateReport);
        this.cost = findViewById(R.id.tv_costUpdateReport);
        this.time = findViewById(R.id.et_timeUpdateReport);
        this.hemoglobin = findViewById(R.id.et_labelForHimoglobinUpdateReport);
        this.wbc = findViewById(R.id.et_labelForWbcUpdateReport);
        this.neutrophils = findViewById(R.id.et_labelForNeutrophilsUpdateReport);
        this.lymphocytes = findViewById(R.id.et_labelForLymphocytesUpdateReport);
        this.eosinophils = findViewById(R.id.et_labelForEosinophilsUpdateReport);
        this.rbc = findViewById(R.id.et_labelForRbcUpdateReport);
        this.pcb = findViewById(R.id.et_labelForPcbUpdateReport);
        this.platelet = findViewById(R.id.et_labelForPlateletUpdateReport);

        this.name.setText((String)reportDetails.get(0));
        this.age.setText((String)reportDetails.get(1));
        this.gender = (String)reportDetails.get(2);
        this.nic.setText((String)reportDetails.get(3));
        this.date.setText((String)reportDetails.get(4));
        this.time.setText((String)reportDetails.get(5));
        this.hemoglobin.setText((String)reportDetails.get(6));
        this.wbc.setText((String)reportDetails.get(7));
        this.neutrophils.setText((String)reportDetails.get(8));
        this.lymphocytes.setText((String)reportDetails.get(9));
        this.eosinophils.setText((String)reportDetails.get(10));
        this.rbc.setText((String)reportDetails.get(11));
        this.pcb.setText((String)reportDetails.get(12));
        this.platelet.setText((String)reportDetails.get(13));
        this.cost.setText((String)reportDetails.get(14) + ".00");

        if(this.gender.equals("Male")){
            this.male.setChecked(true);
        }
        else{
            this.female.setChecked(true);
        }


    }

    public void onClickBtnUpdate(View view){
        String name = this.name.getText().toString();
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
        double pcb = Double.parseDouble(this.rbc.getText().toString());
        int platelet = Integer.parseInt(this.platelet.getText().toString());

        if(this.male.isChecked()){
            this.gender = "Male";
        }
        else if(this.female.isChecked()){
            this.gender = "Female";
        }

        int response = this.dbHelper.updateReport(this.reportId, name, age, this.gender, nic, date, time, cost, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet);

        if(response > 0){
            Toast.makeText(this, "Report Details Updated !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewAllReports.class);
            startActivity(intent);
        }
        else{
            Snackbar snackbar = Snackbar.make(view, "Update Failed !", Snackbar.LENGTH_LONG);
            snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
            snackbar.show();
        }

    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, ViewReport.class);
        intent.putExtra("reportId", String.valueOf(this.reportId));
        startActivity(intent);
    }
}