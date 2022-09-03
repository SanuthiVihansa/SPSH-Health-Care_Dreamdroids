package database;

import android.provider.BaseColumns;

public final class AppointmentsMaster {
    private AppointmentsMaster(){ }

    public class Appointments implements BaseColumns{
        public static final String TABLE_NAME = "appointments";
        public static final String COLUMN_NAME_PNAME = "Pname";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_CONTACTNO = "contactNo";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_SPECIALIZATION = "specialization";
        public static final String COLUMN_NAME_DOCTORSNAME = "doctorName";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
    }
}
