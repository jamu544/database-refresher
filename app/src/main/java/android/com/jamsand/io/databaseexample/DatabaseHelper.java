package android.com.jamsand.io.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

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

    public void add(String name, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(ADDRESS,address);
        database.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getAllEmployees() {
        String[] projection = {
                _ID, NAME, ADDRESS
        };
        Cursor cursor = database.query(TABLE_NAME, projection,null,null,null,null,null);
    return cursor;
    }

    public int update(long _id,String name, String address){
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);

        int count = database.update(TABLE_NAME, contentValues,_ID+ " = "+ _id, null);

        return count;
    }

    public void delete(long _id){
        database.delete(TABLE_NAME,_ID + " = "+_id, null);

    }




}
