package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LabHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_lab_home);
    }

    public void onClickViewAllLabReportBtn(View view){
        Intent intent = new Intent(this, ViewAllReports.class);
        startActivity(intent);
        finish();
    }

    public void onClickMakeReportBtn(View view){
        Intent intent = new Intent(this, AddReport.class);
        startActivity(intent);
        finish();
    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}