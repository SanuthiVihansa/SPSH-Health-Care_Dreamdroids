package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

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

        Intent intent = new Intent(this, Pharmacist_addItem_page.class);
        startActivity(intent);
        finish();

    }

    public void pharmacistViewAll(View view) {

        Intent intent = new Intent(this, Pharmacist_view_allPharmacy_items.class);
        startActivity(intent);
        finish();

    }

    public void backBtn(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }


}