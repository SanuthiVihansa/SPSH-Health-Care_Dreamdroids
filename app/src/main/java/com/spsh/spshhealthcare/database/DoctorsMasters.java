package com.spsh.spshhealthcare.database;

import android.provider.BaseColumns;

public class DoctorsMasters {

        private DoctorsMasters(){}

        public static class Doctors implements BaseColumns {
            public static final String TABLE_NAME = "doctors";
            public static final String COLUMN_NAME_DOCTORNAME = "Doctor_Name";
            public static final String COLUMN_NAME_SPECIALITY = "Speciality";
            public static final String COLUMN_NAME_WORKINGPLACE = "Working_Place";
            public static final String COLUMN_NAME_EXPERIENCE = "Experience";
            public static final String COLUMN_NAME_FEE = "Fee";
            public static final String COLUMN_NAME_MAXIMUMPATIENTS = "Maximum_Patients";

        }

}
