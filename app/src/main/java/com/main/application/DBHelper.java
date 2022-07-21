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
        super(context, "Project.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Select * from user");
        db.execSQL("Select * from register_course");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String studId, String name, String email, String password){
        long ins1;
        SQLiteDatabase db;

        //insert user
        ContentValues uservalues;

        db = this.getWritableDatabase();
        uservalues = new ContentValues();

        uservalues.put("Student_ID", studId);
        uservalues.put("name", name);
        uservalues.put("email", email);
        uservalues.put("password", password);


        ins1 = db.insert("user",null, uservalues);

        if(ins1==-1)
            return false;
        else
            return true;

    }

    public boolean insert2(String studId, int semester, String course, double grade ){

        long ins2;
        SQLiteDatabase db;

        //insert course
        ContentValues registervalues;

        db = this.getWritableDatabase();
        registervalues = new ContentValues();

        registervalues.put("Student_ID", studId);
        registervalues.put("semester num", semester);
        registervalues.put("course_code", course);
        registervalues.put("password", grade);


        ins2 = db.insert("register_course",null, registervalues);

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

    //sdbsaibviksauhvbiab

}
