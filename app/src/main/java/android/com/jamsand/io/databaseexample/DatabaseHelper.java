package android.com.jamsand.io.databaseexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Table Name
    public static final String  TABLE_NAME = "EMPLOYEES";

    //Table columns
    public static final String  _ID = "id";
    public static final String  NAME = "name";
    public static final String  ADDRESS = "address";

    // Database information
    public static final String DB_NAME = "myEmployee.DB";

    // database version
    public static final int DB_VERSION = 1;

    private SQLiteDatabase database;

    //create table query
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME + " TEXT NOT NULL, "+ ADDRESS +" CHAR(50));";




    public DatabaseHelper(@Nullable Context context){
    super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    onCreate(db);
    }

    public  void open() throws SQLException {
        database = this.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
}
