package com.addy.basicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email_input, password_input;
    private Button login;
    private static final int DASHBOARD_REQUEST_CODE = 1;        // for DashboardActivity

    // Firebase Authentication instance
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init vars
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        login = findViewById(R.id.login);

        // get a FirebaseAuth instance to work with firebase
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // Login user with email and password
                    auth.signInWithEmailAndPassword(email_input.getText().toString(), password_input.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                        startActivityForResult(intent, DASHBOARD_REQUEST_CODE);
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }catch(IllegalArgumentException e){
                    Toast.makeText(LoginActivity.this, "Input fields can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set RESULT_OK and finish() recent Activity
        setResult(RESULT_OK, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check whether requestCode is same as we requested for DashboardActivity, if same close LoginActivity using finish()
        if(requestCode == DASHBOARD_REQUEST_CODE && resultCode == RESULT_OK){
            finish();
        }
    }
}