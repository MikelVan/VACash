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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vacash.android.adapters.PurchaseHistoryAdapter;
import com.vacash.android.models.PurchaseHistory;
import com.vacash.android.models.User;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class ProfilePage extends AppCompatActivity {

    User user;

    ScrollView scrollView;
    EditText usernameField, emailField, amountField;
    TextView errorMsg;
    Button topUpBtn;

    public void showError(TextView errorView){
        errorView.animate().alpha(1.0f).setDuration(250);
    }

    public void hideError(TextView errorView){
        errorView.animate().alpha(0.0f).setDuration(250);
    }

    public boolean isNumeric(String string){
        try{
            Integer integer = Integer.parseInt(string);
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent previousActivity = getIntent();
        user = previousActivity.getParcelableExtra("userData");

        scrollView = findViewById(R.id.scrollableView);
        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailField);
        amountField = findViewById(R.id.amountField);
        errorMsg = findViewById(R.id.errorMessage);
        topUpBtn = findViewById(R.id.topUpBtn);

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());

        hideError(errorMsg);

        topUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = amountField.getText().toString();

                if(amount.isEmpty()){
                    errorMsg.setText("Please input the amount.");
                    showError(errorMsg);
                } else if (!isNumeric(amount)) {
                    errorMsg.setText("Input must be number.");
                    showError(errorMsg);
                } else if (Integer.parseInt(amount) <= 0) {
                    errorMsg.setText("Amount must be more 0.");
                    showError(errorMsg);
                } else {
                    hideError(errorMsg);

                    user.addBalance(Integer.parseInt(amount));
                    amountField.setText("");
                }
            }
        });

        List<PurchaseHistory> purchaseHistories = new ArrayList<>();
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));
        purchaseHistories.add(new PurchaseHistory("Genshin Impact", "60 Genesis Crystal", 1, 16500));

        RecyclerView purchaseHistoryRecycleView = findViewById(R.id.purchaseHistoryRecycleView);
        purchaseHistoryRecycleView.setLayoutManager(new LinearLayoutManager(this));
        purchaseHistoryRecycleView.setAdapter(new PurchaseHistoryAdapter(
                purchaseHistories));
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