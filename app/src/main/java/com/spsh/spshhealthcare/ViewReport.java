package com.spsh.spshhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;

public class ViewReport extends AppCompatActivity {

    // Variables declaration
    TextView tv_name, tv_age, tv_gender, tv_nic, tv_date, tv_time, tv_hemoglobin, tv_wbc, tv_neutrophils, tv_lymphocytes, tv_eosinophils, tv_rbc, tv_pcb, tv_platelet, tv_cost;
    int reportId;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_view_report);

        Intent intent = getIntent();
        this.reportId = Integer.parseInt(intent.getStringExtra("reportId"));

        // Initialize variables
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

        this.dbHelper = new DBHelper(this);
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
        finish();
    }

    public void onClickBtnEdit(View view) {
        Intent intent = new Intent(this, UpdateReport.class);
        intent.putExtra("reportId", String.valueOf(this.reportId));
        startActivity(intent);
        finish();
    }

    public void onClickBtnRemove(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove this report?");
        builder.setMessage("Are you sure you want to remove this report?\n\nNOTE : This cannot be undone.");
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int response = ViewReport.this.dbHelper.removeReport(ViewReport.this.reportId);
                if(response > 0) {
                    Toast.makeText(ViewReport.this, "Report Successfully Removed !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewReport.this, ViewAllReports.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Snackbar snackbar = Snackbar.make(view, "Something went wrong !", Snackbar.LENGTH_LONG);
                    snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                    snackbar.show();
                }
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
}