package com.danielkarlkvist.umberent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initiateObjects();
    }

    public void onClickLogin(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        Toast.makeText(this, "Under utveckling!", Toast.LENGTH_LONG).show();
    }

    private void initiateObjects(){
        email = (EditText) findViewById(R.id.email_EditText);
        password = (EditText) findViewById(R.id.password_EditText);
        loginBtn = (Button) findViewById(R.id.login_button);
    }

}
