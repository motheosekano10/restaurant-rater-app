package com.example.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "restaurant.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "restaurants";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_CUISINE = "cuisine";
    private static final String COL_RATING = "rating";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_CUISINE + " TEXT, " +
                COL_RATING + " REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addRestaurant(String name, String cuisine, double rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_CUISINE, cuisine);
        values.put(COL_RATING, rating);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Restaurant(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3)
                ));
            } while (cursor.moveToNext());
        }
        return list;
    }
    
    public boolean deleteRestaurant(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}
