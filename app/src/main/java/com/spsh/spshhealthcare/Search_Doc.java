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

import com.spsh.spshhealthcare.database.DBHelper;
import com.spsh.spshhealthcare.database.DoctorsMasters;

import java.util.ArrayList;
import java.util.HashMap;

public class Search_Doc extends AppCompatActivity {
    ListView docListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_search_doc);

        docListView = findViewById(R.id.listView_SearchDoc);
        //String docName = "Dr.Hilarina";
        //String docId = "D001";
        DBHelper dbHelper = new DBHelper(this);

        //Have to catch the Array List --> so create an array list.

        ArrayList<HashMap<String,String>> allDoc = dbHelper.readAllDocInfo();

        ListAdapter listAdapter = new SimpleAdapter(Search_Doc.this,allDoc,R.layout.doctor_row,new String[]{"_id","Doctor_Name","Speciality","Maximum_Patients"},new int[]{R.id.tv_doc_rowId,R.id.tv1_searchDoc_row_Name,R.id.tv2_searchDoc_row_Speciality,R.id.tv3_searchDoc_row_MaxPat}){
            //returns a view
            public View getView(int position, View convertView, ViewGroup parent){
                //Pass the 3 parameters on top
                View view = super.getView(position, convertView, parent);
               //Displays the View button of a specific row.
                Button button = (Button)view.findViewById(R.id.btn_row_view);

                //FETCHES THE ID OF THE INVISIBLE TEXT VIEW.
                TextView invId = (TextView) view.findViewById(R.id.tv_doc_rowId);

                String docId = invId.getText().toString();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Search_Doc.this,Doc_Profile.class);
                        intent.putExtra("docId",docId);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        docListView.setAdapter(listAdapter);
    }
}