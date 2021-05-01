package com.addy.basicapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuccessfulSignActivity extends AppCompatActivity {

    private TextView login_here_text;
    private static final int LOGIN_REQUEST_CODE = 1;        // for LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_sign);

        // Init vars
        login_here_text = findViewById(R.id.login_here_text);

        // Set onclick listener for login_here textView to open LoginActivity
        login_here_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessfulSignActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        // Set RESULT_OK and finish() recent Activity
        setResult(RESULT_OK, getIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check whether requestCode is same as we requested for LoginActivity, if same close SuccessfulSignActivity using finish()
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK){
            finish();
        }
    }
}