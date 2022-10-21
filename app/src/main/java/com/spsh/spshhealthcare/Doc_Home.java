package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Doc_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //Battery meter, signal strength ---> hidden.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Potrait mode ---> even if we choose landscape ---> doesn't rotate.(Eg :- Teams chat is portrait, regardless of it being chosen as landscape)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_doc_home);
    }

    public void addDoc(View view){
        Intent intent = new Intent(this,ADD_DOC.class);
        startActivity(intent);
        finish();
    }

    public void availableDoc(View view){
        Intent intent = new Intent(this,Search_Doc.class);
        startActivity(intent);
        finish();
    }

    public void backBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}