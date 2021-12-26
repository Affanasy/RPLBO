package com.example.sampri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "appmob" ;

    public static final int DATABASE_VERSION = 1 ;

    //Table
    public static final String TABLE_USERS = "users";
    public static final String TABLE_BARANG = "barang";


    //Table Pengguna
    public static final String KEY_ID = "id" ;
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NOHP = "nohp";
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_NOHP + " TEXT"
            + " ) ";

    public static final String ID_BARANG = "id" ;
    public static final String NAMA_BARANG = "namabarang";
    public static final String HARGA_BELI = "hargabeli";
    public static final String HARGA_JUAL = "hargajual";
    public static final String JUMLAH_BARANG = "jumlahbarang";
    public static final String ENTRY_BY = "entryby";
    public static final String SQL_TABLE_BARANG = " CREATE TABLE " + TABLE_BARANG
            + " ( "
            + ID_BARANG + " INTEGER PRIMARY KEY, "
            + NAMA_BARANG + " TEXT, "
            + HARGA_BELI + " TEXT, "
            + HARGA_JUAL + " TEXT,"
            + JUMLAH_BARANG + " TEXT,"
            + ENTRY_BY + " TEXT"
            + " ) ";

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_BARANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS + TABLE_BARANG);

    }

    public void addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, user.username);

        values.put(KEY_PASSWORD, user.password);

        values.put(KEY_NOHP, user.nohp);

        long todo_id = db.insert(TABLE_USERS, null, values);

    }

    public User Authenticate(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_USER_NAME, KEY_PASSWORD, KEY_NOHP},
                KEY_USER_NAME + "=?", new String[]{user.username}, null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));

            if (user.password.equalsIgnoreCase(user1.password)){
                return user1;
            }
        }

        return null;
    }

    public boolean isNomorExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_NOHP, KEY_PASSWORD},//Selecting columns want to query
                KEY_NOHP + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

}
