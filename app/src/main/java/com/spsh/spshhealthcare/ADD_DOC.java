package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

public class ADD_DOC extends AppCompatActivity {

    EditText et1_ADD_DOC_Name,et2_ADD_DOC_Speciality,et3_ADD_DOC_workingplace,et4_ADD_DOC_Experience,et_Fee_ADD_DOC,numEdt_ADD_DOC_MaxPat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_add_doc);

        //Edit Text variables are assigned relevant IDs.
        et1_ADD_DOC_Name = findViewById(R.id.et1_ADD_DOC_Name);
        et2_ADD_DOC_Speciality = findViewById(R.id.et2_ADD_DOC_Speciality);
        et3_ADD_DOC_workingplace = findViewById(R.id.et3_ADD_DOC_workingplace);
        et4_ADD_DOC_Experience = findViewById(R.id.et4_ADD_DOC_Experience);
        et_Fee_ADD_DOC = findViewById(R.id.et_Fee_ADD_DOC);
        numEdt_ADD_DOC_MaxPat = findViewById(R.id.numEdt_ADD_DOC_MaxPat);
    }

    public void saveDoctor(View view) {

        String doc_Name = et1_ADD_DOC_Name.getText().toString();
        String doc_speciality = et2_ADD_DOC_Speciality.getText().toString();
        String doc_workingplace = et3_ADD_DOC_workingplace.getText().toString();
        String doc_experience = et4_ADD_DOC_Experience.getText().toString();


        DBHelper dbHelper = new DBHelper(this);

        //Customized error message.
        if (doc_Name.isEmpty()){
            this.et1_ADD_DOC_Name.setError("Enter Doctor Name!");
        }else if(!doc_Name.startsWith("Dr.")){
            this.et1_ADD_DOC_Name.setError("Error in Pattern Name!");
        }else if(this.checkDigit(doc_Name) == false) {
            this.et1_ADD_DOC_Name.setError("Must contain only characters!");
        }else if(doc_speciality.isEmpty()){
            this.et2_ADD_DOC_Speciality.setError("Enter Speciality");
        }else if(this.checkDigit(doc_speciality) == false){
            this.et2_ADD_DOC_Speciality.setError("Must contain only Characters!");
        }else if(doc_workingplace.isEmpty()){
            this.et3_ADD_DOC_workingplace.setError("Enter Working Place");
        }else if(this.checkDigit(doc_workingplace) == false){
            this.et3_ADD_DOC_workingplace.setError("Must Contain Only Characters!");
        }else if(doc_experience.isEmpty()){
            this.et4_ADD_DOC_Experience.setError("Enter Doctor Experience");
        }else if(et_Fee_ADD_DOC.getText().toString().isEmpty()){
            this.et_Fee_ADD_DOC.setError("Enter Doctor Fee");
        }else if(numEdt_ADD_DOC_MaxPat.getText().toString().isEmpty()){
            this.numEdt_ADD_DOC_MaxPat.setError("Enter Maximum Patients");
        }else{
            double doc_fee = Double.parseDouble(et_Fee_ADD_DOC.getText().toString());
            int doc_maxpat = Integer.parseInt(numEdt_ADD_DOC_MaxPat.getText().toString());
            long inserted = dbHelper.addDocInfo(doc_Name,doc_speciality,doc_workingplace,doc_experience,doc_fee,doc_maxpat);
            if(inserted>0){
                Toast.makeText(this, "Added Doc Details successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Doc_Home.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Failed To Add Doctor Details", Toast.LENGTH_SHORT).show();
            }
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

    public void back(View view){
        //Can navigate to the Home page once back button is clicked.
        Intent intent = new Intent(this,Doc_Home.class);
        startActivity(intent);
        finish();
    }

}