package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.spsh.spshhealthcare.database.DBHelper;

public class Patient_Update extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText et_update_name_sathira, et_update_age_sathira, et_update_contactNum_sathira;
    String appointmentID, pname, age, gender, contactNo;
    String globalNic, spinnerGender;
    Spinner spinner;

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
        this.globalNic = intent.getStringExtra("nic");

        ArrayList list = dbHelper.readAppointmentByID(appointmentID);

        //Settings values to editable text boxes
        pname = (String) list.get(0);
        et_update_name_sathira = findViewById(R.id.et_update_name_sathira);
        et_update_name_sathira.setText(pname);

        age = (String) list.get(1);
        et_update_age_sathira = findViewById(R.id.et_update_age_sathira);
        et_update_age_sathira.setText(age);

        gender = (String) list.get(2);
        //spinner
        spinner = (Spinner) findViewById(R.id.sp_update_gender_sathira);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,
                R.array.sp_genders_sathira, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        contactNo = (String) list.get(3);
        et_update_contactNum_sathira = findViewById(R.id.et_update_contactNum_sathira);
        et_update_contactNum_sathira.setText(contactNo);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerGender = (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //validations
    //validation to check that only letters exist
    public boolean validateName(String string) { //checks for letters
        return string.matches("[a-z. A-Z]+");//NOT WORKING "[a-zA-Z]*" , "[a-zA-Z]+"
    }

    //validation for age
    public boolean validateAge(String string) {
        int isAgeValid = Integer.parseInt(string);
        if (isAgeValid <= 0 || isAgeValid > 125) {
            return false;
        } else {
            return true;
        }
    }

    //validation for contact number
    public boolean validateContactNo(String string) { //checks for letters
        if (string.length() == 10 && string.charAt(0) == '0') {
            return true;
        } else {
            return false;
        }
    }

    //update onclick event
    public void editAppointment(View view) {
        pname = et_update_name_sathira.getText().toString();
        age = et_update_age_sathira.getText().toString();
        gender = spinnerGender;
        contactNo = et_update_contactNum_sathira.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if (!validateName(pname)) {
            et_update_name_sathira.setError(getText(R.string.onlyLetters));
        } else if (!validateAge(age)) {
            et_update_age_sathira.setError(getText(R.string.validateAge));
        } else if (!validateContactNo(contactNo)) {
            et_update_contactNum_sathira.setError(getText(R.string.validateContactNo));
        }else if (pname.isEmpty() || age.isEmpty() || contactNo.isEmpty()) {
            Toast.makeText(this, getResources().getText(R.string.toast_addPatient_emptyFields_sathira), Toast.LENGTH_SHORT).show();
        } else {
            int age2 = Integer.parseInt(age);
            int inserted = dbHelper.updateAppointment(pname, age2, gender, contactNo, appointmentID);

            if (inserted > 0) {
                Toast.makeText(this, getResources().getText(R.string.toast_updatePatient_success_sathira), Toast.LENGTH_SHORT).show();

                //intent
                onClickBackBtn(view);
            } else {
                Toast.makeText(this, getResources().getText(R.string.toast_addPatient_unsuccess_sathira), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, Patient_View_Single.class);
        intent.putExtra("appointmentID", appointmentID);
        intent.putExtra("nic", Patient_View_Single.globalNic);
        startActivity(intent);
        finish();
    }
}