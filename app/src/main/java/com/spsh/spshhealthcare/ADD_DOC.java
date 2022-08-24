package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

public class ADD_DOC extends AppCompatActivity {

    EditText et1_ADD_DOC_Name,et2_ADD_DOC_Speciality,et3_ADD_DOC_workingplace,et4_ADD_DOC_Experience,et5_ADD_DOC_Fee,numEdt_ADD_DOC_MaxPat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc);
        et1_ADD_DOC_Name = findViewById(R.id.et1_ADD_DOC_Name);
        et2_ADD_DOC_Speciality = findViewById(R.id.et2_ADD_DOC_Speciality);
        et3_ADD_DOC_workingplace = findViewById(R.id.et3_ADD_DOC_workingplace);
        et4_ADD_DOC_Experience = findViewById(R.id.et4_ADD_DOC_Experience);
        et5_ADD_DOC_Fee = findViewById(R.id.et5_ADD_DOC_Fee);
        numEdt_ADD_DOC_MaxPat = findViewById(R.id.numEdt_ADD_DOC_MaxPat);
    }

    public void saveDoctor(View view){
        String doc_Name = et1_ADD_DOC_Name.getText().toString();
        String doc_speciality = et2_ADD_DOC_Speciality.getText().toString();
        String doc_workingplace = et3_ADD_DOC_workingplace.getText().toString();
        String doc_experience = et4_ADD_DOC_Experience.getText().toString();
        String doc_fee = et5_ADD_DOC_Fee.getText().toString();
        String doc_maxpat = numEdt_ADD_DOC_MaxPat.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if(doc_Name.isEmpty()||doc_speciality.isEmpty()||doc_workingplace.isEmpty()||doc_experience.isEmpty()||doc_fee.isEmpty()||doc_maxpat.isEmpty()){
            Toast.makeText(this, "Fill the field",Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dbHelper.addDocInfo(doc_Name,doc_speciality,doc_workingplace,doc_experience,doc_fee,doc_maxpat);
            if(inserted>0){
                Toast.makeText(this, "Added Doc Details successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Failed to add Doc Details", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void back(View view){
        Intent intent = new Intent(this,Doc_Home.class);
        startActivity(intent);
    }



}