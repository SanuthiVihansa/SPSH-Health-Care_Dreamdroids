package com.spsh.spshhealthcare.database;

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

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SPSH.db";

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
                DoctorsMasters.Doctors.COLUMN_NAME_FEE+ " TEXT,"+
                DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+ " INTEGER)";

        //inbuilt method called execSQL in SQLiteDatabase.
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Long addDocInfo(String docName, String speciality, String working_place, String experience, String fee, String maxpat){
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

    public List readAll(){
        SQLiteDatabase db = getReadableDatabase();

        //ContentValues values = new ContentValues();
        String [] projection = {
                DoctorsMasters.Doctors._ID,
                DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME,
                DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY,
                DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS
        };

        String sortOrder = DoctorsMasters.Doctors._ID + "DESC";
        Cursor cursor = db.query(
                DoctorsMasters.Doctors.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List info = new ArrayList();

        while(cursor.moveToNext()){
            String docName = cursor.getString(cursor.getColumnIndexOrThrow(DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME));
            String docSpeciality = cursor.getString(cursor.getColumnIndexOrThrow(DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY));
            String docMaxPat = cursor.getString(cursor.getColumnIndexOrThrow(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS));

            info.add(docName + ":" + docSpeciality + ":" + docMaxPat);

        }

        cursor.close();

        //just return the cursor --> if there are many columns.
        return info;
    }

}
