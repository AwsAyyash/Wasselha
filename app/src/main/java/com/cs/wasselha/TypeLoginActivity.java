package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TypeLoginActivity extends AppCompatActivity {

    Button loginAsCustomerBtn, loginAsTransporterBtn, loginAsCollectionPointProviderBtn;
    TextView signupQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_login);
        getSupportActionBar().hide();

        //References
        setupReference();
        loginAsCustomerBtnSetup();
        loginAsTransporterBtnSetup();
        loginAsCollectionPointProviderBtnSetup();
        signupQuestionBtnSetup();

    }



    //--------------Methods---------------------------------------------------------

    //References
    private void setupReference()
    {
        loginAsCustomerBtn = findViewById(R.id.loginAsCustomerBtn);

        loginAsTransporterBtn = findViewById(R.id.loginAsTransporterBtn);
        loginAsCollectionPointProviderBtn = findViewById(R.id.loginAsCollectionPointProviderBtn);
        signupQuestion = findViewById(R.id.signupQuestionBtnInTypeLoginPage);
    }


    private void loginAsCustomerBtnSetup() {
        loginAsCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //save in shared preferences --> type=customer
                Intent intent = new Intent(TypeLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loginAsTransporterBtnSetup() {
        loginAsTransporterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //save in shared preferences --> type=transporter
                Intent intent = new Intent(TypeLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loginAsCollectionPointProviderBtnSetup() {
        loginAsCollectionPointProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //save in shared preferences --> type=collectionpointprovider
                Intent intent = new Intent(TypeLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void signupQuestionBtnSetup()
    {
        signupQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TypeLoginActivity.this, TypeSignupActivity.class);
                startActivity(intent);
            }
        });
    }

}