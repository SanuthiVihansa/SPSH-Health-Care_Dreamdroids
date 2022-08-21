package com.spsh.spshhealthcare.database;

import android.provider.BaseColumns;

public final class ReportsMaster {
    private ReportsMaster(){}

    public static class Reports implements BaseColumns{
        public static final String TABLE_NAME = "reports";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_HEMOGLOBIN = "hemoglobin";
        public static final String COLUMN_NAME_WBC = "wbc";
        public static final String COLUMN_NAME_NEUTROPHILS = "neutrophils";
        public static final String COLUMN_NAME_LYMPHOCYTES = "lymphocytes";
        public static final String COLUMN_NAME_EOSINOPHILS = "eosinophils";
        public static final String COLUMN_NAME_RBC = "rbc";
        public static final String COLUMN_NAME_PCB = "pcb";
        public static final String COLUMN_NAME_PLATELET = "platelet";
    }
}
