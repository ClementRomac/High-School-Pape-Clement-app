package fr.ynov.applpc.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by clément on 24/02/2016.
 */
public class AppLpcDBContract {

    public static final class ParentsEntry implements BaseColumns {
        public static final String TABLE_NAME = "infos_parents";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
    }

    public static final class StudentsEntry implements BaseColumns {
        public static final String TABLE_NAME = "infos_students";

        public static final String COLUMN_CLASS = "class";
        public static final String COLUMN_MONDAY = "monday";
        public static final String COLUMN_TUESDAY = "tuesday";
        public static final String COLUMN_WEDNESDAY = "wednesday";
        public static final String COLUMN_THURSDAY = "thursday";
        public static final String COLUMN_FRIDAY = "friday";
    }
}
