package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Doc_Profile extends AppCompatActivity {
   TextView tv2_Doc_Profile_Name,TV3_DOC_PROFILE_Speciality,tv4_Doc_Profile_WorkingPlace,tv5_Doc_Profile_Experience,tv6_Doc_Profile_Fee,tv7_DOC_PROFILE_MaxPat;
   //ListView  singledocListView;
    private String docId;
   @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_doc_profile);
        Intent intent = getIntent();
        this.docId = intent.getStringExtra("docId");


        DBHelper dbHelper = new DBHelper(this);

        ArrayList singleDocInfo = dbHelper.viewSingleDocInfo(Integer.parseInt(docId));

       tv2_Doc_Profile_Name = findViewById(R.id.tv2_Doc_Profile_Name);
       TV3_DOC_PROFILE_Speciality = findViewById(R.id.TV3_DOC_PROFILE_Speciality);
       tv4_Doc_Profile_WorkingPlace = findViewById(R.id.tv4_Doc_Profile_WorkingPlace);
       tv5_Doc_Profile_Experience = findViewById(R.id.tv5_Doc_Profile_Experience);
       tv6_Doc_Profile_Fee = findViewById(R.id.tv6_Doc_Profile_Fee);
       tv7_DOC_PROFILE_MaxPat = findViewById(R.id.tv7_DOC_PROFILE_MaxPat);

       //Since its an arraylist --> pass the index within normal brackets.
       tv2_Doc_Profile_Name.setText("Doctor Name : " +(String)singleDocInfo.get(0));
       TV3_DOC_PROFILE_Speciality.setText("Doctor Speciality : " +(String)singleDocInfo.get(1));
       tv4_Doc_Profile_WorkingPlace.setText("Working Place : "+(String)singleDocInfo.get(2));
       tv5_Doc_Profile_Experience.setText("Experience : "+(String)singleDocInfo.get(3));
       tv6_Doc_Profile_Fee.setText("Fee : "+(String)singleDocInfo.get(4));
       tv7_DOC_PROFILE_MaxPat.setText("Maximum Patients : "+(String)singleDocInfo.get(5));

    }

    public void update(View view){
       Intent intent = new Intent(this,Available_DOC.class);
       startActivity(intent);
    }

    public void deleteDoc(View view){
       DBHelper dbHelper = new DBHelper(this);
       dbHelper.deleteDoc(Integer.parseInt(this.docId));

       Toast.makeText(this,"Doctor deleted successfully.", Toast.LENGTH_SHORT).show();
       Intent intent = new Intent(this,Search_Doc.class);
       startActivity(intent);
    }


}