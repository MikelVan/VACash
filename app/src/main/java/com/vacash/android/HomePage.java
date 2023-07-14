package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Integer id = item.getItemId();

        if(id == R.id.profile){
            Intent profileActivity = new Intent(HomePage.this, ProfilePage.class);
            startActivity(profileActivity);
        } else if (id == R.id.logout) {
            Intent loginActivity = new Intent(HomePage.this, LoginPage.class);
            startActivity(loginActivity);
        }

        return true;
    }
}