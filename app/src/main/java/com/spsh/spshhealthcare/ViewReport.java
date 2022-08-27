package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;

public class ViewReport extends AppCompatActivity {

    TextView tv_name, tv_age, tv_gender, tv_nic, tv_date, tv_time, tv_hemoglobin, tv_wbc, tv_neutrophils, tv_lymphocytes, tv_eosinophils, tv_rbc, tv_pcb, tv_platelet, tv_cost;
    int reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_view_report);

        Intent intent = getIntent();
        this.reportId = Integer.parseInt(intent.getStringExtra("reportId"));

        this.tv_name = findViewById(R.id.tv_nameViewBloodReport);
        this.tv_age = findViewById(R.id.tv_ageViewBloodReport);
        this.tv_gender = findViewById(R.id.tv_genderViewBloodReport);
        this.tv_nic = findViewById(R.id.tv_nicViewBloodReport);
        this.tv_date = findViewById(R.id.tv_dateViewBloodReport);
        this.tv_time = findViewById(R.id.tv_timeViewBloodReport);
        this.tv_hemoglobin = findViewById(R.id.tv_hemoglobinViewBloodReport);
        this.tv_wbc = findViewById(R.id.tv_totalWbcViewBloodReport);
        this.tv_neutrophils = findViewById(R.id.tv_neutrophilsViewBloodReport);
        this.tv_lymphocytes = findViewById(R.id.tv_lymphocytesViewBloodReport);
        this.tv_eosinophils = findViewById(R.id.tv_eosinophilsViewBloodReport);
        this.tv_rbc = findViewById(R.id.tv_rbcCountViewBloodReport);
        this.tv_pcb = findViewById(R.id.tv_pcbViewBloodReport);
        this.tv_platelet = findViewById(R.id.tv_plateletCountViewBloodReport);
        this.tv_cost = findViewById(R.id.tv_costViewBloodReport);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList reportDetails = dbHelper.getReport(reportId);

        this.tv_name.setText(": " + (String)reportDetails.get(0));
        this.tv_age.setText(": " + (String)reportDetails.get(1));
        this.tv_gender.setText(": " + (String)reportDetails.get(2));
        this.tv_nic.setText(": " + (String)reportDetails.get(3));
        this.tv_date.setText(": " + (String)reportDetails.get(4));
        this.tv_time.setText(": " + (String)reportDetails.get(5));
        this.tv_hemoglobin.setText(": " + (String)reportDetails.get(6) + "g");
        this.tv_wbc.setText(": " + (String)reportDetails.get(7) + " /cumm");
        this.tv_neutrophils.setText(": " + (String)reportDetails.get(8) + "%");
        this.tv_lymphocytes.setText(": " + (String)reportDetails.get(9) + "%");
        this.tv_eosinophils.setText(": " + (String)reportDetails.get(10) + "%");
        this.tv_rbc.setText(": " + (String)reportDetails.get(11) + " millioncells/mcl");
        this.tv_pcb.setText(": " + (String)reportDetails.get(12) + "%");
        this.tv_platelet.setText(": " + (String)reportDetails.get(13) + " /cumm");
        this.tv_cost.setText("  Rs." + (String)reportDetails.get(14) + ".00");

    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, ViewAllReports.class);
        startActivity(intent);
    }

    public void onClickBtnEdit(View view) {
        Intent intent = new Intent(this, UpdateReport.class);
        intent.putExtra("reportId", String.valueOf(this.reportId));
        startActivity(intent);
    }
}