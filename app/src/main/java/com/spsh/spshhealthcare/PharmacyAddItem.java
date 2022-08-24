package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class PharmacyAddItem extends AppCompatActivity {

    //Declaring attributes
    EditText et_ItemCode,et_pItemName,et_pProducerName,et_Usage,et_strength,et_manuDate,et_expDate,et_pprice,et_pdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen mode code

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmacy_add_item);

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

    public void AddItem (View view) {

        String Itemcode = et_ItemCode.getText().toString();
        String Itemname = et_pItemName.getText().toString();
        String ProducerName = et_pProducerName.getText().toString();
        String Usage = et_Usage.getText().toString();
        Integer Strength = Integer.parseInt(et_strength.getText().toString());
        String ManuDate = et_manuDate.getText().toString();
        String ExpDate = et_expDate.getText().toString();
        Double Price = Double.parseDouble(et_pprice.getText().toString());





    }
}