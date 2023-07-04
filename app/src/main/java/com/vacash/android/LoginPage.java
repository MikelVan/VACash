package com.vacash.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
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
    }
}