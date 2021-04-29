package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText email_input, password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init vars
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
    }
}