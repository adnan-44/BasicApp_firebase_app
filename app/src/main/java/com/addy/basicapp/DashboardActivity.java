package com.addy.basicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    private Button my_profile, create_profile, logout;
    private static final int MAIN_REQUEST_CODE = 1;

    // FirebaseAuth reference
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Init vars
        my_profile = findViewById(R.id.my_profile);
        create_profile = findViewById(R.id.create_profile);
        logout = findViewById(R.id.logout);

        // FirebaseAuth get reference to work with Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Open CreateProfileActivity onclick of create_profile button
        create_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

        my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener on logout button to Logout current User
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get Current user reference, and logout current user with signOut() method and show logout toast
                firebaseAuth.signOut();
                Toast.makeText(DashboardActivity.this, "User Logout Successfully", Toast.LENGTH_SHORT).show();

                // and open MainActivity and close current Activity
                Intent mainIntent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivityForResult(mainIntent, MAIN_REQUEST_CODE);
            }
        });

        // Set RESULT_OK to the requested intent
        setResult(RESULT_OK, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check whether requestCode is same as we requested for MainActivity, if same close DashboardActivity using finish()
        if(requestCode == MAIN_REQUEST_CODE && resultCode == RESULT_OK){
            finish();
        }
    }
}