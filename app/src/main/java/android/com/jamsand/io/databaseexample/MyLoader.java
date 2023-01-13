package android.com.jamsand.io.databaseexample;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

public class MyLoader  extends CursorLoader {
    DatabaseHelper myDatabaseHelper;
    public MyLoader(Context context,DatabaseHelper db) {
        super(context);
        myDatabaseHelper = db;
    }

    @Override
    public Cursor loadInBackground() {
        return myDatabaseHelper.getAllEmployees();
    }
}
