package database;

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE "+PharmacyMaster.Pharmacy.TABLE_NAME+" ("+
                        PharmacyMaster.Pharmacy._ID+ " INTEGER PRIMARY KEY, " +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH + " INTEGER, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE + " TEXT, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE+ " REAL, "+
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION+ " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);


    }

    public long addInfo(String itemCode, String itemName, String producerName, String usage, Integer strength, String expirationDate, String manufactureDate, Double unitPrice, String description) {
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
            String description = cursor.getString(cursor.getColumnIndexOrThrow(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION));
        }
        cursor.close();
        return info;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Delete
    public void deleteInfo(String itemCode, String itemName, String producerName, String usage, Integer strength, String expirationDate, String manufactureDate, Double unitPrice, String description) {
        //Use Readable to check if data is available --> "getReadableDATABASE"
        SQLiteDatabase db = getReadableDatabase();

        //Where we check if the value is there.
        //Check if the username is equal or similar to the one that we are passing.
        String selection = PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + "LIKE ?";


        //Pass the columns as an array.
        String[] stringArgs = {itemCode};

        //delete the  query
        db.delete((PharmacyMaster.Pharmacy.TABLE_NAME), selection, stringArgs);

    }

    //Search and display all data
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> readAllInfo() {
        SQLiteDatabase sqldb = getReadableDatabase();
        ArrayList<HashMap<String, String>> allPharmacyEquipments = new ArrayList<>();

        //Sql query
        String query = "SELECT " + PharmacyMaster.Pharmacy._ID + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE + " FROM " + PharmacyMaster.Pharmacy.TABLE_NAME;

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
    public ArrayList oneSpecificInfo(int pharmacyEquipID) {
        SQLiteDatabase sqldb = getReadableDatabase();
        ArrayList pharmacyEquipments = new ArrayList<>();

        //Sql query
        String query = "SELECT " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME + ", " + PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE+ " , " +PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH +", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE + ", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE+ ", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE+", "+PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION+" FROM " + PharmacyMaster.Pharmacy.TABLE_NAME +" WHERE "+ PharmacyMaster.Pharmacy._ID +"="+String.valueOf(pharmacyEquipID);

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
            pharmacyEquipments.add(cursor.getString(cursor.getColumnIndex(PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION)));

        }
        return pharmacyEquipments;
    }



}