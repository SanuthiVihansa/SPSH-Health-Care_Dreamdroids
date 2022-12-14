package com.spsh.spshhealthcare;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.spsh.spshhealthcare.database.DBHelper;

public class Pharmacist_view_page extends AppCompatActivity {
    TextView tv_pvItemCodeD, tv_pvItemNameD, tv_pvProducerNameD, tv_pvUsageD, tv_pvStrengthD, tv_manuDateD, tv_pvexpirationdateD, tv_pvpriceD, tv_pvQuantityD, tv_pvDescriptionD,tv_TotalCost;
    private String pharmaEqID;
    ArrayList pharmacyEquipments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen mode code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmasist_view_page);

        Intent intent = getIntent();
        this.pharmaEqID = intent.getStringExtra("PharamcyEquipID");


        DBHelper helper = new DBHelper(this);

        pharmacyEquipments = helper.pharmacistOneSpecificInfo(Integer.parseInt(pharmaEqID));

        tv_pvItemCodeD = findViewById(R.id.tv_pvItemCodeD);
        tv_pvItemNameD = findViewById(R.id.tv_pvItemNameD);
        tv_pvProducerNameD = findViewById(R.id.tv_pvProducerNameD);
        tv_pvUsageD = findViewById(R.id.tv_pvUsageD);
        tv_pvStrengthD = findViewById(R.id.tv_pvStrengthD);
        tv_manuDateD = findViewById(R.id.tv_manuDateD);
        tv_pvexpirationdateD = findViewById(R.id.tv_pvexpirationdateD);
        tv_pvpriceD = findViewById(R.id.tv_pvpriceD);
        tv_pvQuantityD = findViewById(R.id.tv_pvQuantityD);
        tv_pvDescriptionD = findViewById(R.id.tv_pvDescriptionD);
        tv_TotalCost = findViewById(R.id.tv_TotalCost);


        tv_pvItemCodeD.setText((String)pharmacyEquipments.get(0));
        tv_pvItemNameD.setText((String)pharmacyEquipments.get(1));
        tv_pvProducerNameD.setText((String)pharmacyEquipments.get(2));
        tv_pvUsageD.setText((String)pharmacyEquipments.get(3));
        tv_pvStrengthD.setText((String)pharmacyEquipments.get(4));
        tv_manuDateD.setText((String)pharmacyEquipments.get(6));
        tv_pvexpirationdateD.setText((String)pharmacyEquipments.get(5));
        tv_pvpriceD.setText((String)pharmacyEquipments.get(7));
        tv_pvQuantityD.setText((String)pharmacyEquipments.get(8));
        tv_pvDescriptionD.setText((String)pharmacyEquipments.get(9));

        int x;
        double ans;
        double y ;
        x = Integer.parseInt(tv_pvQuantityD.getText().toString());
        y = Double.parseDouble(tv_pvpriceD.getText().toString());
        ans = x * y ;
        tv_TotalCost.setText(String.valueOf(ans));
    }
    public void pharmacistDeleteItem(View view) {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.pharmacistDeleteInfo(Integer.parseInt(this.pharmaEqID));

        Toast.makeText(this,  "Pharmacy equipment is deleted", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Pharmacist_view_page.this, Pharmacist_view_allPharmacy_items.class);
        startActivity(intent);
        finish();

    }

        public void updatePharmacyEquip(View view) {

        Intent intent = new Intent(this, Pharmacist_update_page.class);
        intent.putExtra("PharamcyEquipID",pharmaEqID);
        startActivity(intent);
        finish();
        }

        public void backwardNavigation(View view) {
            Intent intent = new Intent(this, pharmacist_search_page.class);
            intent.putExtra("pharmacyID",((String)pharmacyEquipments.get(0)).substring(0, 1));
            startActivity(intent);
            finish();
        }


    }
