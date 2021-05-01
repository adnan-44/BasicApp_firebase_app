package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessfulSignActivity extends AppCompatActivity {

    private TextView login_here_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_sign);

        // Init vars
        login_here_text = findViewById(R.id.login_here_text);

        // Set onclick listener for login_here textView to open LoginActivity
        login_here_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessfulSignActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}