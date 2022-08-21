package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);


    }

    public void onClickLoginBtn(View view){
        EditText et_usernameLogin = findViewById(R.id.et_usernameLogin);
        EditText et_passwordLogin = findViewById(R.id.et_passwordLogin);

        String username = et_usernameLogin.getText().toString();
        String password = et_passwordLogin.getText().toString();

        if(username.length()==0){
            et_usernameLogin.setError("This field is required!");
        }
        else if(password.length()==0){
            et_passwordLogin.setError("This field is required!");
        }
        else{
            //Lab login process
            if(username.equals("lab@spsh.lk") && password.equals("spshadmin")){
                Intent intent = new Intent(this, LabHome.class);
                startActivity(intent);
            }
            //Hospital login process
            else if (username.equals("hospital@spsh.lk") && password.equals("spshadmin")){

            }
            //Pharmacy login process
            else if(username.equals("pharmacy@spsh.lk") && password.equals("spshadmin")){

            }
            else{
                //patient login process
            }
        }
    }

    public void onClickSignupBtn(View view){
        Intent intent = new Intent(this, PatientSignup.class);
        startActivity(intent);
    }
}