package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="SPSH.db";
    public dbHelper (Context context) {super(context, DATABASE_NAME,null,1);}


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + PharmacyMaster.Pharmacy.TABLE_NAME + " (" +
                        PharmacyMaster.Pharmacy._ID + " INTEGER PRIMARY KEY, " +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMCODE + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_ITEMNAME + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_PRODUCERNAME + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_USAGE + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_STRENGTH + "INTEGER," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_EXPIRATIONDATE + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_MANUFACTURINGDATE + "TEXT," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_UNITPRICE + "REAL," +
                        PharmacyMaster.Pharmacy.COLOUMN_NAME_DESCRIPTION + "TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
