package com.main.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCourseActivity extends AppCompatActivity {

    EditText id, semester, subject, grade;
    Button button1,button2;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        id = (EditText) findViewById(R.id.etId);
        semester = (EditText) findViewById(R.id.etSemester);
        subject = (EditText) findViewById(R.id.etSubject);
        grade = (EditText) findViewById(R.id.etGrade);
        button1 = (Button) findViewById(R.id.btnRegister);
        button2 = (Button) findViewById(R.id.btnUpdate);
        db = new DBHelper(this);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String studentId, sub;
                int sem;
                double gd;
                boolean ins2;

                studentId = id.getText().toString();
                sem = Integer.parseInt(semester.getText().toString());
                sub = subject.getText().toString();
                gd = Double.parseDouble(grade.getText().toString());


                if (studentId.equals("") || sub.equals(""))
                    if(sem == 0 && gd == 0)
                        Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                else {
                    ins2 = db.insert2(studentId, sem, sub, gd);
                    if (ins2) {
                        Toast.makeText(getApplicationContext(), "Course registered successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}