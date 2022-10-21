package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;

public class Available_DOC extends AppCompatActivity {
    private String docId;
    EditText et2_UPDATEDOC_Name,et3_UPDATEDOC_Speciality,et4_UPDATEDOC_WorkingPlace,et5_UPDATEDOC_Experience,et6_UPDATEDDOC_fee,numtxt_UPDATEDOC_MaxPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_available_doc);

        Intent intent = getIntent();
        this.docId = intent.getStringExtra("docId");

        DBHelper dbHelper = new DBHelper(this);

        ArrayList singleDocInfo = dbHelper.viewSingleDocInfo(Integer.parseInt(docId));

        et2_UPDATEDOC_Name = findViewById(R.id.et2_UPDATEDOC_Name);
        et3_UPDATEDOC_Speciality = findViewById(R.id.et3_UPDATEDOC_Speciality);
        et4_UPDATEDOC_WorkingPlace = findViewById(R.id.et4_UPDATEDOC_WorkingPlace);
        et5_UPDATEDOC_Experience = findViewById(R.id.et5_UPDATEDOC_Experience);
        et6_UPDATEDDOC_fee = findViewById(R.id.et6_UPDATEDDOC_fee);
        numtxt_UPDATEDOC_MaxPatients = findViewById(R.id.numtxt_UPDATEDOC_MaxPatients);

        et2_UPDATEDOC_Name.setText((String) singleDocInfo.get(0));
        et3_UPDATEDOC_Speciality.setText((String) singleDocInfo.get(1));
        et4_UPDATEDOC_WorkingPlace.setText((String) singleDocInfo.get(2));
        et5_UPDATEDOC_Experience.setText((String) singleDocInfo.get(3));
        et6_UPDATEDDOC_fee.setText((String) singleDocInfo.get(4));
        numtxt_UPDATEDOC_MaxPatients.setText((String) singleDocInfo.get(5));
    }

    public void updateDoc(View view){
        DBHelper dbHelper = new DBHelper(this);

        String docname = et2_UPDATEDOC_Name.getText().toString();
        String docSpeciality = et3_UPDATEDOC_Speciality.getText().toString();
        String workplace = et4_UPDATEDOC_WorkingPlace.getText().toString();
        String experience = et5_UPDATEDOC_Experience.getText().toString();



        if(docname.isEmpty()) {
            this.et2_UPDATEDOC_Name.setError("Enter Doctor Name!");
        }else if(this.checkDigit(docname) == false) {
            this.et2_UPDATEDOC_Name.setError("Must contain only characters!");
        }else if(!docname.startsWith("Dr.")){
            this.et2_UPDATEDOC_Name.setError("Error in Pattern of the Name!");
        }else if(docSpeciality.isEmpty()) {
            this.et3_UPDATEDOC_Speciality.setError("Enter Speciality!");
        }else if(this.checkDigit(docSpeciality) == false){
            this.et3_UPDATEDOC_Speciality.setError("'Must contain only Characters!");
        }else if(workplace.isEmpty()){
            this.et4_UPDATEDOC_WorkingPlace.setError("Enter Working Place!");
        }else if(this.checkDigit(workplace) == false){
            this.et4_UPDATEDOC_WorkingPlace.setError("Must Contain Only Characters");
        }else if(experience.isEmpty()){
            this.et5_UPDATEDOC_Experience.setError("Enter Experience!");
        }else if(et6_UPDATEDDOC_fee.getText().toString().isEmpty()){
            this.et6_UPDATEDDOC_fee.setError("Enter Fee!");
        }else if(numtxt_UPDATEDOC_MaxPatients.getText().toString().isEmpty()){
            this.numtxt_UPDATEDOC_MaxPatients.setError("Enter Maximum Patients!");
        }else {
            //converted numerical values.
            double fee = Double.parseDouble(et6_UPDATEDDOC_fee.getText().toString());
            int maxPat = Integer.parseInt(numtxt_UPDATEDOC_MaxPatients.getText().toString());
            int updated = dbHelper.updateDocInfo(docId,docname,docSpeciality,workplace,experience,fee,maxPat);
            if (updated > 0) {
                //clears all the values after inserting.
//            et2_UPDATEDOC_Name.setText("");
//            et3_UPDATEDOC_Speciality.setText("");
//            et4_UPDATEDOC_WorkingPlace.setText("");
//            et5_UPDATEDOC_Experience.setText("");
//            et6_UPDATEDDOC_fee.setText("");
//            numtxt_UPDATEDOC_MaxPatients.setText("");
                Toast.makeText(this, "Updated details successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Search_Doc.class);
                startActivity(intent);
                finish();
            }else
                Toast.makeText(this,"Update failed", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkDigit(String name) {
        //converts the string to an array.
        char[] arr = name.toCharArray();
        //length of the array taken.
        int len = arr.length;
        //iterate through all the characters/elements in the array.
        for(int i =0; i < len; i++){
            //even if a single character is an integer --> returns false.
            if (Character.isDigit(arr[i])== true){
                return false;
            }
        }
        //after iterating through the entire array --> no numerical values at all --> return true.
        return true;
    }

    public void back(View view) {
        Intent intent = new Intent(Available_DOC.this, Doc_Profile.class);
        intent.putExtra("docId",docId);
        startActivity(intent);
        finish();
    }
}