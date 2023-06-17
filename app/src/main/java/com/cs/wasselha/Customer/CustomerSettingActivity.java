package com.cs.wasselha.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cs.wasselha.R;

public class CustomerSettingActivity extends AppCompatActivity {

    EditText newCustomerEmail, newCustomerPassword, newCustomerPhoneNumber;
    Button updateCustomerInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_setting);
        getSupportActionBar().hide();

        //Calls
        setupReferences();
    }


    //References
    private void setupReferences()
    {
        newCustomerEmail = findViewById(R.id.newCustomerEmail);
        newCustomerPassword = findViewById(R.id.newCustomerPassword);
        newCustomerPhoneNumber = findViewById(R.id.newCustomerPhoneNumber);
        updateCustomerInfoBtn = findViewById(R.id.updateCustomerInformationBtn);
    }
}