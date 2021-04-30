package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    // GUI elements
    private TextView full_name_text, phone_text, email_text;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // Init vars
        full_name_text = findViewById(R.id.full_name_text);
        phone_text = findViewById(R.id.phone_text);
        email_text = findViewById(R.id.email_text);
        profile_image = findViewById(R.id.profile_image);
    }
}