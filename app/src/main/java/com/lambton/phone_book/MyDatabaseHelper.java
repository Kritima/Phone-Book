package com.lambton.phone_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;


 class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PhoneBook.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "_id";
    private static final String FIRSTNAME = "Firstname";    //Column II
    private static final String LASTNAME= "Lastname";// Column III
    private static final String ADDRESS = "Address";
    private static final String CITY= "City";
    private static final String PROVINCE= "Province";
    private static final String POSTALCODE= "Postalcode";
    private static final String EMAIL= "Email";
    private static final String PHONE= "Phone";
    private static final String AREA= "Area";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRSTNAME+" VARCHAR(255) , " +
                LASTNAME+" VARCHAR(225) , " +
                ADDRESS+" VARCHAR(225) , " +
                CITY+" VARCHAR(225) , " +
                PROVINCE+" VARCHAR(225) , " +
                POSTALCODE+" VARCHAR(225), " +
                EMAIL+" VARCHAR(225) ," +
                 PHONE+" VARCHAR(225),"+
                AREA+" VARCHAR(225));";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addContact(String firstName, String lastName, String address, String city, String province, String postalCode,
                    String email, String phone, String area){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.FIRSTNAME, firstName);
        contentValues.put(MyDatabaseHelper.LASTNAME, lastName);
        contentValues.put(MyDatabaseHelper.ADDRESS, address);
        contentValues.put(MyDatabaseHelper.CITY, city);
        contentValues.put(MyDatabaseHelper.PROVINCE, province);
        contentValues.put(MyDatabaseHelper.POSTALCODE, postalCode);
        contentValues.put(MyDatabaseHelper.EMAIL, email);
        contentValues.put(MyDatabaseHelper.PHONE, phone);
        contentValues.put(MyDatabaseHelper.AREA, area);

        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,String firstName, String lastName, String address, String city, String province, String postalCode,
                    String email, String phone, String area){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.FIRSTNAME, firstName);
        contentValues.put(MyDatabaseHelper.LASTNAME, lastName);
        contentValues.put(MyDatabaseHelper.ADDRESS, address);
        contentValues.put(MyDatabaseHelper.CITY, city);
        contentValues.put(MyDatabaseHelper.PROVINCE, province);
        contentValues.put(MyDatabaseHelper.POSTALCODE, postalCode);
        contentValues.put(MyDatabaseHelper.EMAIL, email);
        contentValues.put(MyDatabaseHelper.PHONE, phone);
        contentValues.put(MyDatabaseHelper.AREA, area);

        long result = db.update(TABLE_NAME, contentValues, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
