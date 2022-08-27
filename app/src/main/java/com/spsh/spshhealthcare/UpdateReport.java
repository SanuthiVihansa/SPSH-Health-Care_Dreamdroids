package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

public class UpdateReport extends AppCompatActivity {

    int reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_update_report);

        Intent intent = getIntent();
        this.reportId = Integer.parseInt(intent.getStringExtra("reportId"));

        EditText et_name = findViewById(R.id.et_labelForPatientNameUpdateReport);
        et_name.setText(String.valueOf(this.reportId));

    }
}