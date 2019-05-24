package com.xheghun.esopos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class UserSheetActivity extends AppCompatActivity {
    MaterialButton loginButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sheet);

        loginButton = findViewById(R.id.esopos_login_btn);
        signUpButton = findViewById(R.id.esopos_sign_up_btn);

        loginButton.setOnClickListener(view -> {
            startActivity(new Intent(UserSheetActivity.this,LoginActivity.class));
            //finish();
        });
        signUpButton.setOnClickListener(view -> {
            startActivity(new Intent(UserSheetActivity.this,SignUpActivity.class));
            //finish();
        });
    }
}