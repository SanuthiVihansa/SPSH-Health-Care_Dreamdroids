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

import com.spsh.spshhealthcare.database.DBHelper;

public class Pharmacist_update_page extends AppCompatActivity {
    EditText et_ItemCode,et_pItemName,et_pProducerName,et_Usage,et_strength,et_manuDate,et_expDate,et_pprice,et_puQuantity,et_pdescription;
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

        DBHelper helper = new DBHelper(this);

        ArrayList pharmacyEquipments = helper.pharmacistOneSpecificInfo(Integer.parseInt(pharmaEqID));

        //Initialization of variables

        et_ItemCode=findViewById(R.id.et_puitemcode);
        et_pItemName=findViewById(R.id.et_puitemName);
        et_pProducerName=findViewById(R.id.et_puproducerName);
        et_Usage=findViewById(R.id.et_pusage);
        et_strength=findViewById(R.id.et_puStrenegth);
        et_manuDate=findViewById(R.id.et_uexpirationDate);
        et_expDate = findViewById(R.id.et_pumanufactureDate);
        et_pprice = findViewById(R.id.et_puUnintPrice);
        et_puQuantity = findViewById(R.id.et_puQuantity);
        et_pdescription = findViewById(R.id.et_puDescription);

        // Get the data from the data base and arrange in order
        et_ItemCode.setText((String)pharmacyEquipments.get(0));
        et_pItemName.setText((String)pharmacyEquipments.get(1));
        et_pProducerName.setText((String)pharmacyEquipments.get(2));
        et_Usage.setText((String)pharmacyEquipments.get(3));
        et_strength.setText((String)pharmacyEquipments.get(4));
        et_manuDate.setText((String)pharmacyEquipments.get(6));
        et_expDate.setText((String)pharmacyEquipments.get(5));
        et_pprice.setText((String)pharmacyEquipments.get(7));
        et_puQuantity.setText((String)pharmacyEquipments.get(8));
        et_pdescription.setText((String)pharmacyEquipments.get(9));

    }

    public void updataPharmacy(View view){

        DBHelper dbHelper = new DBHelper(this);

        String Itemcode = et_ItemCode.getText().toString();
        String Itemname = et_pItemName.getText().toString();
        String ProducerName = et_pProducerName.getText().toString();
        String Usage = et_Usage.getText().toString();
        int Strength = Integer.parseInt(et_strength.getText().toString());
        String ManuDate = et_manuDate.getText().toString();
        String ExpDate = et_expDate.getText().toString();
        Double Price = Double.parseDouble(et_pprice.getText().toString());
        int quantity = Integer.parseInt(et_puQuantity.getText().toString());
        String description = et_pdescription.getText().toString();

        if(Itemname.isEmpty()||ProducerName.isEmpty()||Usage.isEmpty()||ManuDate.isEmpty()||ExpDate.isEmpty()||Price.isNaN()||description.isEmpty()){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.pharmacistUpdate(Itemcode,Itemname,ProducerName,Usage,Strength,ManuDate,ExpDate,Price,quantity,description);

            Toast.makeText(this,"Successfully updated",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Pharmacist_view_allPharmacy_items.class);
            startActivity(intent);
        }

    }

    public void backwardNavigation(View view) {
        Intent intent = new Intent(this, Pharmacist_view_allPharmacy_items.class);
        startActivity(intent);
    }




}


