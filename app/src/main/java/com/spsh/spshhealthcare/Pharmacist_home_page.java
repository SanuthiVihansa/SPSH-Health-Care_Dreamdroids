package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import database.DBHelper;

public class Pharmacist_home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen mode code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmisist_home);

    }



    public void pharmacistAddItem (View view) {

        Intent intent = new Intent(this,PharmacyAddItem.class);
        startActivity(intent);

    }

    public void pharmacistViewAll(View view) {

        Intent intent = new Intent(this,ViewAllPharamacyItems.class);
        startActivity(intent);

    }


}