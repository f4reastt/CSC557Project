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
        //db.execSQL("CREATE TABLE user (Student_ID integer primary key, Fullname text , Email text , Password text)");
        //db.execSQL("CREATE TABLE [registered course] (numRegister INTEGER primary key, course_code INTEGER NOT NULL," +
                //"student_id INTEGER NOT NULL, grade_id INTEGER NOT NULL, [semester num] INTEGER NOT NULL," +
                //"cgpa REAL NOT NULL, gpa REAL NOT NULL," +
                //"FOREIGN KEY(course_code) REFERENCES [course] (Course_code)," +
                //"FOREIGN KEY(grade_id) REFERENCES [grade] (grade_id)," +
                //"FOREIGN KEY(student_id) REFERENCES [user] (Student_ID))");
        //db.execSQL("CREATE TABLE course (Course_code TEXT primary key NOT NULL UNIQUE, Course_name TEXT NOT NULL, " +
                        //"Course_credit INTEGER NOT NULL, Semester_course INTEGER NOT NULL)");
        //db.execSQL("CREATE TABLE grade (grade_id integer primary key NOT NULL, grade text NOT NULL, grade_point REAL NOT NULL)");
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

    public boolean insert3(String courseId, String corName, int cred, int crHour ){

        long ins2;
        SQLiteDatabase db;

        //insert course
        ContentValues x;

        db = this.getWritableDatabase();
        x = new ContentValues();

        x.put("Student_ID", courseId);
        x.put("semester num", corName);
        x.put("course_code", cred);
        x.put("password", crHour);


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
