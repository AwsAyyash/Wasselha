package com.cs.wasselha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        getSupportActionBar().hide();
    }
}