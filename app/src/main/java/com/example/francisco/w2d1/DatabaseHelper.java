package com.example.francisco.w2d1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by FRANCISCO on 07/08/2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts";

    public static final String TABLE_NAME ="Contacts";
    public static final String CONTACT_NAME ="Name";
    public static final String CONTACT_NUMBER ="Number";
    private static final String TAG = "MyDBTag";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                CONTACT_NAME + " TEXT, " +
                CONTACT_NUMBER + " TEXT " +
                ")";
        Log.d(TAG, "onCreate: "+CREATE_TABLE);

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void saveNewContact(String name, String number){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, name);
        contentValues.put(CONTACT_NUMBER, number);
        database.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "saveNewContact: ");
    }

    public void saveNewContact(MyContact contact){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, contact.getName());
        contentValues.put(CONTACT_NUMBER, contact.getPhone());
        database.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "saveNewContact: "+contact.getName() + " " + contact.getPhone());
    }

    public ArrayList<MyContact> getContacts(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;

        //Cursor cursor = database.rawQuery(query, new String[]{CONTACT_NAME,"francisco"});
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<MyContact> contacts = new ArrayList();
        if(cursor.moveToFirst()){
            do{
                Log.d(TAG, "getContacts: Name:" + cursor.getString(0) + ", Phone: "+ cursor.getString(1));
                contacts.add(new MyContact(cursor.getString(0),cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        else{
            Log.d(TAG, "getContacts: empty");
        }
        return contacts;
    }
}
