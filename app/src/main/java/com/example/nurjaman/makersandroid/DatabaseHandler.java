package com.example.nurjaman.makersandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nurjaman on 13/12/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME = "MakersAndroid";
    private static final String TABLE_TODO = "Todos";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                +KEY_ID+ " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_TABLE);
    }

    public void deleteTodo(String todo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_NAME + " = ?",
                new String[] { String.valueOf(todo) });
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    void addTodo (String todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, todo);

        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public List<String> getAllTodo(){
        List<String>  toDoList = new ArrayList<String>();
        String selecQuery = "SELECT * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selecQuery, null);

        if (cursor.moveToFirst()){
            do {
                toDoList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return toDoList;
    }
}
