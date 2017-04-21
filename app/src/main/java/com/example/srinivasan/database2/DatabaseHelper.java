package com.example.srinivasan.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SRINIVASAN on 3/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="users.db";
    private static final String TABLE_NAME="user_table";
    private static final String COLOUMN_ID="ID";
    private static final String COLOUMN_NAME ="NAME";
    private static final String COLOUMN_PASSWORD="PASSWORD";

    SQLiteDatabase db;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, PASSWORD TEXT)");
    }
    public String searchpass(String uname){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select NAME,PASSWORD from " +TABLE_NAME,null);
        String a,b;
        b= "not found";
        if (cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if (a.equals(uname))
                {
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String name,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLOUMN_NAME,name);
        contentValues.put(COLOUMN_PASSWORD,password);
        long result =  db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
