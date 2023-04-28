package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CustomerSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        getSupportActionBar().hide();
    }


//    //References
//    private void setupReference() {
//        loginBtn = findViewById(R.id.loginBtnInRegisterPage);
//        signupBtn = findViewById(R.id.signupBtnInRegisterPage);
//    }
}