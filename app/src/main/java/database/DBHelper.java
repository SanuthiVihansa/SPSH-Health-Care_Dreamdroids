package database;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.spsh.spshhealthcare.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper { //DBHelper class includes all the CRUD Operations

    public  static final String DATABASE_NAME = "SPSH.db"; //static name for database

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1); //when changing data base, if you want to keep a backup you can change the version, then you can backup previous version of database
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //this will create a table the first time it is called, if table already exists then nothing will happen //if you have more than 1 table, you can either have many DBHelper classes or you can do the create table part (line 20-24) multiple times and call that in line 26 with different names
        String SQL_CREATE_ENTRIES =
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

        db.execSQL(SQL_CREATE_ENTRIES); //we pass the string from LINE 18 in this
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    //*************************************************************************Create method****************************************************************************
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
