package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class Pharmacist_view_allPharmacy_items extends AppCompatActivity {

    ImageView imageView2,imageView3,imageView4,imageView5;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        back = findViewById(R.id.btn_navigate);
        setContentView(R.layout.activity_view_all_pharamacy_items);

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

