package com.spsh.spshhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.spsh.spshhealthcare.database.DBHelper;

public class Patient_View_Single extends AppCompatActivity {

    TextView textView;
    TextView tv_single_appointment_sathira;
    String appointmentID;
    public static String globalNic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen and orientation code
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //

        setContentView(R.layout.activity_patient_view_single);

        Intent intent = getIntent();  //what does this do?
        DBHelper dbHelper = new DBHelper(this);
        tv_single_appointment_sathira = findViewById(R.id.tv_single_appointment_sathira);
        textView = findViewById(R.id.tv_single_id2_sathira);
        appointmentID = intent.getStringExtra("appointmentID");

        Patient_View_Single.globalNic = intent.getStringExtra("nic");

        tv_single_appointment_sathira.setText(getResources().getString(R.string.tv_single_appointment_sathira));
        textView.setText(appointmentID);
        ArrayList list = dbHelper.readAppointmentByID(appointmentID);

        //fetching and setting data to text views
        String pname = (String) list.get(0);
        textView = findViewById(R.id.tv_single_name2_sathira);
        textView.setText(pname);

        String age = (String) list.get(1);
        textView = findViewById(R.id.tv_single_age2_sathira);
        textView.setText(age);

        String gender = (String) list.get(2);
        textView = findViewById(R.id.tv_single_gender2_sathira);
        textView.setText(gender);

        String contactNo = (String) list.get(3);
        textView = findViewById(R.id.tv_single_number2_sathira);
        textView.setText(contactNo);

        String NIC = (String) list.get(4);
        textView = findViewById(R.id.tv_single_nic2_sathira);
        textView.setText(NIC);

        String drName = (String) list.get(5);
        textView = findViewById(R.id.tv_single_drName2_sathira);
        textView.setText(drName);

        String date = (String) list.get(6);
        textView = findViewById(R.id.tv_single_date2_sathira);
        textView.setText(date);

        String time = (String) list.get(7);
        textView = findViewById(R.id.tv_single_time2_sathira);
        textView.setText(time);

        String docCost = dbHelper.retrieveCost(drName);
        String finalCost = costCalc(docCost, time);
        textView = findViewById(R.id.tv_single_cost2_sathira);
        textView.setText("Rs. " + finalCost);
    }

    public String costCalc(String DocFee, String appointmentTime){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String finalCost = null;
        double taxRate = 0.1, taxAmount = 0.0, feeDoc = 0.0, lateNightAndEarlyMorningFee = 0, doubleCost = 0.0;
        int time = 0;

        feeDoc = Double.parseDouble(DocFee);
        taxAmount = feeDoc * taxRate;

        if (appointmentTime.length() == 4){
            time = Integer.parseInt(appointmentTime.substring(0,1));
            if (time == 0 || time <= 6)
                lateNightAndEarlyMorningFee = 0.5 * feeDoc;
            else
                lateNightAndEarlyMorningFee = 0;
        }else if(appointmentTime.length() == 5){
            time = Integer.parseInt(appointmentTime.substring(0,2));
            if (time >= 21 || time <= 6)
                lateNightAndEarlyMorningFee = 0.5 * feeDoc;
            else
                lateNightAndEarlyMorningFee = 0;
        }

        doubleCost = taxAmount + lateNightAndEarlyMorningFee + feeDoc;
        finalCost = String.valueOf(decimalFormat.format(doubleCost));

        return finalCost;
    }

    public void onClickUpdateBtn(View view) { //method to navigate to update activity
        Intent intent = new Intent(this, Patient_Update.class);
        intent.putExtra("appointmentID", appointmentID);
        intent.putExtra("nic", Patient_View_Single.globalNic);
        startActivity(intent);
        finish();
    }

    //delete method
    public void OnClickDeleteBtn(View view) {
        DBHelper dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String appointmentID = intent.getStringExtra("appointmentID");

        //building alert dialogue box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.view_single_dialog_message));

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deleteAppointment(appointmentID);

                //toast message
                Toast.makeText(Patient_View_Single.this, R.string.toast_single_successDeleteMsg_sathira, Toast.LENGTH_SHORT).show();

                //intent - navigates to all appointments page
                Intent intent = new Intent(Patient_View_Single.this, Patient_All_Appointments.class);
                intent.putExtra("nic", Patient_View_Single.globalNic);
                startActivity(intent);
                finish();
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

    public void onClickBackBtn(View view) {
        Intent intent = new Intent(this, Patient_All_Appointments.class);
        intent.putExtra("nic", Patient_View_Single.globalNic);
        startActivity(intent);
        finish();
    }
}