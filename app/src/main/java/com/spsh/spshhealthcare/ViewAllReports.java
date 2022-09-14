package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.spsh.spshhealthcare.database.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllReports extends AppCompatActivity {

    EditText searchBox;
    DBHelper dbHelper;
    ArrayList<HashMap<String, String>> reportList;
    String keyword;
    ListView lv_reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_view_all_reports);

        this.searchBox = findViewById(R.id.editTextTextPersonName);

        this.searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search();
            }
        });

        this.dbHelper = new DBHelper(this);
        this.reportList = this.dbHelper.getAllReports();

        this.lv_reportList = findViewById(R.id.lv_reportList);
        ListAdapter adapter = new SimpleAdapter(ViewAllReports.this, reportList, R.layout.report_row, new String[]{"name", "age", "nic", "_id"}, new int[]{R.id.name, R.id.age, R.id.nic, R.id.tv_idViewAllReports}){
            public View getView (int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                Button btn_view = (Button)v.findViewById(R.id.btn_viewViewAllReports);
                TextView tv_idViewAllReports = (TextView)v.findViewById(R.id.tv_idViewAllReports);
                String reportId = tv_idViewAllReports.getText().toString();

                btn_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewAllReports.this, ViewReport.class);
                        intent.putExtra("reportId", reportId);
                        startActivity(intent);
                    }
                });
                return v;
            }
        };

        lv_reportList.setAdapter(adapter);

    }

    public void onClickBtnBack(View view){
        Intent intent = new Intent(this, LabHome.class);
        startActivity(intent);
    }

    public void onClickBtnSearch(View view){
        this.keyword = this.searchBox.getText().toString();

        if(this.keyword.isEmpty())
            this.reportList = this.dbHelper.getAllReports();
        else
            this.reportList = this.dbHelper.searchReports(keyword);

        ListAdapter adapter = new SimpleAdapter(ViewAllReports.this, reportList, R.layout.report_row, new String[]{"name", "age", "nic", "_id"}, new int[]{R.id.name, R.id.age, R.id.nic, R.id.tv_idViewAllReports}){
            public View getView (int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                Button btn_view = (Button)v.findViewById(R.id.btn_viewViewAllReports);
                TextView tv_idViewAllReports = (TextView)v.findViewById(R.id.tv_idViewAllReports);
                String reportId = tv_idViewAllReports.getText().toString();

                btn_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewAllReports.this, ViewReport.class);
                        intent.putExtra("reportId", reportId);
                        startActivity(intent);
                    }
                });
                return v;
            }
        };

        lv_reportList.setAdapter(adapter);
    }

    public void search(){
        this.keyword = this.searchBox.getText().toString();

        if(this.keyword.isEmpty())
            this.reportList = this.dbHelper.getAllReports();
        else
            this.reportList = this.dbHelper.searchReports(keyword);

        ListAdapter adapter = new SimpleAdapter(ViewAllReports.this, reportList, R.layout.report_row, new String[]{"name", "age", "nic", "_id"}, new int[]{R.id.name, R.id.age, R.id.nic, R.id.tv_idViewAllReports}){
            public View getView (int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                Button btn_view = (Button)v.findViewById(R.id.btn_viewViewAllReports);
                TextView tv_idViewAllReports = (TextView)v.findViewById(R.id.tv_idViewAllReports);
                String reportId = tv_idViewAllReports.getText().toString();

                btn_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewAllReports.this, ViewReport.class);
                        intent.putExtra("reportId", reportId);
                        startActivity(intent);
                    }
                });
                return v;
            }
        };

        lv_reportList.setAdapter(adapter);
    }

}