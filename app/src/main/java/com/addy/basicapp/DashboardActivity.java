package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    private Button my_profile, create_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Init vars
        my_profile = findViewById(R.id.my_profile);
        create_profile = findViewById(R.id.create_profile);
    }
}