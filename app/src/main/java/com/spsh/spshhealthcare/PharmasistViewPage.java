package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import database.DBHelper;
import database.PharmacyMaster;

public class PharmasistViewPage extends AppCompatActivity {
     TextView tv_pvItemCodeD,tv_pvItemNameD,tv_pvProducerNameD,tv_pvUsageD,tv_pvStrengthD,tv_manuDateD,tv_pvexpirationdateD,tv_pvpriceD,tv_pvDescriptionD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen mode code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmasist_view_page);

        Intent intent = getIntent();
        String pharmaEqID = intent.getStringExtra("PharamcyEquipID");


        DBHelper helper = new DBHelper(this);

        ArrayList pharmacyEquipments = helper.oneSpecificInfo(Integer.parseInt(pharmaEqID));

        tv_pvItemCodeD = findViewById(R.id.tv_pvItemCodeD);
        tv_pvItemNameD = findViewById(R.id.tv_pvItemNameD);
        tv_pvProducerNameD = findViewById(R.id.tv_pvProducerNameD);
        tv_pvUsageD = findViewById(R.id.tv_pvUsageD);
        tv_pvStrengthD = findViewById(R.id.tv_pvStrengthD);
        tv_manuDateD = findViewById(R.id.tv_manuDateD);
        tv_pvexpirationdateD = findViewById(R.id.tv_pvexpirationdateD);
        tv_pvpriceD = findViewById(R.id.tv_pvpriceD);
        tv_pvDescriptionD = findViewById(R.id.tv_pvDescriptionD);


        tv_pvItemCodeD.setText((String)pharmacyEquipments.get(0));
        tv_pvItemNameD.setText((String)pharmacyEquipments.get(1));
        tv_pvProducerNameD.setText((String)pharmacyEquipments.get(2));
        tv_pvUsageD.setText((String)pharmacyEquipments.get(3));
        tv_pvStrengthD.setText((String)pharmacyEquipments.get(4));
        tv_manuDateD.setText((String)pharmacyEquipments.get(6));
        tv_pvexpirationdateD.setText((String)pharmacyEquipments.get(5));
        tv_pvpriceD.setText((String)pharmacyEquipments.get(7));
        tv_pvDescriptionD.setText((String)pharmacyEquipments.get(8));


    }
}