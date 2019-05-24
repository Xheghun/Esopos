package com.xheghun.esopos;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.esopos_username_layout)
    TextInputLayout username_layout;

    @BindView(R.id.esopos_username)
    TextInputEditText username;

    @BindView(R.id.esopos_password_layout)
    TextInputLayout password_layout;

    @BindView(R.id.esopos_password)
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.esopos_login_action)
    public void n() {
        String pw = String.valueOf(password.getText()).trim();
        String u_name = String.valueOf(username.getText()).trim();

        if (TextUtils.isEmpty(u_name)){
            username_layout.setErrorEnabled(true);
            username_layout.setError("username required");
        }
        else if (TextUtils.isEmpty(pw) || pw.length() < 6){
                password_layout.setErrorEnabled(true);
                password_layout.setError("please provide a valid password");
            }
        else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}