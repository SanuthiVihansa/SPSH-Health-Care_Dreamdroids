package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.spsh.spshhealthcare.database.PharmacyMaster;
import com.spsh.spshhealthcare.database.DBHelper;

public class pharmacist_search_page extends AppCompatActivity {

    ListView listView;
    String PharmID;
    EditText tv_pSearchView;
    String keyword;
    ArrayList<HashMap<String, String>> allPharmacyEquip;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_pharmisisit_search);

        PharmID = getIntent().getStringExtra("pharmacyID");
        tv_pSearchView = findViewById(R.id.tv_pSearchView);
        //Initialising the listview
        listView = findViewById(R.id.lv_PlistView);

        tv_pSearchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search();
            }
        });

        helper = new DBHelper(this);

        allPharmacyEquip = helper.pharmacistReadAllInfo(PharmID);

        ListAdapter listAdapter = new SimpleAdapter(pharmacist_search_page.this, allPharmacyEquip, R.layout.pharmacy_item_row, new String[]{PharmacyMaster.Pharmacy._ID, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME, PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE}, new int[]{R.id.tv_pharmIdTextView, R.id.tv_pharmaItemCode, R.id.tv_pharmItemName, R.id.tv_pharmUsage}) {

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
                        Intent intent = new Intent(pharmacist_search_page.this, Pharmacist_view_page.class);
                        intent.putExtra("PharamcyEquipID", pharmaEqID);
                        startActivity(intent);
                        finish();
                    }
                });

                return view;
            }

        };
        listView.setAdapter(listAdapter);
    }

    private void search() {
        this.keyword = this.tv_pSearchView.getText().toString();

        if(this.keyword.isEmpty())
            allPharmacyEquip = this.helper. pharmacistReadAllInfo(PharmID);
        else
            allPharmacyEquip = this.helper.searchItems(PharmID,keyword);

        ListAdapter adapter = new SimpleAdapter(pharmacist_search_page.this, allPharmacyEquip,  R.layout.pharmacy_item_row, new String[]{PharmacyMaster.Pharmacy._ID, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE, PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME, PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE}, new int[]{R.id.tv_pharmIdTextView, R.id.tv_pharmaItemCode, R.id.tv_pharmItemName, R.id.tv_pharmUsage}) {
            public View getView (int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                Button button = (Button) v.findViewById(R.id.btn_pharmaView);
                TextView textView = (TextView) v.findViewById(R.id.tv_pharmIdTextView);
                String pharmaEqID = textView.getText().toString();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(pharmacist_search_page.this, Pharmacist_view_page.class);
                        intent.putExtra("PharamcyEquipID", pharmaEqID);
                        startActivity(intent);
                        finish();
                    }
                });
                return v;
            }
        };

        listView.setAdapter(adapter);
    }



    public void backwardNavigation(View view) {
        Intent intent = new Intent(this, Pharmacist_view_allPharmacy_items.class);
        startActivity(intent);
        finish();
    }
}