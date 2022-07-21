package com.main.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterAccActivity extends AppCompatActivity {

    EditText id, name, email, pass1, pass2;
    Button button;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acc);

        id = (EditText) findViewById(R.id.etId);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        pass1 = (EditText) findViewById(R.id.etPass1);
        pass2 = (EditText) findViewById(R.id.etPass2);
        button = (Button) findViewById(R.id.btnRegister);
        db = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentId, nama, emel, pwd1, pwd2;
                boolean chk, ins;
                Intent i;

                studentId = id.getText().toString();
                nama = name.getText().toString();
                emel = email.getText().toString();
                pwd1 = pass1.getText().toString();
                pwd2 = pass2.getText().toString();

                if (nama.equals("") || emel.equals("") || pwd1.equals("") || pwd2.equals(""))
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                else {
                    if (pwd1.equals(pwd2)) {
                        chk = db.chkemail(emel);
                        if (chk) {
                            ins = db.insert(studentId, emel, pwd1, nama);
                            if (ins) {
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                i = new Intent(RegisterAccActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}