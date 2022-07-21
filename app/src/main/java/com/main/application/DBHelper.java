package com.main.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Project.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Select * from user");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String studId, String name, String email, String password){
        long ins;
        SQLiteDatabase db;
        ContentValues values;

        db = this.getWritableDatabase();
        values = new ContentValues();

        values.put("Student_ID", studId);
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);


        ins = db.insert("user",null, values);

        if(ins==-1)
            return false;
        else
            return true;
    }

    public boolean chkemail(String email){
        SQLiteDatabase db;
        Cursor cursor;

        db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public boolean chklogin(String email, String password){
        SQLiteDatabase db;
        Cursor cursor;

        db = this.getReadableDatabase();
        cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()==1)
            return true;
        else
            return false;
    }


}
