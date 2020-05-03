package com.danielkarlkvist.umberent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
    }

    public void onClickLogin(View view) {
        String userEmail = emailEditText.getText().toString();
        String userPassword = passwordEditText.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initializeViews(){
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        loginButton = (Button) findViewById(R.id.login_button);
    }


}
