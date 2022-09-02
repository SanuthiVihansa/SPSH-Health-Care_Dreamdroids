package com.spsh.spshhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Doc_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);
    }

    public void addDoc(View view){
        Intent intent = new Intent(this,ADD_DOC.class);
        startActivity(intent);
    }

    public void availableDoc(View view){
        Intent intent = new Intent(this,Search_Doc.class);
        startActivity(intent);
    }
}