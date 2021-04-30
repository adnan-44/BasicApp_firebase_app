package com.addy.basicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateProfileActivity extends AppCompatActivity {

    // GUI elements
    private EditText full_name_input, phone_input;
    private Button create_profile;
    private ImageView profile_image;

    // Gallery stuff , imageUri to get Uri from gallery intent
    private Uri imageUri;
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        // Init vars reference
        full_name_input = findViewById(R.id.full_name_input);
        phone_input = findViewById(R.id.phone_input);
        create_profile = findViewById(R.id.create_profile);
        profile_image = findViewById(R.id.profile_image);

        // Get image from gallery onClick of ImageView (setOnClickListener)
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use ACTION_PICK for getting image from default media provider
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);        // Need to implement method for response from this media intent
            }
        });
    }

    @Override       // Implement in response for intent result
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check whether it's response for gallery intent or not, only do rest stuff if result is successful
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            // Get Image URI, and set that URI on profile_image ImageView
            imageUri = data.getData();
            profile_image.setImageURI(imageUri);
        }
    }
}