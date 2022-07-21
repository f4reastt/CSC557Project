package com.main.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NavBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navhome:
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                return true;

            case R.id.navregcourse:
                Intent intent2 = new Intent(getApplicationContext(), RegisterCourseActivity.class);
                startActivity(intent2);
                return true;

            case R.id.navresult:
                Intent intent3 = new Intent(getApplicationContext(), Registered_CourseActivity.class);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}