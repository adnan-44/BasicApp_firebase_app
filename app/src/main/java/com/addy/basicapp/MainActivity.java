package com.addy.basicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email_input, password_input;
    private Button sign_up;
    private TextView login_text;
    private static final int SS_REQUEST_CODE = 1;       // for SuccessfulActivity

    // Firebase Authentication instance
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init vars
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        sign_up = findViewById(R.id.sign_up);
        login_text = findViewById(R.id.login_text);

        // get a FirebaseAuth instance to work with firebase
        auth = FirebaseAuth.getInstance();

        // Set sign-up button to create a user for firebase
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // create a new user with email and password
                    auth.createUserWithEmailAndPassword(email_input.getText().toString(), password_input.getText().toString())
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Check whether task is successful or not
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(MainActivity.this, SuccessfulSignActivity.class);
                                        startActivityForResult(intent, SS_REQUEST_CODE);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Failed to sign-up", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }catch(IllegalArgumentException e){
                    Toast.makeText(MainActivity.this, "Input fields can't be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Open LoginActivity on click of Login text using intents
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Set RESULT_OK to the requested intent
        setResult(RESULT_OK, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check whether requestCode is same as we requested for SuccessfulActivity, if same close MainActivity using finish()
        if(requestCode == SS_REQUEST_CODE && resultCode == RESULT_OK){
            finish();
        }
    }
}