package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vacash.android.models.PurchaseHistory;
import com.vacash.android.models.User;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {

    public void showError(TextView errorView){
        errorView.animate().alpha(1.0f).setDuration(250);
    }

    public void hideError(TextView errorView){
        errorView.animate().alpha(0.0f).setDuration(250);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // setting app name text color
        TextView appName =  findViewById(R.id.appName);
        appName.setText(Html.fromHtml("<font color='#8453D5'>V</font>" + "<font color='#A479EB'>A</font>" + "<font color='#FFFFFF'> Cash</font>"));

        // validation
        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        Button logInBtn = findViewById(R.id.logInBtn);
        TextView errorMsg = findViewById(R.id.errorMessage);

        hideError(errorMsg);

        logInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if(email.isEmpty()){
                    errorMsg.setText("* Email cannot be empty.");
                    showError(errorMsg);
                } else if (password.isEmpty()) {
                    errorMsg.setText("* Password cannot be empty.");
                    showError(errorMsg);
                } else if (!email.endsWith(".com")) {
                    errorMsg.setText("* Email must contain \".com\".");
                    showError(errorMsg);
                } else if (!email.contains("@")) {
                    errorMsg.setText("* Email must contain \"@\".");
                    showError(errorMsg);
                } else if (email.indexOf("@") != email.lastIndexOf("@")) {
                    errorMsg.setText("* Email must contain only one \"@\".");
                    showError(errorMsg);
                } else if (password.length() < 8) {
                    errorMsg.setText("* Password cannot be less than 8 characters.");
                    showError(errorMsg);
                } else {
                    Intent homeActivity = new Intent(LoginPage.this, HomePage.class);

                    String username = email.substring(0, email.indexOf('@'));
                    Integer balance = 0;
                    ArrayList<PurchaseHistory> purchaseHistories = new ArrayList<>();

                    User user = new User(username, email, balance, purchaseHistories);
                    homeActivity.putExtra("userData", user);

                    startActivity(homeActivity);
                }
            }
        });
    }
}