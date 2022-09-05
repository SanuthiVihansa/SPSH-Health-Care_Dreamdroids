package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.database.DBHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReport extends AppCompatActivity {

    // Variables declaration
    EditText patientName, age, nic, time, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet;
    TextView date, cost;
    RadioButton male, female;
    double initialCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_add_report);

        this.initialCost = 400.00;

        // Initialize variables
        this.patientName = findViewById(R.id.et_labelForPatientNameAddReport);
        this.age = findViewById(R.id.et_labelForPatientAgeAddReport);
        this.male = findViewById(R.id.radioBtn_maleAddReport);
        this.female = findViewById(R.id.radioBtn_femaleAddReport);
        this.nic = findViewById(R.id.et_labelForNicAddReport);
        this.date = findViewById(R.id.tv_dateAddReport);
        this.time = findViewById(R.id.et_timeAddReport);
        this.cost = findViewById(R.id.tv_costAddReport);
        this.hemoglobin = findViewById(R.id.et_labelForHimoglobinAddReport);
        this.wbc = findViewById(R.id.et_labelForWbcAddReport);
        this.neutrophils = findViewById(R.id.et_labelForNeutrophilsAddReport);
        this.lymphocytes = findViewById(R.id.et_labelForLymphocytesAddReport);
        this.eosinophils = findViewById(R.id.et_labelForEosinophilsAddReport);
        this.rbc = findViewById(R.id.et_labelForRbcAddReport);
        this.pcb = findViewById(R.id.et_labelForPcbAddReport);
        this.platelet = findViewById(R.id.et_labelForPlateletAddReport);

        // Get system date and set
        this.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        this.time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateCost();
            }
        });

    }

    public void onClickBtnSubmit(View view){
        String name = this.patientName.getText().toString();
        String age = this.age.getText().toString();
        String nic = this.nic.getText().toString();
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();

        String cost = this.cost.getText().toString();

        String hemoglobin = this.hemoglobin.getText().toString();
        String wbc = this.wbc.getText().toString();
        String neutrophils = this.neutrophils.getText().toString();
        String lymphocytes = this.lymphocytes.getText().toString();
        String eosinophils = this.eosinophils.getText().toString();
        String rbc = this.rbc.getText().toString();
        String pcb = this.pcb.getText().toString();
        String platelet = this.platelet.getText().toString();
        String gender;

        if(male.isChecked()){
            gender = "Male";
        }
        else{
            gender = "Female";
        }

        if(name.isEmpty()) {
            this.patientName.setError("Name is required!");
        }
        else if(this.nameIsCorrect(name) == false){
            this.patientName.setError("You cannot enter numeric characters or symbols!");
        }
        else if(age.isEmpty()){
            this.age.setError("Age is required!");
        }
        else if(Integer.parseInt(age) > 124){
            this.age.setError("Age should be less than 125!");
        }
        else if(Integer.parseInt(age) == 0) {
            this.age.setError("Invalid Value!");
        }
        else if(nic.isEmpty()){
            this.nic.setError("NIC is required!");
        }
        else if(!((nic.length()==10&&nic.endsWith("V")) || nic.length()==12)){
            this.nic.setError("Invalid NIC format!");
        }
        else if(time.isEmpty()){
            this.time.setError("Time is required!");
        }
        else if(this.timeIsCorrect(time) == false){
            this.time.setError("Invalid Time!");
        }
        else if(hemoglobin.isEmpty()){
            this.hemoglobin.setError("This field is required!");
        }
        else if(wbc.isEmpty()){
            this.wbc.setError("This field is required!");
        }
        else if(neutrophils.isEmpty()){
            this.neutrophils.setError("This field is required!");
        }
        else if(Double.parseDouble(neutrophils) > 100){
            this.neutrophils.setError("Invalid Value!");
        }
        else if(lymphocytes.isEmpty()){
            this.lymphocytes.setError("This field is required!");
        }
        else if(Double.parseDouble(lymphocytes) > 100){
            this.lymphocytes.setError("Invalid Value!");
        }
        else if(eosinophils.isEmpty()){
            this.eosinophils.setError("This field is required!");
        }
        else if(Double.parseDouble(eosinophils) > 100){
            this.eosinophils.setError("Invalid Value!");
        }
        else if(rbc.isEmpty()){
            this.rbc.setError("This field is required!");
        }
        else if(pcb.isEmpty()){
            this.pcb.setError("This field is required!");
        }
        else if(Double.parseDouble(pcb) > 100){
            this.pcb.setError("Invalid Value!");
        }
        else if(platelet.isEmpty()){
            this.platelet.setError("This field is required!");
        }
        else{
            DBHelper dbHelper = new DBHelper(this);
            long added = dbHelper.addReport(name, Integer.parseInt(age), gender, nic, date, time, Double.parseDouble(cost), Double.parseDouble(hemoglobin), Integer.parseInt(wbc), Double.parseDouble(neutrophils), Double.parseDouble(lymphocytes), Double.parseDouble(eosinophils), Double.parseDouble(rbc), Double.parseDouble(pcb), Integer.parseInt(platelet));

            if(added > 0){
                Toast.makeText(this, "Report Successfully Added !", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, LabHome.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, LabHome.class);
        startActivity(intent);
    }

    public void calculateCost(){
        DecimalFormat df = new DecimalFormat("0.00");
        int time = 0;
        double cost = initialCost;

        if(this.time.getText().toString().length() > 1 && !this.time.getText().toString().contains(":")) {
            time = Integer.parseInt(this.time.getText().toString().substring(0, 2));

            if (time > 19){
                cost = cost * 2;
            }
            else{
                cost = initialCost;
            }

            this.cost.setText(String.valueOf(df.format(cost)));
        }

        else if(this.time.getText().toString().length() > 0 && !this.time.getText().toString().contains(":")){
            time = Integer.parseInt(this.time.getText().toString().substring(0, 1));

            if(time < 6){
                cost = cost * 2;
            }
            else{
                cost = initialCost;
            }

            this.cost.setText(String.valueOf(df.format(cost)));
        }
    }
    
    public boolean timeIsCorrect(String time){
        if(time.length() == 4){
            if(time.charAt(1) == ':' && Integer.parseInt((Character.toString(time.charAt(0)))) > -1 && Integer.parseInt(time.substring(2)) > -1 && Integer.parseInt(time.substring(2)) < 60)
                return true;
            else
                return false;
        }
        else if(time.length() == 5){
            if(time.charAt(2) == ':' && Integer.parseInt(time.substring(0, 2)) > -1 && Integer.parseInt(time.substring(0, 2)) < 24 && Integer.parseInt(time.substring(3)) > -1 && Integer.parseInt(time.substring(3)) < 60)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public boolean nameIsCorrect(String name) {
        if(name.contains("0") || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9") || name.contains("`") || name.contains("~") || name.contains("!") || name.contains("@") || name.contains("#") || name.contains("$") || name.contains("%") || name.contains("^") || name.contains("&") || name.contains("*") || name.contains("(") || name.contains(")") || name.contains("-") || name.contains("_") || name.contains("=") || name.contains("+") || name.contains("{") || name.contains("[") || name.contains("]") || name.contains("}") || name.contains("\\") || name.contains("|") || name.contains(":") || name.contains(";") || name.contains("\"") || name.contains("'") || name.contains(",") || name.contains("<") || name.contains(">") || name.contains("/") || name.contains("?") || name.contains("™") || name.contains("℉") || name.contains("⟬") || name.contains("²") || name.contains("№") || name.contains("⟧") || name.contains("‱") || name.contains("é") || name.contains("℃") || name.contains("⟦") || name.contains("‰") || name.contains("©") || name.contains("€") || name.contains("¥"))
            return false;
        else
            return true;
    }
}