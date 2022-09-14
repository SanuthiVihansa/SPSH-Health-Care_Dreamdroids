package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        this.dbHelper = new DBHelper(this);
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
        //Lab login process
        else if(username.equals("lab@spsh.lk") && password.equals("spshadmin")){
            Intent intent = new Intent(this, LabHome.class);
            startActivity(intent);
        }
        //Hospital login process
        else if (username.equals("hospital@spsh.lk") && password.equals("spshadmin")){
            Intent intent = new Intent(this, LabHome.class);
            startActivity(intent);
        }
        //Pharmacy login process
        else if(username.equals("pharmacy@spsh.lk") && password.equals("spshadmin")){
            Intent intent = new Intent(this, LabHome.class);
            startActivity(intent);
        }
        else{
            //patient login process
            boolean isValidated = this.dbHelper.validateUser(username, password);

            if(isValidated == true) {
                Intent intent = new Intent(this, LabHome.class);
                intent.putExtra("nic", username);
                startActivity(intent);
            }
            else
                Toast.makeText(this, "Invalid Credentials !", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickSignupBtn(View view){
        Intent intent = new Intent(this, PatientSignup.class);
        startActivity(intent);
    }
}