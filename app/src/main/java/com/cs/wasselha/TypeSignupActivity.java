package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TypeSignupActivity extends AppCompatActivity {

    private Button customerSignupBtn, transporterSignupBtn, collectionPointSignupBtn;
    private TextView loginQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_signup);
        getSupportActionBar().hide();

        //References
        setupReference();
        signupAsCustomerBtnSetup();
        signupAsTransporterBtnSetup();
        signupAsCollectionPointProviderBtnSetup();
        loginQuestionBtnSetup();

    }



    //References
    private void setupReference() {
        customerSignupBtn = findViewById(R.id.signupAsCustomerBtn);
        transporterSignupBtn = findViewById(R.id.signupAsTransporterBtn);
        collectionPointSignupBtn = findViewById(R.id.signupAsCollectionPointProviderBtn);
        loginQuestionBtn = findViewById(R.id.loginQuestionBtn);
    }

    private void signupAsCustomerBtnSetup() {
        customerSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(TypeSignupActivity.this, CustomerSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signupAsTransporterBtnSetup() {
        transporterSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(TypeSignupActivity.this, "Not available now!", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(TypeSignupActivity.this, TransporterSignupActivity.class);
               // startActivity(intent);
            }
        });
    }

    private void signupAsCollectionPointProviderBtnSetup() {
        collectionPointSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(TypeSignupActivity.this, "Not available now!", Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(TypeSignupActivity.this, collectionPointProviderSignupActivity.class);
                //startActivity(intent);
            }
        });
    }


    private void loginQuestionBtnSetup() {
        loginQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(TypeSignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}