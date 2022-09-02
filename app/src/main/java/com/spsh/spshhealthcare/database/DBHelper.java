package com.spsh.spshhealthcare.database;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SPSH.db";
    String docId;
    public DBHelper(@Nullable Context context) {super(context, DATABASE_NAME, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //This onCreate method is implemented if you are creating the table for the first time.
        String SQL_CREATE_ENTRIES = "CREATE TABLE "+ DoctorsMasters.Doctors.TABLE_NAME + " (" +
                DoctorsMasters.Doctors._ID+ " INTEGER PRIMARY KEY," +
                DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+ " TEXT," +
                DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+ " TEXT,"+
                DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE+ " TEXT,"+
                DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE+ " TEXT,"+
                DoctorsMasters.Doctors.COLUMN_NAME_FEE+ " REAL,"+
                DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+ " INTEGER)";

        //inbuilt method called execSQL in SQLiteDatabase.
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Long addDocInfo(String docName, String speciality, String working_place, String experience, double fee, int maxpat){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME, docName);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY, speciality);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE, working_place);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE, experience);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_FEE, fee);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS, maxpat);

        return db.insert(DoctorsMasters.Doctors.TABLE_NAME, null, values);

    }

    //Fetch all Details to the view page
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> readAllDocInfo(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,String>> docList = new ArrayList<>();
        String query = "SELECT "+DoctorsMasters.Doctors._ID+", "+DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+", "+DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+", "+DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+" FROM "+DoctorsMasters.Doctors.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){

            //Gives details of one entry.(1 DOC)
            //First param in the Generics --> holds a reference.
            //second param in the Generics --> holds the values associated.
            HashMap<String, String> hmap = new HashMap<>();
            //we created a hashmap to save one record or row.
            hmap.put(DoctorsMasters.Doctors._ID,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors._ID)));
            hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME)));
            hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY)));
            hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS)));

            docList.add(hmap);
        }
        return docList;
    }

    //Fetch Single doctor details.
    @SuppressLint("Range")
    public ArrayList viewSingleDocInfo(int docId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList singleDocDetails = new ArrayList<>();
        String query = "SELECT " +DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+", "+DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+","+DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE+","+DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE+","+DoctorsMasters.Doctors.COLUMN_NAME_FEE+", "+DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+" FROM "+DoctorsMasters.Doctors.TABLE_NAME+" WHERE "+DoctorsMasters.Doctors._ID+"="+String.valueOf(docId);

        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){


            //Gives details of one entry.(1 DOC)
            //First param in the Generics --> holds a reference.
            //second param in the Generics --> holds the values associated.

            //we created a hashmap to save one record or row.
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME)));
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY)));
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE)));
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE)));
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_FEE)));
            singleDocDetails.add(cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS)));
        }
        return singleDocDetails;
    }

    public void deleteDoc(int docId){
        SQLiteDatabase db = getReadableDatabase();

        //Where we check if the value is there.
        //Check if the username in the DB is equal or similar to the one that we are passing.
        String selection = DoctorsMasters.Doctors._ID + " LIKE ?";

        //Pass the columns as an array.
        String[] stringArgs = {String.valueOf(docId)};

        //delete the  query
        db.delete((DoctorsMasters.Doctors.TABLE_NAME),selection,stringArgs);
    }

    public void updateDocInfo(View view, String doctorName, String Docspeciality, String workplace, String experience, double fee, int maxPat){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //Only password changes here. Username remains as it is.
        //adjust it according to the number of columns you want to update.
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME, doctorName);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY, Docspeciality);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE, workplace);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE, experience);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_FEE, fee);
        values.put(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS, maxPat);


        //qUESTION MARK REPLACED BY ARGUMENTS.
        String selection = DoctorsMasters.Doctors._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(docId)};

        //count shows the affected number of rows.
        //Same username --> all will be updated with the same password--> can count how many such rows were updated.
        int count = db.update(
               DoctorsMasters.Doctors.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        //Display to the user as to how many such records were updated.
        Snackbar snackbar = Snackbar.make(view, count+" rows were affected!",Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();
    }

}



