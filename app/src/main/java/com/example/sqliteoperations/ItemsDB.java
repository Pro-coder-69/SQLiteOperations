package com.example.sqliteoperations;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import java.util.ArrayList;


public class ItemsDB {
    ItemHelper Item;

    public ItemsDB(Context context)
    {
        Item = new ItemHelper(context);
    }

    public long insertData(String name, String desc, String payment, String pic)
    {
        SQLiteDatabase dbb = Item.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemHelper.NAME, name);
        contentValues.put(ItemHelper.DESC, name);
        contentValues.put(ItemHelper.PAYMENT, payment);
        contentValues.put(ItemHelper.URL, pic);
        long id = dbb.insert(ItemHelper.TABLE_NAME, null , contentValues);
        return id;
    }




    public ArrayList<String[]> getData()
    {
        SQLiteDatabase db = Item.getWritableDatabase();
        String[] columns = {ItemHelper.UID,ItemHelper.NAME,ItemHelper.DESC, ItemHelper.PAYMENT, ItemHelper.URL};
        Cursor cursor =db.query(ItemHelper.TABLE_NAME,columns,null,null,null,null,null);
        ArrayList<String[]> buffer= new ArrayList<String[]>();
        while (cursor.moveToNext())
        {
            String[] entry= new String[4];
            int cid =cursor.getInt(cursor.getColumnIndex(ItemHelper.UID));
            entry[0] = cursor.getString(cursor.getColumnIndex(ItemHelper.NAME));
            entry[1] =cursor.getString(cursor.getColumnIndex(ItemHelper.DESC));
            entry[2] = cursor.getString(cursor.getColumnIndex(ItemHelper.PAYMENT));
            entry[3] =cursor.getString(cursor.getColumnIndex(ItemHelper.URL));
            buffer.add(entry);
        }
        return buffer;
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = Item.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(ItemHelper.TABLE_NAME ,ItemHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName, String newName)
    {
        SQLiteDatabase db = Item.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(ItemHelper.TABLE_NAME,contentValues, ItemHelper.NAME+" = ?",whereArgs );
        return count;
    }

    static class ItemHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "Itemdatabase";    // Database Name
        private static final String TABLE_NAME = "Items";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String DESC= "description";    // Column III
        private static final String PAYMENT= "payment";
        private static final String URL= "url";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ DESC+" VARCHAR(225),"+ PAYMENT+" VARCHAR(225),"+ URL+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        static String current_user_ID;

        public ItemHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}

