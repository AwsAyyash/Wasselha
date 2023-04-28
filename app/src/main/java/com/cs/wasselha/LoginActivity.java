package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    TextView signupQuestionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //References
        setupReference();
        signupQuestionBtnSetup();
    }




    //References
    private void setupReference() {
        signupQuestionBtn = findViewById(R.id.signupQuestionBtn);
    }


    private void signupQuestionBtnSetup() {
        signupQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, TypeSignupActivity.class);
                startActivity(intent);
            }
        });
    }
}