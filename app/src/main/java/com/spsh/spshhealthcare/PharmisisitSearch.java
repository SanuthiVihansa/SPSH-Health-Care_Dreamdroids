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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import database.PharmacyMaster;
import database.DBHelper;

public class PharmisisitSearch extends AppCompatActivity {

    ListView listView;
    String PharmID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmisisit_search);

        PharmID = getIntent().getStringExtra("pharmacyID");
        //Initialising the listview
        listView = findViewById(R.id.lv_PlistView);

        DBHelper helper = new DBHelper(this);

        ArrayList<HashMap<String, String>> allPharmacyEquip = helper.readAllInfo(PharmID);

        ListAdapter listAdapter = new SimpleAdapter(PharmisisitSearch.this, allPharmacyEquip, R.layout.pharmacy_item_row, new String[]{PharmacyMaster.Pharmacy._ID, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME, PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE}, new int[]{R.id.tv_pharmIdTextView, R.id.tv_pharmaItemCode, R.id.tv_pharmItemName, R.id.tv_pharmUsage}) {
            //returns a view
            public View getView(int position, View convertView, ViewGroup parent) {
                //Pass the 3 parameters on top
                View view = super.getView(position, convertView, parent);
                //Displays the View button of a specific row.
                Button button = (Button) view.findViewById(R.id.btn_pharmaView);

                TextView textView = (TextView) view.findViewById(R.id.tv_pharmIdTextView);

                String pharmaEqID = textView.getText().toString();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PharmisisitSearch.this, PharmasistViewPage.class);
                        intent.putExtra("PharamcyEquipID", pharmaEqID);
                        startActivity(intent);
                    }
                });

                return view;
            }

        };
        listView.setAdapter(listAdapter);
    }
}