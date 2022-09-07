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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.database.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UpdateReport extends AppCompatActivity {

    EditText name, age, nic, time, hemoglobin, wbc, neutrophils, lymphocytes, eosinophils, rbc, pcb, platelet;
    RadioButton male, female;
    TextView date, cost;
    int reportId;
    String gender;
    DBHelper dbHelper;
    double initialCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_update_report);

        this.initialCost = 400.00;

        Intent intent = getIntent();
        this.reportId = Integer.parseInt(intent.getStringExtra("reportId"));

        this.dbHelper = new DBHelper(this);
        ArrayList reportDetails = dbHelper.getReport(reportId);

        this.name = findViewById(R.id.et_labelForPatientNameUpdateReport);
        this.age = findViewById(R.id.et_labelForPatientAgeUpdateReport);
        this.male = findViewById(R.id.radioBtn_maleUpdateReport);
        this.female = findViewById(R.id.radioBtn_femaleUpdateReport);
        this.nic = findViewById(R.id.et_labelForNicUpdateReport);
        this.date = findViewById(R.id.tv_dateUpdateReport);
        this.cost = findViewById(R.id.tv_costUpdateReport);
        this.time = findViewById(R.id.et_timeUpdateReport);
        this.hemoglobin = findViewById(R.id.et_labelForHimoglobinUpdateReport);
        this.wbc = findViewById(R.id.et_labelForWbcUpdateReport);
        this.neutrophils = findViewById(R.id.et_labelForNeutrophilsUpdateReport);
        this.lymphocytes = findViewById(R.id.et_labelForLymphocytesUpdateReport);
        this.eosinophils = findViewById(R.id.et_labelForEosinophilsUpdateReport);
        this.rbc = findViewById(R.id.et_labelForRbcUpdateReport);
        this.pcb = findViewById(R.id.et_labelForPcbUpdateReport);
        this.platelet = findViewById(R.id.et_labelForPlateletUpdateReport);

        this.name.setText((String)reportDetails.get(0));
        this.age.setText((String)reportDetails.get(1));
        this.gender = (String)reportDetails.get(2);
        this.nic.setText((String)reportDetails.get(3));
        this.date.setText((String)reportDetails.get(4));
        this.time.setText((String)reportDetails.get(5));
        this.hemoglobin.setText((String)reportDetails.get(6));
        this.wbc.setText((String)reportDetails.get(7));
        this.neutrophils.setText((String)reportDetails.get(8));
        this.lymphocytes.setText((String)reportDetails.get(9));
        this.eosinophils.setText((String)reportDetails.get(10));
        this.rbc.setText((String)reportDetails.get(11));
        this.pcb.setText((String)reportDetails.get(12));
        this.platelet.setText((String)reportDetails.get(13));
        this.cost.setText((String)reportDetails.get(14) + ".00");

        if(this.gender.equals("Male")){
            this.male.setChecked(true);
        }
        else{
            this.female.setChecked(true);
        }

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

    public void onClickBtnUpdate(View view){
        String name = this.name.getText().toString();
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
        String pcb = this.rbc.getText().toString();
        String platelet = this.platelet.getText().toString();

        if(this.male.isChecked()){
            this.gender = "Male";
        }
        else if(this.female.isChecked()){
            this.gender = "Female";
        }

        if(name.isEmpty()) {
            this.name.setError("Name is required!");
        }
        else if(this.nameIsCorrect(name) == false){
            this.name.setError("You cannot enter numeric characters or symbols!");
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
        else {
            int response = this.dbHelper.updateReport(this.reportId, name, Integer.parseInt(age), this.gender, nic, date, time, Double.parseDouble(cost), Double.parseDouble(hemoglobin), Integer.parseInt(wbc), Double.parseDouble(neutrophils), Double.parseDouble(lymphocytes), Double.parseDouble(eosinophils), Double.parseDouble(rbc), Double.parseDouble(pcb), Integer.parseInt(platelet));

            if (response > 0) {
                Toast.makeText(this, "Report Details Updated !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ViewAllReports.class);
                startActivity(intent);
            } else {
                Snackbar snackbar = Snackbar.make(view, "Update Failed !", Snackbar.LENGTH_LONG);
                snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                snackbar.show();
            }
        }
    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, ViewReport.class);
        intent.putExtra("reportId", String.valueOf(this.reportId));
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