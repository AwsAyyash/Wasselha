package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cs.wasselha.CollectionPointProvider.MainCollectionPointProviderActivity;
import com.cs.wasselha.Customer.MainCustomerActivity;
import com.cs.wasselha.Dialogs.ForgetRegistrationDialog;
import com.cs.wasselha.Login.TypeLoginActivity;
import com.cs.wasselha.Signup.TypeSignupActivity;
import com.cs.wasselha.Transporter.MainTransporterActivity;

public class RegistrationActivity extends AppCompatActivity {
    private static final String ID_KEY = "id";
    private static final String LOGIN_TYPE_KEY = "loginType";
    private static final String PREFERENCES_NAME = "MyPreferences";
    private Button loginBtn, signupBtn;
    ImageView skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
       // SharedPreferences.Editor editor = preferences.edit();
        String loginType = preferences.getString(LOGIN_TYPE_KEY, null);
        String id = preferences.getString(ID_KEY, null);

        if (loginType != null && id != null) {
            if (loginType.equals("customer")) {
                Intent intent = new Intent(RegistrationActivity.this, MainCustomerActivity.class);
                startActivity(intent);
                finish();
            } else if (loginType.equals("transporter")) {
                Intent intent = new Intent(RegistrationActivity.this, MainTransporterActivity.class);
                startActivity(intent);
                finish();
            } else if (loginType.equals("collectionpointprovider")) {
                Intent intent = new Intent(RegistrationActivity.this, MainCollectionPointProviderActivity.class);
                startActivity(intent);
                finish();
            }
        }

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