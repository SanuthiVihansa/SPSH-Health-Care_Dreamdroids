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

import java.util.ArrayList;
import java.util.HashMap;

import com.spsh.spshhealthcare.database.DBHelper;

public class Patient_All_Appointments extends AppCompatActivity {

    ListView listView;
    EditText editText;
    public static String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen and orientation code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //

        setContentView(R.layout.activity_patient_all_appointments);

        Intent intent = getIntent();

        editText = findViewById(R.id.et_allAppointments_SearchHint_sathria);

        //display all appointments
        listView = findViewById(R.id.lv_allAppointments_sathira);
        Patient_All_Appointments.nic = intent.getStringExtra("nic");
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<HashMap<String,String>> allAppointments = dbHelper.readAllAppointments(nic);
                                                                                                        //file that represents row
        ListAdapter listAdapter = new SimpleAdapter(Patient_All_Appointments.this,allAppointments,R.layout.appointment_row,new String[]{"Pname","date","doctorName","_id"},new int[]{R.id.tv_row_name_sathira,R.id.tv_row_date_sathira,R.id.tv_row_dName_sathira,R.id.tv_single_appointment_sathira}){
            public View getView(int position, View convertView, ViewGroup parent){ //fetches a view
                View view = super.getView(position, convertView, parent);
                Button button = (Button) view.findViewById(R.id.btn_row_view_sathira);  //this is the "view button" from wireframe
                TextView textView = (TextView) view.findViewById(R.id.tv_single_appointment_sathira);   //fetches id of invisible textbox
                String appointmentID = textView.getText().toString();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_All_Appointments.this,Patient_View_Single.class);
                        intent.putExtra("appointmentID",appointmentID);
                        intent.putExtra("nic", Patient_All_Appointments.nic);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        listView.setAdapter(listAdapter);
    } //display all appointments end

    public void searchAppointment(View view){
        listView = findViewById(R.id.lv_allAppointments_sathira);
        DBHelper dbHelper =  new DBHelper(this);
        String name = editText.getText().toString();
        ArrayList<HashMap<String,String>> searchedList = dbHelper.searchAppointmentByName(name);

        ListAdapter listAdapter = new SimpleAdapter(Patient_All_Appointments.this,searchedList,R.layout.appointment_row,new String[]{"Pname","date","doctorName","_id"},new int[]{R.id.tv_row_name_sathira,R.id.tv_row_date_sathira,R.id.tv_row_dName_sathira,R.id.tv_single_appointment_sathira}){
            public View getView(int position, View convertView, ViewGroup parent){ //fetches a view
                View view = super.getView(position, convertView, parent);
                Button button2 = (Button) view.findViewById(R.id.btn_row_view_sathira);//this is the "view button" from wireframe
                TextView textView = (TextView) view.findViewById(R.id.tv_single_appointment_sathira);    //fetches id of invisible textbox
                String appointmentID = textView.getText().toString();

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Patient_All_Appointments.this,Patient_View_Single.class);
                        intent.putExtra("appointmentID",appointmentID);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        listView.setAdapter(listAdapter);
    }

    public void onClickBackbtn(View view){
        Intent intent = new Intent(this,Patient_Home.class);
        intent.putExtra("nic", Patient_All_Appointments.nic);
        startActivity(intent);
    }
}