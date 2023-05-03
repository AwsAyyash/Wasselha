package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {

    private Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        //References
        setupReference();
        loginSetup();
        signupSetup();
    }


    //References
    private void setupReference() {
        loginBtn = findViewById(R.id.loginBtnInRegisterPage);
        signupBtn = findViewById(R.id.signupBtnInRegisterPage);
    }

    private void loginSetup() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signupSetup() {
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RegistrationActivity.this, TypeSignupActivity.class);
                startActivity(intent);
            }
        });
    }


}