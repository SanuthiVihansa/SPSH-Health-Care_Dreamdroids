package com.spsh.spshhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.database.AppointmentsMaster;
import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Doc_Profile extends AppCompatActivity {
    TextView TV8_Remaining_Appointments_Doc_Profile,tv2_Doc_Profile_Name, TV3_DOC_PROFILE_Speciality, tv4_Doc_Profile_WorkingPlace, tv5_Doc_Profile_Experience, tv6_Doc_Profile_Fee, tv7_DOC_PROFILE_MaxPat;

    private String docId;
    private String docName;

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
        TV8_Remaining_Appointments_Doc_Profile = findViewById(R.id.TV8_Remaining_Appointments_Doc_Profile);

        //Since its an arraylist --> pass the index within normal brackets.
        tv2_Doc_Profile_Name.setText("Doctor Name : " + (String) singleDocInfo.get(0));
        TV3_DOC_PROFILE_Speciality.setText("Doctor Speciality : " + (String) singleDocInfo.get(1));
        tv4_Doc_Profile_WorkingPlace.setText("Working Place : " + (String) singleDocInfo.get(2));
        tv5_Doc_Profile_Experience.setText("Experience : " + (String) singleDocInfo.get(3));
        tv6_Doc_Profile_Fee.setText("Fee : " + (String) singleDocInfo.get(4));
        tv7_DOC_PROFILE_MaxPat.setText("Maximum Patients : " + (String) singleDocInfo.get(5));

        docName = (String) singleDocInfo.get(0);
        TV8_Remaining_Appointments_Doc_Profile.setText("Remaining Appointments : " + (Integer.parseInt((String) singleDocInfo.get(5)) - dbHelper.totAppointments(docName)));


    }

    public void update(View view) {
        Intent intent = new Intent(Doc_Profile.this, Available_DOC.class);
        intent.putExtra("docId",docId);
        startActivity(intent);
    }

    public void deleteDoc(View view) {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteDoc(Integer.parseInt(this.docId));

        Toast.makeText(this, "Doctor deleted successfully.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Search_Doc.class);
        startActivity(intent);
    }


    /*
    //DELETE SINGLE DOCTOR.
    public void deleteDoc(View view){
        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String docId = intent.getStringExtra("docId");

        //building alert dialogue box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.view_single_dialog_message));

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deleteAppointment(docId);

                //Confirmation of deletion.
                Toast.makeText(Doc_Profile.this, "Doctor Deleted Successfully", Toast.LENGTH_SHORT).show();

               //Intent set to navigate to Search Doc page.
                Intent intent = new Intent(Doc_Profile.this, Search_Doc.class);
                //intent.putExtra("docId", Patient_View_Single.globalNic);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dialog box will disappear
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

*/
    public void back(View view) {
        Intent intent = new Intent(this,Search_Doc.class);
        startActivity(intent);
    }


    }
