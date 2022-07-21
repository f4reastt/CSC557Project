package com.main.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    DBHelper db;
    EditText email, pass;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        email = (EditText) findViewById(R.id.etEmail);
        pass = (EditText) findViewById(R.id.etPass);
        button1 = (Button) findViewById(R.id.btnLogin);
        button2 = (Button) findViewById(R.id.btnSignup);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean chk;
        String emel, pwd;
        Intent i;
        switch(v.getId()) {
            case R.id.btnLogin:
                emel = email.getText().toString();
                pwd = pass.getText().toString();

                if (emel.equals("") || pwd.equals(""))
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                else {
                    chk = db.chklogin(emel, pwd);
                    if (chk) {
                        i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "Error: Username or Password", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSignup:
                // Toast.makeText(getApplicationContext(), "Signup", Toast.LENGTH_SHORT).show();
                i = new Intent(LoginActivity.this, RegisterAccActivity.class);
                startActivity(i);
                finish();
        }
    }
}