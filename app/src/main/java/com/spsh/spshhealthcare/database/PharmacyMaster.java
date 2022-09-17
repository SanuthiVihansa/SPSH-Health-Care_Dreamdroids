package com.spsh.spshhealthcare.database;

import android.provider.BaseColumns;

public final class PharmacyMaster{
    private PharmacyMaster (){} //Constructor

    public static class Pharmacy implements BaseColumns{
        public static final String TABLE_NAME="pharmacy"; //Table name
        public static final String COLOUMN_NAME_ITEMCODE = "itemcode";
        public static final String COLOUMN_NAME_ITEMNAME = "item_name";
        public static final String COLOUMN_NAME_PRODUCERNAME = "producer_name";
        public static final String COLOUMN_NAME_USAGE = "usage";
        public static final String COLOUMN_NAME_STRENGTH = "strength";
        public static final String COLOUMN_NAME_EXPIRATIONDATE = "expiration_date";
        public static final String COLOUMN_NAME_MANUFACTURINGDATE = "manufacturing_date";
        public static final String COLOUMN_NAME_UNITPRICE = "unit_price";
        public static final String COLOUMN_NAME_QUANTITY = "Quantity";
        public static final String COLOUMN_NAME_DESCRIPTION = "description";


    }
}

