package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Pharmacist_view_allPharmacy_items extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmacist_view_all_pharmacy_items);
    }

    public void PharmacyEquipment(View view) {

        Intent intent = new Intent (this, pharmacist_search_page.class);
        intent.putExtra("pharmacyID","P");
        startActivity(intent);

    }

    public void prescriptionMedicine(View view) {
        Intent intent = new Intent( this, pharmacist_search_page.class);
        intent.putExtra("pharmacyID","M");
        startActivity(intent);
    }

    public void Suppliments(View view) {
        Intent intent = new Intent( this, pharmacist_search_page.class);
        intent.putExtra("pharmacyID","S");
        startActivity(intent);
    }

    public void OverTheCounterMedicine(View view) {
        Intent intent = new Intent( this, pharmacist_search_page.class);
        intent.putExtra("pharmacyID","C");
        startActivity(intent);
    }

    public void bakwardNavigation (View view){
        Intent intent = new Intent(this, Pharmacist_home_page.class);
        startActivity(intent);
    }
}