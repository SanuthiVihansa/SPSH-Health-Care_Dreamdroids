package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

public class Pharmacist_addItem_page extends AppCompatActivity {

    //Declaring attributes
    EditText et_ItemCode, et_pItemName, et_pProducerName, et_Usage, et_strength, et_manuDate, et_expDate, et_pprice, et_pQuantity, et_pdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen mode code

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmacy_add_item);

        //Initialization of variables

        et_ItemCode = findViewById(R.id.et_ItemCode);
        et_pItemName = findViewById(R.id.et_pItemName);
        et_pProducerName = findViewById(R.id.et_pProducerName);
        et_Usage = findViewById(R.id.et_Usage);
        et_strength = findViewById(R.id.et_strength);
        et_manuDate = findViewById(R.id.et_manuDate);
        et_expDate = findViewById(R.id.et_expDate);
        et_pprice = findViewById(R.id.et_pprice);
        et_pQuantity = findViewById(R.id.et_ppQuantity);
        et_pdescription = findViewById(R.id.et_pdescription);
    }

    public void AddItem(View view) {

        String Itemcode = et_ItemCode.getText().toString();
        String Itemname = et_pItemName.getText().toString();
        String ProducerName = et_pProducerName.getText().toString();
        String Usage = et_Usage.getText().toString();
        String Strength = et_strength.getText().toString();
        String ManuDate = et_manuDate.getText().toString();
        String ExpDate = et_expDate.getText().toString();
        String Price = et_pprice.getText().toString();
        String Quantity =et_pQuantity.getText().toString();
        String description = et_pdescription.getText().toString();

        DBHelper dbHelper = new DBHelper(this);



        if (checkAllFields() == true) {
            long inserted = dbHelper.pharmacistAddInfo(Itemcode, Itemname, ProducerName, Usage, Integer.valueOf(Strength), ManuDate, ExpDate, Double.valueOf(Price), Integer.valueOf(Quantity), description);
            if (inserted > 0) {
                Toast.makeText(this, "Data successfully added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Data insertion failed " + inserted, Toast.LENGTH_SHORT).show();
            }
        }


    }

    public boolean checkAllFields() {
        //ItemCode check
        if (et_ItemCode.length() != 5) {
            et_ItemCode.setError("Invalid ItemCode");
            return false;
        }
        else if (!(et_ItemCode.getText().toString().startsWith("P") || et_ItemCode.getText().toString().startsWith("M") || et_ItemCode.getText().toString().startsWith("S") || et_ItemCode.getText().toString().startsWith("C"))) {
            et_ItemCode.setError("Invalid ItemCode");
            return false;
        }
        //ItemName check
        else if (et_pItemName.length() == 0) {
            et_pItemName.setError("This field is required");
            return false;
        }
        //ProducerName check
        else if (et_pProducerName.length() == 0) {
            et_pProducerName.setError("This field is required");
            return false;
        }
        //Usage check
        else if (et_Usage.length() == 0) {
            et_Usage.setError("This field is required");
            return false;
        }
        //Manufacture Date check
        else if (et_manuDate.length() == 0) {
            et_manuDate.setError("This field is required");
            return false;
        }
        //Expiration date check
        else if (et_expDate.length() == 0) {
            et_expDate.setError("This field is required");
            return false;
        }
        //Description check
        else if (et_pdescription.length() == 0) {
            et_pdescription.setError("This field is required");
            return false;
        }
        else{
            return true;
        }
    }

    public void backwardNavigation(View view) {
        Intent intent = new Intent(this, Pharmacist_home_page.class);
        startActivity(intent);
        finish();
    }
}