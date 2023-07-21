package com.vacash.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends AppCompatActivity {

    User user;

    EditText usernameField, emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent previousActivity = getIntent();
        user = previousActivity.getParcelableExtra("userData");

        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailField);

        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());

        List<PurchaseHistory> purchaseHistories = new ArrayList<>();
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));

        RecyclerView purchaseHistoryRecycleView = findViewById(R.id.purchaseHistoryRecycleView);
        purchaseHistoryRecycleView.setLayoutManager(new LinearLayoutManager(this));
        purchaseHistoryRecycleView.setAdapter(new PurchaseHistoryAdapter(getApplicationContext(), purchaseHistories));
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
            Intent profileActivity = new Intent(ProfilePage.this, ProfilePage.class);
            profileActivity.putExtra("userData", user);
            startActivity(profileActivity);
        } else if (id == R.id.logout) {
            Intent loginActivity = new Intent(ProfilePage.this, LoginPage.class);
            startActivity(loginActivity);
        }

        return true;
    }
}