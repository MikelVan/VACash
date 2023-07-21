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

public class LoginPage extends AppCompatActivity {

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
        logInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if(email.isEmpty()){
                    errorMsg.setText("* Email cannot be empty.");
                    errorMsg.setVisibility(View.VISIBLE);
                } else if (password.isEmpty()) {
                    errorMsg.setText("* Password cannot be empty.");
                    errorMsg.setVisibility(View.VISIBLE);
                } else if (!email.endsWith(".com")) {
                    errorMsg.setText("* Email must contain \".com\".");
                    errorMsg.setVisibility(View.VISIBLE);
                } else if (!email.contains("@")) {
                    errorMsg.setText("* Email must contain \"@\".");
                    errorMsg.setVisibility(View.VISIBLE);
                } else if (email.indexOf("@") != email.lastIndexOf("@")) {
                    errorMsg.setText("* Email must contain only one \"@\".");
                    errorMsg.setVisibility(View.VISIBLE);
                } else if (password.length() < 8) {
                    errorMsg.setText("* Password cannot be less than 8 characters.");
                    errorMsg.setVisibility(View.VISIBLE);
                } else {
                    errorMsg.setVisibility(View.INVISIBLE);

                    Intent homeActivity = new Intent(LoginPage.this, HomePage.class);
                    startActivity(homeActivity);
                    Toast.makeText(LoginPage.this, "Berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}