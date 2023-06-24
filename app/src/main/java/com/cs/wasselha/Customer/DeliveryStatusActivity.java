package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cs.wasselha.R;

public class DeliveryStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_status);
        getSupportActionBar().hide();
    }
}