package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import database.AppointmentsMaster;
import database.DBHelper;

public class Patient_Update extends AppCompatActivity {
    EditText et_update_name_sathira, et_update_age_sathira, et_update_gender_sathira, et_update_contactNum_sathira;
    String appointmentID, pname, age, gender, contactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen and orientation code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_patient_update);

        Intent intent = getIntent();
        DBHelper dbHelper = new DBHelper(this);

        appointmentID = intent.getStringExtra("appointmentID");

        ArrayList list = dbHelper.readAppointmentByID(appointmentID);

        //Settings values to editable text boxes
        pname = (String) list.get(0);
        et_update_name_sathira = findViewById(R.id.et_update_name_sathira);
        et_update_name_sathira.setText(pname);

        age = (String) list.get(1);
        et_update_age_sathira = findViewById(R.id.et_update_age_sathira);
        et_update_age_sathira.setText(age);

        gender = (String) list.get(2);
        et_update_gender_sathira = findViewById(R.id.et_update_gender_sathira);
        et_update_gender_sathira.setText(gender);

        contactNo = (String) list.get(3);
        et_update_contactNum_sathira = findViewById(R.id.et_update_contactNum_sathira);
        et_update_contactNum_sathira.setText(contactNo);
    }

    //update onclick event
    public void editAppointment(View view){
        pname = et_update_name_sathira.getText().toString();
        age = et_update_age_sathira.getText().toString();
        gender = et_update_gender_sathira.getText().toString();
        contactNo = et_update_contactNum_sathira.getText().toString();
        
        DBHelper dbHelper = new DBHelper(this);
        
        if(pname.isEmpty()||age.isEmpty()||gender.isEmpty()||contactNo.isEmpty()){
            Toast.makeText(this, getResources().getText(R.string.toast_addPatient_emptyFields_sathira), Toast.LENGTH_SHORT).show();
        }else {
            int age2 = Integer.parseInt(age);
            int inserted = dbHelper.updateAppointment(pname, age2, gender, contactNo, appointmentID);

            if(inserted > 0){
                Toast.makeText(this, getResources().getText(R.string.toast_updatePatient_success_sathira), Toast.LENGTH_SHORT).show();

                //intent
                onClickBackBtn(view);
//                Intent intent = new Intent(Patient_Update.this, Patient_View_Single.class);
//                intent.putExtra("appointmentID", appointmentID);
//                startActivity(intent);

            }else {
                Toast.makeText(this, getResources().getText(R.string.toast_addPatient_unsuccess_sathira), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickBackBtn(View view){
        Intent intent = new Intent(this, Patient_View_Single.class);
        intent.putExtra("appointmentID", appointmentID);
        startActivity(intent);
    }
}