package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting app name text color
        TextView appName =  findViewById(R.id.appName);
        appName.setText(Html.fromHtml("<b><font color='#8453D5'>V</font>" + "<font color='#A479EB'>A</font>" + "<font color='#FFFFFF'> Cash</font></b>"));

        // validation
        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        Button logInBtn = findViewById(R.id.logInBtn);
        TextView errorMsg = findViewById(R.id.errorMessage);
        logInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(emailField.getText().toString().isEmpty()){
                    errorMsg.setText("* Email cannot be empty.");
                    errorMsg.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}