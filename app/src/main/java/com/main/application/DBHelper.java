//sync ikut DB
package com.main.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "ProjectDB.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (Student_ID integer primary key, Fullname text , Email text , Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(int studId, String name, String email, String password){
        long ins1;
        SQLiteDatabase db;

        //insert user
        ContentValues value;

        db = this.getWritableDatabase();
        value = new ContentValues();

        value.put("Student_ID", studId);
        value.put("Fullname", name);
        value.put("Email", email);
        value.put("Password", password);


        ins1 = db.insert("user",null, value);

        if(ins1==-1)
            return false;
        else
            return true;

    }

    public boolean insert2(int studId, int semester, String course, double grade ){

        long ins2;
        SQLiteDatabase db;

        //insert course
        ContentValues x;

        db = this.getWritableDatabase();
        x = new ContentValues();

        x.put("Student_ID", studId);
        x.put("semester num", semester);
        x.put("course_code", course);
        x.put("password", grade);


        ins2 = db.insert("register_course",null, x);

        if(ins2==-1)
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
