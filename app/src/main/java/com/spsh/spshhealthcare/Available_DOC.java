package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

public class Available_DOC extends AppCompatActivity {
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

        et2_UPDATEDOC_Name = findViewById(R.id.et2_UPDATEDOC_Name);
        et3_UPDATEDOC_Speciality = findViewById(R.id.et3_UPDATEDOC_Speciality);
        et4_UPDATEDOC_WorkingPlace = findViewById(R.id.et4_UPDATEDOC_WorkingPlace);
        et5_UPDATEDOC_Experience = findViewById(R.id.et5_UPDATEDOC_Experience);
        et6_UPDATEDDOC_fee = findViewById(R.id.et6_UPDATEDDOC_fee);
        numtxt_UPDATEDOC_MaxPatients = findViewById(R.id.numtxt_UPDATEDOC_MaxPatients);
    }

    public void updateDoc(View view){
        DBHelper dbHelper = new DBHelper(this);

        String docname = et2_UPDATEDOC_Name.getText().toString();
        String docSpeciality = et3_UPDATEDOC_Speciality.getText().toString();
        String workplace = et4_UPDATEDOC_WorkingPlace.getText().toString();
        String experience = et5_UPDATEDOC_Experience.getText().toString();
        double fee = Double.parseDouble(et6_UPDATEDDOC_fee.getText().toString());
        int maxPat = Integer.parseInt(numtxt_UPDATEDOC_MaxPatients.getText().toString());


        if(docname.isEmpty()||docSpeciality.isEmpty()||workplace.isEmpty()||experience.isEmpty()||et6_UPDATEDDOC_fee.getText().toString().isEmpty()||numtxt_UPDATEDOC_MaxPatients.getText().toString().isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.updateDocInfo(view,docname,docSpeciality,workplace,experience,fee,maxPat);
            //clears all the values after inserting.
            et2_UPDATEDOC_Name.setText("");
            et3_UPDATEDOC_Speciality.setText("");
            et4_UPDATEDOC_WorkingPlace.setText("");
            et5_UPDATEDOC_Experience.setText("");
            et6_UPDATEDDOC_fee.setText("");
            numtxt_UPDATEDOC_MaxPatients.setText("");
        }
    }
}