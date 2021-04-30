package com.addy.basicapp;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreateProfileActivity extends AppCompatActivity {

    // GUI elements
    private EditText full_name_input, phone_input;
    private Button create_profile;
    private ImageView profile_image;

    // Gallery stuff , imageUri to get Uri from gallery intent
    private Uri imageUri;
    private static final int GALLERY_REQUEST_CODE = 1;

    // Firebase connection stuff
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        // Init vars reference
        full_name_input = findViewById(R.id.full_name_input);
        phone_input = findViewById(R.id.phone_input);
        create_profile = findViewById(R.id.create_profile);
        profile_image = findViewById(R.id.profile_image);

        // Firebase stuff reference
        storageReference = FirebaseStorage.getInstance().getReference();    // reference to firebase storage, here we'll store profile picture
        firebaseAuth = FirebaseAuth.getInstance();

        // Get image from gallery onClick of ImageView (setOnClickListener)
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use ACTION_PICK for getting image from default media provider
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);        // Need to implement method for response from this media intent
            }
        });

        // Set OnClickListener to create_profile button and implement code to upload profile picture to Firebase storage
        create_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* I used getUid method, so that profile picture will be stored in different folder for each user.
                Folder name will be user unique id, like UID/profile will be the path for image file */

                storageReference.child(firebaseAuth.getUid() + "/profile").putFile(imageUri)        // Image uploaded by ImageUri
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Show successful toast on task get successfully completed
                        Toast.makeText(CreateProfileActivity.this,"Profile Picture Uploaded",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show failed toast if task get failed
                        Toast.makeText(CreateProfileActivity.this, "Failed to Upload :(", Toast.LENGTH_SHORT).show();
                    }
                });
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