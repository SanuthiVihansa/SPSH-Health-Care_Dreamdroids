package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.spsh.spshhealthcare.database.AppointmentsMaster;
import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Search_Doc extends AppCompatActivity {
    ListView docListView;
    //String docName;

    EditText et_SearchDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_search_doc);

        et_SearchDoc = findViewById(R.id.et_SearchDoc);
        docListView = findViewById(R.id.listView_SearchDoc);

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
                        finish();
                    }
                });
                return view;
            }
        };
        docListView.setAdapter(listAdapter);
    }

    public void Search(View view){
        docListView = findViewById(R.id.listView_SearchDoc);
        DBHelper dbhelper = new DBHelper(this);
        //initialization of the Search Edit Text.

        String searchDoc = et_SearchDoc.getText().toString();

        ArrayList<HashMap<String,String>> docSearched = dbhelper.searchDocInfo(searchDoc);

        ListAdapter searchlist = new SimpleAdapter(Search_Doc.this,docSearched,R.layout.doctor_row,new String[]{"_id","Doctor_Name","Speciality","Maximum_Patients"},new int[]{R.id.tv_doc_rowId,R.id.tv1_searchDoc_row_Name,R.id.tv2_searchDoc_row_Speciality,R.id.tv3_searchDoc_row_MaxPat}) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Button btn = (Button)view.findViewById(R.id.btn_row_view);

                TextView invId = (TextView) view.findViewById(R.id.tv_doc_rowId);
                String docId = invId.getText().toString();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Search_Doc.this, Doc_Profile.class);
                        intent.putExtra("docName", searchDoc);
                        startActivity(intent);
                        finish();
                    }
                });
                return view;
            }

        };
        docListView.setAdapter(searchlist);
    }

    public void back(View view){
        Intent intent = new Intent(this,Doc_Home.class);
        startActivity(intent);
        finish();
    }



}