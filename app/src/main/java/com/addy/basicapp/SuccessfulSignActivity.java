package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulSignActivity extends AppCompatActivity {
    
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_sign);

        // Init vars
        login_button = findViewById(R.id.login);

        // Set onclick listener for button to open LoginActivity
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessfulSignActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}