package com.example.srinivasan.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SRINIVASAN on 4/17/2017.
 */

public class DatabaseHelperTwo extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="users3.db";
    private static final String TABLE_NAME="photo_table";
    private static final String COLOUMN_DATE="DATE";
    private static final String COLOUMN_TIME ="TIME";
    private static final String COLOUMN_CONTENT="CONTENT";
    private static final String KEY_IMAGE = "image_data";
    private static final String COLOUMMN_LOC = "LOCATION";
    SQLiteDatabase db;
int j=0;
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " +TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "DATE TEXT," + " TIME TEXT," + " CONTENT TEXT," + "LOCATION TEXT,"+ KEY_IMAGE + " BLOB);";
    public DatabaseHelperTwo(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_IMAGE);
    }

    public boolean addEntry( String date,String time,String content,String location, byte[] image) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLOUMN_DATE, date);
        cv.put(COLOUMN_TIME, time);
        cv.put(COLOUMN_CONTENT, content);
        cv.put(COLOUMMN_LOC,location);
        cv.put(KEY_IMAGE, image);


        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            return false;
        }
        else {

            return true;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

   public  ArrayList<Bitmap> searchpass() {
       db = this.getReadableDatabase();
       Cursor cursor= db.rawQuery("select * from " +TABLE_NAME,null);
       Bitmap myBitMap ;

       ArrayList<Bitmap> bitmapArray1 = new ArrayList<Bitmap>();
       if (cursor.moveToFirst()){
           do{
               byte[] image = cursor.getBlob(5);
               BitmapFactory.Options options = new BitmapFactory.Options();
               try {
                   myBitMap = BitmapFactory.decodeByteArray(image, 0, image.length, options);
                   bitmapArray1.add(myBitMap);
               }catch (NullPointerException e){
                   break;
               }
           } while (cursor.moveToNext());
       }
       return bitmapArray1;
       }

       public String[] dat(){
           db = this.getReadableDatabase();
           Cursor cursor= db.rawQuery("select * from " +TABLE_NAME,null);
           String date;
           int i=0;
       String[] a = new String[500];
           if (cursor.moveToFirst()){
               do{
                   date = cursor.getString(1);

                   try {

                          a[i]=date;
                          i++;
                       j=i;
                   }catch (ArrayIndexOutOfBoundsException e){
                       break;
                   }
               } while (cursor.moveToNext());
           }
           return a;
       }
    public String[] tim(){
        db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TABLE_NAME,null);
        String date;
        int i=0;
        String[] a = new String[500];
        if (cursor.moveToFirst()){
            do{
                date = cursor.getString(2);

                try {
                    a[i]=date;
                    i++;
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            } while (cursor.moveToNext());
        }
        return a;
    }

    public String[] com(){
        db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TABLE_NAME,null);
        String date;
        int i=0;
        String[] a = new String[500];
        if (cursor.moveToFirst()){
            do{
                date = cursor.getString(3);

                try {
                    a[i]=date;
                    i++;
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            } while (cursor.moveToNext());
        }
        return a;
    }
    public String[] locat(){
        db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TABLE_NAME,null);
        String date;
        int i=0;
        String[] a = new String[500];
        if (cursor.moveToFirst()){
            do{
                date = cursor.getString(4);

                try {
                    a[i]=date;
                    i++;
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            } while (cursor.moveToNext());
        }
        return a;
    }
    public  int ii(){
        return j;
    }
}


