package com.spsh.spshhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.spsh.spshhealthcare.database.DBHelper;
import com.spsh.spshhealthcare.models.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_view_all_reports);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<HashMap<String, String>> reportList = dbHelper.getAllReports();

        ListView lv_reportList = findViewById(R.id.lv_reportList);
        ListAdapter adapter = new SimpleAdapter(ViewAllReports.this, reportList, R.layout.report_row, new String[]{"name", "age", "nic"}, new int[]{R.id.name, R.id.age, R.id.nic});

        lv_reportList.setAdapter(adapter);

    }

}