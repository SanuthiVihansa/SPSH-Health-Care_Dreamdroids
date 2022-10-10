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

        String SQL_CREATE_DOCTOR_TABLE =
                "CREATE TABLE "+ DoctorsMasters.Doctors.TABLE_NAME + " (" +
                        DoctorsMasters.Doctors._ID+ " INTEGER PRIMARY KEY," +
                        DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+ " TEXT," +
                        DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+ " TEXT,"+
                        DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE+ " TEXT,"+
                        DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE+ " TEXT,"+
                        DoctorsMasters.Doctors.COLUMN_NAME_FEE+ " REAL,"+
                        DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+ " INTEGER)";

        String SQL_CREATE_PHARMACY_TABLE =
                "CREATE TABLE "+ PharmacyMaster.Pharmacy.TABLE_NAME+" ("+
                        PharmacyMaster.Pharmacy._ID+ " INTEGER PRIMARY KEY, " +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH + " INTEGER, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE+ " REAL, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY+ " INTEGER, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION+ " TEXT)";

        db.execSQL(SQL_CREATE_PATIENT_TABLE);
        db.execSQL(SQL_CREATE_REPORT_TABLE);
        db.execSQL(SQL_CREATE_APPOINTMENT_TABLE);
        db.execSQL(SQL_CREATE_DOCTOR_TABLE);
        db.execSQL(SQL_CREATE_PHARMACY_TABLE);
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


    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getPatientReports(String nic){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String, String>> reportList = new ArrayList<>();
        String query = "SELECT " + ReportsMaster.Reports._ID + ", " + ReportsMaster.Reports.COLUMN_NAME_NAME + ", " + ReportsMaster.Reports.COLUMN_NAME_AGE + ", " + ReportsMaster.Reports.COLUMN_NAME_NIC + " FROM " + ReportsMaster.Reports.TABLE_NAME + " WHERE " + ReportsMaster.Reports.COLUMN_NAME_NIC + " = " + nic;
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

    public int updateDocInfo(String docId,String doctorName, String Docspeciality, String workplace, String experience, double fee, int maxPat){
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
        String[] selectionArgs = {docId};

        //count shows the affected number of rows.
        //Same username --> all will be updated with the same password--> can count how many such rows were updated.
        int count = db.update(
                DoctorsMasters.Doctors.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
        //Display to the user as to how many such records were updated.
//        Snackbar snackbar = Snackbar.make(view, count+" rows were affected!",Snackbar.LENGTH_LONG);
//        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
//       snackbar.show();
        //Toast.makeText(this, "Rows updated successfully", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> searchDocInfo(String docName){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,String>> docSearchList = new ArrayList<>();
        //String query = "SELECT "+DoctorsMasters.Doctors._ID+", "+DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+", "+DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+", "+DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+" FROM "+DoctorsMasters.Doctors.TABLE_NAME;

        String query = "SELECT "+DoctorsMasters.Doctors._ID +","+DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+", "+DoctorsMasters.Doctors.COLUMN_NAME_SPECIALITY+","+DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE+","+DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE+","+DoctorsMasters.Doctors.COLUMN_NAME_FEE+", "+DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS+" FROM "+DoctorsMasters.Doctors.TABLE_NAME+" WHERE "+DoctorsMasters.Doctors.COLUMN_NAME_DOCTORNAME+"='"+ docName + "'";
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
            // hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_WORKINGPLACE)));
            // hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_EXPERIENCE)));
            // hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_FEE,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_FEE)));
            hmap.put(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS,cursor.getString(cursor.getColumnIndex(DoctorsMasters.Doctors.COLUMN_NAME_MAXIMUMPATIENTS)));

            docSearchList.add(hmap);
        }
        return docSearchList;
    }






























    public long pharmacistAddInfo(String itemCode, String itemName, String producerName, String usage, Integer strength, String expirationDate, String manufactureDate, Double unitPrice,  Integer Quantity, String description) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //create a new map of values, where column names the key
        ContentValues values = new ContentValues();
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE, itemCode);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME, itemName);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME, producerName);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE, usage);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH, strength);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE, expirationDate);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE, manufactureDate);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE, unitPrice);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY, Quantity);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION, description);

        return sqLiteDatabase.insert(PharmacyMaster.Pharmacy.TABLE_NAME, null, values);
    }

    public List readAll() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                PharmacyMaster.Pharmacy._ID,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY,
                PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION
        };

        String sortOrder = PharmacyMaster.Pharmacy._ID + "DESC";
        Cursor cursor = db.query(
                PharmacyMaster.Pharmacy.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List info = new ArrayList();
        while (cursor.moveToNext()) {
            String itemCode = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME));
            String producerName = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME));
            String usage = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE));
            String strength = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH));
            String expirationDate = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE));
            String manufactureDate = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE));
            String unitPrice = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE));
            String quantity = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION));
        }
        cursor.close();
        return info;
    }


    //Search and display all data
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> pharmacistReadAllInfo(String PharmID) {
        SQLiteDatabase sqldb = getReadableDatabase();
        ArrayList<HashMap<String, String>> allPharmacyEquipments = new ArrayList<>();

        //Sql query
        String query = "SELECT " + PharmacyMaster.Pharmacy._ID + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE + " FROM " + PharmacyMaster.Pharmacy.TABLE_NAME+" WHERE "+PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE+ " like '" +PharmID+"____'";

        Cursor cursor = sqldb.rawQuery(query,null);

        while (cursor.moveToNext()) {
            //To get one leave request
            HashMap<String,String> hmap = new HashMap<>();
            hmap.put(PharmacyMaster.Pharmacy._ID,cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy._ID)));
            hmap.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE,cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE)));
            hmap.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME,cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME)));
            hmap.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE,cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE)));

            allPharmacyEquipments.add(hmap);

        }
        return allPharmacyEquipments;
    }

    //PharmasistViewpage one specific item
    @SuppressLint("Range")
    public ArrayList pharmacistOneSpecificInfo(int pharmacyEquipID) {
        SQLiteDatabase sqldb = getReadableDatabase();
        ArrayList pharmacyEquipments = new ArrayList<>();

        //Sql query
        String query = "SELECT " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE+ " , " +PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH +", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE + ", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE+ ", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE+", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY+", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION+" FROM " + PharmacyMaster.Pharmacy.TABLE_NAME +" WHERE "+ PharmacyMaster.Pharmacy._ID +"="+String.valueOf(pharmacyEquipID);

        Cursor cursor = sqldb.rawQuery(query,null);

        while (cursor.moveToNext()) {
            //To get one leave request

            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY)));
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION)));

        }
        return pharmacyEquipments;
    }

    //Update CRUD
    @SuppressLint("Range")
    public void pharmacistUpdate(String itemCode,String itemName,String producerName,String usage,int strength, String expirationDate,String manufactureDate,Double unitPrice,int quantity, String description) {

        SQLiteDatabase db = getWritableDatabase(); //Because we are going to change the inserted values
//        ArrayList pharmacyEquipments = new ArrayList<>(); //Data available in the database are brought through an arrayList
        ContentValues values = new ContentValues(); //used to store a set of values that the ContentResolver can process.


        //set the values that need to be updated using the 'values' object created in the Content values method

        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME, itemName);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME, producerName);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE, usage);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH, strength);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE, expirationDate);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE, manufactureDate);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE, unitPrice);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_QUANTITY, quantity);
        values.put(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION, description);


        String selection = PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + " LIKE ?";
        String[] selectionArgs = {itemCode};

        //count shows the affected number of rows.
        //updating and counting what is updated
        int count = db.update(
                PharmacyMaster.Pharmacy.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );


//        return pharmacyEquipments;
    }

    //DELETE CRUD
    public void pharmacistDeleteInfo(int pharmacyEquipID){

        SQLiteDatabase db = getReadableDatabase();
        String selection = PharmacyMaster.Pharmacy._ID + " LIKE ?";

        //Pass the columns as an array.
        String[] stringArgs = {String.valueOf(pharmacyEquipID)};

        //delete the  query
        db.delete((PharmacyMaster.Pharmacy.TABLE_NAME),selection,stringArgs);

    }
}