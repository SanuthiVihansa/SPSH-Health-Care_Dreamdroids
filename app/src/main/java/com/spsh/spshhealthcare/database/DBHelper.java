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

        String SQL_CREATE_APPOINTMENT_TABLE =
                "CREATE TABLE " + AppointmentsMaster.Appointments.TABLE_NAME + " (" +
                        AppointmentsMaster.Appointments._ID + " INTEGER PRIMARY KEY," +   //_ID and _COUNT are default methods that are available when we implement basecolumns, they are like the default columns, _COUNT returns the number of rows you have
                        AppointmentsMaster.Appointments.COLUMN_NAME_PNAME + " TEXT,"+
                        AppointmentsMaster.Appointments.COLUMN_NAME_AGE + " INTEGER," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_GENDER + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_CONTACTNO + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_NIC + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_SPECIALIZATION + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_DATE + " TEXT," +
                        AppointmentsMaster.Appointments.COLUMN_NAME_TIME + " TEXT)";

        db.execSQL(SQL_CREATE_PATIENT_TABLE);
        db.execSQL(SQL_CREATE_REPORT_TABLE);
        db.execSQL(SQL_CREATE_APPOINTMENT_TABLE);
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





























    public long addAppointment(String Pname, int age, String gender, String contactNo, String nic, String specialization, String doctorName, String date, String time){ //inside brackets we put elements we are going to pass
        SQLiteDatabase db = getWritableDatabase();   //this create this db as an instance of Writeable Database so we will be able to add information to it

        //ContentValues class is used to create the object that will contain the username and the password, this will act as a row
        ContentValues values = new ContentValues();

        //adding values
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME, Pname); //the key is the Pname
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_AGE, age);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_GENDER, gender);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_CONTACTNO, contactNo);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_NIC, nic);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_SPECIALIZATION, specialization);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME, doctorName);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_DATE, date);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_TIME, time);

        //insert query                      //table name below
        return db.insert(AppointmentsMaster.Appointments.TABLE_NAME, null, values);
    }

    //*************************************************************************Update method****************************************************************************
    public int updateAppointment(String Pname, int age, String gender, String contactNo, String id){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(); //creates an object that looks like a row

        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME, Pname);//in this case we are only changing the password so that is the only thing passed
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_AGE, age);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_GENDER, gender);
        values.put(AppointmentsMaster.Appointments.COLUMN_NAME_CONTACTNO, contactNo);

        String selection = AppointmentsMaster.Appointments._ID + " LIKE ?"; //for the project we can use the id and access an entry to update whatever attributes(such as username and password) //When we pass arguments question mark will be replaced by the arguments
        String[] selectionArgs = {id};

        int count = db.update(
                AppointmentsMaster.Appointments.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    //*************************************************************************Delete method****************************************************************************
    public void deleteAppointment(String appointmentID){
        SQLiteDatabase db =  getReadableDatabase(); //this should be readable database because, we are going to check whether data is available when you are going to execute the query
        String selection = AppointmentsMaster.Appointments._ID + " LIKE ?"; //here we check username whether the username being passed to function(line 92) is same as the selection variable
        String[] stringArgs = {appointmentID};//passing arguments as arrays //you can delete multiple stuff if you add argument as an array

        db.delete(AppointmentsMaster.Appointments.TABLE_NAME,selection,stringArgs);
    }

    //*************************************************************************Read method****************************************************************************
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> readAllAppointments(String nic){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,String>> allAppointments = new ArrayList<>();
        //SQL query
        String query = "SELECT "+AppointmentsMaster.Appointments._ID+", "+AppointmentsMaster.Appointments.COLUMN_NAME_PNAME+", "+AppointmentsMaster.Appointments.COLUMN_NAME_DATE+", "+AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME+" FROM "+AppointmentsMaster.Appointments.TABLE_NAME+" WHERE "+AppointmentsMaster.Appointments.COLUMN_NAME_NIC+"="+nic;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String , String> hmap = new HashMap<>();//this gives 1 appointment
            //1st parameter                                                             //value from cursor
            hmap.put(AppointmentsMaster.Appointments._ID,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments._ID)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_DATE,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DATE)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME)));

            allAppointments.add(hmap);
        }
        return allAppointments;
    }

    //*************************************************************************Read Single method****************************************************************************
    @SuppressLint("Range")
    public ArrayList readAppointmentByID(String id){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList oneAppointment = new ArrayList();

        //SQL Query
        //or can use SELECT *
        String query = "SELECT " +AppointmentsMaster.Appointments.COLUMN_NAME_PNAME+", "+AppointmentsMaster.Appointments.COLUMN_NAME_AGE+", "+AppointmentsMaster.Appointments.COLUMN_NAME_GENDER+", "+AppointmentsMaster.Appointments.COLUMN_NAME_CONTACTNO+", "+AppointmentsMaster.Appointments.COLUMN_NAME_NIC+", "+AppointmentsMaster.Appointments.COLUMN_NAME_SPECIALIZATION+ ", " +AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME+", "+AppointmentsMaster.Appointments.COLUMN_NAME_DATE+", "+AppointmentsMaster.Appointments.COLUMN_NAME_TIME+" FROM "+AppointmentsMaster.Appointments.TABLE_NAME+" WHERE "+AppointmentsMaster.Appointments._ID+"="+Integer.parseInt(id);


        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_AGE)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_GENDER)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_CONTACTNO)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_NIC)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DATE)));
            oneAppointment.add(cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_TIME)));
        }
        return oneAppointment;
    }

    //*************************************************************************Search method****************************************************************************
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> searchAppointmentByName(String Pname){
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        //SQL query
        String query = "SELECT "+AppointmentsMaster.Appointments._ID+", "+AppointmentsMaster.Appointments.COLUMN_NAME_PNAME+", "+AppointmentsMaster.Appointments.COLUMN_NAME_DATE+", "+AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME+" FROM "+AppointmentsMaster.Appointments.TABLE_NAME+" WHERE "+AppointmentsMaster.Appointments.COLUMN_NAME_PNAME+"= '"+Pname + "'";

        Cursor cursor = database.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String , String> hmap = new HashMap<>();//this gives 1 appointment
            //1st parameter                                                             //value from cursor
            hmap.put(AppointmentsMaster.Appointments._ID,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments._ID)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_PNAME)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_DATE,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DATE)));
            hmap.put(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME,cursor.getString(cursor.getColumnIndex(AppointmentsMaster.Appointments.COLUMN_NAME_DOCTORSNAME)));

            list.add(hmap);
        }
        return list;
    }































}
