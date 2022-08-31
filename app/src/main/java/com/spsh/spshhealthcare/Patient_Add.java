package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.DBHelper;

public class Patient_Add extends AppCompatActivity {

    EditText et_addName_sathira, et_addAge_sathira, et_addGender_sathira, et_addNum_sathira, et_addNic_sathira, et_addSpecial_sathira, et_addDrName_sathira, et_addDate_sathira, et_addTime_sathira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

        et_addName_sathira = findViewById(R.id.et_addName_sathira);
        et_addAge_sathira = findViewById(R.id.et_addAge_sathira);
        et_addGender_sathira = findViewById(R.id.et_addGender_sathira);
        et_addNum_sathira = findViewById(R.id.et_addNum_sathira);
        et_addNic_sathira  = findViewById(R.id.et_addNic_sathira);
        et_addSpecial_sathira = findViewById(R.id.et_addSpecial_sathira);
        et_addDrName_sathira = findViewById(R.id.et_addDrName_sathira);
        et_addDate_sathira = findViewById(R.id.et_addDate_sathira);
        et_addTime_sathira = findViewById(R.id.et_addTime_sathira);
    }

    public void saveAppointment(View view){
        String Pname = et_addName_sathira.getText().toString();
        String age = et_addAge_sathira.getText().toString();
        String gender = et_addGender_sathira.getText().toString();
        String contactNo = et_addNum_sathira.getText().toString();
        String nic = et_addNic_sathira.getText().toString();
        String specialization = et_addSpecial_sathira.getText().toString();
        String doctorName = et_addDrName_sathira.getText().toString();
        String date = et_addDate_sathira.getText().toString();
        String time = et_addTime_sathira.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if(Pname.isEmpty()||age.isEmpty()||gender.isEmpty()||contactNo.isEmpty()||nic.isEmpty()||specialization.isEmpty()||doctorName.isEmpty()||date.isEmpty()||time.isEmpty()){
            Toast.makeText(this, getResources().getText(R.string.toast_addPatient_emptyFields_sathira), Toast.LENGTH_SHORT).show();
        }else{
            int age2 = Integer.parseInt(age);
            long inserted = dbHelper.addInfo(Pname, age2, gender, contactNo, nic, specialization, doctorName, date, time);

            if(inserted>0){
                Toast.makeText(this, getResources().getText(R.string.toast_addPatient_success_sathira), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, getResources().getText(R.string.toast_addPatient_unsuccess_sathira), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //onClick method
    public void onClickBackBtn(View view){
        Intent intent = new Intent(this,Patient_Home.class);
        startActivity(intent);
    }
}