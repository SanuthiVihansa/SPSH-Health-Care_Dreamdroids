package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import database.DBHelper;

public class PharmacisitUpdate extends AppCompatActivity {
    EditText et_ItemCode,et_pItemName,et_pProducerName,et_Usage,et_strength,et_manuDate,et_expDate,et_pprice,et_pdescription;
    private String pharmaEqID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmacisit_update);

        Intent intent = getIntent();
        this.pharmaEqID = intent.getStringExtra("PharamcyEquipID");


        //Initialization of variables

        et_ItemCode=findViewById(R.id.et_ItemCode);
        et_pItemName=findViewById(R.id.et_pItemName);
        et_pProducerName=findViewById(R.id.et_pProducerName);
        et_Usage=findViewById(R.id.et_Usage);
        et_strength=findViewById(R.id.et_strength);
        et_manuDate=findViewById(R.id.et_manuDate);
        et_expDate = findViewById(R.id.et_expDate);
        et_pprice = findViewById(R.id.et_pprice);
        et_pdescription = findViewById(R.id.et_pdescription);


    }

}


