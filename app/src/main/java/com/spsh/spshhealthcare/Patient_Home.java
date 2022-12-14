package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Patient_Home extends AppCompatActivity {

    private String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen and orientation code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        this.nic = intent.getStringExtra("nic");
    }

    //Method 2 to create intents
    public void onClickViewAppointmentBtn(View view) {
        Intent intent = new Intent(this, Patient_All_Appointments.class);
        intent.putExtra("nic", this.nic);
        startActivity(intent);
        finish();
    }

    public void onClickMakeAppointmentBtn(View view) {
        Intent intent = new Intent(this, Patient_Add.class);
        intent.putExtra("nic", this.nic);
        startActivity(intent);
        finish();
    }

    public void onClickViewLabReportsBtn(View view){
        Intent intent = new Intent(this,Patient_View_Reports.class);
        intent.putExtra("nic", this.nic);
        startActivity(intent);
        finish();
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}