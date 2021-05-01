package com.addy.basicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyProfileActivity extends AppCompatActivity {

    // GUI elements
    private TextView full_name_text, phone_text, email_text, create_message_text;
    private ImageView profile_image;

    //Firebase for authentication and retrieve data from FireStore
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // Init vars
        full_name_text = findViewById(R.id.full_name_text);
        phone_text = findViewById(R.id.phone_text);
        email_text = findViewById(R.id.email_text);
        create_message_text = findViewById(R.id.create_message_text);
        profile_image = findViewById(R.id.profile_image);

        // Firebase reference to work with it
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        // Get document of Uid name, because we saved like this before
        DocumentReference documentReference = firebaseFirestore.collection("user_info").document(auth.getUid());
        documentReference.addSnapshotListener(MyProfileActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                // Set data to TextView from document snapshot
                if(value.get("full_name") != null && value.get("phone") != null ) {
                    // Change create_message text visibility to gone, once we have full_name and phone to set
                    create_message_text.setVisibility(View.GONE);
                    full_name_text.setText(value.getString("full_name"));
                    phone_text.setText(value.getString("phone"));
                    email_text.setText(auth.getCurrentUser().getEmail());       // Get current user email from auth reference
                }

                // Get DownloadUrl method to get URL and then Use Glide to download and set that in ImageView automatically
                StorageReference imageRef = storageReference.child(auth.getUid()+"/profile");       // Because we've stored profile image in Uid/profile
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // FirebaseUI dependency using Glide made easier to do automatically
                        Glide.with(MyProfileActivity.this).load(uri).into(profile_image);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show failed toast if something gets wrong
                        Toast.makeText(MyProfileActivity.this, "Failed to download profile",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}