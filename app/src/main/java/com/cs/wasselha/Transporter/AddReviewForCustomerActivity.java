package com.cs.wasselha.Transporter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cs.wasselha.R;

public class AddReviewForCustomerActivity extends AppCompatActivity
{
    EditText customerReview, notes;
    Button addReviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review_for_customer);
        getSupportActionBar().hide();

        //calls
        setupRefernces();
    }



    //---------------Methods----------------------------------------------------
    private void setupRefernces()
    {
        customerReview = findViewById(R.id.reviewAboutCustomerEditText);
        notes = findViewById(R.id.noteAboutCustomer);
        addReviewBtn = findViewById(R.id.addReviewAboutCustomerBtn);
    }
}