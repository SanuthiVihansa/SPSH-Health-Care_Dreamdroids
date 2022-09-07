package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Patient_View_Single extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_single);

        Intent intent = getIntent();

        String appointmentID = intent.getStringExtra("appointmentID");
        TextView textView = findViewById(R.id.tv_single_id2_sathira);
        textView.setText(appointmentID);
    }

    public void onClickBackBtn(View view){
        Intent intent = new Intent(this,Patient_All_Appointments.class);
        startActivity(intent);
    }

    public void onClickUpdateBtn(View view){
        Intent intent = new Intent(this,Patient_Update.class);
        startActivity(intent);
    }

//    public void onClickDeleteBtn(View view){
//        Intent intent = new Intent();
//        startActivity(intent);
//    }
}