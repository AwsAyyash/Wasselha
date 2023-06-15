package com.cs.wasselha.CollectionPointProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs.wasselha.R;
import com.cs.wasselha.interfaces.implementation.CollectionPPDA;
import com.cs.wasselha.model.CollectionPointProvider;

import java.io.IOException;

public class CollectionPointProviderSignupActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText editTextFName;
    private EditText editTextLName;
    private EditText editTextNId;
    private EditText editTextPhoneN;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPasswordRe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_point_provider_signup);
        getSupportActionBar().hide();


        setUpViews();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionPointProvider cpp = new CollectionPointProvider(editTextFName.getText().toString(),
                        editTextLName.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextPassword.getText().toString(),editTextPhoneN.getText().toString(),
                        editTextNId.getText().toString(),false);

                if (editTextPassword.getText().toString().equals(editTextPasswordRe.getText().toString())){
                    CollectionPPDA cppDa = new CollectionPPDA();
                    try {
                        cppDa.saveCollectionPP(cpp);
                        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Passwords does not match",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void setUpViews() {

        registerButton = findViewById(R.id.signupCollectionPointProviderBtn);
        editTextFName = findViewById(R.id.firstNameCollectionPointProvider);
        editTextLName = findViewById(R.id.lastNameCollectionPointProvider);
        editTextNId = findViewById(R.id.nationalIDCollectionPointProviderSignup);
        editTextEmail = findViewById(R.id.emailCollectionPointProviderSignup);

        editTextPhoneN = findViewById(R.id.phoneNumberCollectionPointProviderSignup);
        editTextPassword = findViewById(R.id.passwordCollectionPointProviderSignup);
        editTextPasswordRe = findViewById(R.id.repeatPasswordCollectionPointProviderSignup);
    }
}