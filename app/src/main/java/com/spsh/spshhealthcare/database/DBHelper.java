package com.spsh.spshhealthcare.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SPSH.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PATIENT_TABLE =
                "CREATE TABLE " + PatientsMaster.Patients.TABLE_NAME + " ("+
                        PatientsMaster.Patients.COLUMN_NAME_NIC + " TEXT PRIMARY KEY," +
                        PatientsMaster.Patients.COLUMN_NAME_NAME + " TEXT," +
                        PatientsMaster.Patients.COLUMN_NAME_CONTACTNO + " TEXT," +
                        PatientsMaster.Patients.COLUMN_NAME_PASSWORD + " TEXT)";

        String SQL_CREATE_REPORT_TABLE =
                "CREATE TABLE " + ReportsMaster.Reports.TABLE_NAME + " (" +
                        ReportsMaster.Reports._ID + " INTEGER PRIMARY KEY, " +
                        ReportsMaster.Reports.COLUMN_NAME_NAME + " TEXT, " +
                        ReportsMaster.Reports.COLUMN_NAME_AGE + " INTEGER, " +
                        ReportsMaster.Reports.COLUMN_NAME_GENDER + " TEXT, " +
                        ReportsMaster.Reports.COLUMN_NAME_NIC + " TEXT, " +
                        ReportsMaster.Reports.COLUMN_NAME_DATE + " TEXT, " +
                        ReportsMaster.Reports.COLUMN_NAME_TIME + " TEXT, " +
                        ReportsMaster.Reports.COLUMN_NAME_COST + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_HEMOGLOBIN + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_WBC + " INTEGER, " +
                        ReportsMaster.Reports.COLUMN_NAME_NEUTROPHILS + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_LYMPHOCYTES + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_EOSINOPHILS + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_RBC + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_PCB + " REAL, " +
                        ReportsMaster.Reports.COLUMN_NAME_PLATELET + " INTEGER)";

        db.execSQL(SQL_CREATE_PATIENT_TABLE);
        db.execSQL(SQL_CREATE_REPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addToPatients(String name, String nic, String contactNo, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PatientsMaster.Patients.COLUMN_NAME_NIC, nic);
        values.put(PatientsMaster.Patients.COLUMN_NAME_NAME, name);
        values.put(PatientsMaster.Patients.COLUMN_NAME_CONTACTNO, contactNo);
        values.put(PatientsMaster.Patients.COLUMN_NAME_PASSWORD, password);

        return db.insert(PatientsMaster.Patients.TABLE_NAME, null, values);
    }

    public List getPatient(String nic){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                PatientsMaster.Patients.COLUMN_NAME_NIC
        };

        String selection = PatientsMaster.Patients.COLUMN_NAME_NIC + " LIKE ?";
        String[] stringArgs = {nic};

        Cursor cursor = db.query(
                PatientsMaster.Patients.TABLE_NAME,
                projection,
                selection,
                stringArgs,
                null,
                null,
                null
        );

        List patients = new ArrayList();

        while(cursor.moveToNext()){
            String pNic = cursor.getString(cursor.getColumnIndexOrThrow(PatientsMaster.Patients.COLUMN_NAME_NIC));
            patients.add(pNic);
        }

        cursor.close();

        return patients;
    }

    @SuppressLint("Range")
    public boolean validateUser(String nic, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + PatientsMaster.Patients.COLUMN_NAME_PASSWORD + " FROM " + PatientsMaster.Patients.TABLE_NAME + " WHERE " + PatientsMaster.Patients.COLUMN_NAME_NIC + "='" + nic + "'";
        Cursor cursor = db.rawQuery(query, null);
        String correctPW = null;

        while (cursor.moveToNext()) {
            correctPW = cursor.getString(cursor.getColumnIndex(PatientsMaster.Patients.COLUMN_NAME_PASSWORD));
        }

        if(password.equals(correctPW))
            return true;
        else
            return false;
    }

    public long addReport(String name, int age, String gender, String nic, String date, String time, double cost, double hemoglobin, int wbc, double neutrophils, double lymphocytes, double eosinophils, double rbc, double pcb, int platelet){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ReportsMaster.Reports.COLUMN_NAME_NAME, name);
        values.put(ReportsMaster.Reports.COLUMN_NAME_AGE, age);
        values.put(ReportsMaster.Reports.COLUMN_NAME_GENDER, gender);
        values.put(ReportsMaster.Reports.COLUMN_NAME_NIC, nic);
        values.put(ReportsMaster.Reports.COLUMN_NAME_DATE, date);
        values.put(ReportsMaster.Reports.COLUMN_NAME_TIME, time);
        values.put(ReportsMaster.Reports.COLUMN_NAME_COST, cost);
        values.put(ReportsMaster.Reports.COLUMN_NAME_HEMOGLOBIN, hemoglobin);
        values.put(ReportsMaster.Reports.COLUMN_NAME_WBC, wbc);
        values.put(ReportsMaster.Reports.COLUMN_NAME_NEUTROPHILS, neutrophils);
        values.put(ReportsMaster.Reports.COLUMN_NAME_LYMPHOCYTES, lymphocytes);
        values.put(ReportsMaster.Reports.COLUMN_NAME_EOSINOPHILS, eosinophils);
        values.put(ReportsMaster.Reports.COLUMN_NAME_RBC, rbc);
        values.put(ReportsMaster.Reports.COLUMN_NAME_PCB, pcb);
        values.put(ReportsMaster.Reports.COLUMN_NAME_PLATELET, platelet);

        return db.insert(ReportsMaster.Reports.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getAllReports(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
        String query = "SELECT " + ReportsMaster.Reports._ID + ", " + ReportsMaster.Reports.COLUMN_NAME_NAME + ", " + ReportsMaster.Reports.COLUMN_NAME_AGE + ", " + ReportsMaster.Reports.COLUMN_NAME_NIC + " FROM " + ReportsMaster.Reports.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            HashMap<String, String> report = new HashMap<>();
            report.put(ReportsMaster.Reports._ID, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports._ID)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_NAME, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NAME)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_AGE, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_AGE)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_NIC, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NIC)));

            reportList.add(report);
        }

        return reportList;
    }

    @SuppressLint("Range")
    public ArrayList getReport(int reportId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList reportDetails = new ArrayList();
        String query = "SELECT " + ReportsMaster.Reports.COLUMN_NAME_NAME + ", " + ReportsMaster.Reports.COLUMN_NAME_AGE + ", " + ReportsMaster.Reports.COLUMN_NAME_GENDER + ", " + ReportsMaster.Reports.COLUMN_NAME_NIC + ", " + ReportsMaster.Reports.COLUMN_NAME_DATE + ", " + ReportsMaster.Reports.COLUMN_NAME_TIME + ", " + ReportsMaster.Reports.COLUMN_NAME_HEMOGLOBIN + ", " + ReportsMaster.Reports.COLUMN_NAME_WBC + ", " + ReportsMaster.Reports.COLUMN_NAME_NEUTROPHILS + ", " + ReportsMaster.Reports.COLUMN_NAME_LYMPHOCYTES + ", " + ReportsMaster.Reports.COLUMN_NAME_EOSINOPHILS + ", " + ReportsMaster.Reports.COLUMN_NAME_RBC + ", " + ReportsMaster.Reports.COLUMN_NAME_PCB + ", " + ReportsMaster.Reports.COLUMN_NAME_PLATELET + ", " + ReportsMaster.Reports.COLUMN_NAME_COST + " FROM " + ReportsMaster.Reports.TABLE_NAME + " WHERE " + ReportsMaster.Reports._ID + "=" + reportId;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NAME)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_AGE)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_GENDER)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NIC)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_DATE)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_TIME)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_HEMOGLOBIN)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_WBC)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NEUTROPHILS)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_LYMPHOCYTES)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_EOSINOPHILS)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_RBC)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_PCB)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_PLATELET)));
            reportDetails.add(cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_COST)));
        }

        return reportDetails;
    }

    public int updateReport(int reportId, String name, int age, String gender, String nic, String date, String time, double cost, double hemoglobin, int wbc, double neutrophils, double lymphocytes, double eosinophils, double rbc, double pcb, int platelet) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ReportsMaster.Reports.COLUMN_NAME_NAME, name);
        values.put(ReportsMaster.Reports.COLUMN_NAME_AGE, age);
        values.put(ReportsMaster.Reports.COLUMN_NAME_GENDER, gender);
        values.put(ReportsMaster.Reports.COLUMN_NAME_NIC, nic);
        values.put(ReportsMaster.Reports.COLUMN_NAME_DATE, date);
        values.put(ReportsMaster.Reports.COLUMN_NAME_TIME, time);
        values.put(ReportsMaster.Reports.COLUMN_NAME_COST, cost);
        values.put(ReportsMaster.Reports.COLUMN_NAME_HEMOGLOBIN, hemoglobin);
        values.put(ReportsMaster.Reports.COLUMN_NAME_WBC, wbc);
        values.put(ReportsMaster.Reports.COLUMN_NAME_NEUTROPHILS, neutrophils);
        values.put(ReportsMaster.Reports.COLUMN_NAME_LYMPHOCYTES, lymphocytes);
        values.put(ReportsMaster.Reports.COLUMN_NAME_EOSINOPHILS, eosinophils);
        values.put(ReportsMaster.Reports.COLUMN_NAME_RBC, rbc);
        values.put(ReportsMaster.Reports.COLUMN_NAME_PCB, pcb);
        values.put(ReportsMaster.Reports.COLUMN_NAME_PLATELET, platelet);

        String selection = ReportsMaster.Reports._ID + " = ?";
        String[] selectionArgs = {String.valueOf(reportId)};

        int response = db.update(
                ReportsMaster.Reports.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        return response;
    }

    public int removeReport(int reportId) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = ReportsMaster.Reports._ID + " LIKE ?";
        String[] stringArgs = {String.valueOf(reportId)};

        return db.delete(ReportsMaster.Reports.TABLE_NAME, selection, stringArgs);
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> searchReports(String keyword){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
        String query = "SELECT " + ReportsMaster.Reports._ID + ", " + ReportsMaster.Reports.COLUMN_NAME_NAME + ", " + ReportsMaster.Reports.COLUMN_NAME_AGE + ", " + ReportsMaster.Reports.COLUMN_NAME_NIC + " FROM " + ReportsMaster.Reports.TABLE_NAME + " WHERE " + ReportsMaster.Reports.COLUMN_NAME_NAME + " LIKE '%" + keyword + "%'";
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            HashMap<String, String> report = new HashMap<>();
            report.put(ReportsMaster.Reports._ID, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports._ID)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_NAME, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NAME)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_AGE, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_AGE)));
            report.put(ReportsMaster.Reports.COLUMN_NAME_NIC, cursor.getString(cursor.getColumnIndex(ReportsMaster.Reports.COLUMN_NAME_NIC)));

            reportList.add(report);
        }

        return reportList;
    }
}
