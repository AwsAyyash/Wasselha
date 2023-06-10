package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cs.wasselha.Dialogs.ForgetRegistrationDialog;
import com.cs.wasselha.Login.TypeLoginActivity;
import com.cs.wasselha.Signup.TypeSignupActivity;

public class RegistrationActivity extends AppCompatActivity {

    private Button loginBtn, signupBtn;
    ImageView skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        //References
        setupReference();
        loginSetup();
        signupSetup();
        skipSetup();
    }


    //References
    private void setupReference() {
        loginBtn = findViewById(R.id.loginBtnInLoginPage);
        signupBtn = findViewById(R.id.signupBtnInRegisterPage);
        skipBtn = findViewById(R.id.skipBtnInRegisterPage);
    }

    private void loginSetup() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RegistrationActivity.this, TypeLoginActivity.class);
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

    private void skipSetup() {
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openDialog();
            }
        });
    }

    private void openDialog()
    {
        ForgetRegistrationDialog forgetRegistration = new ForgetRegistrationDialog();
        forgetRegistration.show(getSupportFragmentManager(), "Test");
    }


}