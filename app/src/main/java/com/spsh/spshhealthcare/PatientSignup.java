package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatientSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_patient_signup);
    }

    public void onClickSignupBtn(View view){
        EditText et_nameSignup = findViewById(R.id.et_nameSignup);
        EditText et_nicSignup = findViewById(R.id.et_nicSignup);
        EditText et_contactNoSignup = findViewById(R.id.et_contactNoSignup);
        EditText et_passwordSignup = findViewById(R.id.et_passwordSignup);
        CheckBox chkBox_signup = findViewById(R.id.chkBox_signup);

        String name = et_nameSignup.getText().toString();
        String nic = et_nicSignup.getText().toString();
        String contactNo = et_contactNoSignup.getText().toString();
        String password = et_passwordSignup.getText().toString();
        DBHelper dbHelper = new DBHelper(this);

        if(name.length()==0){
            et_nameSignup.setError("Name is required!");
        }
        else if((nic.length()==10&&nic.endsWith("V")) || nic.length()==12){
            if(contactNo.length()==10 || (contactNo.length()==12 && contactNo.startsWith("+"))){
                if(password.length() < 8){
                    et_passwordSignup.setError("Password length should be more than 8.");
                }
                else{
                    if(!chkBox_signup.isChecked()){
                        Context context = getApplicationContext();
                        CharSequence text = "You must agree to our terms and conditions before registering!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else{
                        //send to the database
                        List patients = dbHelper.getPatient(nic);
                        if(patients.isEmpty()){
                            long registered = dbHelper.addToPatients(name, nic, contactNo, password);
                            Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(this, "Sorry! There is an existing account.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            else{
                et_contactNoSignup.setError("Contact number must contain 10 digits or '+' sign and 12 digits");
            }
        }
        else{
            et_nicSignup.setError("NIC must contain 9 digits and 'V' or 12 digits!");
        }
    }
}