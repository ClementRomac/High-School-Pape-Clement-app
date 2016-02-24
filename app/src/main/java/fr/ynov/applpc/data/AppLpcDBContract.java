package fr.ynov.applpc.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by clément on 24/02/2016.
 */
public class AppLpcDBContract {
    public static final String CONTENT_AUTHORITY = "fr.ynov.applpc";
    public static final Uri BASE_CONTENT_URI = Uri.parse("CONTENT://"+CONTENT_AUTHORITY);
    public static final String PATH_PARENRS = "parents";

    public static final class ParentsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PARENRS).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" +
                CONTENT_AUTHORITY + "/" + PATH_PARENRS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" +
                        PATH_PARENRS;
        public static final String TABLE_NAME = "infos_parents";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_CONTENT = "content";
    }
}
