package com.addy.basicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MyProfileActivity extends AppCompatActivity {

    // GUI elements
    private TextView full_name_text, phone_text, email_text;
    private ImageView profile_image;

    //Firebase for authentication and retrieve data from FireStore
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // Init vars
        full_name_text = findViewById(R.id.full_name_text);
        phone_text = findViewById(R.id.phone_text);
        email_text = findViewById(R.id.email_text);
        profile_image = findViewById(R.id.profile_image);

        // Firebase reference to work with it
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Get document of Uid name, because we saved like this before
        DocumentReference documentReference = firebaseFirestore.collection("user_info").document(auth.getUid());
        documentReference.addSnapshotListener(MyProfileActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                // Set data to TextView from document snapshot
                full_name_text.setText(value.getString("full_name"));
                phone_text.setText(value.getString("phone"));
                email_text.setText(auth.getCurrentUser().getEmail());       // Get current user email from auth reference
            }
        });
    }
}