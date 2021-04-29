package com.addy.basicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText full_name_input, phone_input;
    private Button create_profile;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        // Init vars reference
        full_name_input = findViewById(R.id.full_name_input);
        phone_input = findViewById(R.id.phone_input);
        create_profile = findViewById(R.id.create_profile);
        profile_image = findViewById(R.id.profile_image);
    }
}