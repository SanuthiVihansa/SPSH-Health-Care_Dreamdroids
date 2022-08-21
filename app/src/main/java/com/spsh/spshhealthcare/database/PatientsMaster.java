package com.spsh.spshhealthcare.database;
import android.provider.BaseColumns;

public final class PatientsMaster {
    private PatientsMaster(){}

    public static class Patients implements BaseColumns{
        public static final String TABLE_NAME = "patients";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_CONTACTNO = "contactno";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
