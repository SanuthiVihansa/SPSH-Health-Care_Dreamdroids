package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.Calendar;
import java.util.Date;


public class Patient_Add extends AppCompatActivity {

    EditText et_addName_sathira, et_addAge_sathira, et_addGender_sathira, et_addNum_sathira, et_addSpecial_sathira, et_addDrName_sathira, et_addDate_sathira, et_addTime_sathira;
    TextView tv_addNic_sathira;
    String nic2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen and orientation code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //

        setContentView(R.layout.activity_patient_add);

        Intent intent = getIntent();
//        this.nic2 = intent.getStringExtra("nic");

        this.nic2 = "200123456789";

        et_addName_sathira = findViewById(R.id.et_addName_sathira);
        et_addAge_sathira = findViewById(R.id.et_addAge_sathira);
        et_addGender_sathira = findViewById(R.id.et_addGender_sathira);
        et_addNum_sathira = findViewById(R.id.et_addNum_sathira);
        tv_addNic_sathira = findViewById(R.id.et_addNic_sathira);

        tv_addNic_sathira.setText(nic2);

        et_addSpecial_sathira = findViewById(R.id.et_addSpecial_sathira);
        et_addDrName_sathira = findViewById(R.id.et_addDrName_sathira);
        et_addDate_sathira = findViewById(R.id.et_addDate_sathira);
        et_addTime_sathira = findViewById(R.id.et_addTime_sathira);
    }

    public void saveAppointment(View view) {
        String Pname = et_addName_sathira.getText().toString();
        String age = et_addAge_sathira.getText().toString();
        String gender = et_addGender_sathira.getText().toString();
        String contactNo = et_addNum_sathira.getText().toString();
        String nic = tv_addNic_sathira.getText().toString();
        String specialization = et_addSpecial_sathira.getText().toString();
        String doctorName = et_addDrName_sathira.getText().toString();
        String date = et_addDate_sathira.getText().toString();
        String time = et_addTime_sathira.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if (Pname.isEmpty() || age.isEmpty() || gender.isEmpty() || contactNo.isEmpty() || nic.isEmpty() || specialization.isEmpty() || doctorName.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, getResources().getText(R.string.toast_addPatient_emptyFields_sathira), Toast.LENGTH_SHORT).show();
        }
//        else if(isNumeric(Pname, et_addName_sathira)){}
//        else if(isNumeric(gender, et_addGender_sathira)){}
//        else if(isNumeric(specialization, et_addSpecial_sathira)){}
//        else if(isNumeric(doctorName, et_addDrName_sathira)){}
        else if(!onlyLetters(Pname, et_addName_sathira)){}
        else if(!onlyLetters(gender, et_addGender_sathira)){}
        else if(!validateAge(age, et_addAge_sathira)){}
        else if(!onlyLetters(specialization, et_addSpecial_sathira)){}
        else if(!onlyLetters(doctorName, et_addDrName_sathira)){}

        else {
                int age2 = Integer.parseInt(age);
                long inserted = dbHelper.addAppointment(Pname, age2, gender, contactNo, nic, specialization, doctorName, date, time);

                if (inserted > 0) {
                    Toast.makeText(this, getResources().getText(R.string.toast_addPatient_success_sathira), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getResources().getText(R.string.toast_addPatient_unsuccess_sathira), Toast.LENGTH_SHORT).show();
                }
            }
        }

    //validation
    public boolean isNumeric(String string, EditText editText){ //checks if there are numbers
        boolean status = TextUtils.isDigitsOnly(string);
        if(status == true){
            editText.setError("Can't include numbers");
        }
        return status;
    }

    public boolean onlyLetters(String string, EditText editText){ //checks for letters
        boolean status = string.matches("[a-zA-Z]");   //NOT WORKING
        if(status == false){
            editText.setError("Can only include letters");
        }
        return status;
    }

    private boolean validateAge(String string, EditText editText) {
        int isAgeValid = Integer.parseInt(string);
        if (isAgeValid <= 0 || isAgeValid > 125) {
            editText.setError("Invalid age");
            return false;
        } else
            return true;
    }

    //onClick method
    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, Patient_Home.class);
        intent.putExtra("nic", this.nic2);
        startActivity(intent);
    }
}