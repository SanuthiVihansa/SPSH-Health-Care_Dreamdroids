package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import database.AppointmentsMaster;
import database.DBHelper;

public class Patient_All_Appointments extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_all_appointments);

        listView = findViewById(R.id.lv_allAppointments_sathira);

        String nic = "200045500065";
        DBHelper dbHelper = new DBHelper(this);

        ArrayList<HashMap<String,String>> allAppointments = dbHelper.readAllInfo(nic);
                                                                                                        //file that represents row
        ListAdapter listAdapter = new SimpleAdapter(Patient_All_Appointments.this,allAppointments,R.layout.appointment_row,new String[]{"Pname","date","doctorName","_id"},new int[]{R.id.tv_row_name_sathira,R.id.tv_row_date_sathira,R.id.tv_row_dName_sathira,R.id.tv_single_appointment_sathira}){
            public View getView(int position, View convertView, ViewGroup parent){ //fetches a view
                View view = super.getView(position, convertView, parent);

                Button button = (Button) view.findViewById(R.id.btn_row_view_sathira);//this is the "view button" from wireframe

                TextView textView = (TextView) view.findViewById(R.id.tv_single_appointment_sathira);    //fetches id of invisible textbox

                String appointmentID = textView.getText().toString();

                button.setOnClickListener(new View.OnClickListener() {
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
        startActivity(intent);
    }
}