package database;

import android.provider.BaseColumns;

public final class PharmacyMaster{
    private PharmacyMaster (){} //Constructor

    public static class Pharmacy implements BaseColumns{
        public static final String TABLE_NAME="Pharmacy"; //Table name
        public static final String COLOUMN_NAME_ITEMCODE = "Item Code";
        public static final String COLOUMN_NAME_ITEMNAME = "Item Name";
        public static final String COLOUMN_NAME_PRODUCERNAME = "Producer Name";
        public static final String COLOUMN_NAME_USAGE = "Usage";
        public static final String COLOUMN_NAME_STRENGTH = "Strength";
        public static final String COLOUMN_NAME_EXPIRATIONDATE = "Expiration date";
        public static final String COLOUMN_NAME_MANUFACTURINGDATE = "Manufacturing date";
        public static final String COLOUMN_NAME_UNITPRICE = "Unit price";
        public static final String COLOUMN_NAME_DESCRIPTION = "Description";


    }
}

